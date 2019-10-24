package com.epax.testrunners;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

/**
 * Please notice that cucumber.examples.java.testNG.stepDefinitions.BeforeAfterHooks class
 * is in the same package as the steps definitions.
 * It has two methods that are executed before or after scenario.
 * I'm using it to delete cookies and take a screenshot if scenario fails.
 */
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.runtime.model.CucumberFeature;
import gherkin.formatter.model.Feature;

public class RunCukesAsTestngTest {
	private TestNGCucumberRunner test;
	/**
	 * 
	 */
	/**
	 * 
	 */
	@Test()
	@Parameters({"browserName","OSName", "features","glue","plugin"})
	public void runFeatures(String browserName,String OSName,String features,String glue,String plugin) {
		try {
			System.out.println("Run Initiated1");
			String args[] = {features,"-g",glue,"-p",plugin,"-p","html:target/cucumber-report/"+browserName+OSName};
			cucumber.api.cli.Main.run(args, Thread.currentThread().getContextClassLoader());
			System.out.println("Run Initiated2");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
}