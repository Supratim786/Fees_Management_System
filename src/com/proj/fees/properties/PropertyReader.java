package com.proj.fees.properties;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

Properties prop;
String propertiesFilePath=null;

	public PropertyReader(){
		try {
			
			propertiesFilePath = System.getenv("PROPERTIESFILEPATH");
			
			
			System.out.println("propertiesFilePath:"+propertiesFilePath);
			
			InputStream input = new FileInputStream(propertiesFilePath);
			//InputStream input = new FileInputStream("config.properties");
			prop = new Properties();
			prop.load(input);
			
			System.out.println("properties file loaded successfully");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getProperty(String key){
		
	 return	prop.getProperty(key);
	}
}
