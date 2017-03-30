package configuration;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import configuration.read.ReadTablesStruct;
import configuration.write.writerPojoClass;
import configuration.write.writerQueryClass;
import configuration.write.writerMapper;

public class config {
	//表名与别名的标记符
public static String splitSingn="_";
public static String pojoPackage="pojo";
public static String queryParentClassName="queryParams";
public static String pojoParentClassName="basicModel";
public static String queryPackageName="query";
public static String queryStuff="QueryParams";//查询变量的后缀名
public static String mapperPackage="dao";
public static String mapperPackageStuff="Dao";//dao的后缀名
public final static boolean isDig=true;//pojo类首字母是否大写
 private static String url="jdbc:mysql://localhost/students";
 private static String driver="com.mysql.jdbc.Driver";
 private static String userName="root";
 private static String pwd="123";
public static String createFilePath="c:\\mapper";
 public static DatabaseMetaData  m_DBMetaData =null;
 public static Connection conn;
 public static void config(){
	 try {
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url,userName,pwd);
			m_DBMetaData=conn.getMetaData();
			 ReadTablesStruct.readTableAndFields();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(conn!=null&&!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	 
 }
 
 public static void write(){
	 writerMapper.writerMappers();
	 writerPojoClass.writerPojosClass();
	 writerQueryClass.writerQueryClass();
 }



public static void setSplitSingn(String splitSingn) {
	config.splitSingn = splitSingn;
	
}


public static void setQueryPackageName(String queryPackageName) {
	config.queryPackageName = queryPackageName;
	
}



public static void setQueryStuff(String queryStuff) {
	config.queryStuff = queryStuff;
}



public static void setMapperPackage(String mapperPackage) {
	config.mapperPackage = mapperPackage;
}



public static void setMapperPackageStuff(String mapperPackageStuff) {
	config.mapperPackageStuff = mapperPackageStuff;
}



public static void setUrl(String url) {
	config.url = url;
}



public static void setDriver(String driver) {
	config.driver = driver;
}



public static void setUserName(String userName) {
	config.userName = userName;
}

public static void setPwd(String pwd) {
	config.pwd = pwd;
}



public static void setCreateFilePath(String createFilePath) {
	config.createFilePath = createFilePath;
}

public static void setPojoPackage(String pojoPackage) {
	config.pojoPackage = pojoPackage;
}



}
