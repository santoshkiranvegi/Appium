package com.epax.testrunners;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
		features = "src/test/java/com/epax/features/TRTEPAX-133.feature",
		glue = {"com.epax.stepdefinitions"},
		plugin = {"pretty",
				"html:target/cucumber-report/edge",
				"json:target/cucumber-report/edge/cucumber.json",
				"junit:target/cucumber-report/edge/cucumber.xml"})
public class RunCukesTestInEdge extends AbstractTestNGCucumberTests {

}