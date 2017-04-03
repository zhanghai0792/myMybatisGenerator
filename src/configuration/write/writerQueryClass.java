package configuration.write;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Set;

import classCreate.classCreateTemplate;
import classCreate.queryClassCreateTemplate;
import configuration.config;
import configuration.read.ReadTablesStruct;
import mapperFormate.mapperXmlTemplate;
import util.myStringUtil;

public class writerQueryClass {
 public static File queryPackage;
private static void createqueryPackage(){
	  if(queryPackage==null){
		  //queryPackage=new File(config.createFilePath+"/"+config.queryPackageName);
		  String classFold=config.queryPackageName.replace(".", "/");
		  queryPackage=new File(config.createFilePath+"/"+classFold);
		  if(!queryPackage.exists())
			  queryPackage.mkdirs();
	  }
 }
private static void createBasicPojoClass(){
	String className=myStringUtil.firstCharToUpper(config.queryParentClassName);
String fileName=className+".java";
if(queryPackage==null)
	createqueryPackage();
File outputFile=new File(queryPackage, fileName);
PrintWriter pw=null;
try {
	
	pw=new PrintWriter(outputFile);
	pw.write(queryClassCreateTemplate.getBasicClass());	
	pw.flush();
	
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally{
	if(pw!=null)
		pw.close();
}
} 


 public static void writerQueryClass(){
	 createqueryPackage();
	 createBasicPojoClass();
	 Set<String> tablenames=ReadTablesStruct.tableDefines.keySet();
	 for(String tableName:tablenames)
		 writerQueryMapper(tableName);
 }
 
private static void writerQueryMapper(String tableName){
	 String className=tableName;
		if(config.isDig){
			className=tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
		}
	String fileName=className+config.queryStuff+".java";
	if(queryPackage==null)
		createqueryPackage();
	File outputFile=new File(queryPackage, fileName);
	PrintWriter pw=null;
	try {
		
		pw=new PrintWriter(outputFile);
		pw.write(queryClassCreateTemplate.getPojoQueryClass(tableName));	
		pw.flush();
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		if(pw!=null)
			pw.close();
	}
	
 }
}
