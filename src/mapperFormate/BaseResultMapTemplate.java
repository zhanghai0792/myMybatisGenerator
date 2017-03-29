package mapperFormate;

import java.util.List;

import tableField.fieldDefine;
import util.config;

public class BaseResultMapTemplate {
public static String baseResultId="BaseResultMap";
public static String detailResultId="DetailResultMap";
public static String pojoPackage="pojo";
public static boolean isDig=true;//pojo类首字母是否大写
public static String idTemplate="<id column=\"%s\" jdbcType=\"%s\" property=\"%s\" />\r\n";
public static String resultTemplate=" <result column=\"%s\" jdbcType=\"%s\" property=\"%s\" />\r\n";
public static String getBasic(String tableName,List<fieldDefine> fields){
	String className=tableName;
	if(isDig){
		className=tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
	}
	StringBuffer content=new StringBuffer();
	content.append("<resultMap id=\""+baseResultId+"\" type=\""+pojoPackage+"."+className+"\">\r\n");
	for(fieldDefine f:fields){
		if(f.isPrimaryKey()){
			//是ID项目
			content.append(String.format(idTemplate,tableName+config.splitSingn+f.getFieldName(),f.getJdbcType(),f.getFieldName()));
		}else{
			//一般项目
			content.append(String.format(resultTemplate,tableName+config.splitSingn+f.getFieldName(),f.getJdbcType(),f.getFieldName()));
		}
		
	}
	content.append("</resultMap>");
	return content.toString();
}

public static String getDetail(String tableName,List<fieldDefine> fields){
	String className=tableName;
	if(isDig){
		className=tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
	}
	StringBuffer content=new StringBuffer();
	content.append("<resultMap id=\""+detailResultId+"\" type=\""+pojoPackage+"."+className+"\" extends=\""+baseResultId+"\">\r\n");
	content.append("</resultMap>");
	return content.toString();
}

}
