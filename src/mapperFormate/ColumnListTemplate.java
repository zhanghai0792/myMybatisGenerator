package mapperFormate;

import java.util.List;

import tableField.fieldDefine;
import util.config;

public class ColumnListTemplate {
public static String basic_columm_id="Base_Column";
public static String detail_column_id="Detail_Column";
public static String mapper(String tableName,List<fieldDefine> fields,String sqlId){
	StringBuffer columm_content=new StringBuffer("<sql id=\"");
	String aliaTabel=tableName+config.splitSingn;//表名_
	System.out.println(tableName+" "+sqlId);
	columm_content.append(sqlId);
	columm_content.append("\">\n\rselect");
	for(fieldDefine f:fields){
		columm_content.append(" ");
		columm_content.append(aliaTabel+"."+f.getFieldName()+" as "+aliaTabel+f.getFieldName()+" ,");
	}
	columm_content.deleteCharAt(columm_content.length()-1);
	columm_content.append(" from "+tableName+" as "+aliaTabel);
	columm_content.append(" \n\r</sql>");
	return columm_content.toString();
}
public static String getBasicColumn(String tableName,List<fieldDefine> fields){
	return mapper(tableName,fields,basic_columm_id);
}
public static String getDetailColumn(String tableName,List<fieldDefine> fields){
	return mapper(tableName,fields,detail_column_id);
}
}
