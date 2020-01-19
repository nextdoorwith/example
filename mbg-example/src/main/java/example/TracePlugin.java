package example;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.GeneratedFile;
import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.VisitableElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * mybatis-generatorプラグインのメソッド呼出しをトレースするプラグイン。
 * 
 * @author nextdoorwith
 *
 */
public class TracePlugin implements Plugin {

	/** XML要素値をログ出力する際の上限 */
	private static final int MAX_LENGTH = 1024;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void setContext(Context context) {
		log("setContext");
	}

	@Override
	public void setProperties(Properties properties) {
		log("setProperties");
	}

	@Override
	public void initialized(IntrospectedTable introspectedTable) {
		log("initialized");
	}

	@Override
	public boolean validate(List<String> warnings) {
		log("validate");
		return true;
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
		log("contextGenerateAdditionalJavaFiles");
		return Collections.emptyList();
	}

	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		log("contextGenerateAdditionalJavaFiles");
		return Collections.emptyList();
	}

	@Override
	public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles() {
		log("contextGenerateAdditionalXmlFiles");
		return Collections.emptyList();
	}

	@Override
	public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(IntrospectedTable introspectedTable) {
		log("contextGenerateAdditionalXmlFiles");
		return Collections.emptyList();
	}

	@Override
	public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
		log("clientGenerated", interfaze);
		return true;
	}

	@Override
	public boolean clientBasicCountMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientBasicCountMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientBasicDeleteMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientBasicDeleteMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientBasicInsertMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientBasicInsertMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientBasicInsertMultipleMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientBasicInsertMultipleMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientBasicInsertMultipleHelperMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientBasicInsertMultipleHelperMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientBasicSelectManyMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientBasicSelectManyMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientBasicSelectOneMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientBasicSelectOneMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientBasicUpdateMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientBasicUpdateMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientCountByExampleMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientBasicUpdateMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientDeleteByExampleMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientDeleteByExampleMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientDeleteByPrimaryKeyMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientGeneralCountMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientGeneralCountMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientGeneralDeleteMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientGeneralDeleteMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientGeneralSelectDistinctMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientGeneralSelectDistinctMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientGeneralSelectMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientGeneralSelectMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientGeneralUpdateMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientGeneralUpdateMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientInsertMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientInsertMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientInsertMultipleMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientInsertMultipleMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientInsertSelectiveMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientSelectByExampleWithBLOBsMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientSelectByExampleWithoutBLOBsMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientSelectByPrimaryKeyMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientSelectListFieldGenerated(Field field, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientSelectListFieldGenerated", field, interfaze);
		return true;
	}

	@Override
	public boolean clientSelectOneMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientSelectOneMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientUpdateByExampleSelectiveMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientUpdateAllColumnsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientUpdateAllColumnsMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientUpdateSelectiveColumnsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientUpdateSelectiveColumnsMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientUpdateByExampleWithBLOBsMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientUpdateByExampleWithoutBLOBsMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientUpdateByPrimaryKeySelectiveMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientUpdateByPrimaryKeyWithBLOBsMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean clientSelectAllMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable)
	{
		log("clientSelectAllMethodGenerated", method, interfaze);
		return true;
	}

	@Override
	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType)
	{
		log("modelFieldGenerated", field, topLevelClass);
		return true;
	}

	@Override
	public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType)
	{
		log("modelGetterMethodGenerated", method, topLevelClass);
		return true;
	}

	@Override
	public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType)
	{
		log("modelSetterMethodGenerated", method, topLevelClass);
		return true;
	}

	@Override
	public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		log("modelPrimaryKeyClassGenerated", topLevelClass);
		return true;
	}

	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		log("modelBaseRecordClassGenerated", topLevelClass);
		return true;
	}

	@Override
	public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		log("modelRecordWithBLOBsClassGenerated", topLevelClass);
		return true;
	}

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		log("modelExampleClassGenerated", topLevelClass);
		return true;
	}

	@Override
	public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
		log("sqlMapGenerated", sqlMap);
		return true;
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
		log("sqlMapDocumentGenerated", document.getRootElement());
		return true;
	}

	@Override
	public boolean sqlMapResultMapWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable)
	{
		log("sqlMapResultMapWithoutBLOBsElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		log("sqlMapCountByExampleElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		log("sqlMapDeleteByExampleElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		log("sqlMapDeleteByPrimaryKeyElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapExampleWhereClauseElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		log("sqlMapExampleWhereClauseElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapBaseColumnListElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		log("sqlMapBaseColumnListElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapBlobColumnListElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		log("sqlMapBlobColumnListElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		log("sqlMapInsertElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		log("sqlMapInsertSelectiveElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapResultMapWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		log("sqlMapResultMapWithBLOBsElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapSelectAllElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		log("sqlMapSelectAllElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
		log("sqlMapSelectByPrimaryKeyElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable)
	{
		log("sqlMapSelectByExampleWithoutBLOBsElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable)
	{
		log("sqlMapSelectByExampleWithBLOBsElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable)
	{
		log("sqlMapUpdateByExampleSelectiveElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable)
	{
		log("sqlMapUpdateByExampleWithBLOBsElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable)
	{
		log("sqlMapUpdateByExampleWithoutBLOBsElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable)
	{
		log("sqlMapUpdateByPrimaryKeySelectiveElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable)
	{
		log("sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated", element);
		return true;
	}

	@Override
	public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable)
	{
		log("sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated", element);
		return true;
	}

	@Override
	public boolean providerGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		log("providerGenerated", topLevelClass);
		return true;
	}

	@Override
	public boolean providerApplyWhereMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		log("providerApplyWhereMethodGenerated", method, topLevelClass);
		return true;
	}

	@Override
	public boolean providerCountByExampleMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		log("providerCountByExampleMethodGenerated", method, topLevelClass);
		return true;
	}

	@Override
	public boolean providerDeleteByExampleMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		log("providerDeleteByExampleMethodGenerated", method, topLevelClass);
		return true;
	}

	@Override
	public boolean providerInsertSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		log("providerInsertSelectiveMethodGenerated", method, topLevelClass);
		return true;
	}

	@Override
	public boolean providerSelectByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		log("providerSelectByExampleWithBLOBsMethodGenerated", method, topLevelClass);
		return true;
	}

	@Override
	public boolean providerSelectByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		log("providerSelectByExampleWithoutBLOBsMethodGenerated", method, topLevelClass);
		return true;
	}

	@Override
	public boolean providerUpdateByExampleSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		log("providerUpdateByExampleSelectiveMethodGenerated", method, topLevelClass);
		return true;
	}

	@Override
	public boolean providerUpdateByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		log("providerUpdateByExampleWithBLOBsMethodGenerated", method, topLevelClass);
		return true;
	}

	@Override
	public boolean providerUpdateByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		log("providerUpdateByExampleWithoutBLOBsMethodGenerated", method, topLevelClass);
		return true;
	}

	@Override
	public boolean providerUpdateByPrimaryKeySelectiveMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable)
	{
		log("providerUpdateByPrimaryKeySelectiveMethodGenerated", method, topLevelClass);
		return true;
	}

	@Override
	public boolean dynamicSqlSupportGenerated(TopLevelClass supportClass, IntrospectedTable introspectedTable) {
		log("dynamicSqlSupportGenerated", supportClass);
		return true;
	}

	// 以降はデバッグ用のメソッド群 **********************************

	protected void log(String name) {
		logger.debug("invoked: {}()", name);
	}

	protected void log(String name, CompilationUnit unit) {
		logger.debug("invoked: {}(): {}", name, unit.getType().getShortName());
	}

	protected void log(String name, GeneratedFile file) {
		logger.debug("invoked: {}(): {}", name, file.getFileName());
	}

	protected void log(String name, Method method, CompilationUnit unit) {
		logger.debug("invoked: {}(): {}#{}", name, unit.getType().getShortName(), method.getName());
	}

	protected void log(String name, Field field, CompilationUnit unit) {
		logger.debug("invoked: {}(): {}.{}", name, unit.getType().getShortName(), field.getName());
	}

	protected void log(String name, XmlElement element) {

		// 要素内のコンテンツを全て文字列として結合
		String msg = getInnerContent(element).trim();

		// 長すぎる場合は切り落とす
		if (MAX_LENGTH < msg.length()) {
			msg = msg.substring(0, MAX_LENGTH) + "...";
		}

		logger.debug("invoked: {}(): {}", name, msg);
	}

	private String getInnerContent(XmlElement element) {

		List<VisitableElement> ve = element.getElements();

		StringBuilder sb = new StringBuilder();
		for (VisitableElement v : ve) {
			if (v instanceof TextElement) {
				sb.append(((TextElement) v).getContent());
			} else if (v instanceof XmlElement) {
				sb.append("[x]"); // getElements()しても空なので断念
			} else {
				sb.append("[?]");
			}
		}
		return sb.toString();
	}

}
