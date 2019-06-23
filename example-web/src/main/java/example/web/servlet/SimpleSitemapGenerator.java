package example.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;
import com.redfin.sitemapgenerator.WebSitemapUrl.Options;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * シンプルサイトマップジェネレータ
 * <p />
 * 小規模なサイト用のGoogleサイトマップを生成するサーブレットです。
 * <ul>
 * <li>クラスパス上にあるプロパティファイルから設定をロードします。</li>
 * <li>ページ数が50,000以上のサイトには対応しません。</li>
 * <li>サイトマップファイルを作成するために一時ディレクトリを使用します。</li>
 * </ul>
 * 
 * @author nextdoorwith
 *
 */
public class SimpleSitemapGenerator extends HttpServlet {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/**
	 * プロパティファイル名
	 * <p />
	 * クラスパスからロードするためファイル名のみを指定する。
	 */
	private static final String PROP_FILENAME = "sitemap-config.properties";

	/** プロパティ名: ベースURL */
	private static final String PROPKEY_BASE_URL = "sitemap.baseurl";

	/** プロパティ名: 走査ディレクトリ */
	private static final String PROPKEY_SCAN_PATH = "sitemap.scandir";

	/** プロパティ名(接頭語): 対象パターン */
	private static final String PROPKEY_PREFIX_INCLUDE_PATTERN = "sitemap.include.pattern";

	/** プロパティ名(接頭語): 除外パターン */
	private static final String PROPKEY_PREFIX_EXCLUDE_PATTERN = "sitemap.exclude.pattern";

	/** URL区切り文字 */
	private static final char URL_SEP = '/';

	/**
	 * サイトマップファイル名
	 * <p />
	 * sitemapgen4jが生成する既定のサイトマップファイル名<br />
	 * URL数が50,000件を超えるとsitemap1.xml等のようにファイル名が変わる場合がある。
	 */
	private static final String SITEMAP_FILENAME = "sitemap.xml";

	/** サイトマップファイル応答時のContent-Type */
	private static final String CONTENT_TYPE = "application/xml";

	/** ロガー */
	private Logger logger = LoggerFactory.getLogger(getClass());

	/** コンテキストパス */
	private Path contextPath;

	/** ベースURL */
	private URL baseUrl;

	/** 走査パス */
	private Path scanPath;

	/** 対象パターン */
	private Pattern[] inRegexes;

	/** 除外パターン */
	private Pattern[] exRegexes;

	@Override
	public void init() throws ServletException {

		logger.trace("init(): start");

		// コンテキストの物理パス
		Path contextPath = Paths.get(getServletContext().getRealPath(File.separator));

		// プロパティのロード
		Properties prop = new Properties();
		ClassLoader cl = getClass().getClassLoader();
		try (InputStream is = cl.getResourceAsStream(PROP_FILENAME)) {
			prop.load(is);
		} catch (IOException e) {
			throw new ServletException("missing or invalid properties: " + PROP_FILENAME);
		}

		// ベースURLの取得
		URL baseUrl;
		try {
			String baseUrlStr = prop.getProperty(PROPKEY_BASE_URL, "");
			baseUrl = new URL(stripRight(baseUrlStr, URL_SEP));
		} catch (MalformedURLException e) {
			throw new ServletException("invalid baseurl", e);
		}

		// 走査ディレクトリの取得
		String scanPathStr = prop.getProperty(PROPKEY_SCAN_PATH);
		if (scanPathStr == null) {
			throw new ServletException("missing parameter: " + PROPKEY_SCAN_PATH);
		}
		Path scanPath = Paths.get(contextPath.toString(), scanPathStr);

		// 対象パターンの取得
		String[] inRegexStrs = getMultiProperties(prop, PROPKEY_PREFIX_INCLUDE_PATTERN);
		Pattern[] inRegexes = createRegexes(inRegexStrs);

		// 除外パターンの取得
		String[] exRegexStrs = getMultiProperties(prop, PROPKEY_PREFIX_EXCLUDE_PATTERN);
		Pattern[] exRegexes = createRegexes(exRegexStrs);

		this.contextPath = contextPath;
		this.baseUrl = baseUrl;
		this.scanPath = scanPath;
		this.inRegexes = inRegexes;
		this.exRegexes = exRegexes;

		logger.debug("contextPath: {}", this.contextPath);
		logger.debug("baseUrl: {}", this.baseUrl);
		logger.debug("scanPath: {}", this.scanPath);
		logger.debug("includeRegex: {}", Arrays.toString(this.inRegexes));
		logger.debug("excludeRegex: {}", Arrays.toString(this.exRegexes));

		logger.trace("init(): end");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		logger.trace("doGet(): start");

		// sitemapgen4jのAPIはメモリ上にサイトマップを作成できないので
		// 一時ディレクトリにサイトマップファイルを作成し、その内容をレスポンスに設定する。
		Path tmpDirPath = null;
		try {

			// サイトマップファイルを格納するためのディレクトリを作成
			tmpDirPath = Files.createTempDirectory("sitemap");
			logger.debug("tmpdir: {}", tmpDirPath);

			// URL一覧の作成
			List<String> fileList = getFileList(this.contextPath, this.scanPath, this.inRegexes, this.exRegexes);
			List<WebSitemapUrl> urlList = createUrlList(this.baseUrl, fileList);

			// サイトマップファイルの作成
			WebSitemapGenerator wsg = new WebSitemapGenerator(this.baseUrl, tmpDirPath.toFile());
			for (WebSitemapUrl url : urlList) {
				logger.info("sitemap url: {}", url.getUrl());
				wsg.addUrl(url);
			}
			wsg.write();

			// サイトマップが作成されているかを検証
			Path sitemapFilePath = tmpDirPath.resolve(SITEMAP_FILENAME);
			if (!Files.isReadable(sitemapFilePath)) {
				throw new ServletException("no sitemap file found: " + sitemapFilePath);
			}

			// サイトマップファイルの内容を返却
			resp.setContentType(CONTENT_TYPE);
			Files.copy(sitemapFilePath, resp.getOutputStream());
			resp.flushBuffer();

			logger.info("responsed: sitemap url: {}", urlList.size());

		} finally {
			cleanup(tmpDirPath);
		}

		logger.trace("doGet(): end");
	}

	/**
	 * 指定フォルダ配下のファイル一覧を取得する。
	 * 
	 * @param rootPath  ルートパス
	 * @param scanPath  走査フォルダパス
	 * @param inRegexes 出力対象パターン(正規表現)
	 * @param exRegexes 出力除外パターン(正規表現)
	 * @return ファイル一覧
	 * @throws IOException ファイル一覧取得失敗
	 */
	protected List<String> getFileList(Path rootPath, Path scanPath, Pattern[] inRegexes, Pattern[] exRegexes)
			throws IOException
	{

		// 引数がnullの場合は補完
		inRegexes = (inRegexes != null ? inRegexes : new Pattern[0]);
		exRegexes = (exRegexes != null ? exRegexes : new Pattern[0]);

		// 走査パス配下のファイル一覧を取得し、マッチング判定してファイル一覧を作成
		List<String> fileList = new ArrayList<>();
		try (Stream<Path> walk = Files.walk(scanPath).filter(Files::isRegularFile)) {

			Iterator<Path> it = walk.iterator();
			while (it.hasNext()) {
				Path path = it.next();

				// ルートを基準としたパスに変換する。
				// 併せてセパレータをURLの"/"に置き換え
				// 例) C:\path\to\scan, C:\path\to\scan\path\to\page.xhtml -> /path/to/page.xhtml
				Path relPath = rootPath.relativize(path);
				String relPathStr = URL_SEP + relPath.toString().replace(File.separatorChar, URL_SEP);

				// 除外条件にマッチすれば除外
				if (regexesMatched(exRegexes, relPathStr)) {
					logger.trace("exclude path: {}", relPathStr);
					continue;
				}

				// 対象条件にマッチしない場合は除外
				if (!regexesMatched(inRegexes, relPathStr)) {
					logger.trace("no include path: {}", relPathStr);
					continue;
				}

				fileList.add(relPathStr);
			}
		}
		return fileList;
	}

	/**
	 * ファイル一覧からURL一覧を生成する。
	 * 
	 * @param baseUrl  ベースURL
	 * @param fileList ファイル一覧(コンテキストパス基準のパスを前提)
	 * @return URL一覧
	 * @throws IOException URL生成失敗
	 */
	protected List<WebSitemapUrl> createUrlList(URL baseUrl, List<String> fileList) throws IOException {

		// URL一覧を生成
		List<WebSitemapUrl> urlList = new ArrayList<>();
		Date now = new Date();
		for (String filePathStr : fileList) {

			String url = baseUrl + filePathStr;

			// エントリとなるWebSitemapUrlを生成
			Options opts = new Options(url);
			opts = opts.changeFreq(ChangeFreq.HOURLY);
			opts = opts.lastMod(now);
			opts = opts.priority(1.0);
			WebSitemapUrl wsu = opts.build();

			urlList.add(wsu);
			logger.debug("add url: {}", url);
		}

		return urlList;
	}

	/**
	 * 複数のプロパティを配列にマップする。
	 * 
	 * @param prop   プロパティオブジェクト
	 * @param prefix プロパティ名接頭語
	 * @return プロパティ値
	 */
	protected String[] getMultiProperties(Properties prop, String prefix) {
		List<String> list = new ArrayList<>();
		for (String key : prop.stringPropertyNames()) {
			if (!key.startsWith(prefix)) {
				continue;
			}
			String val = prop.getProperty(key);
			if (val == null || val.trim().length() <= 0) {
				continue;
			}
			list.add(val.trim());
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * 文字列配列から正規表現パターン配列を生成する。
	 * 
	 * @param patternStrs 正規表現の文字列配列
	 * @return 正規表現オブジェクト配列
	 */
	protected Pattern[] createRegexes(String[] patternStrs) {
		Pattern[] patterns = new Pattern[patternStrs.length];
		for (int i = 0; i < patterns.length; i++) {
			patterns[i] = Pattern.compile(patternStrs[i]);
		}
		return patterns;
	}

	/**
	 * 正規表現配列のいずれかにマッチするかを判定する。
	 * 
	 * @param patterns 正規表現配列
	 * @param value    検査対象値
	 * @return いずれかにマッチした場合はtrue、それ以外はfalse
	 */
	protected boolean regexesMatched(Pattern[] patterns, String value) {
		for (Pattern p : patterns) {
			if (p.matcher(value).find()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * クリーンアップする。
	 * 
	 * @param tmpPath 一時ディレクトリパス
	 */
	public void cleanup(Path tmpPath) {

		if (tmpPath == null) {
			return;
		}

		try (Stream<Path> stream = Files.list(tmpPath)) {

			// ファイルを削除(フォルダは考慮外)
			Iterator<Path> it = stream.iterator();
			while (it.hasNext()) {
				Path p = it.next();
				Files.delete(p);
				logger.trace("delete file: {}", p);
			}

			// ファイルが一つでも残っていると削除不可
			Files.deleteIfExists(tmpPath);
			logger.trace("delete dir : {}", tmpPath);

		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
	}

	/**
	 * 文字列右側の文字を除外する。
	 * 
	 * @param str 文字列
	 * @param ch  除外文字
	 * @return 文字列
	 */
	protected String stripRight(String str, char ch) {
		if (str == null) {
			return null;
		}
		char[] chars = str.toCharArray();
		int idx;
		for (idx = str.length() - 1; 0 <= idx; idx--) {
			if (chars[idx] != ch) {
				break;
			}
		}
		return str.substring(0, idx + 1);
	}

}
