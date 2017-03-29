package util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class config {
	//表名与别名的标记符
public static String splitSingn="_";
 private static String url="jdbc:mysql://localhost/students";
 private static String driver="com.mysql.jdbc.Driver";
 private static String userName="root";
 private static String pwd="123";
 private static String appPath;
 public static DatabaseMetaData  m_DBMetaData =null;
 public static Connection conn;
 static{
	 try {
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url,userName,pwd);
		m_DBMetaData=conn.getMetaData();
	} catch (Exception e) {
		e.printStackTrace();
	}	  
 }
}
