package tableField;

import java.util.HashMap;
import java.util.Map;
/*

binary1 BINARY 255 0 1
varbinary1 VARBINARY 255 0 1
 * */
public class typeMapper {
public static Map<String,String> jdbc_TypesMap=new HashMap<String,String>(0);
public static Map<String,String> java_TypesMap=new HashMap<String,String>(0);
static{
     jdbc_TypesMap.put("INT", "INTEGER");
     java_TypesMap.put("INTEGER", "java.lang.Integer");
     
     
     jdbc_TypesMap.put("TINYINT", "TINYINT");
     java_TypesMap.put("TINYINT", "java.lang.Byte");
     
     jdbc_TypesMap.put("SMALLINT","SMALLINT");
     java_TypesMap.put("SMALLINT", "java.lang.Short");
     
     jdbc_TypesMap.put("MEDIUMINT", "INTEGER");
 
     
     jdbc_TypesMap.put("BIGINT", "BIGINT");
     java_TypesMap.put("BIGINT", "java.lang.Long");
     
     jdbc_TypesMap.put("FLOAT", "REAL");
     java_TypesMap.put("REAL", "java.lang.Float");
     
     jdbc_TypesMap.put("DOUBLE", "DOUBLE");
     java_TypesMap.put("DOUBLE", "java.lang.Double");
     
     jdbc_TypesMap.put("BIT", "BIT");
     java_TypesMap.put("BIT", "java.lang.Boolean");
     
     jdbc_TypesMap.put("DECIMAL","DECIMAL");
     java_TypesMap.put("DECIMAL", "java.math.BigDecimal");
     
     jdbc_TypesMap.put("CHAR", "CHAR");
     java_TypesMap.put("CHAR", "java.lang.String");
     
     jdbc_TypesMap.put("VARCHAR", "VARCHAR");
     java_TypesMap.put("VARCHAR", "java.lang.String");
     
     jdbc_TypesMap.put("DATE", "DATE");
     java_TypesMap.put("DATE", "java.util.Date");
     
     jdbc_TypesMap.put("TIME", "TIME");
     java_TypesMap.put("TIME", "java.util.Date");
     
     jdbc_TypesMap.put("YEAR", "DATE");
   
     
     jdbc_TypesMap.put("TIMESTAMP", "TIMESTAMP");
     java_TypesMap.put("TIMESTAMP", "java.util.Date");
     
     jdbc_TypesMap.put("DATETIME", "TIMESTAMP");
  
     
     jdbc_TypesMap.put("TINYBLOB", "BINARY");
     java_TypesMap.put("BINARY", "byte[]");
     
     jdbc_TypesMap.put("BLOB", "LONGVARBINARY");
     java_TypesMap.put("LONGVARBINARY", "byte[]");
     
     jdbc_TypesMap.put("MEDIUMBLOB", "LONGVARBINARY");
  
     
     jdbc_TypesMap.put("LONGBLOB", "LONGVARBINARY");

     
     jdbc_TypesMap.put("TINYTEXT", "VARCHAR");
 
     
     jdbc_TypesMap.put("TEXT", "LONGVARCHAR");
     java_TypesMap.put("LONGVARCHAR", "java.lang.String");
     
     jdbc_TypesMap.put("MEDIUMTEXT", "LONGVARCHAR");
  
     
     jdbc_TypesMap.put("LONGTEXT", "LONGVARCHAR");
   
     
     jdbc_TypesMap.put("ENUM", "CHAR");
    
     
     jdbc_TypesMap.put("SET", "CHAR");
   
     
     jdbc_TypesMap.put("BINARY", "BINARY");
     java_TypesMap.put("BINARY", "byte[]");
     
     jdbc_TypesMap.put("VARBINARY", "VARBINARY");
     java_TypesMap.put("VARBINARY", "byte[]");  
}
}
