package classCreate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import configuration.config;
import configuration.read.ReadTablesStruct;
import configuration.tableStruct.tableFieldDefine;
import util.myStringUtil;

public class classCreateTemplate {
	private static String basicClass="package %s;\r\nimport java.io.Serializable;\r\npublic class %s implements Serializable{\r\n protected Integer id;\r\npublic Integer getId() {\r\nreturn id;\r\n}\r\npublic void setId(Integer id) {\r\nthis.id = id;\r\n\r\n}public boolean equals(Object obj) {\r\n if(this.getClass().getName().equals(obj.getClass().getName())){\r\nreturn this.hashCode()==obj.hashCode();\r\n}\r\nreturn false;\r\n}\r\npublic int hashCode() {\r\nint basic=this.getClass().hashCode()*1000;\r\nif(id==null){\r\nreturn -basic;\r\n}else{\r\nreturn basic+id;\r\n}\r\n}\r\n}";
	private static String classItemFormat="public %s get%s() {\r\nreturn %s;\r\n}\r\n\r\npublic void set%s(%s %s) {\r\nthis.%s = %s;\r\n}\r\n";
	private static String classStringItemFormat="public %s get%s() {\r\nreturn %s;\r\n}\r\n\r\npublic void set%s(%s %s) {\r\nthis.%s = %s == null ? null : %s.trim();\r\n}\r\n";
	private static String fieldContent="private %s %s;\r\n";
	public static String getBasicClass(){
		 String className=config.pojoParentClassName;
		if(config.isDig){
			className=className.substring(0, 1).toUpperCase()+className.substring(1);
		}
		return String.format(basicClass, config.pojoPackage,className);
	}
	public static String getClass(String tableName){
		return getClass(tableName,ReadTablesStruct.tableDefines.get(tableName));
	}
	
	private static String getClass(String tableName,List<tableFieldDefine> fields){
		Set<String> imports=new HashSet<String>(0);
		StringBuffer classAll=new StringBuffer();
		StringBuffer classPre=new StringBuffer();
		classPre.append("package "+config.pojoPackage+";\r\n");
		StringBuffer classContent=new StringBuffer();
		classContent.append("public class "+myStringUtil.firstCharToUpper(tableName)+" extends "+myStringUtil.firstCharToUpper(config.pojoParentClassName)+" {\r\n");
		StringBuffer classMethod=new StringBuffer();
		for(tableFieldDefine df:fields){
			//是主键id
			if(df.isPrimaryKey())
				continue;
			classContent.append(String.format(fieldContent, df.getJavaType(),df.getFieldName()));
			if("String".equals(df.getJavaType())){
				//字符串格式处理
				classMethod.append(String.format(classStringItemFormat,df.getJavaType(),myStringUtil.firstCharUpper(df.getFieldName()),df.getFieldName(),myStringUtil.firstCharUpper(df.getFieldName()),df.getJavaType(),df.getFieldName(),df.getFieldName(),df.getFieldName(),df.getFieldName()));
				continue;
			}
			
			if(df.getJavaType().indexOf(".")>0){
				if(!imports.contains(df.getJavaType())){
				classPre.append("import "+df.getJavaType()+";\r\n");
				 imports.add(df.getJavaType());
				}
			}
			//非字符串格式处理
			classMethod.append(String.format(classItemFormat,df.getJavaType(),myStringUtil.firstCharUpper(df.getFieldName()),df.getFieldName(),myStringUtil.firstCharUpper(df.getFieldName()),df.getJavaType(),df.getFieldName(),df.getFieldName(),df.getFieldName()));

		}
		classAll.append(classPre);//import语句
		classAll.append(classContent);
		classAll.append(classMethod);
		classAll.append("\r\n}");
		return classAll.toString();
	}
	
}
