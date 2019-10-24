package com.epax.framework.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class DriverListener extends AbstractWebDriverEventListener {

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {

		try {
			WebElement elem = driver.findElement(by);
			// draw a border around the found element
			if (driver instanceof JavascriptExecutor) {
				((JavascriptExecutor) driver).executeScript("arguments[0].style.border='4px solid yellow'", elem);
			}
		}
		catch(Exception ex){

		}

	}
}

