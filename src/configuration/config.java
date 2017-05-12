package configuration;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import configuration.read.ReadTablesStruct;
import configuration.write.writerPojoClass;
import configuration.write.writerQueryClass;
import util.myStringUtil;
import configuration.write.writerMapperClass;
import configuration.write.writerMapperXml;

public class config {
	//表名与别名的标记符
public static String splitSingn="_";
public static String pojoPackage="com.jm.pojo";
public static String queryParentClassName="queryParams";
public static String pojoParentClassName="basicModel";
public static String queryPackageName="com.jm.query";
public static String queryStuff="QueryParams";//查询变量的后缀名
public static String mapperPackage="com.jm.dao";
public static String mapperParentClassName="daoTemplate";
public static String mapperPackageStuff="Dao";//dao的后缀名
public final static boolean isDig=true;//pojo类首字母是否大写
 private static String url="jdbc:mysql://localhost/integrateplatform";
 private static String driver="com.mysql.jdbc.Driver";
 private static String userName="root";
 private static String pwd="123456";
public static String createFilePath="c:\\mapper";
 public static DatabaseMetaData  m_DBMetaData =null;
 public static Connection conn;
 public static void configuration(String driver,String url,String userName,String pwd){
	 setDriver(driver);
	 setUrl(url);
	 setUserName(userName);
	 setPwd(pwd);
	 config();
 }
 public static void configuration(){
	 config();
 }
 
 private static void config(){
	 queryParentClassName=myStringUtil.firstCharToUpper(queryParentClassName);
	 pojoParentClassName=myStringUtil.firstCharToUpper(pojoParentClassName);
	 mapperParentClassName=myStringUtil.firstCharToUpper(mapperParentClassName);
	 
	 
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
 //生成相应的文件
 public static void write(){
	 //生成mybatis的配置文件
	 writerMapperXml.writerMappers();
	 //生成对应的pojo类的文件
	 writerPojoClass.writerPojosClass();
	 //生成对应的查询类Query
	 writerQueryClass.writerQueryClass();
	 //生成mapper类文件
	 writerMapperClass.writerMappersClass();
 }


//设置表名后的字符为 别名，如 student student_ splitSingn为_
public static void setSplitSingn(String splitSingn) {
	config.splitSingn = splitSingn;
	
}

//生成的查询类所在的package名
public static void setQueryPackageName(String queryPackageName) {
	config.queryPackageName = queryPackageName;
	
}


//查询类后面的部分,如 studentQueryParamas的后缀为QueryParamas
public static void setQueryStuff(String queryStuff) {
	config.queryStuff = queryStuff;
}


//生成的dao的包名
public static void setMapperPackage(String mapperPackage) {
	config.mapperPackage = mapperPackage;
}


//dao的后缀 如 stduentDao 后缀为Dao
public static void setMapperPackageStuff(String mapperPackageStuff) {
	config.mapperPackageStuff = mapperPackageStuff;
}


//设置连接的url
public static void setUrl(String url) {
	config.url = url;
}


//设置数据库的驱动
public static void setDriver(String driver) {
	config.driver = driver;
}


//数据库的连接用户名
public static void setUserName(String userName) {
	config.userName = userName;
}
//数据库的连接用户密码
public static void setPwd(String pwd) {
	config.pwd = pwd;
}


//生成的文件放在哪个目录下，要用绝对路径表示
public static void setCreateFilePath(String createFilePath) {
	config.createFilePath = createFilePath;
}
//设置的pojo类的包名
public static void setPojoPackage(String pojoPackage) {
	config.pojoPackage = pojoPackage;
}


//设置查询类父类的名字
public static void setQueryParentClassName(String queryParentClassName) {
	config.queryParentClassName = queryParentClassName;
}
//设置pojo类父类的名字
public static void setPojoParentClassName(String pojoParentClassName) {
	config.pojoParentClassName = pojoParentClassName;
}



}
