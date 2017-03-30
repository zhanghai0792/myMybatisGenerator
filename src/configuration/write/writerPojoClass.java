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
import configuration.config;
import configuration.read.ReadTablesStruct;
import mapperFormate.mapperXmlTemplate;
import util.myStringUtil;

public class writerPojoClass {
 public static File pojopackage;
private static void createpojopackage(){
	  if(pojopackage==null){
		  pojopackage=new File(config.createFilePath+"/"+config.pojoPackage);
		  if(!pojopackage.exists())
			  pojopackage.mkdirs();
	  }
 }
private static void createBasicPojoClass(){
	String className=myStringUtil.firstCharToUpper(config.pojoParentClassName);
String fileName=className+".java";
if(pojopackage==null)
	createpojopackage();
File outputFile=new File(pojopackage, fileName);
PrintWriter pw=null;
try {
	
	pw=new PrintWriter(outputFile);
	pw.write(classCreateTemplate.getBasicClass());	
	pw.flush();
	
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally{
	if(pw!=null)
		pw.close();
}
} 


 public static void writerPojosClass(){
	 createpojopackage();
	 createBasicPojoClass();
	 Set<String> tablenames=ReadTablesStruct.tableDefines.keySet();
	 for(String tableName:tablenames)
		 writerPojoMapper(tableName);
 }
 
private static void writerPojoMapper(String tableName){
	 String className=tableName;
		if(config.isDig){
			className=tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
		}
	String fileName=className+".java";
	if(pojopackage==null)
		createpojopackage();
	File outputFile=new File(pojopackage, fileName);
	PrintWriter pw=null;
	try {
		
		pw=new PrintWriter(outputFile);
		pw.write(classCreateTemplate.getClass(tableName));	
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
