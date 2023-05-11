/**
 * 
 */
package com.tripAdvisor.webApp.runnerFiles;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

/**
 * This the Junit Runner class
 * @author Vrushali
 *
 */

@RunWith(Cucumber.class)
@CucumberOptions(features= {"src/test/resources/featureFiles"},
				glue={"com/tripAdvisor/webApp/stepDefinationFiles","com/tripAdvisor/webApp/base"},
				plugin= {"pretty",
						  "html:target/cucumber-reports/report.html",
						  "junit:target/cucumber-reports/report.xml",
						  "json:target/cucumber-reports/report.json",
						  "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
						  "rerun:target/failedrerun.txt"},
				monochrome=true,
				publish = true,
				dryRun=false
				
				)
public class MyRunnerTest {

}
