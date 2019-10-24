package com.epax.stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class JavaSample {

    public static final String USERNAME = "rajuvemu3";
    public static final String AUTOMATE_KEY = "qQza2nMJ6z9Xv8Drwx1E";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void main(String[] args) throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "iPhone");
        caps.setCapability("device", "iPhone 8 Plus");
        caps.setCapability("realMobile", "true");
        caps.setCapability("os_version", "11");
        caps.setCapability("name", "Bstack-[Java] Sample Test");

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        driver.get("https://qa-epax.bsgg.co.uk/dashboard");
        driver.findElement(By.cssSelector("i[data-testid*=floating-menu-mobile] +i")).click();
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("div[class*=CategoryButtons_category] +div:nth-child(2) span")).click();
        driver.findElement(By.cssSelector("a[data-testid*=Spirits]")).click();

        System.out.println(driver.getTitle());
        driver.quit();

    }
}