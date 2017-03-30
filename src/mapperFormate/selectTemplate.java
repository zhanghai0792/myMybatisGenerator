package mapperFormate;

import configuration.config;

public class selectTemplate {
private static String getBasic_content="\r\n<select id=\"%s\" parameterType=\"%s\" resultMap=\"%s\">\r\n<include refid=\"%s\" />\r\n<where>\r\n<if test=\"cond ！= null\">${cond}</if>\r\n<if test=\"pojos != null\">\r\n and %s.id in\r\n<foreach collection=\"pojos\" open=\"(\" close=\")\" separator=\",\" item=\"pojo\">\r\n#{pojo.id}\r\n</foreach>\r\n</if>\r\n</where>\r\n<if test=\"orderBy != null\"> order by ${orderBy}</if>\r\n<if test=\"recordIndex !=null\"> limit #{recordIndex},#{pageSize}</if>\r\n</select>\r\n";
private static String getBasic_id="getBasic";
private static String getDetail_id="getDetail";
public static String count_content="\r\n<select id=\"%s\" parameterType=\"%s\" resultType=\"long\">\r\nselect count(id) from %s\r\n<where>\r\n<if test=\"cond ！= null\">${cond}</if>\r\n</where>\r\n</select>\r\n";
public static String count_id="count";

public static String getCount(String tableName){
	 String queryClassName=config.queryPackageName+".";
	 if(config.isDig){
		 queryClassName=queryClassName+tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
	 }else{
		 queryClassName=queryClassName+tableName;
	 }
	 return String.format(count_content, count_id,queryClassName,tableName);
}


 public static String getBasic(String tableName){
	 String queryClassName=config.queryPackageName+".";
	 if(config.isDig){
		 queryClassName=queryClassName+tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
	 }else{
		 queryClassName=queryClassName+tableName;
	 }
	 queryClassName= queryClassName+config.queryStuff;
	 return String.format(getBasic_content, getBasic_id,queryClassName,BaseResultMapTemplate.baseResultId,ColumnListTemplate.basic_columm_id,tableName);
 }
 
 
 public static String getDetail(String tableName){
	 String queryClassName=config.queryPackageName+".";
	 if(config.isDig){
		 queryClassName=queryClassName+tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
	 }else{
		 queryClassName=queryClassName+tableName;
	 }
	 queryClassName= queryClassName+config.queryStuff;
	 return String.format(getBasic_content, getDetail_id,queryClassName,BaseResultMapTemplate.detailResultId,ColumnListTemplate.detail_column_id,tableName);
 }
}
