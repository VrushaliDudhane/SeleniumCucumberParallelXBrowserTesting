/**
 * 
 */
package com.tripAdvisor.webApp.factory;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.tripAdvisor.webApp.customException.FrameworkException;
import com.tripAdvisor.webApp.utilities.PropertyFileReader;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * @author Vrushali
 *
 */
public class WebDriverFactory {
	
	public static ThreadLocal<WebDriver> tl=new ThreadLocal<>();
	public static Logger logger=Logger.getLogger(WebDriverFactory.class.getName());
	public static WebDriver initDriver(String browser)
	{
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("--remote-allow-origins=*");
			tl.set(new ChromeDriver(opt));
		}

		else if (browser.equalsIgnoreCase("MicrosoftEdge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions opt=new EdgeOptions();
			opt.addArguments("--remote-allow-origins=*");
			tl.set(new EdgeDriver(opt));
		}
		else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions opt=new FirefoxOptions();
			opt.addArguments("--remote-allow-origins=*");
			tl.set(new FirefoxDriver(opt));
		}
		else{
			logger.error("Wrong browser value passed:"+browser);
			throw new FrameworkException("wrong browser value passed");	
		}
		return getDriver();
		
		
	}
	
	public static WebDriver getDriver()
	{
		return tl.get();
	}
}
