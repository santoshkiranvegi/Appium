package com.epax.stepdefinitions;


import java.util.Collections;
import java.util.List;

import java.awt.event.WindowListener;


import org.apache.log4j.Logger;
import org.apache.maven.model.Reporting;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.epax.framework.base.DriverManager;
import com.epax.framework.base.WebDriverListener;
import com.epax.framework.utilities.WebDriverActions;
import com.epax.pages.CartPage;
import com.epax.pages.CommonPage;
import com.epax.pages.HeaderMenusPage;
import com.epax.pages.L1Page;
import com.epax.pages.L2Page;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CategoryL1PageStepDefs extends WebDriverActions {

	static Logger log = Logger.getLogger(StepDefs.class);
	WebDriver driver = DriverManager.getDriver();
	L1Page page = new L1Page();
	CommonPage commonPage = new CommonPage();

	@Then("^the Side menu overlay shall persist for further user action$")
	public void the_Side_menu_overlay_shall_persist_for_further_user_action() throws Throwable {
		reportInfo("Side menu overlay shall persist for the user action");
	}

	@Given("^a Side menu that is open with a Category LOne expanded$")
	public void a_Side_menu_that_is_open_with_a_Category_LOne_expanded() throws Throwable {

		if(getListOfMatchingElements(CommonPage.btnHamburgerMenu).size() > 0) {
			clickElement(CommonPage.btnHamburgerMenu);
			reportInfo("Side Menu is open with Category L1 List");
			clickElement(L2Page.L2CatrgoryName);
			reportInfo("Side Menu is open with category L1 expanded");
		} else {
			reportStatus(isElementDisplayed(L2Page.L2CatrgoryName), "Side Menu is opened with Category L1 expanded", "Side Menu is not opened with Category L1 expanded");
		}		
	}

	@When("^the user taps a Category LOne name at the top of the list$")
	public void the_user_taps_a_Category_LOne_name_at_the_top_of_the_list() throws Throwable {
		
		if(isElementEnabled(L1Page.topOfTheList)) {
			clickElement(L1Page.topOfTheList);
		} else {
			reportInfo("Not able to tap on the top of the list");
		}		
	}

	@Then("^the Side menu shall revert back to its base state$")
	public void the_Side_menu_shall_revert_back_to_its_base_state() throws Throwable {
		Assert.assertTrue(isElementEnabled(L2Page.L2CatrgoryName),
				"Side menu is not reverted back to its base state");
	}

	@Then("^the app shall navigate to the Category page for the applicable Category LOne$")
	public void the_app_shall_navigate_to_the_Category_page_for_the_applicable_Category_LOne() throws Throwable {
		
		commonPage.waitForElementPresent(implicitWaitTimeout);
		reportStatus(isElementEnabled(L2Page.btnSort_Andriod), "App is navigated to L2 Category Page", "App is not navigated to L2 Category Page");		
	}

	@When("the user taps the \"([^\"]*)\" menu element")
	public void the_user_taps_the_menu_element(String string) throws Exception {

		if(isMobile()) {
				//jsClick(CommonPage.btnHamburgerMenu);
				clickMatchingelementByText(CommonPage.btnHamburgerMenu,"menu");
				if (string.equalsIgnoreCase("FAQ")) {
					clickMatchingelementByText(CommonPage.listMobileMenu,string);
					//jsClick(HeaderMenusPage.btnFaq);
					} else if (string.equalsIgnoreCase("My Orders")) {
						clickMatchingelementByText(HeaderMenusPage.btnMyOrder,string);
					} else {
						jsClick(CartPage.btnCloseShoppingCart);
						clickMatchingelementByText(CartPage.btnCloseShoppingCart,"close");
						clickMatchingelementByText(HeaderMenusPage.btnShoppingCartIcon,"shopping_cart");
				}
				
		}else {
				if (string.equalsIgnoreCase("FAQ")) {
					jsClick(HeaderMenusPage.btnFaq);
					Thread.sleep(2000);
					} else if (string.equalsIgnoreCase("My Orders")) {
						jsClick(HeaderMenusPage.btnMyOrder);
						waitForPageToLoad(elementTimeout);
					} else {
						waitForPageToLoad(elementTimeout);
						jsClick(HeaderMenusPage.btnShoppingCart);
					}
				reportInfo(string + " is Clicked");
			  }
	}
	

	@Given("a header menu with a Cat L2 dropdown shown")
	public void a_header_menu_with_a_Cat_L2_dropdown_shown() {
		jsMouseOver(L1Page.L1MenuSelector);

		reportStatus(isElementDisplayed(L2Page.L2SubMenu), "L2 category items are displayed successfully", "L2 category items are not displayed");

		int subMenucount = getListOfMatchingElements(L2Page.L2SubMenu).size();
		reportInfo("Sub Menu displayed (0 means dismisses) : " + subMenucount);
		reportStatus((subMenucount > 0), "L2 category items are displayed successfully", "L2 category items are not displayed");

	}

	@When("the user taps on a Cat L1 element again")
	public void the_user_taps_on_a_Cat_L1_element_again() {
		clickElement(L1Page.L1MenuSelector);
		clickElement(L1Page.L1Menu);
		reportInfo("Clicked L1 Menu");

		waitForPageToLoad(3);

		//jsMouseOver(page.firstSeemoreitems_link);//to avoid mousehover on L1
		clickElement(L1Page.txtSearchTextBox);
		reportInfo("Clicked Search Text Box");

		/*reportInfo(getElementText(L1Page.L1Menu)+" got click on Headermenu List");*/

		//jsMouseOver(page.firstSeemoreitems_link);//to avoid mousehover on L1		
		//clickElement(CartPage.boxSearchTextBox);
		
		WebElement searchBox = getElement(CartPage.boxSearchTextBox);
		searchBox.click();
		
		reportInfo("Clicked Search TextBox in L1 page");
		waitForPageToLoad(3);
		//reportInfo(getElementText(L1Page.L1Menu)+" got click on Headermenu List");

	}

	@Then("the Cat L2 dropdown dismisses")
	public void the_Cat_L2_dropdown_dismisses() {
		

		waitForPageToLoad(5);
		int countSubMenu = getListOfMatchingElements(L2Page.L2SubMenu).size();
		reportInfo("Sub Menu count : " + countSubMenu);
		reportStatus((countSubMenu == 0), "L2 category dropdown dismisses successfully", "L2 category dropdown not dismisses");

		int l2SubMenu = getListOfMatchingElements(L2Page.L2SubMenu).size();
		reportInfo("Sub Menu displayed (0 means dismisses) : " + l2SubMenu);
		reportStatus((l2SubMenu == 0), "L2 category dropdown dismisses successfully", "L2 category dropdown not dismisses");

	}

	@Then("the app navigates to the corresponding Cat L1 page")
	public void the_app_navigates_to_the_corresponding_Cat_L1_page() {
		//reportInfo(getElementText(page.L1headertitle));
		waitForPageToLoad(3);
		int headerTitle = getListOfMatchingElements(page.L1headertitle).size();
		reportInfo("Header elements in L1 Page : " + headerTitle);
		reportStatus((headerTitle > 0), "The app navigates to corresponding L1 page", "Unable to navigates to corresponding L1 page");
	}

	@When("the user taps on a Cat L2 link")
	public void the_user_taps_on_a_Cat_L2_link() {
		mouseHover(L1Page.L1Menu);
		clickElement(L2Page.L2SubMenu);
	}


	@Given("^a Category highlight for (.+)$")
	public void a_Category_highlight_for(String arg1) throws Exception {
		// Assert.assertTrue(arg1+" is not displyaing on the L1
		// Page",page.iscatL2itemdisplayed(arg1).isDisplayed());
		// Assert.assertTrue(arg1+" is not displyaing on the L1
		// Page",page.iscatL2itemdisplayed(arg1));
		page.iscatL2itemdisplayed(arg1);
	}

	@When("^the user taps the SeeMore link for (.+) catl2$")
	public void the_user_taps_the_SeeMore_link_for_catl2(String arg1) {
		page.clickseemorelink(arg1);
	}
	
	//TRTEPAX-131
	@Given("a Category LOne page")
	public void a_Category_LOne_page() {
	    
		if (getListOfMatchingElements(CommonPage.btnHamburgerMenu).size() > 0) {
			clickElement(CommonPage.btnHamburgerMenu);
			reportInfo("Clicked Hamburger Menu");
			commonPage.waitForElementPresent(implicitWaitTimeout);
			clickElement(L2Page.L2CatrgoryName);
			reportInfo("Clicked L2 Category Name");
			if (isElementEnabled(L2Page.AlcoholSubMenu)) {
				clickElement(L2Page.AlcoholSubMenu);
				reportInfo("Clicked Sub Menu");
			} else {
				reportInfo("Sub Menu is not available to click");
			}
			commonPage.waitForElementPresent(implicitWaitTimeout);
			clickElement(L2Page.lblLOnePageName);
			reportInfo("Navigated to L1 Page");
		} else {
			clickElement(L1Page.L1MenuSelector);
			clickElement(L1Page.L1Menu);	
			reportInfo("Navigated to L1 Page");
		}
	}

	@When("CatLTwo is a Category LTwo within the current Category LOne")
	public void catltwo_is_a_Category_LTwo_within_the_current_Category_LOne() {
	    
		if (getListOfMatchingElements(CommonPage.btnHamburgerMenu).size() > 0) {
			commonPage.waitForElementPresent(implicitWaitTimeout);
			List<WebElement> listOfL2Names = getListOfMatchingElements(L1Page.lblL2NameInL1Page_Mobile);
			reportInfo("L2 Names in L1 Page : " + listOfL2Names.size());
			if(listOfL2Names.size() > 0) {
				reportInfo("Category L2 Names are available in Category L1 Page");
			} else {
				reportStatus(false, "Category L2 Names are available in Category L1 Page", "Category L2 Names are not available in Category L1 Page");
			}
		} else {
			List<WebElement> listOfL2Names = getListOfMatchingElements(L1Page.lblL2NameInL1Page);
			reportInfo("L2 Names in L1 Page : " + listOfL2Names.size());
			if(listOfL2Names.size() > 0) {
				reportInfo("Category L2 Names are available in Category L1 Page");
			} else {
				reportStatus(false, "Category L2 Names are available in Category L1 Page", "Category L2 Names are not available in Category L1 Page");
			}
		}
	}

	@Then("the page shall include a Category highlight for CatTwo")
	public void the_page_shall_include_a_Category_highlight_for_CatTwo() {
	    
		// Need to verify the L2 category names are in Bold
	}

	@Then("the ordering of the Category highlights is A-Z based on Category name")
	public void the_ordering_of_the_Category_highlights_is_A_Z_based_on_Category_name() {
	    		
		if (getListOfMatchingElements(CommonPage.btnHamburgerMenu).size() > 0) {
			List<String> listOfL2Names = getTextFromAllElements(L1Page.lblL2NameInL1Page_Mobile);
			for(String str: listOfL2Names) {
				reportInfo("L2 Names in L1 Page : " + str);			
			}		
			List<String> listOfL2NamesSorted = getTextFromAllElements(L1Page.lblL2NameInL1Page_Mobile);
			Collections.sort(listOfL2NamesSorted);
			for(String str: listOfL2NamesSorted) {
				reportInfo("Sorted L2 Names in L1 Page : " + str);			
			}
			reportStatus(listOfL2Names.equals(listOfL2NamesSorted), "Ordering of the L2 Category Names are Sorted by A to Z", "Ordering of the L2 Category Names are not Sorted by A to Z");
		} else {
			List<String> listOfL2Names = getTextFromAllElements(L1Page.lblL2NameInL1Page);
			for(String str: listOfL2Names) {
				reportInfo("L2 Names in L1 Page : " + str);			
			}		
			List<String> listOfL2NamesSorted = getTextFromAllElements(L1Page.lblL2NameInL1Page);
			Collections.sort(listOfL2NamesSorted);
			for(String str: listOfL2NamesSorted) {
				reportInfo("Sorted L2 Names in L1 Page : " + str);			
			}
			reportStatus(listOfL2Names.equals(listOfL2NamesSorted), "Ordering of the L2 Category Names are Sorted by A to Z", "Ordering of the L2 Category Names are not Sorted by A to Z");
		}
		
		
	}

	
	@Given("^a header menu in default state on a TOUCH device$")
	public void a_header_menu_in_default_state_on_a_TOUCH_device(){
		reportInfo("a header menu in default state on a TOUCH device");
	}
	
	@When("^the user taps L1Menuitem$")
	public void the_user_taps_L1Menuitem() {
		page.Mob_displayMenuitems();
	}
}
