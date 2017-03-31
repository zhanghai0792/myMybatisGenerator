package classCreate;

import java.util.List;

import configuration.config;
import configuration.read.ReadTablesStruct;
import configuration.tableStruct.tableFieldDefine;
import mapperFormate.*;

import util.myStringUtil;

public class daoClassCreateTemplate {
	//config.mapperPackage,config.pojoPackage+"."+config.pojoParentClassName,config.queryPackageName+"."+config.queryParentClassName,config.mapperParentClassName,config.pojoParentClassName,config.queryParentClassName,deleteTemplate.deleteById_id,deleteTemplate.deleteByIds_id,deleteTemplate.deleteByObjects_id,insertTemplate.insertNoNull_id,insertTemplate.insertAll_id,selectByIdTemplate.getBasicById_id,selectByIdTemplate.getDetailById_id,updateTemplate.updateAll_id,updateTemplate.updateNoNull_id,selectTemplate.getBasic_id,selectTemplate.getDetail_id,selectTemplate.count_id
	private static String basicClass="package %s;\r\nimport java.util.List;\r\nimport %s;\r\nimport %s;\r\npublic interface %s <T extends %s,Query extends %s>{\r\nint %s(Integer id) throws Exception;\r\n    int %s(List<Integer> ids)throws Exception;\r\n    int %s(List<T> objs)throws Exception;\r\n    int %s(T record)throws Exception;\r\n    int %s(T record)throws Exception;\r\n T %s(Integer id)throws Exception;\r\n    T %s(Integer id)throws Exception;\r\n    int %s(T record)throws Exception;\r\n    int %s(T record)throws Exception;\r\n    List<T> %s(Query query)throws Exception;\r\n  List<T> %s(Query query)throws Exception;\r\n    long %s(Query query)throws Exception;\r\n}";
	private static String pojoMapperClass="package %s;\r\nimport %s.%s;\r\nimport %s.%s;\r\npublic interface %s%s extends %s<%s,%s>{\r\n  \r\n}";
	//config.mapperPackage,config.pojoPackage,className,config.queryPackageName,className+,className,config.mapperPackageStuff,config.mapperParentClassName,className,className+config.queryStuff
	public static String getBasicClass(){
		 String className=myStringUtil.firstCharToUpper(config.queryParentClassName);
		String pojoParentClassName=myStringUtil.firstCharToUpper(config.pojoParentClassName);
		return String.format(basicClass, config.mapperPackage,config.pojoPackage+"."+pojoParentClassName,config.queryPackageName+"."+config.queryParentClassName,config.mapperParentClassName,config.pojoParentClassName,config.queryParentClassName,deleteTemplate.deleteById_id,deleteTemplate.deleteByIds_id,deleteTemplate.deleteByObjects_id,insertTemplate.insertNoNull_id,insertTemplate.insertAll_id,selectByIdTemplate.getBasicById_id,selectByIdTemplate.getDetailById_id,updateTemplate.updateAll_id,updateTemplate.updateNoNull_id,selectTemplate.getBasic_id,selectTemplate.getDetail_id,selectTemplate.count_id);
	}
	public static String getPojoMapperClass(String tableName){
		String className=myStringUtil.firstCharToUpper(tableName);
		return String.format(pojoMapperClass, config.mapperPackage,config.pojoPackage,className,config.queryPackageName,className+config.queryStuff,className,config.mapperPackageStuff,config.mapperParentClassName,className,className+config.queryStuff);
	}
	
}
