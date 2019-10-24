package com.epax.stepdefinitions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.epax.framework.base.DriverManager;
import com.epax.framework.cucumber.TestContext;
import com.epax.framework.utilities.WebDriverActions;
import com.epax.pages.CartPage;
import com.epax.pages.CommonPage;
import com.galenframework.api.Galen;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.model.LayoutReport;

import cucumber.api.Scenario;
import cucumber.api.java.en.Then;

/**
 *
 */
public class StepDefs extends WebDriverActions {

	TestContext testContext;
	// ProductListingPage productListingPage;

	public StepDefs(TestContext context) {
		testContext = context;
		// productListingPage =
		// testContext.getPageObjectManager().getProductListingPage();
	}

	static Logger log = Logger.getLogger(StepDefs.class);
	WebDriver driver = DriverManager.getDriver();
	WebElement webElement;
	WebDriverWait wait = new WebDriverWait(driver, 10);
	int cntInitialCartItems = 0, cntCurrentCartItems;
	CartPage cartPage = new CartPage();
	CommonPage commonpage = new CommonPage();
	public static String currentBrowser;
	public static String currentOS;
	public static Scenario s;
	public static List<GalenTestInfo> tests = new ArrayList<>();

	@Then("^Amazon website is layout tested$")
	public void amazon_website_is_layout_tested() throws Throwable {
		// Executing layout check and obtaining the layout report
		System.out.println("******************************************************");
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = cap.getBrowserName().toLowerCase();
		System.out.println(browserName);
		String os = cap.getPlatform().toString();
		System.out.println(os);
		String v = cap.getVersion().toString();
		System.out.println(v);
		System.out.println("******************************************************");

		LayoutReport layoutReport = Galen.checkLayout(driver, "specs/amazon.spec", Arrays.asList("desktop"), null, null,
				null);

		System.out.println("The Current Browser Name is : " + currentBrowser);
		System.out.println("The Current OS Name is : " + currentOS);
		// Creating a list of tests
		// List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();*/

		// Creating an object that will contain the information about the test
		GalenTestInfo test = GalenTestInfo
				.fromString(os + "_" + browserName + "_" + s.getName() + " check layout on Desktop device");
		System.out.println("The Current Browser Name is : " + currentBrowser);
		System.out.println("The Current OS Name is : " + currentOS);
		// Adding layout report to the test report
		test.getReport().layout(layoutReport, s.getName() + " check layout on Desktop device");
		tests.add(test);
		// s.write("target/galen-html-reports/1--2--login-page-on-desktop-device-test.html");

	}

	@Then("^Welcome Page Header Layout is tested$")
	public void welcome_Page_Header_Layout_is_tested() throws Throwable {
		// Executing layout check and obtaining the layout report
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		getCapabilities(cap);
		String browserName = cap.getBrowserName().toLowerCase();
		System.out.println(browserName);
		String os = cap.getPlatform().toString();
		System.out.println(os);
		String v = cap.getVersion().toString();
		System.out.println(v);
		System.out.println("******************************************************");
		LayoutReport layoutReport = Galen.checkLayout(driver, "specs/GGHeader.spec", Arrays.asList("desktop"), null,
				null, null);
		// Creating an object that will contain the information about the test
		GalenTestInfo test = GalenTestInfo
				.fromString(os + "_" + browserName + "_" + s.getName() + " check layout on Desktop device");
		// Adding layout report to the test report
		test.getReport().layout(layoutReport, s.getName() + " check layout on Desktop device");
		tests.add(test);
		String TestResult = "LayOut Satisfied", abc = "LayOut Satisfied";
		if (test.isFailed()) {
			TestResult = "LayOut Not Satisfied";
		}
		Assert.assertEquals(TestResult, abc);

	}

	@Then("^Welcome Page Content Layout is tested$")
	public void welcome_Page_Content_Layout_is_tested() throws Throwable {
		// Executing layout check and obtaining the layout report
		Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
		getCapabilities(cap);
		String browserName = cap.getBrowserName().toLowerCase();
		System.out.println(browserName);
		String os = cap.getPlatform().toString();
		System.out.println(os);
		String v = cap.getVersion().toString();
		System.out.println(v);
		System.out.println("******************************************************");
		LayoutReport layoutReport = Galen.checkLayout(driver, "specs/GGWelcomePageContent.spec",
				Arrays.asList("desktop"), null, null, null);
		// Creating an object that will contain the information about the test
		GalenTestInfo test = GalenTestInfo
				.fromString(os + "_" + browserName + "_" + s.getName() + " check layout on Desktop device");
		// Adding layout report to the test report
		test.getReport().layout(layoutReport, s.getName() + " check layout on Desktop device");
		tests.add(test);
		String TestResult = "LayOut Satisfied", abc = "LayOut Satisfied";
		if (test.isFailed()) {
			TestResult = "LayOut Not Satisfied";
		}
		Assert.assertEquals(TestResult, abc);
	}

	public String getCapabilities(Capabilities cap) {

		System.out.println("************Started To print Browser Capabilities For the Current Instance***************");
		String deviceName;
		try {
			deviceName = cap.getCapability("device").toString();
			System.out.println("deviceName : " + deviceName);
		} catch (NullPointerException ex) {
			deviceName = cap.getCapability("platform").toString();
			System.out.println("platform : " + deviceName);
		}
		String browserName = cap.getBrowserName().toLowerCase();
		System.out.println("browserName : " + browserName);

		/*
		 * String BrowserName=cap.getCapability("browserName").toString();
		 * System.out.println("BrowserName : "+BrowserName); String browserName =
		 * cap.getBrowserName().toLowerCase();
		 * System.out.println("browserName : "+browserName);
		 * 
		 * String os = cap.getPlatform().toString(); System.out.println(os); String v =
		 * cap.getVersion().toString(); System.out.println(v); String browser =
		 * cap.getCapability("browser").toString(); System.out.println(browser);
		 */
		System.out.println("************Ending To print Browser Capabilities***************");
		return deviceName + "_" + browserName;
	}

}
