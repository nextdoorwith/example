package example.zip4j;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.BeforeClass;
import org.junit.Test;

// java.util.zipにいくつか同名クラスがあることに注意
import net.lingala.zip4j.io.inputstream.ZipInputStream;
import net.lingala.zip4j.io.outputstream.ZipOutputStream;
import net.lingala.zip4j.model.LocalFileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;

public class Zip4jExampleTest {

	private static final int BUF_SIZE = 4 * 1024 * 10;

	private static final String IN_TOP_DIR = ".\\indata\\"; // 入力フォルダ

	private static final String OUT_TOP_DIR = ".\\outdata\\"; // 出力先フォルダ(削除されます)

	private static final char[] PASSWORD = "password".toCharArray();

	private byte[] buf = new byte[BUF_SIZE];

	// 本来であればtry-with-resources等でストリームを閉じる必要があります！

	@BeforeClass
	public static void prepare() throws IOException {
		// 出力先ディレクトリを一旦削除
		Path out = Paths.get(OUT_TOP_DIR).toAbsolutePath().normalize();
		if (Files.exists(out)) {
			System.out.println("deleting: " + out);
			Files.walk(out).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
		}
		Files.createDirectories(out);
	}

	@Test
	public void createZip() throws IOException {
		String[] files = { IN_TOP_DIR + "testdir" };
		FileOutputStream fos = new FileOutputStream(OUT_TOP_DIR + "testdir_zip4j.zip");
		ZipOutputStream zos = new ZipOutputStream(fos);
		ZipParameters baseParams = new ZipParameters();
		baseParams.setEncryptFiles(false);
		create(files, baseParams, zos);
		zos.close();
	}

	@Test
	public void createPasswordZip_ZipCrypto() throws IOException {
		String[] files = { IN_TOP_DIR + "testdir" };
		FileOutputStream fos = new FileOutputStream(OUT_TOP_DIR + "testdir_zip4j_zipcrypto.zip");
		ZipOutputStream zos = new ZipOutputStream(fos, PASSWORD);
		ZipParameters baseParams = new ZipParameters();
		baseParams.setEncryptFiles(true);
		baseParams.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);
		create(files, baseParams, zos);
		zos.close();
	}

	@Test
	public void createPasswordZip_AES256() throws IOException {
		String[] files = { IN_TOP_DIR + "testdir" };
		FileOutputStream fos = new FileOutputStream(OUT_TOP_DIR + "testdir_zip4j_aes256.zip");
		ZipOutputStream zos = new ZipOutputStream(fos, PASSWORD);
		ZipParameters baseParams = new ZipParameters();
		baseParams.setEncryptFiles(true);
		baseParams.setEncryptionMethod(EncryptionMethod.AES);
		baseParams.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256); // 128/192/256
		create(files, baseParams, zos);
		zos.close();
	}

	@Test
	public void extractZip() throws IOException {
		FileInputStream fis = new FileInputStream(IN_TOP_DIR + "testdirjp_zip4j.zip");
		ZipInputStream zis = new ZipInputStream(fis);
		ZipParameters baseParams = new ZipParameters();
		baseParams.setEncryptFiles(false);
		extract(zis, OUT_TOP_DIR + "extracted_zip");
		zis.close();
	}

	@Test
	public void extractPasswordZip_ZipCrypto() throws IOException {
		FileInputStream fis = new FileInputStream(IN_TOP_DIR + "testdir_zip4j_zipcrypto.zip");
		ZipInputStream zis = new ZipInputStream(fis, PASSWORD);
		extract(zis, OUT_TOP_DIR + "extracted_zipcrypto");
		zis.close();
	}

	@Test
	public void extractPasswordZip_AES256() throws IOException {
		FileInputStream fis = new FileInputStream(IN_TOP_DIR + "testdir_zip4j_aes256.zip");
		ZipInputStream zis = new ZipInputStream(fis, PASSWORD);
		extract(zis, OUT_TOP_DIR + "extracted_aes256");
		zis.close();
	}

	public void create(String[] srcFiles, ZipParameters baseParams, ZipOutputStream zos) throws IOException {

		// 圧縮対象の元となるソースパスリスト
		List<Path> srcPathList = new ArrayList<>();
		try (Stream<String> st = Arrays.stream(srcFiles)) {
			srcPathList = st.map(Paths::get).collect(Collectors.toList());
		}

		debug("圧縮開始");

		// ソースパス上のファイルを再帰的に圧縮
		for (Path srcPath : srcPathList) {

			// ソースファイルのパスを正規化済の絶対パスに変換
			srcPath = srcPath.toAbsolutePath().normalize();
			Path parentPath = srcPath.getParent();

			// 圧縮対象ファイルリスト
			List<Path> targetPathList = new ArrayList<>();

			// パスがディレクトリの場合、ディレクトリ配下のファイルを再帰的に
			// 圧縮対象ファイルリストへ追加
			if (Files.isDirectory(srcPath)) {
				// 解凍時にディレクトリは自動で作成されるのでディレクトリは除外
				try (Stream<Path> st = Files.walk(srcPath)) {
					st.filter(Files::isRegularFile).forEach(targetPathList::add);
				}
			} else {
				targetPathList.add(srcPath);
			}

			// 圧縮対象ファイルリストのファイルをZIPストリームに出力
			for (Path targetPath : targetPathList) {

				// ソースファイルのパスから、圧縮対象ファイルの相対パスを生成
				Path relPath = parentPath.relativize(targetPath);

				// 対象ファイル用のパラメータ定義
				ZipParameters targetParams = new ZipParameters(baseParams);
				targetParams.setFileNameInZip(relPath.toString());

				// ZIPストリームにファイルを出力
				zos.putNextEntry(targetParams);
				try (InputStream is = new FileInputStream(targetPath.toFile())) {
					int readSize;
					while ((readSize = is.read(buf)) > 0) {
						zos.write(buf, 0, readSize);
					}
				}
				zos.closeEntry();

				debug("圧縮済: " + relPath + " <- " + targetPath);
			}
		}
		// close()が確実に実行される実装にしないと不正なzipファイルになる場合がある
	}

	public void extract(ZipInputStream zis, String extractTopDir) throws IOException {

		byte[] buf = new byte[BUF_SIZE];

		// 展開先ディレクトリパス(存在しない場合は新規作成)
		Path extractTopPath = Paths.get(extractTopDir).toAbsolutePath().normalize();
		if (!Files.exists(extractTopPath)) {
			Files.createDirectories(extractTopPath);
		}

		debug("解凍開始");

		// ZIPストリームのエントリ毎にファイルを作成
		LocalFileHeader fh;
		while ((fh = zis.getNextEntry()) != null) {

			// 解凍先パスの決定
			String filename = fh.getFileName();
			File extractFile = new File(extractTopPath + File.separator + filename);

			// ディレクトリを解凍
			if (fh.isDirectory()) {
				extractFile.mkdirs(); // 2層以上作成する場合もあるのでmkdirsを使用
				continue;
			}

			// ファイルを解凍
			// ※zipファイルによってはディレクトリが含まれない場合があるので事前作成
			File parentFile = extractFile.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}
			try (OutputStream os = new BufferedOutputStream(new FileOutputStream(extractFile));) {
				int readSize;
				while ((readSize = zis.read(buf)) > 0) {
					os.write(buf, 0, readSize);
				}
			}

			debug("解凍済: " + filename + " -> " + extractFile);
		}
	}

	// debug purpose only
	private static void debug(String msg) {
		Runtime r = Runtime.getRuntime();
		long base = 1024 * 1024;
		long total = r.totalMemory() / base;
		long free = r.freeMemory() / base;
		long used = total - free;
		String ts = new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());
		System.out.printf("%s:(%3d/%3d[MB]): %s\n", ts, used, total, msg);
	}

}
