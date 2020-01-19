package example;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.VisitableElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

public class ExamplePlugin extends PluginAdapter {

	/* テスト用にメソッドに設定するアノテーション */
	private FullyQualifiedJavaType reqanno = new FullyQualifiedJavaType("example.misc.Required");

	/** {xxxx}を検索・置換するための正規表現 */
	private Pattern paramPattern = Pattern.compile("\\#\\{(.*?)\\}");

	/** プラグインプロパティ1 */
	private String arg1;

	/** プラグインプロパティ2 */
	private String arg2;

	@Override
	public void setProperties(Properties properties) {
		this.arg1 = properties.getProperty("arg1");
		this.arg2 = properties.getProperty("arg2");
	}

	@Override
	public boolean validate(List<String> warnings) {
		if (this.arg1 == null || this.arg2 == null) {
			warnings.add("please specify arg1 and arg2");
		}

		// メッセージがない場合は正常と見なす。
		return warnings.size() <= 0;
	}

	@Override
	public void initialized(IntrospectedTable introspectedTable) {

		// 特定の名前のカラムについては、後続処理から除外
		// (モデルやXMLからdummy2の存在が消される。)
		Iterator<IntrospectedColumn> cols = introspectedTable.getBaseColumns().iterator();
		while (cols.hasNext()) {
			IntrospectedColumn ic = cols.next();
			if (ic.getActualColumnName().equals("testDummyCol1")) {
				cols.remove();
			}
		}
	}

	@Override
	public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		// 既存メソッドから新しいメソッドを作成してインターフェイスに追加
		Method addMethod = new Method(method);
		addMethod.setName(method.getName() + "AddedTest"); // selectByPrimaryKeyAddedTestを追加
		addMethod.addAnnotation("@" + reqanno.getShortName()); // アノテーションを追加
		interfaze.addMethod(addMethod); // Javaインターフェイスにメソッド追加

		interfaze.addImportedType(reqanno); // import文一覧に宣言を追加

		return true;
	}

	@Override
	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType)
	{
		String fieldName = field.getName();
		if (fieldName.equals("testDummyCol1")) {
			field.addAnnotation("@" + reqanno.getShortName()); // アノテーションを追加
			topLevelClass.addImportedType(reqanno); // import文一覧に宣言を追加
		}
		return true;
	}

	@Override
	public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		// 特定のカラムに関数を付与
		List<VisitableElement> veList = element.getElements();
		for (int i = 0; i < veList.size(); i++) {

			VisitableElement ve = veList.get(i);
			String line = ((TextElement) ve).getContent();

			// 正規表現でパラメータ部分を検索・置換
			// ※下記は適当なサンプルになっているので業務使用時は注意！
			Matcher m = paramPattern.matcher(line);
			StringBuffer sb = new StringBuffer(); // 置換後文字列を格納
			while (m.find()) {
				String all = m.group(0); // "#{dummy,jdbcType=VARCHAR})"
				String col = m.group(1); // "dummy,jdbcType=VARCHAR"
				if (col.startsWith("testDummyCol2,")) {
					String replace = "func(" + all + ")";
					m.appendReplacement(sb, replace);
				}
			}
			m.appendTail(sb);
			String newText = sb.toString();

			// 上記で変更があった場合、要素を差し替える
			if (!line.equals(newText)) {
				veList.set(i, new TextElement(newText));
			}
		}

		return true;
	}
}
