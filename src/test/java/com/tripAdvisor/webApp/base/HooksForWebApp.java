/**
 * 
 */
package com.tripAdvisor.webApp.base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.tripAdvisor.webApp.factory.WebDriverFactory;
import com.tripAdvisor.webApp.utilities.PropertyFileReader;
import com.tripAdvisor.webApp.utilities.TimeOutsProvider;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * This class has hooks for cucumber which runs before and after each scenario
 * This class uses Before annotation to initialize WebDriver instance 
 * And after annotation with order 1 to take the screenshot in case scenario is failed
 * another After annotation with order 0 to quite the browser
 * @author Vrushali
 *
 */
public class HooksForWebApp {
	private WebDriver driver;
	public Logger logger=Logger.getLogger(HooksForWebApp.class.getName());
	/**
	 * This Before annotated method initialise browser according to configuration file
	 * and opens the base URL of application mentioned in Configuration file
	 */
	@Before
	public void init(Scenario scenario)
	{
		logger.info("running scenario: "+scenario.getName());
		//driver=WebDriverFactory.initDriver(PropertyFileReader.readConfigFile().getProperty("browser"));
		driver=WebDriverFactory.initDriver(System.getProperty("Browser"));
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TimeOutsProvider.PAGETIMEOUT));
		
		driver.get(PropertyFileReader.readConfigFile().getProperty("url"));
	}
	
	/**
	 * This Method Takes the screenshot in case Scenario failed
	 * and attach the screenshot to report as well Saves The screenshot in Screenshot folder
	 * @param scenario
	 */
	@After(order=1)
	public void takeScreenShot(Scenario scenario)
	{
		if(scenario.isFailed())
		{
			TakesScreenshot sc=(TakesScreenshot)driver;
			
			Date today=new Date();
			String todayDate=today.toString().replace(" ", "_").replace(":", "_");
			
//			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//			String currentDir = System.getProperty("user.dir");
//			try {
//				FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + todayDate+"_"+System.currentTimeMillis()+ ".png"));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			
			
			String screenshotName=scenario.getName().replace(" ", "_");
			byte[] source=sc.getScreenshotAs(OutputType.BYTES);
			scenario.attach(source,"image/png",screenshotName);
		}
		
		
	/**
	 * This method quite the browser
	 */
	}
	@After(order=0)
	public void tearDown()
	{
		driver.quit();
	}

}
