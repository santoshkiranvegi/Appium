package com.epax.stepdefinitions;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.epax.framework.base.DriverManager;
import com.epax.framework.cucumber.TestContext;
import com.epax.framework.utilities.WebDriverActions;
import com.epax.pages.CommonPage;
import com.epax.pages.HeaderMenusPage;
import com.epax.pages.ItemPage;
import com.epax.pages.L2Page;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ItemPageStepDefs extends WebDriverActions{
	
	TestContext testContext;
	int cntInitialCartItems = 0, cntCurrentCartItems;
	CommonPage commonpage = new CommonPage();
	L2Page page = new L2Page();
	int iItemsInCartBeforeAdd;
	// ProductListingPage productListingPage;

	public ItemPageStepDefs(TestContext context) {
		testContext = context;
		// productListingPage =
		// testContext.getPageObjectManager().getProductListingPage();
	}

	static Logger log = Logger.getLogger(CategoryL2PageStepDefs.class);
	WebDriver driver = DriverManager.getDriver();
	WebElement webElement;
	WebDriverWait wait = new WebDriverWait(driver, 10);
	
	
	@Given("an Item page in mobile view")
    public void an_Item_page_in_mobile_view() {
		
		if(getListOfMatchingElements(CommonPage.btnHamburgerMenu).size() > 0) {
    		reportInfo("Item Page is in Mobile View");
    	} else {
    		Assert.assertTrue(false, "Item Page is not in a Mobile View");
    	}		
    	List<WebElement> imgActiveStockItems = getListOfMatchingElements(L2Page.imgActiveStock);
		for(int i = 1; i < imgActiveStockItems.size(); i++) {			
			if(imgActiveStockItems.get(i).getTagName().equalsIgnoreCase("img")) {
				imgActiveStockItems.get(i).click();
				reportInfo("Clicked an Item to navigate to Item Page");
				break;
			}
		}    	
    	
    }

    @When("the user scrolls the view so that the Cart controls move off screen at the top")
    public void the_user_scrolls_the_view_so_that_the_Cart_controls_move_off_screen_at_the_top() throws InterruptedException {
    	Thread.sleep(3000);
    	scrollPageDown();    	
    	if(getListOfMatchingElements(CommonPage.btnHamburgerMenu).size() == 0) {
    		reportStatus(true, "Cart Controls moved off the screen at the top", "Cart Controls are not moved off the screen at the top");    		
    	}
    }

    @Then("a Cart control overlay section shall appear on the bottom of the viewport")
    public void a_Cart_control_overlay_section_shall_appear_on_the_bottom_of_the_viewport() {
    	if(isElementDisplayed(ItemPage.btnAddToCart))
        	reportStatus(true, "Cart control overlay section shall appear on the bottom of the viewport", "Cart control overlay section is not appeared on the bottom of the viewport");
    }

    @Then("the Cart control overlay shall be persistently visible above the scrolling view")
    public void the_Cart_control_overlay_shall_be_persistently_visible_above_the_scrolling_view() {
    	if(isElementDisplayed(CommonPage.btnHamburgerMenu))
        	reportStatus(true, "Cart Control overlay shall not be visible above the scrolling view", "Cart Controls overlay is visible above the scrolling view");
    }
    

    @Given("an Item page for {string}")
	public void an_Item_page_for_Current_Item(String itemName) throws Throwable {
    	waitForElementPresent(elementTimeout);
    	//waitForAllPageRequests();
    	int itemInItemPage = getListOfMatchingElements(By.xpath(CommonPage.byText.replace(CommonPage.replaceText, itemName))).size();
    	reportInfo("Item count in Item Page : " + itemInItemPage);
		reportStatus(itemInItemPage > 0, "Item Page Displayed for : "+itemName, "Failed to Dispaly Item in Item Page:"+itemName);

	}

}
