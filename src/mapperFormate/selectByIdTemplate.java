package mapperFormate;

import configuration.config;

public class selectByIdTemplate {
public static String getBasicById_id="getBasicById";
public static String getDetailById_id="getDetailById";
public static String format="\r\n<select id=\"%s\"  resultMap=\"%s\" parameterType=\"java.lang.Integer\">\r\n<include refid=\"%s\" />\r\nwhere %sid = #{id,jdbcType=INTEGER}</select>\r\n";
public static String getBasic(String tabelName){
	String tableNameAlias=tabelName+config.splitSingn;
	return (String.format(format, getBasicById_id,BaseResultMapTemplate.baseResultId,ColumnListTemplate.basic_columm_id,tableNameAlias));
	
}

public static String getDetail(String tabelName){
	String tableNameAlias=tabelName+config.splitSingn;
	return (String.format(format, getDetailById_id,BaseResultMapTemplate.detailResultId,ColumnListTemplate.detail_column_id,tableNameAlias));
}
}
