package com.epax.stepdefinitions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.epax.framework.base.DriverManager;
import com.epax.framework.base.ExecutionManager;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Configuration {

	static WebDriver baseDriver = null;
	public static List<GalenTestInfo> tests = new ArrayList<>();
	public static String currentBrowser;
	public static String currentOS;
	public static Scenario s;

	@BeforeSuite
	public void SuiteSetUp() {
		System.out.println("SuiteSetUp Started");
		// Creating a list of tests
		tests = new LinkedList<GalenTestInfo>();
		System.out.println("SuiteSetUp End");
	}

	@AfterSuite
	public void CloseSuite() throws IOException {
		System.out.println("SuiteClose Started");
		// Exporting all test reports to html
		new HtmlReportBuilder().build(tests, "target/galen-html-reports");
		System.out.println("After Suite For Epax Layout is Triggered");

	}

	@Before()
	public void before(Scenario scenario) {
		ExecutionManager.setTestScenario(scenario);
		// testContext.scenarioContext.setContext(Context.SCENARIO, scenario);
		s = scenario;
		scenario.getId();
		System.out.println("This is before Scenario: " + scenario.getName());
		// tests = new LinkedList<GalenTestInfo>();
	}

	@After
	public void after(Scenario scenario) throws IOException {
		System.out.println("This is after Scenario: " + scenario.getName());
		// new HtmlReportBuilder().build(tests, "target/galen-html-reports");
		baseDriver = DriverManager.getDriver();
		final byte[] screenshot = ((TakesScreenshot) baseDriver).getScreenshotAs(OutputType.BYTES);
		scenario.write(scenario.getName() +"Execution is done");
		scenario.embed(screenshot, "image/png");

		double sName=Math.random();

		File sourcePath = ((TakesScreenshot) baseDriver).getScreenshotAs(OutputType.FILE);
		File destinationPath = new File(System.getProperty("user.dir") + "/target/test-report/extent-report/screenshots/" + sName + ".png");
		FileUtils.copyFile(sourcePath,destinationPath);
		scenario.embed(Files.readAllBytes(destinationPath.toPath()),"image/png");
	}

}
