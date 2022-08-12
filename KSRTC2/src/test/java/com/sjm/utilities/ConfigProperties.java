package com.sjm.utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigProperties {
	public final static String workingDir = System.getProperty("user.dir");
	public final String propertyFile= workingDir+"/src/test/resources/configuration.properties";
    private final static Logger log = LogManager.getLogger(ConfigProperties.class);
	private Properties properties;
	
	public ConfigProperties(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFile));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFile);
		}		
	}
	
		 public String getApplicationUrl(String appUrl) {
		 String url = properties.getProperty(appUrl);
		 if(url != null) {
			 log.info("URL of the application : "+url);
			 return url;
		 }else throw new RuntimeException("url not specified in the Configuration.properties file.");
		 }
		 

}
