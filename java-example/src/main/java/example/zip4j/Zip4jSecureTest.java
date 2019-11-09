package example.zip4j;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import java.util.Comparator;

import org.junit.BeforeClass;
import org.junit.Test;

//java.util.zipにいくつか同名クラスがあることに注意
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.inputstream.ZipInputStream;
import net.lingala.zip4j.model.LocalFileHeader;

public class Zip4jSecureTest {

	private static final int BUF_SIZE = 4 * 1024 * 10;

	private static final String IN_TOP_DIR = ".\\indata\\"; // 入力フォルダ

	private static final String OUT_TOP_DIR = ".\\outdata\\"; // 出力先フォルダ(削除されます)

	private static final long MAX_TOTAL_SIZE = 10 * 1024 * 1024L; // 10[MB]

	private static final long MAX_FILE_SIZE = 5 * 1024 * 1024L; // 5[MB]

	private static final long MAX_FILE_COUNT = 10L;

	private byte[] buf = new byte[BUF_SIZE];

	// ・本来であればtry-with-resources等でストリームを閉じる必要があります！
	// ・テストファイル作成例(1MB): fsutil file createnew test.dat 1048576

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
	public void extract01() throws IOException {
		InputStream is = new FileInputStream(IN_TOP_DIR + "size01x05.zip");
		ZipInputStream zis = new ZipInputStream(is);
		extract(zis, OUT_TOP_DIR + "size01x05");
	}

	@Test
	public void extract02() throws IOException {
		try {
			InputStream is = new FileInputStream(IN_TOP_DIR + "size03x05.zip");
			ZipInputStream zis = new ZipInputStream(is);
			extract(zis, OUT_TOP_DIR + "size03x05");
			fail();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			assertTrue(e.getMessage().contains("total size is exceeded"));
		}
	}

	@Test
	public void extract03() throws IOException {
		try {
			InputStream is = new FileInputStream(IN_TOP_DIR + "size030609.zip");
			ZipInputStream zis = new ZipInputStream(is);
			extract(zis, OUT_TOP_DIR + "size030609");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			assertTrue(e.getMessage().contains("file size is exceeded"));
		}
	}

	public void extract(ZipInputStream zis, String extractTopDir) throws IOException {

		long fileCount = 0L;
		long totalSize = 0L;

		// 展開先ディレクトリパス(存在しない場合は新規作成)
		// ※検証などで比較しやすいよう正規化された絶対パスに変換
		Path extractTopPath = Paths.get(extractTopDir).toAbsolutePath().normalize();
		if (!Files.exists(extractTopPath)) {
			Files.createDirectories(extractTopPath);
		}

		// ZIPストリームのエントリ毎にファイルを作成
		LocalFileHeader fh;
		while ((fh = zis.getNextEntry()) != null) {

			// ファイル数上限チェック
			if (MAX_FILE_COUNT <= fileCount) {
				throw new ZipException("too many files: " + MAX_FILE_COUNT);
			}

			// 解凍先パスの決定
			// ※解凍先トップディレクトリ配下であることを検証
			String filename = fh.getFileName();
			Path filePath = extractTopPath.resolve(filename).toAbsolutePath().normalize();
			if (!filePath.startsWith(extractTopPath)) {
				throw new IllegalArgumentException("invalid path: " + filePath);
			}
			File extractFile = filePath.toFile();

			// ディレクトリを解凍
			if (fh.isDirectory()) {
				extractFile.mkdirs(); // 2層以上作成する場合もあるのでmkdirsを使用
				continue;
			}
			// ※zipファイルによってはディレクトリが含まれない場合があるので事前作成
			File parentFile = extractFile.getParentFile();
			if (!parentFile.exists()) {
				parentFile.mkdirs();
			}

			// ファイルを解凍
			try (OutputStream os = new BufferedOutputStream(new FileOutputStream(extractFile));) {
				long fileSize = 0L;
				int readSize;
				while ((readSize = zis.read(buf)) > 0) {

					// ファイル単位でのサイズ上限チェック
					if (fileSize + readSize > MAX_FILE_SIZE) {
						throw new IllegalArgumentException("file size is exceeded(" + filename + "): " + MAX_FILE_SIZE);
					}

					// zip解凍全体でのサイズ上限チェック
					if (totalSize + readSize > MAX_TOTAL_SIZE) {
						throw new IllegalArgumentException("total size is exceeded: " + MAX_TOTAL_SIZE);
					}

					os.write(buf, 0, readSize);
					fileSize += readSize;
					totalSize += readSize;
				}
				fileCount++;
			}
		}
	}

}
