package com.tripAdvisor.webApp.runnerFiles;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features= {"@target/failedrerun.txt"},
					glue= {"com/tripAdvisor/webApp/stepDefinationFiles","com/tripAdvisor/webApp/base"},
					plugin= {"pretty",
							 "html:target/FailedScenarioReport/failedTestReport.html",
							 "json:target/FailedScenarioReport/failedTestReport.json",
							 "junit:target/FailedScenarioReport/failedTestReport.xml"},
					monochrome=true
					)
public class RunnerFailedTest {

}
