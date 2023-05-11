# SeleniumCucumberParallelXBrowserTesting
Cucumber 7 with Jenkins Integration

Behavioral Driven Development (BDD) using latest cucmber7,Design approach Page Object Model(POM) using Selenium with Java, JUnit framework and Maven AUT-tripadvisor

The given project demonstrate
1. The way of creating Page Object Model(POM) design pattern using Cucumber BDD and selenium 
2. Creating entire framework using JUnit 
3. Parallel execution of feature files using surefire plugin
4. Parallel cross browser testing
5. Rerunning failed test cases
6. Creating reports in Extent pdf (with attachment of failed test cases's screenshot), spark report (with attachment of failed test cases's screenshot), html, junit, json format 
7. Creating jenkins parameterised job and published cucumber report along with sending email
8. Creating Jenkins pipeline for parallel cross browser testing and published cucumber report along with sending email

Application Under Test: Tripadvisor.com (You can find manual Test cases under folder /ManualTestCases

Technologies Used:
Selenium WebDriver 4.4 (opensource)
JDK 1.8 (Java Development Kit)
Eclipse (Java Editor)
Cucumber 7.x JVM library
JUnit 4 (Test Framework)
Maven (Build Automation Tool)
Log4j (logging API)
Browser - Google Chrome, Microsoft Edge, Firefox

Automation Framework Architecture:
POM (Page Object Model)
Maven (Build Automation Tool)
Test Libraries for different UI Pages
Report – cucumber web report, cucumber html report, junit report, json report
GITHub Repository (Code Versioning Tool) 

The best Practices following to design:
1. Every Test Scenario has single responsibility
2. Each page has separate page class
3. Step definition methods do not call any WebDriver methods they can have assertions only.
4. All checked and unchecked exceptions handeled properly.
5. Customized FrameworkException created
6. A page object represents meaningful elements of a page and not necessarily a complete page
7. When navigate to another page, the initial page object should return another page object for the new page
8. A page object is not have any assertions
9. Followed Object Oriented Programming Concepts 
	i) Encapsulation: In every page class made all the locators private and action methods as public 
	ii) Abstraction: created Page class as a abstract class which has abstract methods like getPageTitle(), getWebElement(By locator) 
	iii) Inheritance: BasePage class which has common methods which is been extended by all Page classes 
	iv) Polymorphism: will use method overloading and logged in to the application with different set of parameters
10. Reduced code duplicacy. Increased code reusability
11.Left no hardcoded data in source code
12. Used Extensive logging- everything which is part of source code get analyzed from logs without looking at the source code
13. Generated and saved failure proofs outside the src code
14. Used as many assertions as possible
15. Used explicite wait on each webelement to get loaded

The steps taken to create framework
Created feature files under src/test/resources for HomePage and Login Page Test Scenarios
Created cucumber hooks under src/test/java inside com.TripAdvisor.WebApp.Base package in which @Before annotated method initialize WebDriver instance to the browser specified in configuration file and opened the base URL of the application
Created different page classes for Home Page as well Login Page according to POM
Written a glue code src/test/java inside com.TripAdvisor.WebApp.StepDefinationFiles package and called action methods of page class
Under cucumber Hooks @After(order=1) annotated method takes the screenshot of failed scenarios and save to the screenshot folder as well attach it to report.

To clone the project:
git clone https://github.com/VrushaliDudhane/SeleniumCucumberParallelXBrowserTesting.git

Run The Test Cases Using Maven
mvn test -DRunnerFile=MyRunnerTest.java -DBrowser=chrome

Rerun The failed Test Cases Using Maven
mvn test -DRunnerFile=RunnerFailedTest.java -DBrowser=chrome

To configure Jenkins parameterised job:
Jenkins-Git and Maven
new job->MavenProject->
General->This project is parameterized->AddParameter->Choice Parameter->Name-"browserChoice"->Choices-"chrome""MicrosoftEdge" ""firefox"->Description-"Please Select The Browser"->Choice Parameter->Name-"runnerFile"->Choices-"MyRunnerTest.java" "RunnerFailedTest.java"->Description-"Please Choose The Runner File"->
Source Code Management-> Git-> Repositories->Repository URL-"https://github.com/VrushaliDudhane/SeleniumCucumberParallelXBrowserTesting.git"->Credentials-none- ->Branches to build-> Branch Specifier (blank for 'any')-"*/master"-> 
Build->Root POM-"pom.xml"->Goals and options-"test -DBrowser=$browserChoice -DRunnerFile=$runnerFile"
Configuration on Jenkins to create Cucumber Report---
Manage Jenkins-> Manage Plugins-> Available-> search for "Cucumber Report"-> install the plug in
Post-build Actions->add post-build action-> Cucumber reports-> Advanced -> Report title-"TripAdvisorReport"->JSON Report Location->JSON Reports Path-keep blank or give relative path "target/cucumber-reports/"->File Include Pattern-"**/*.json"->Build Result- make everything 0

To configure Jenkins pipelined job:
new Item-> pipeline->under pipline -defination- select Pipeline Script
Refer the jenkins Groovy script under JenkinsFile
