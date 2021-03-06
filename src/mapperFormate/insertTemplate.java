package mapperFormate;

import java.util.List;

import configuration.config;
import configuration.tableStruct.tableFieldDefine;

public class insertTemplate {
public static String insertNoNull_id="saveNoNull";
public static String insertAll_id="save";
public static String insert_head="\r\n<insert id=\"%s\" parameterType=\"%s\" useGeneratedKeys=\"true\" keyProperty=\"%s\">\r\ninsert into %s";
public static String insert_values="#{%s,jdbcType=%s},";
public static String trim_content="\r\n<trim prefix=\"%s\" suffix=\")\" suffixOverrides=\",\">\r\n";
public static String ifField_insert_content=" <if test=\"%s != null\">\r\n%s,\r\n</if>\r\n";
public static String ifField_value_content="<if test=\"%s != null\">\r\n#{%s,jdbcType=%s},\r\n</if>\r\n";

//所有字段都要插入，除了id
public static String getInsertAll(String tableName,List<tableFieldDefine> fields){
	StringBuffer insertHead=new StringBuffer();
	StringBuffer insertTail=new StringBuffer();
	String className=tableName;
	if(config.isDig){
		className=tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
	}
	className=config.pojoPackage+"."+className;
	//insertHead.append(String.format(insert_head, insertAll_id,className,,tableName));
	tableFieldDefine keyFd=null;
	insertTail.append(" values(");
	insertHead.append(" (");
	for(tableFieldDefine f:fields){
		if(f.isPrimaryKey()){
			keyFd=f;
			continue;
		}
		insertHead.append(f.getFieldName()+",");
		insertTail.append(String.format(insert_values, f.getFieldName(),f.getJdbcType()));
	}
	insertHead.deleteCharAt(insertHead.length()-1);
	insertTail.deleteCharAt(insertTail.length()-1);
	insertTail.append(")\r\n</insert>\r\n");
	insertHead.append(") \r\n");
	
	return String.format(insert_head, insertAll_id,className,keyFd.getFieldName(),tableName)+insertHead.toString()+insertTail.toString();
}


public static String getInsertNoNull(String tableName,List<tableFieldDefine> fields){
	StringBuffer insertHead=new StringBuffer();
	StringBuffer insertTail=new StringBuffer();
	String className=tableName;
	if(config.isDig){
		className=tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
	}
	className=config.pojoPackage+"."+className;
	//insertHead.append(String.format(insert_head, insertAll_id,className,,tableName));
	tableFieldDefine keyFd=null;
	for(tableFieldDefine f:fields){
		if(f.isPrimaryKey()){
			keyFd=f;
			continue;
		}
		insertHead.append(String.format(ifField_insert_content, f.getFieldName(),f.getFieldName()));
		insertTail.append(String.format(ifField_value_content, f.getFieldName(),f.getFieldName(),f.getJdbcType()));
	}
	
	insertTail.append("</trim>\r\n</insert>\r\n");
	insertHead.append("</trim>\r\n");
	StringBuffer sb=new StringBuffer();
	sb.append(String.format(insert_head, insertNoNull_id,className,keyFd.getFieldName(),tableName));
	sb.append(String.format(trim_content,"("));
	sb.append(insertHead);
	sb.append(String.format(trim_content,"values("));
	sb.append(insertTail);
	
	return sb.toString();
}


}
