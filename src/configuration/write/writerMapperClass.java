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
import classCreate.daoClassCreateTemplate;
import classCreate.queryClassCreateTemplate;
import configuration.config;
import configuration.read.ReadTablesStruct;
import mapperFormate.mapperXmlTemplate;
import util.myStringUtil;

public class writerMapperClass {
 public static File queryPackage;
private static void createqueryPackage(){
	  if(queryPackage==null){
		  String classFold=config.mapperPackage.replace(".", "/");
		 // queryPackage=new File(config.createFilePath+"/"+config.mapperPackage);
		  queryPackage=new File(config.createFilePath+"/"+classFold);
		  if(!queryPackage.exists())
			  queryPackage.mkdirs();
	  }
 }
private static void createBasicMapperClass(){
	String className=myStringUtil.firstCharToUpper(config.mapperParentClassName);
String fileName=className+".java";
if(queryPackage==null)
	createqueryPackage();
File outputFile=new File(queryPackage, fileName);
PrintWriter pw=null;
try {
	
	pw=new PrintWriter(outputFile);
	pw.write(daoClassCreateTemplate.getBasicClass());	
	pw.flush();
	
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally{
	if(pw!=null)
		pw.close();
}
} 


 public static void writerMappersClass(){
	 createqueryPackage();
	 createBasicMapperClass();
	 Set<String> tablenames=ReadTablesStruct.tableDefines.keySet();
	 for(String tableName:tablenames)
		 writerMapperMapper(tableName);
 }
 
private static void writerMapperMapper(String tableName){
	 String className=tableName;
		if(config.isDig){
			className=tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
		}
	String fileName=className+config.mapperPackageStuff+".java";
	if(queryPackage==null)
		createqueryPackage();
	File outputFile=new File(queryPackage, fileName);
	PrintWriter pw=null;
	try {
		
		pw=new PrintWriter(outputFile);
		pw.write(daoClassCreateTemplate.getPojoMapperClass(tableName));	
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
