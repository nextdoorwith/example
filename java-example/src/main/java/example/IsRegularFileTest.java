package example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

public class IsRegularFileTest {

	// 第1引数にパスを指定してください
	public static void main(String[] args) throws IOException {

		System.out.println("os  : " + System.getProperty("os.name"));
		System.out.println("jvm : " + System.getProperty("java.version"));

		try (Stream<Path> stream = Files.walk(Paths.get(args[0]))) {
			stream.sorted().forEach(p -> IsRegularFileTest.test(p));
		}

	}

	public static void test(Path path) {
		StringBuilder sb = new StringBuilder();
		try {
			BasicFileAttributes bfa = Files.readAttributes(path, BasicFileAttributes.class);
			sb.append("FOLLOW=");
			sb.append(bfa.getClass().getSimpleName() + ":");
			sb.append("[");
			sb.append(bfa.isDirectory() ? "D" : "-");
			sb.append(bfa.isRegularFile() ? "R" : "-");
			sb.append(bfa.isSymbolicLink() ? "L" : "-");
			sb.append(bfa.isOther() ? "O" : "-");
			sb.append("]");
			sb.append(", NOFOLLOW=");
			bfa = Files.readAttributes(path, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
			sb.append(bfa.getClass().getSimpleName() + ":[");
			sb.append(bfa.isDirectory() ? "D" : "-");
			sb.append(bfa.isRegularFile() ? "R" : "-");
			sb.append(bfa.isSymbolicLink() ? "L" : "-");
			sb.append(bfa.isOther() ? "O" : "-");
			sb.append("]");
		} catch (IOException e) {
			sb.append(e);
		}
		System.out.format("%-50s: %s\n", path, sb);
	}

}
