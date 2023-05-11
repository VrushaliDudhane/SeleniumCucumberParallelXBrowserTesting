/**
 * 
 */
package com.tripAdvisor.webApp.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.tripAdvisor.webApp.base.BasePage;

/**
 * @author Vrushali
 *
 */
public class PropertyFileReader {

	public static Logger logger=Logger.getLogger(PropertyFileReader.class.getName());
	
	public static Properties readConfigFile()
	{
		Properties prop=new Properties();
		FileInputStream ip;
		try {
			ip=new FileInputStream("ConfigurationFiles\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			logger.error("congiguration file not found");
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			logger.error("congiguration file could not be loaded");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return prop;
	}
}
