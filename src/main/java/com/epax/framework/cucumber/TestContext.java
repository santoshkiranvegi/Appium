package com.epax.framework.cucumber;

import com.epax.framework.base.DriverManager;

public class TestContext {
        private DriverManager webDriverManager;
       // private PageObjectManager pageObjectManager;
        public ScenarioContext scenarioContext;

        public TestContext(){
            webDriverManager = new DriverManager();
           // pageObjectManager = new PageObjectManager(webDriverManager.getDriver());
            scenarioContext = new ScenarioContext();
        }

        public DriverManager getWebDriverManager() {
            return webDriverManager;
        }

           public ScenarioContext getScenarioContext() {
            return scenarioContext;
        }
}
