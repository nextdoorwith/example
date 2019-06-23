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
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
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

	/** プロパティ名: 除外パターン */
	private static final String PROPKEY_EXCLUDE = "sitemap.exclude";

	/** プロパティ名: スキャン */
	private static final String PROPKEY_SCAN = "^sitemap.scan([0-9]+).(.*)$";

	/** プロパティ名(接頭語): パス */
	private static final String PROPKEY_SUFFIX_PATH = "path";

	/** プロパティ名(接頭語): パターン */
	private static final String PROPKEY_SUFFIX_PATTERN = "pattern";

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

	/** サイトマップスキャンリスト */
	private List<SitemapScan> scanList;

	/** 除外パターン */
	private List<Pattern> exPatternList;

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

		// スキャンエントリの取得
		List<SitemapScan> scanList = parseScanEntries(prop);

		// 除外パターンの取得
		List<Pattern> exPatternList = parseExcludeEntries(prop);

		this.contextPath = contextPath;
		this.baseUrl = baseUrl;
		this.scanList = scanList;
		this.exPatternList = exPatternList;

		logger.debug("contextPath: {}", this.contextPath);
		logger.debug("baseUrl: {}", this.baseUrl);
		logger.debug("scanList: {}", this.scanList);
		logger.debug("exPatternList: {}", this.exPatternList);

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

			// ファイル一覧からURLを生成してサイトマップを作成
			WebSitemapGenerator wsg = new WebSitemapGenerator(this.baseUrl, tmpDirPath.toFile());
			for (SitemapScan scan : this.scanList) {
				loadUrl(wsg, scan);
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

		} finally {
			cleanup(tmpDirPath);
		}

		logger.trace("doGet(): end");
	}

	/**
	 * サイトマップスキャン情報に基づいてURLをロードする。
	 * 
	 * @param wsg  サイトマップジェネレータ
	 * @param scan サイトマップスキャン
	 * @throws IOException ファイル一覧取得失敗
	 */
	protected void loadUrl(WebSitemapGenerator wsg, SitemapScan scan) throws IOException {
		if (scan == null || scan.scanPath == null) {
			return;
		}

		// 走査パス配下のファイル一覧を取得し、マッチング判定してファイル一覧を作成
		Path scanPath = Paths.get(this.contextPath.toString(), scan.scanPath.toString());
		try (Stream<Path> walk = Files.walk(scanPath).filter(Files::isRegularFile)) {

			Iterator<Path> it = walk.iterator();
			while (it.hasNext()) {
				Path path = it.next();

				// ルートを基準としたパスに変換する。
				// 併せてセパレータをURLの"/"に置き換え
				// 例) C:\path\to\scan, C:\path\to\scan\path\to\page.xhtml -> /path/to/page.xhtml
				Path relPath = this.contextPath.relativize(path);
				String relPathStr = URL_SEP + relPath.toString().replace(File.separatorChar, URL_SEP);

				// 除外条件にマッチすれば除外
				if (regexesMatched(this.exPatternList, relPathStr)) {
					logger.trace("exclude path: {}", relPathStr);
					continue;
				}

				// 対象条件にマッチしない場合は除外
				if (!regexesMatched(scan.patternList, relPathStr)) {
					logger.trace("no include path: {}", relPathStr);
					continue;
				}

				// エントリとなるWebSitemapUrlを生成
				String url = this.baseUrl + relPathStr;
				Options opts = new Options(url);
				opts = opts.changeFreq(ChangeFreq.HOURLY);
				opts = opts.lastMod(new Date(Files.getLastModifiedTime(path).toMillis()));
				opts = opts.priority(1.0);
				WebSitemapUrl wsu = opts.build();

				wsg.addUrl(wsu);
				logger.info("add: {}", wsu.getUrl());
			}
		}
	}

	/**
	 * 正規表現配列のいずれかにマッチするかを判定する。
	 * 
	 * @param patterns 正規表現リスト
	 * @param value    検査対象値
	 * @return いずれかにマッチした場合はtrue、それ以外はfalse
	 */
	protected boolean regexesMatched(List<Pattern> patternList, String value) {
		for (Pattern p : patternList) {
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
	 * プロパティからスキャン情報を取得する。
	 * 
	 * @param prop プロパティ
	 * @return スキャン情報リスト
	 */
	private List<SitemapScan> parseScanEntries(Properties prop) {

		// sitemap.scan(グループ1).(グループ2)
		Pattern pattern = Pattern.compile(PROPKEY_SCAN);

		// キー名(昇順)でスキャン情報を作成するためにソート
		List<String> keys = new ArrayList<>(prop.stringPropertyNames());
		Collections.sort(keys);

		Map<String, SitemapScan> map = new LinkedHashMap<>();
		for (String key : keys) {

			Matcher matcher = pattern.matcher(key);
			if (!matcher.find()) {
				continue;
			}

			// グループ1の値をキーとしてスキャン情報を格納
			String id = matcher.group(1);
			SitemapScan scan;
			if (map.containsKey(id)) {
				scan = map.get(id);
			} else {
				scan = new SitemapScan();
				map.put(id, scan);
			}

			// グループ2の値に基づいてスキャン情報に値を設定
			String arg = matcher.group(2);
			String val = prop.getProperty(key);
			if (arg.equals(PROPKEY_SUFFIX_PATH)) {
				scan.scanPath = Paths.get(val);
			} else if (arg.startsWith(PROPKEY_SUFFIX_PATTERN)) {
				scan.patternList.add(Pattern.compile(val));
			} else {
				continue;
			}
		}
		return new ArrayList<>(map.values());
	}

	/**
	 * プロパティから除外パターンを取得する。
	 * 
	 * @param prop プロパティオブジェクト
	 * @return 正規表現パターン一覧
	 */
	protected List<Pattern> parseExcludeEntries(Properties prop) {
		List<Pattern> list = new ArrayList<>();
		for (String key : prop.stringPropertyNames()) {
			if (!key.startsWith(PROPKEY_EXCLUDE)) {
				continue;
			}
			String val = prop.getProperty(key);
			if (val == null || val.trim().length() <= 0) {
				continue;
			}
			list.add(Pattern.compile(val));
		}
		return list;
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

	/**
	 * スキャン情報
	 * 
	 * @author nextdoorwith
	 *
	 */
	private static class SitemapScan {

		/** スキャンパス */
		private Path scanPath;

		/** パターン一覧 */
		private List<Pattern> patternList = new ArrayList<>();

		@Override
		public String toString() {
			return "SitemapScan [scanPath=" + scanPath + ", patternList=" + patternList + "]";
		}

	}

}
