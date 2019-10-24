package com.epax.testrunners;

import java.io.IOException;
import java.util.LinkedList;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.epax.stepdefinitions.StepDefs;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = "src/test/java/com/epax/features/TRTEPAX-21.feature",
        glue = {"com.epax.framework.stepdefinitions"},
        plugin = {"pretty",
                "html:target/cucumber-report/browserStackiphone",
                "json:target/cucumber-report/iphone/cucumber.json",
                "junit:target/cucumber-report/iphone/cucumber.xml"})
public class RunCukesTestInBrowserStackIPhone extends AbstractTestNGCucumberTests{

}
