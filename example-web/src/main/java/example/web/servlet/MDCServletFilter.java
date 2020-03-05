package example.web.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

@WebFilter(filterName = "fuga-filter", urlPatterns = "/*")
public class MDCServletFilter implements Filter {

	// HTTPヘッダ名

	/** HTTPヘッダ名: Host */
	private static final String HTTP_ITEM_HOST = "Host";

	
	// MDC項目名

	/** MDC項目名: host */
	private static final String MDC_ITEM_HOST = "host";

	/** MDC項目名: session_id */
	private static final String MDC_ITEM_SESSION_ID = "session_id";

	
	/** MDC出力値が空の場合の値 */
	private static final String EMPTY_VALUE = "-";

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.trace("init(): invoked!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		logger.trace("doFilter(): invoked!");

		// MDC関連の初期化
		initMdc(request);

		// 本来の業務処理
		logger.trace("doFilterでテストロギング");
		
		try {
			chain.doFilter(request, response);
		}finally {
			// 他のスレッドでの誤使用防止
			resetMdc();
		}
	}

	@Override
	public void destroy() {
		logger.trace("destroy(): invoked!");
	}

	private void initMdc(ServletRequest request) {
		try {

			resetMdc(); // 念のため初期化

			// HTTP要求以外の場合は何もしない
			if (!(request instanceof HttpServletRequest)) {
				return;
			}
			HttpServletRequest httpRequest = (HttpServletRequest) request;

			// 非セッション関連項目の挿入 --------------------------------------

			// Refererヘッダ
			String xffVal = httpRequest.getHeader(HTTP_ITEM_HOST);
			MDC.put(MDC_ITEM_HOST, xffVal != null ? xffVal : EMPTY_VALUE);

			// セッション関連項目の挿入 ----------------------------------------
			HttpSession ss = httpRequest.getSession(false); // 存在しない場合は作成しない

			// セッションID
			MDC.put(MDC_ITEM_SESSION_ID, ss != null ? ss.getId() : EMPTY_VALUE);

		} catch (Exception e) {
			// 上記のようなロギングは付加的な処理であり、例外が発生しても、
			// 本来の業務処理を止めてはならず、続行すべきである。
			// 念のため、例外の事実を検知できるよう最低限ロギングする。
			logger.warn("unexpected exception: {}", e.getMessage());
		}
	}
	
	private void resetMdc() {
		MDC.clear();
	}

}
