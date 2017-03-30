package mapperFormate;

import java.util.List;

import configuration.config;
import configuration.tableStruct.tableFieldDefine;

public class BaseResultMapTemplate {
public static String baseResultId="BaseResultMap";
public static String detailResultId="DetailResultMap";
//public static String pojoPackage="pojo";
/*public static boolean isDig=true;//pojo类首字母是否大写
*/
public static String idTemplate="<id column=\"%s\" jdbcType=\"%s\" property=\"%s\" />\r\n";
public static String resultTemplate=" <result column=\"%s\" jdbcType=\"%s\" property=\"%s\" />\r\n";
public static String getBasic(String tableName,List<tableFieldDefine> fields){
	String className=tableName;
	if(config.isDig){
		className=tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
	}
	StringBuffer content=new StringBuffer();
	content.append("\r\n<resultMap id=\""+baseResultId+"\" type=\""+config.pojoPackage+"."+className+"\">\r\n");
	for(tableFieldDefine f:fields){
		if(f.isPrimaryKey()){
			//是ID项目
			content.append(String.format(idTemplate,tableName+config.splitSingn+f.getFieldName(),f.getJdbcType(),f.getFieldName()));
		}else{
			//一般项目
			content.append(String.format(resultTemplate,tableName+config.splitSingn+f.getFieldName(),f.getJdbcType(),f.getFieldName()));
		}
		
	}
	content.append("</resultMap>\r\n");
	return content.toString();
}

public static String getDetail(String tableName,List<tableFieldDefine> fields){
	String className=tableName;
	if(config.isDig){
		className=tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
	}
	StringBuffer content=new StringBuffer();
	content.append("\r\n<resultMap id=\""+detailResultId+"\" type=\""+config.pojoPackage+"."+className+"\" extends=\""+baseResultId+"\">\r\n");
	content.append("</resultMap>\r\n");
	return content.toString();
}

}
