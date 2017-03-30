package configuration.write;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Set;

import configuration.config;
import configuration.read.ReadTablesStruct;
import mapperFormate.mapperXmlTemplate;

public class writerMapper {
 public static File fold;
private static void createFold(){
	  if(fold==null){
		  fold=new File(config.createFilePath+"/"+config.mapperPackageStuff);
		  if(!fold.exists())
			  fold.mkdirs();
	  }
 }
 
 public static void writerMappers(){
	 createFold();
	 Set<String> tablenames=ReadTablesStruct.tableDefines.keySet();
	 for(String tableName:tablenames)
		 writerPojoMapper(tableName);
 }
 
private static void writerPojoMapper(String tableName){
	 String className=tableName;
		if(config.isDig){
			className=tableName.substring(0, 1).toUpperCase()+tableName.substring(1);
		}
	String fileName=className+config.mapperPackageStuff+".xml";
	if(fold==null)
		createFold();
	File outputFile=new File(fold, fileName);
	PrintWriter pw=null;
	try {
		
		pw=new PrintWriter(outputFile);
		pw.write(mapperXmlTemplate.getXmlContent(tableName));
		
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
