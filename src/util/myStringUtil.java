package util;

import configuration.config;

public class myStringUtil {
 public static String firstCharToUpper(String content){
	 if(config.isDig&&content!=null&&!"".equals(content)){
		return content.substring(0, 1).toUpperCase()+content.substring(1); 
	 }else{
		 return content;
	 }
 }
 
 public static String firstCharUpper(String content){
	 if(content!=null&&!"".equals(content)){
		return content.substring(0, 1).toUpperCase()+content.substring(1); 
	 }else{
		 return content;
	 }
 }
}
