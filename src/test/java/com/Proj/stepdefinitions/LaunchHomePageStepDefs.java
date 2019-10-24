package com.epax.stepdefinitions;

import com.epax.framework.base.DriverManager;
import com.epax.framework.base.ExecutionManager;
import com.epax.framework.base.WebDriverListener;
import com.epax.framework.cucumber.TestContext;
import com.epax.framework.utilities.WebDriverActions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import static com.epax.framework.utilities.PropertyReader.getPropertyData;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import com.epax.pages.CartPage;
import com.epax.pages.CommonPage;
import com.epax.pages.HeaderMenusPage;
import com.epax.pages.L2Page;

public class LaunchHomePageStepDefs extends WebDriverActions {

	TestContext testContext;
	CommonPage commonPage = new CommonPage();
	public LaunchHomePageStepDefs(TestContext context) {
		testContext = context;
	}
	static Logger log = Logger.getLogger(LaunchHomePageStepDefs.class);

	@Given("^I launch Epax Website$")
	public void i_launch_Epax_Website() throws Throwable {
		String environment = ExecutionManager.getTestEnvironment();
		String launchUrl = "https://" + environment + "-" + getPropertyData("baseUrl") + "/dashboard";
		launchUrl(launchUrl);
		//Check for Device
		if(getListOfMatchingElements(CommonPage.btnHamburgerMenu).size() > 0) {
			reportInfo("Device Screen Height - "+getSizeHeight().toString());
			reportInfo("Device Orientation - " + getDeviceOrientation());
			isMobile="Yes";
		}
	}

	@When("^the Epax welcome page is displayed$")
	public void the_Epax_welcome_page_is_displayed() throws Throwable {
		waitForElementVisibility(CommonPage.lblPageRoot);
		log.info("the Epax welcome page is displayed");
		assertThat("Element is visible!!!", isElementDisplayed(CommonPage.lblPageRoot), is(true));
	}

	@Given("^I am on (.+)$")
	public void givenIAmOn(String URL) {
		log.info("Given I'm on " + URL + "<br/>");
		launchUrl(URL);
	}

	@When("^I search for element (.+)$")
	public void whenISearchForElement(String element_id) {
		log.info("When I search for element " + element_id);
		waitForElementVisibility(By.id(element_id));
	}

	@Given("^a Side menu that is open$")
	public void a_Side_menu_that_is_open() throws Throwable {
		commonPage.waitForElementPresent(implicitWaitTimeout);
		if(getListOfMatchingElements(CommonPage.btnHamburgerMenu).size() > 0) {
			clickElement(CommonPage.btnHamburgerMenu);
			reportInfo("Side Menu is open");
		} else {
			reportStatus(isElementDisplayed(CommonPage.btnHamburgerMenu), "Side Menu is opened", "Side Menu is not opened");
		}		
	}

	// TRTEPAX-68 / TRTEPAX-147
	@Given("^Any page in the shop where the Side menu icon is present$")
	public void any_page_in_the_shop_where_the_Side_menu_icon_is_present() throws Throwable {
		boolean sideMuneIcon = isElementDisplayed(CommonPage.btnHamburgerMenu);
		reportStatus(sideMuneIcon, "Side Menu Icon is present", "Side Menu Icon is not present");
	}

	@When("^the user taps the Side menu icon$")
	public void the_user_taps_the_Side_menu_icon() throws Throwable {
		clickElement(CommonPage.btnHamburgerMenu);
		reportInfo("Clicked Side Menu Icon");
	}

	@Then("^the Side menu overlay shall appear$")
	public void the_Side_menu_overlay_shall_appear() throws Throwable {
		waitForElementPresent(implicitWaitTimeout);
		reportStatus(isElementDisplayed(L2Page.L2CatrgoryName), "Side Menu Overlay is appeared", "Side Menu Overlay is not appeared");		
	}

	@Then("^the Side menu is vertically scrollable if necessary to accommodate the viewport$")
	public void the_Side_menu_is_vertically_scrollable_if_necessary_to_accommodate_the_viewport() throws Throwable {

		reportInfo("List of Side Menu items are appearing at once on the viewport. So scrolling is not required");
	}

	@Then("^the Side menu shall dismiss$")
	public void the_Side_menu_shall_dismiss() throws Throwable {
		
		reportInfo("Side menu is dismissed");		
	}

	// TRTEPAX-17
	@Given("^a Category L2 page$")
	public void a_Category_L_page() throws Throwable {

	}

	@When("^the PAX scrolls to the bottom of the page$")
	public void the_PAX_scrolls_to_the_bottom_of_the_page() throws Throwable {
		scrollPageDown();	
	}

	// TRTEPAX-133
	@Given("^user verify HeaderMenu fields$")
	public void a_Header_menu_on_laptop_view() throws Throwable {
		if(isMobile()) {
			reportStatus(isElementEnabled(HeaderMenusPage.imgHomeLogo), "Image logo is displayed on header menu in Mobile", "Image logo is not displayed on header menu");
			reportStatus(isElementEnabled(HeaderMenusPage.btnShoppingCartIcon), "My Cart button is displayed on header menu", "My Cart button not is displayed on header menu");
			reportStatus(isElementEnabled(HeaderMenusPage.txtSearchBar), "SearchBar is displayed on the HomePage", "SearchBar is displayed on the HomePage");
			//hover on hamburgermenu to verify Deals,MyOrders and FAQ
			clickElement(CommonPage.btnHamburgerMenu);
			reportInfo("Clicked Hamburger Menu");
			commonPage.waitForElementPresent(implicitWaitTimeout);
			reportStatus(isElementEnabled(HeaderMenusPage.lblDeals), "Deals is displayed on the HomePage", "Deals is not displayed on the HomePage");
			reportStatus(isElementEnabled(HeaderMenusPage.btnMyOrder), "MyOrders Option is displayed on header menu", "MyOrders Option not is displayed on header menu");
			reportStatus(isElementEnabled(HeaderMenusPage.btnFaq), "FAQ Option is displayed on header menu", "FAQ Option is not displayed on header menu");
			if(isElementEnabled(CartPage.closeSideMenu)) {
				clickElement(CartPage.closeSideMenu);	
			}
			
		}else {
		//App Logo
		reportStatus(isElementEnabled(HeaderMenusPage.imgHomeLogo), "Image logo is displayed on header menu", "Image logo is not displayed on header menu");
		// FAQ
		reportStatus(isElementEnabled(HeaderMenusPage.btnFaq), "FAQ button is displayed on header menu", "FAQ button is not displayed on header menu");
		// My Orders
		reportStatus(isElementEnabled(HeaderMenusPage.btnMyOrder), "MyOrders button is displayed on header menu", "MyOrders button not is displayed on header menu");
		// My Cart
		reportStatus(isElementEnabled(HeaderMenusPage.btnShoppingCart), "My Cart button is displayed on header menu", "My Cart button not is displayed on header menu");
		//Retail Item
		reportStatus(isElementEnabled(HeaderMenusPage.HeadermenuBarlist1), "Catorory L1s is displayed on the HomePage", "Catorory L1s is not displayed on the HomePage");
		//Deals
		reportStatus(isElementEnabled(HeaderMenusPage.lblDeals), "Deals is displayed on the HomePage", "Deals is not displayed on the HomePage");
        //Search Bar
		reportStatus(isElementEnabled(HeaderMenusPage.txtSearchBar), "SearchBar is displayed on the HomePage", "SearchBar is displayed on the HomePage");
		}
	}

	@When("^the welcome page is displayed$")
	public void the_welcome_page_is_displayed() throws Throwable {
		//driver.findElement(By.xpath("//h2[text()='Welcome to OnBoard shopping']")).isDisplayed();
	}
	
	 @When("^user click on Retails button on HomePage$")
	public void user_click_on_Retails_button_on_HomePage() {
	    	clickElement(HeaderMenusPage.lblRetailItemsMenu);
	    	reportInfo("Clicked Retail Items");
			    	
	}
	 
	 @Given("this scenario covered in \"([^\"]*)\"")
	 public void this_scenario_covered_in(String arg) {
		 reportInfo("this scenario covered in "+ arg);
	 }
	    /**
		 * @Purpose To Navigate to required Page
		 * @Constraints
		 * @Input String Page navigation Ex: Retail Items-->Alcohol-->Johnnie Walker Black
		 * @Output boolean : Returns True if no exceptions  else false
		 */
	 @When("navigate to required Page for {string}")
	public void navigate_to_required_Page_for(String string) {
		waitForPageToLoad(elementTimeout);
		boolean res = true;
		String[] arrOfStr = string.split("-->");
		try {
			if (getListOfMatchingElements(CommonPage.btnHamburgerMenu).size() > 0) {
				clickElement(CommonPage.btnHamburgerMenu);
				for (int level = 0; level < arrOfStr.length-1; level++) {
					waitForPageToLoad(elementTimeout);
					clickMatchingelementByText(CommonPage.listMobileMenu,arrOfStr[level]);
				}
				
				if (arrOfStr.length == 3) { // to Navigate to Item Page
					commonPage.clickrequiredItemL2Page(arrOfStr[2]);
				}

			} else {
				// This should work for all laptop browsers and Tablets
					jsMouseOver(By.xpath(CommonPage.byText.replace(CommonPage.replaceText, arrOfStr[0])));
					jsClick(By.xpath(CommonPage.byText.replace(CommonPage.replaceText, arrOfStr[1])));
					//clickElement(By.xpath(CommonPage.byText.replace(CommonPage.replaceText, arrOfStr[1])));
					if (arrOfStr.length == 3) {
						commonPage.clickrequiredItemL2Page(arrOfStr[2]);

					}
					
				} 
			

		} catch (Exception e) {
			e.printStackTrace();
			res = false;
		}

		reportStatus(res, "Successfully Navigated to :" + string, "Failed to Navigate to Page :" + string);
	}

}

