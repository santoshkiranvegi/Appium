package com.epax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CategoryPage {
    public static By lblNoMoreItems = By.xpath("//h3[contains(text(),'No More Items')]");
    public static By lblOutOfStock_Android = By.xpath("//div[contains(text(),'OUT OF STOCK')]");
}
