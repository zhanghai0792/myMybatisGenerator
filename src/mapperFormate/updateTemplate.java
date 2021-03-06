package mapperFormate;

import java.util.List;

import configuration.config;
import configuration.tableStruct.tableFieldDefine;

public class updateTemplate {
public static String updateAll_id="updateAll";
public static String updateNoNull_id="updateNoNull";
private static String updateHead="<update id=\"%s\" parameterType=\"%s\">\r\nupdate %s \r\nset ";
private static String updateTail=" where %s = #{%s,jdbcType=%s}";
private static String fieldTemp=" %s = #{%s,jdbcType=%s},";
private static String ifFieldTemp="<if test=\"%s != null\"> %s = #{%s,jdbcType=%s},</if>\r\n";
private static String ifupdateHead=" <update id=\"%s\" parameterType=\"%s\">\r\nupdate %s \r\n<set>\r\n";
public static String getUpdateAll(String tableName,List<tableFieldDefine> fields){
	String className=tableName;
	if(config.isDig){
		className=tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
	}
	className=config.pojoPackage+"."+className;
	String head=String.format(updateHead, updateAll_id,className,tableName);
	StringBuffer sb=new StringBuffer();
	sb.append(head);
	tableFieldDefine idDF=null;
	for(tableFieldDefine df:fields){
		if(!df.isPrimaryKey()){
		sb.append(String.format(fieldTemp, df.getFieldName(),df.getFieldName(),df.getJdbcType()));}
		else{
			idDF=df;
		}
	}
	sb.deleteCharAt(sb.toString().length()-1);
	sb.append(String.format(updateTail, idDF.getFieldName(), idDF.getFieldName(),idDF.getJdbcType()));
	sb.append("\r\n</update>\r\n");
	return sb.toString();
}

public static String getUpdateNoNull(String tableName,List<tableFieldDefine> fields){
	String className=tableName;
	if(config.isDig){
		className=tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
	}
	className=config.pojoPackage+"."+className;
	String head=String.format(ifupdateHead, updateNoNull_id,className,tableName);
	StringBuffer sb=new StringBuffer();
	sb.append(head);
	tableFieldDefine idDF=null;
	for(tableFieldDefine df:fields){
		if(!df.isPrimaryKey()){
			sb.append(String.format(ifFieldTemp, df.getFieldName(),df.getFieldName(),df.getFieldName(),df.getJdbcType()));}
			else{
				idDF=df;
			}
	}
	sb.deleteCharAt(sb.toString().length()-1);
	sb.append("</set>\r\n");
	sb.append(String.format(updateTail, idDF.getFieldName(), idDF.getFieldName(),idDF.getJdbcType()));
	sb.append("\r\n</update>\r\n");
	return sb.toString();
}

}
