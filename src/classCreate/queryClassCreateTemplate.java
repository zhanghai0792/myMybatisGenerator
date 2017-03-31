package classCreate;

import java.util.List;

import configuration.config;
import configuration.read.ReadTablesStruct;
import configuration.tableStruct.tableFieldDefine;
import util.myStringUtil;

public class queryClassCreateTemplate {
	//private static String basicClass="package %s;\r\nimport java.util.List;\r\npublic abstract class %s<T extends %s>{\r\nprotected Integer page;\r\nprotected Integer pageSize;\r\nprivate Integer recordIndex;\r\nprivate String orderBy;\r\nprivate List<T> pojos;\r\n}";
	private static String basicClass="package %s;\r\nimport java.util.List;\r\npublic abstract class %s<T extends %s>{\r\nprotected Integer page;\r\nprotected Integer pageSize;\r\nprotected Integer recordIndex;\r\nprotected String orderBy;\r\nprotected List<T> pojos;\r\nprotected String cond;\r\n}";	
	private static String pojoQueryClass="package %s;\r\nimport %s.%s;\r\npublic class %s%s extends %s<%s> {\r\n\r\n}";
	public static String getBasicClass(){
		 String className=myStringUtil.firstCharToUpper(config.queryParentClassName);
		String pojoParentClassName=myStringUtil.firstCharToUpper(config.pojoParentClassName);
		return String.format(basicClass, config.queryPackageName,className,config.pojoPackage+"."+pojoParentClassName);
	}
	public static String getPojoQueryClass(String tableName){
		String packageName=config.queryPackageName;
		String pojoClassPackage=config.pojoPackage;
		String pojoClassName=myStringUtil.firstCharToUpper(tableName);
		String queryStuf=config.queryStuff;
		String queryParentClassName=config.queryParentClassName;
		return String.format(pojoQueryClass, packageName,pojoClassPackage,pojoClassName,pojoClassName,queryStuf,queryParentClassName,pojoClassName);
	}
	
}
