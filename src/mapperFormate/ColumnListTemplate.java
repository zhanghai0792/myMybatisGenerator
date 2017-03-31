package mapperFormate;

import java.util.List;

import configuration.config;
import configuration.tableStruct.tableFieldDefine;

public class ColumnListTemplate {
public static String basic_columm_id="Base_Column";
public static String detail_column_id="Detail_Column";
public static String mapper(String tableName,List<tableFieldDefine> fields,String sqlId){
	StringBuffer columm_content=new StringBuffer("\r\n<sql id=\"");
	String aliaTabel=tableName+config.splitSingn;//表名_
	//System.out.println(tableName+" "+sqlId);
	columm_content.append(sqlId);
	columm_content.append("\">\r\nselect");
	for(tableFieldDefine f:fields){
		columm_content.append(" ");
		columm_content.append(tableName+"."+f.getFieldName()+" as "+aliaTabel+f.getFieldName()+" ,");
	}
	columm_content.deleteCharAt(columm_content.length()-1);
	//columm_content.append(" from "+tableName+" as "+aliaTabel);
	columm_content.append(" from "+tableName+" as "+tableName);
	columm_content.append(" \r\n</sql>\r\n");
	return columm_content.toString();
}
public static String getBasicColumn(String tableName,List<tableFieldDefine> fields){
	return mapper(tableName,fields,basic_columm_id);
}
public static String getDetailColumn(String tableName,List<tableFieldDefine> fields){
	return mapper(tableName,fields,detail_column_id);
}
}
