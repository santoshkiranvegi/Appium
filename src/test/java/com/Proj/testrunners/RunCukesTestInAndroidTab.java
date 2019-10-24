package com.epax.testrunners;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import org.junit.runner.RunWith;

import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/com/epax/features/TRTEPAX-109.feature",
        glue = {"com.epax.stepdefinitions"},
        plugin = {"pretty",
                "html:target/cucumber-report/AndroidTab",
                "json:target/cucumber-report/AndroidTab/cucumber.json",
                "junit:target/cucumber-report/AndroidTab/cucumber.xml"})
public class RunCukesTestInAndroidTab extends AbstractTestNGCucumberTests {
}