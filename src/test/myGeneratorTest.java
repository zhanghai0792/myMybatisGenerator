package test;

import configuration.config;
import configuration.read.ReadTablesStruct;

public class myGeneratorTest {

	public static void main(String[] args) {
		//config.setCreateFilePath(createFilePath);
		config.config();
		config.write();

	}

}
