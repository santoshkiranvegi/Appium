package com.epax.stepdefinitions;

import com.epax.framework.base.DriverManager;
import com.epax.framework.base.ExecutionManager;
import com.epax.framework.cucumber.TestContext;
import com.epax.framework.enums.Context;
import com.epax.framework.utilities.WebDriverActions;
import com.epax.pages.CommonPage;
import com.epax.pages.ItemPage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.epax.framework.utilities.PropertyReader.getPropertyData;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

public class CategoryL3ItemPageStepDefs extends WebDriverActions {

	TestContext testContext;
	// ProductListingPage productListingPage;

	public CategoryL3ItemPageStepDefs(TestContext context) {
		testContext = context;
		// productListingPage =
		// testContext.getPageObjectManager().getProductListingPage();
	}

	static Logger log = Logger.getLogger(CategoryL3ItemPageStepDefs.class);
	WebDriver driver = DriverManager.getDriver();
	WebElement webElement;
	WebDriverWait wait = new WebDriverWait(driver, 10);

	@Then("^The app transitions to the Item page for the Item displayed on the Item card$")
	public void the_app_transitions_to_the_item_page_for_the_item_displayed_on_the_item_card() throws Throwable {
		
		if(isElementDisplayed(CommonPage.txtItemDescription))
		{
		String itemDescription = getElementText(CommonPage.txtItemDescription);
		reportInfo("Taped on the item and navigates to l3 category page and item discription is "+itemDescription+"");
		}
		else
		{
			reportInfo("Taped item is not navigates to l3 category page");
		}
		
	}

	@Then("^I should see this element$")
	public void thenIShouldSeeThisElement() {
		log.info("Then I should see this element");
		assertThat("Element is not visible!!!", webElement.isDisplayed(), is(true));
	}	

	@When("^the user taps the \\[Description control\\] button$")
	public void the_user_taps_the_Description_control_button() throws Throwable {
				
		if (isElementEnabled(ItemPage.btnShowMoreDescription)) {
			clickElement(ItemPage.btnShowMoreDescription);
			reportInfo("Clicked Show More Decription control");
		} else if(isElementEnabled(ItemPage.btnShowLessDescription)) {
			clickElement(ItemPage.btnShowLessDescription);
			reportInfo("Clicked Show Less Decription control");
		} else {			
			reportStatus(false, "Description Control button is visible in the Item Page", "Description Control button is not visible in the Item Page");
		}		
	}

	@Then("^the page shall \\[Description behavior\\]$")
	public void the_page_shall_Description_behavior() throws Throwable {

		if (isElementEnabled(ItemPage.txtFullDescription)) {
			reportInfo("Full Description text after clicking Show More : " + getElementText(ItemPage.txtFullDescription));
		} else {
			reportStatus(false, "Full description section is visible in the Item Page", "Full description section is not visible in the Item Page");
		}
		
	}

	@Then("the default state on page load is Show More")
	public void the_default_state_on_page_load_is_Show_More() throws Throwable {

		if(isElementEnabled(ItemPage.btnShowMoreDescription)) {
			reportInfo("By default Show More description control is show on the Item Page");
		} else {
			reportStatus(false, "By default Show More description control is show on the Item Page", "By default Show More description control is not show on the Item Page");
		}
	}

	@When("^\\[R-Item\\] is an item that is configured as an Item Recommendation for \\[Current Item\\]$")

	public void r_Item_is_an_item_that_is_configured_as_an_Item_Recommendation_for_Current_Item() throws Throwable {

		log.info("It's not a part of user Action");

	}

	@Then("^the Item page shall contain a section for Recommendations$")

	public void the_Item_page_shall_contain_a_section_for_Recommendations() throws Throwable {

		// Verify for Recommended Products Section
		
		reportStatus(isElementDisplayed(CommonPage.lblRecommended), "Recommendation Section was Displayed successfully", "Failed to Display Recommended Section");

	}

	@Then("^the Recommendation section shall display an Item card for \\[R-Item\\]$")

	public void the_Recommendation_section_shall_display_an_Item_card_for_R_Item() throws Throwable {

		// verify Recommended Items are displayed
		
		reportStatus(getListOfMatchingElements(CommonPage.listRecommendedItems).size() >0, "Recommendation Section do have List of Items", "Recommendation Section doesn't have any Item present");
	}

	@When("^there are no Item Recommendations configured for \\[Current Item\\]$")

	public void there_are_no_Item_Recommendations_configured_for_Current_Item() throws Throwable {
		
		log.info("This is Not a part of User Action");
		
	}

	@Then("^the Item page shall not show a section for Recommendations$")

	public void the_Item_page_shall_not_show_a_section_for_Recommendations() throws Throwable {

		Assert.assertTrue(!(isElementDisplayed(CommonPage.lblRecommended)),
				"Recommended Product Section is not available");
		
		reportStatus(!(isElementDisplayed(CommonPage.lblRecommended)), "Recommended Product Section is not available", "Recommended Product Section is available");

	}

	@When("^\\[S-Item\\] is an item that is configured as an Item Substitution for \\[Current Item\\]$")

	public void s_Item_is_an_item_that_is_configured_as_an_Item_Substitution_for_Current_Item() throws Throwable {

		log.info("It's not a part of user Action");

	}

	// to be implemented

	@Then("^the Item page shall contain a section for Substitutions$")

	public void the_Item_page_shall_contain_a_section_for_Substitutions() throws Throwable {

		// Verify for Recommended Products Section

		reportStatus(isElementDisplayed(CommonPage.lblSubstitutions), "Substitution Section is Displayed ", "Failed:Substitution Section is not  present");
	}

	// to be implemented

	@Then("^the Substitution section shall display an Item card for \\[S-Item\\]$")

	public void the_Substitution_section_shall_display_an_Item_card_for_S_Item() throws Throwable {

		
		reportStatus(getListOfMatchingElements(CommonPage.listSubstitutionItems).size() >0, "Substitution Section do have List of Items", "Substitution Section doesn't have any Item present");
	}

	@When("^there are no Item Substitutions configured for \\[Current Item\\]$")

	public void there_are_no_Item_Substitutions_configured_for_Current_Item() throws Throwable {

		log.info("It's not a part of user Action");

	}


	@Then("^the Item page shall not show a section for Substitutions$")

	public void the_Item_page_shall_not_show_a_section_for_Substitutions() throws Throwable {
		
		
		reportStatus(!(isElementDisplayed(CommonPage.lblSubstitutions)),"Item page Doesn't show any Substitution Section", "Item Page Shows Substitution Section");

	}

	@Given("^an Item page$")
	public void anItemPage() {
		// Click on the Item image
		// Navigating to the Item Page which is having Show Less and Show More
		driver.get("https://qa-epax.bsgg.co.uk/product/6122");
	}

}
