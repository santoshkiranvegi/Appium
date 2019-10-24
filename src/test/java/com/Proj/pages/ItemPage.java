package com.epax.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import com.epax.framework.base.DriverManager;
import com.epax.framework.utilities.WebDriverActions;
import com.epax.stepdefinitions.StepDefs;

public class ItemPage extends WebDriverActions {
	
	WebDriver driver = DriverManager.getDriver();
	
	public static By btAddToCart = By.cssSelector("button[data-testid^='itemPage-addToCart-button']");
	public static By lblOutOfStock = By.cssSelector("div[class^='ItemPage_itemState']");
	public static By btnAddToCartGrayed = By.cssSelector("div[data-testid*='itemPage'] div button:disabled");
	public static By btnAddToCart = By.cssSelector("button[class*='ItemPage_AddToCart']");
	public static By itmItemNameInItemPage = By.cssSelector("div[class^='ItemPage_addToCartContainer'] h2");
	
	public static By btnShowMoreDescription = By.xpath("//*[contains(text(),'Show More')]");
	public static By txtFullDescription = By.cssSelector("p[class^='ItemPage_DescriptionSmallText']");
    public static By btnShowLessDescription = By.xpath("//*[contains(text(),'Show Less')]");
    
   //Recommendations
    public static By lblRecommended      =By.xpath("//h3[contains(text(),'RECOMMENDED PRODUCTS')]");
    public static By listRecommendedItems=By.xpath("//div[contains(@class,'SuggestedItemsView_Masonary')]/div[contains(@class,'Item')]");
    //Substitutions to update Later
    public static By lblSubstitutions    =By.xpath("");
    public static By listSubstitutionItems =By.xpath("");
    public static By lblOutOfStock_Android = By.xpath("//div[contains(@class,'ItemPage_itemState') and contains(text(),'OUT OF STOCK')]");

    
    
    
    
    

}
