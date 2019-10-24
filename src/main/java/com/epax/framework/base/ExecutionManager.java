package com.epax.framework.base;

import cucumber.api.Scenario;
import org.apache.log4j.Logger;

public class ExecutionManager {

    private static ThreadLocal<String> testEnvironment = new ThreadLocal<String>();
    private static ThreadLocal<Scenario> testScenario = new ThreadLocal<Scenario>();
    private static ThreadLocal<String> testBrowser = new ThreadLocal<String>();
    static Logger log;

    static {
        log = Logger.getLogger(ExecutionManager.class);
    }
    public static String getTestEnvironment() {
        log.debug("Getting test environment "+testEnvironment);
        return ExecutionManager.testEnvironment.get();
    }


    public static void setTestEnvironment(String testEnvironment) {
        ExecutionManager.testEnvironment.set(testEnvironment);
    }

    public static Scenario getTestScenario() {
        log.debug("Getting test Scenario "+testScenario);
        return ExecutionManager.testScenario.get();
    }


    public static void setTestScenario(Scenario scenario) {
        ExecutionManager.testScenario.set(scenario);
    }

    public static String getTestBrowser() {
        log.debug("Getting test Browser "+testBrowser);
        return ExecutionManager.testBrowser.get();
    }


    public static void setTestBrowser(String testBrowser) {
        ExecutionManager.testBrowser.set(testBrowser);
    }


}