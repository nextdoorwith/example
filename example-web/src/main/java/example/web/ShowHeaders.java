package example.web;

import java.util.Enumeration;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 * サンプルページ
 */
@Named
@RequestScoped
public class ShowHeaders {

	/** 表示値 */
	private String dispValue;

	@Inject
	private HttpServletRequest request;

	/** 初期化 */
	public void init() {

		StringBuilder sb = new StringBuilder();
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String key = headerNames.nextElement();
			String val = request.getHeader(key);
			sb.append(key + ": " + val + "\n");
		}
		this.dispValue = sb.toString();
	}

	/**
	 * 表示値を取得する。
	 * 
	 * @return 表示値
	 */
	public String getDispValue() {
		return this.dispValue;
	}

}
