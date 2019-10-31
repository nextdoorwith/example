package example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePathTest {

	public static void main(String[] args) throws IOException{

		String str1 = "C:\\test\\work1\\.\\work1-1/..\\..\\work2";
		String str2 = ".\\work1\\.\\work1-1/..\\..\\work2";
		
		System.out.println("original               : " + str1);
		File f1 = new File(str1);
		System.out.println("File.getPath()         : " + f1.getPath());
		System.out.println("File.getAbsolutePath() : " + f1.getAbsolutePath());
		System.out.println("File.getCanonicalPath(): " + f1.getCanonicalPath());
		Path p1 = Paths.get(str1);
		System.out.println("Path.toString()        : " + p1.toString());
		System.out.println("Path.toAbsolutePath()  : " + p1.toAbsolutePath());
		System.out.println("Path.normalize()       : " + p1.normalize());
		System.out.println("Path.toRealPath()      : " + p1.toRealPath());
		
		System.out.println();

		System.out.println("original               : " + str2);
		File f2 = new File(str2);
		System.out.println("File.getPath()         : " + f2.getPath());
		System.out.println("File.getPath()         : " + f2.toString());
		System.out.println("File.getAbsolutePath() : " + f2.getAbsolutePath());
		System.out.println("File.getCanonicalPath(): " + f2.getCanonicalPath());
		Path p2 = Paths.get(str2);
		System.out.println("Path.toString()        : " + p2.toString());
		System.out.println("Path.toAbsolutePath()  : " + p2.toAbsolutePath());
		System.out.println("Path.normalize()       : " + p2.normalize());
		System.out.println("Path.toRealPath()      : " + p2.toRealPath()); // NoSuchFileException
		System.out.println("Path other             : " + p2.toAbsolutePath().normalize());

	}

}
