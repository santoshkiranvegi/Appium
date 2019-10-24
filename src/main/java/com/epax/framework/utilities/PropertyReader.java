package com.epax.framework.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	public static String getPropertyData(String key) throws IOException {
		File file = new File("src/test/resources/ExecutionSetup.properties");
		FileReader fs = new FileReader(file);
		Properties properties = new Properties();
		properties.load(fs);
		String value = properties.getProperty(key);
		System.out.println("Data:  "+value);
		fs.close();
		return value;
		
	}

}
