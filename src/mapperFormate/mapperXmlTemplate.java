package mapperFormate;

import java.util.List;

import configuration.config;
import configuration.read.ReadTablesStruct;
import configuration.tableStruct.tableFieldDefine;

public class mapperXmlTemplate {
	public static String mapperHead="<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\r\n<mapper namespace=\"%s\">\r\n";
  public static String getXmlContent(String tableName){
	  List<tableFieldDefine> fds=ReadTablesStruct.tableDefines.get(tableName);
	  StringBuffer sb=new StringBuffer();
	  String className=tableName;
		if(config.isDig){
			className=tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
		}
	  
	  sb.append(String.format(mapperHead, config.mapperPackage+"."+className+config.mapperPackageStuff));
	  sb.append(ColumnListTemplate.getBasicColumn(tableName, fds));
		sb.append(ColumnListTemplate.getDetailColumn(tableName, fds));
		sb.append(BaseResultMapTemplate.getBasic(tableName, fds));
		sb.append(BaseResultMapTemplate.getDetail(tableName, fds));
		sb.append(deleteTemplate.getDeleteById(tableName));
		sb.append(deleteTemplate.getDeleteByIds(tableName));
		sb.append(deleteTemplate.getdeleteByObjects(tableName));
		sb.append(insertTemplate.getInsertAll(tableName, fds));
		sb.append(insertTemplate.getInsertNoNull(tableName, fds));
		
		sb.append(updateTemplate.getUpdateAll(tableName, fds));
		sb.append(updateTemplate.getUpdateNoNull(tableName, fds));
		
		sb.append(selectByIdTemplate.getBasic());
		sb.append(selectByIdTemplate.getDetail());
		
		sb.append(selectTemplate.getCount(tableName));
		sb.append(selectTemplate.getBasic(tableName));
		sb.append(selectTemplate.getDetail(tableName));
	  
	  sb.append("\r\n</mapper>");
	  return sb.toString();
  }
}
