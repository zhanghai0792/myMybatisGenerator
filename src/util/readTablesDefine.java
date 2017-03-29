package util;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mapperFormate.BaseResultMapTemplate;
import mapperFormate.ColumnListTemplate;
import mapperFormate.deleteTemplate;
import mapperFormate.insertTemplate;
import mapperFormate.selectByIdTemplate;
import mapperFormate.selectTemplate;
import mapperFormate.updateTemplate;
import tableField.fieldDefine;

public class readTablesDefine {
static StringBuffer stringBuffer=new StringBuffer();
public static Map<String,List<fieldDefine>> tableDefines=new HashMap<String,List<fieldDefine>>(0);
//读取字段名及类型
public static void readTableAndFields() {
	 DatabaseMetaData  m_DBMetaData =config.m_DBMetaData;
	 ResultSet rs=null;
	 ResultSet colRet=null;
	 ResultSet keyRs=null;
	 List<fieldDefine> fields=null;
	  fieldDefine f=null;
	  String keyName=null;
	try {
		rs = m_DBMetaData.getTables(null, "%","%",new String[]{"TABLE"});
		
		    String columnName;
		    String columnType;
		    while(rs.next()){
		    	fields=new ArrayList<fieldDefine>(0);
		    	if(keyRs!=null&&!keyRs.isClosed()) 
		    	keyRs.close();
		    	//获得表中主键的名字
		    	  keyRs = m_DBMetaData.getPrimaryKeys(null, null, rs.getString("TABLE_NAME"));
		    	if(keyRs.next())
		    	   keyName=keyRs.getString("COLUMN_NAME");
		    	    
		    	if(colRet!=null&&!colRet.isClosed())
		    		colRet.close();
		    	colRet= m_DBMetaData.getColumns(null,"%", rs.getString("TABLE_NAME"),"%");
		    	  while(colRet.next()) {
		    	 /*  columnName = colRet.getString("COLUMN_NAME");
		    	   columnType = colRet.getString("TYPE_NAME");
		    	   int datasize = colRet.getInt("COLUMN_SIZE");
		    	   int digits = colRet.getInt("DECIMAL_DIGITS");
		    	   int nullable = colRet.getInt("NULLABLE");*/
		    		 if(keyName!=null&&keyName.equals(colRet.getString("COLUMN_NAME"))){
		    			 //主键
		    			 f=new fieldDefine(colRet.getString("COLUMN_NAME"), colRet.getString("TYPE_NAME"), colRet.getString("TYPE_NAME"),colRet.getInt("COLUMN_SIZE"),colRet.getInt("DECIMAL_DIGITS"), colRet.getInt("NULLABLE"),true);	
		    			 fields.add(0,f);
		    	   }
		    		 else{
		    			 f=new fieldDefine(colRet.getString("COLUMN_NAME"), colRet.getString("TYPE_NAME"), colRet.getString("TYPE_NAME"),colRet.getInt("COLUMN_SIZE"),colRet.getInt("DECIMAL_DIGITS"), colRet.getInt("NULLABLE"));
		    			  fields.add(f);
		    		 }
		    	  }
		    	
		    	  
		    	  
		    	  tableDefines.put(rs.getString("TABLE_NAME"), fields);
		    	  f=null;
		    	
		    	  keyName=null;
		    }
	} catch (SQLException e3) {
		// TODO Auto-generated catch block
		e3.printStackTrace();
	}finally{
	   if(keyRs!=null)
		try {
			keyRs.close();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
	    if(colRet!=null)
			try {
				colRet.close();
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	    if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    if(config.conn!=null)
			try {
				config.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 
}}

public static void writeMapper(){
	//Map<String,List<fieldDefine>> tableDefines=tableDefines;
	for(Entry<String,List<fieldDefine>> table:tableDefines.entrySet()){
		System.out.println("-------"+table.getKey()+"----------");
		System.out.println(ColumnListTemplate.getBasicColumn(table.getKey(), table.getValue()));
		System.out.println(ColumnListTemplate.getDetailColumn(table.getKey(), table.getValue()));
		System.out.println(BaseResultMapTemplate.getBasic(table.getKey(), table.getValue()));
		System.out.println(BaseResultMapTemplate.getDetail(table.getKey(), table.getValue()));
		System.out.println(deleteTemplate.getDeleteById(table.getKey()));
		System.out.println(deleteTemplate.getDeleteByIds(table.getKey()));
		System.out.println(deleteTemplate.getdeleteByObjects(table.getKey()));
		System.out.println(insertTemplate.getInsertAll(table.getKey(), table.getValue()));
		System.out.println(insertTemplate.getInsertNoNull(table.getKey(), table.getValue()));
		
		System.out.println(updateTemplate.getUpdateAll(table.getKey(), table.getValue()));
		System.out.println(updateTemplate.getUpdateNoNull(table.getKey(), table.getValue()));
		
		System.out.println(selectByIdTemplate.getBasic());
		System.out.println(selectByIdTemplate.getDetail());
		
		System.out.println(selectTemplate.getCount(table.getKey()));
		System.out.println(selectTemplate.getBasic(table.getKey()));
		System.out.println(selectTemplate.getDetail(table.getKey()));
	}
}

}
