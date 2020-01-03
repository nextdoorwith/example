package example.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexReplaceExample1 {

	public static void main(String[] args) {

		String instr = "insert into table1(col1, col2) values(#{abc}, #{xyz})";
		System.out.println("in : " + instr);

		Pattern p = Pattern.compile("\\#\\{(.*?)\\}");
		Matcher m = p.matcher(instr);
		StringBuffer sb = new StringBuffer(); // 置換後文字列を格納
		while (m.find()) {
			String all = m.group(0);
			String part = m.group(1);
			System.out.println("[0]=" + all + ", [1]=" + part);
			String replace = "func(" + all + ")"; // 置換の例
			m.appendReplacement(sb, replace);
		}
		m.appendTail(sb);

		System.out.println("out: " + sb.toString());
	}

}
