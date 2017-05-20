package test;

import configuration.config;
import configuration.read.ReadTablesStruct;

public class myGeneratorTest {

	public static void main(String[] args) {
		//config.setCreateFilePath(createFilePath);
		//如果要表名遵循大小写，要在my.init的[mysqld]中添加
		/*
		 [mysqld]
lower_case_table_names=0
		 * */
		config.configuration("myexam");
		config.write();
 System.out.println("ok");
	}

}
