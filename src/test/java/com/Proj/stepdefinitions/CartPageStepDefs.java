package com.epax.stepdefinitions;

import static org.testng.Assert.assertTrue;
import java.util.List;
import com.epax.framework.cucumber.TestContext;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.epax.framework.base.DriverManager;
import com.epax.framework.base.WebDriverListener;
import com.epax.framework.utilities.WebDriverActions;
import com.epax.pages.CartPage;
import com.epax.pages.CommonPage;
import com.epax.pages.HeaderMenusPage;
import com.epax.pages.L2Page;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CartPageStepDefs extends CommonPage {
	TestContext testContext;
	WebDriver driver = DriverManager.getDriver();
	static Logger log = Logger.getLogger(StepDefs.class);
	CommonPage commonPage = new CommonPage();
	CartPage cartPage = new CartPage();
	public CartPageStepDefs(TestContext context) {
		testContext = context;
	}

	int itemsCountAfterAdd;

	@Given("^a Cart Item element$")
	public void a_Cart_Item_element() throws Throwable {
		String sTotalItemsInCartBeforeAdd = getElementText(CommonPage.lblTotalItemsInCart);
		int itemsCountBeforeAdd = Integer.parseInt(sTotalItemsInCartBeforeAdd);
		reportInfo("Items in the Cart before Adding  the Items : " + itemsCountBeforeAdd);
		List<WebElement> activeAddToCartButton = getListOfMatchingElements(L2Page.itmActiveStock);
		if(activeAddToCartButton.size() > 0) {
			WebElement btnAddToCart = activeAddToCartButton.get(0);
			clickUsingJavaScript(btnAddToCart);
			reportInfo("Clicked Add To Cart button to add an Item to the cart");
		} else {
			reportInfo("Items with Stock or Low Stock are not available on the page");
		}
		Thread.sleep(2000);
		String sTotalItemsInCartAfterAdd = getElementText(CommonPage.lblTotalItemsInCart);
		itemsCountAfterAdd = Integer.parseInt(sTotalItemsInCartAfterAdd);
		reportInfo("Items in the Cart after Adding the Items : " + itemsCountAfterAdd);
	}

	// TRTEPAX-269
	boolean added;
	int iTotalQuantity;

	@When("^the user taps the \\[qty button\\] button$")
	public void the_user_taps_the_qty_button_button() throws Throwable {
		// Navigating to My Cart page
		if (getListOfMatchingElements(CommonPage.btnHamburgerMenu).size() > 0) {
			jsClick(HeaderMenusPage.btnShoppingCartIcon);
			reportInfo("Clicked My Cart Link");
			cartPage.selectSeatItemCart("12", "B");		
		} else {
			jsClick(HeaderMenusPage.btnShoppingCart);
			reportInfo("Clicked My Cart Link");
			Thread.sleep(2000);
			if (isElementDisplayed(CartPage.lblSeatSelection)) {
				cartPage.selectSeatItemCart("12", "B");
			} else {
				reportStatus(true, "Seat Selection window is not displayed", "Seat Selection windown is displayed");
			}
		}
		Thread.sleep(2000);
		String sTotalQuantity = getElementText(CartPage.lblItemQTY);
		iTotalQuantity = Integer.parseInt(sTotalQuantity);
		if (iTotalQuantity > 1) {
			jsClick(CommonPage.btnMinus);
			added = false;
		} else {
			jsClick(CommonPage.btnPlus);
			added = true;
		}
	}
	

	int iTotalQty;
	@Then("^the Cart Qty for that Item shall \\[qty change\\]$")
	public void the_Cart_Qty_for_that_Item_shall_qty_change() throws Throwable {
		String sTotalQuantity = getElementText(CartPage.lblItemQTY);
		iTotalQty = Integer.parseInt(sTotalQuantity);
		if (added) {
			if (iTotalQty > iTotalQuantity)
				reportInfo("Items before add : " + iTotalQuantity + " and Items after add : " + iTotalQty);
		} else {
			if (iTotalQty < iTotalQuantity)
				reportInfo("Items before remove : " + iTotalQuantity + " and Items after remove : " + iTotalQty);
		}
	}

	@Then("^the Cart Item element qty display shall show the updated qty$")
	public void the_Cart_Item_element_qty_display_shall_show_the_updated_qty() throws Throwable {
		if (iTotalQuantity != iTotalQty) {
			reportInfo("Quantity of the Items in the Cart are updated");
		} else {
			reportInfo("Quantity of the Items in the Cart are not updated");
		}
	}

	// TRTEPAX-270
	@When("^the user taps the remove item button$")
	public void the_user_taps_the_remove_item_button() throws Throwable {
		// Navigating to My Cart page
		if (getListOfMatchingElements(CommonPage.btnHamburgerMenu).size() > 0) {
			jsClick(HeaderMenusPage.btnShoppingCartIcon);
			cartPage.selectSeatItemCart("12", "B");
		} else {
			clickElement(HeaderMenusPage.btnShoppingCart);
			jsClick(HeaderMenusPage.btnShoppingCart);
			reportInfo("Clicked My Cart Link");
			Thread.sleep(2000);
			if (isElementDisplayed(CartPage.lblSeatSelection)) {
				cartPage.selectSeatItemCart("12", "B");
			} else {
				reportStatus(true, "Seat Selection window is not displayed", "Seat Selection windown is displayed");
			}
		}
		Thread.sleep(2000);
		String sTotalQuantity = getElementText(CartPage.lblItemQTY);
		reportInfo("Item Quantity : " + sTotalQuantity);
		iTotalQuantity = Integer.parseInt(sTotalQuantity);
		if (getListOfMatchingElements(CommonPage.btnHamburgerMenu).size() > 0) {
			jsClick(CommonPage.btnRemoveItem);
			reportInfo("Remove Item Button (X) is clicked to remove the item");
		} else {
			if (isElementDisplayed(CommonPage.btnRemoveItem)) {
				jsClick(CommonPage.btnRemoveItem);
				reportInfo("Remove Item Button (X) is clicked to remove the item");
			} else {
				reportInfo("Remove Item Button (X) is not available in the Cart Page");
			}
		}
	}

	@Then("^the Item shall be removed from the cart$")
	public void the_Item_shall_be_removed_from_the_cart() throws Throwable {
		if (getListOfMatchingElements(CommonPage.btnHamburgerMenu).size() > 0) {
			WebElement continueShopping = driver.findElement(CartPage.btnContinueShopping);
			if(continueShopping.isEnabled()) {
				reportInfo("Item is successfully removed from the cart");
			} else {
				reportInfo("Item is not removed from the cart");
			}
		} else {
			String bContinueShopping = getAttributeValue(CartPage.btnContinueShopping, "class");
			reportStatus(bContinueShopping.contains("ContinueShopping"), "Item is successfully removed from the cart",
					"Item is not removed from the cart");
		}
	}

	@Then("^expandable categories are visually identified$")
	public void expandable_categories_are_visually_identified() throws Throwable {
		
		List<WebElement> forwardArrowIcon = getListOfMatchingElements(CommonPage.lblForwardArrowIcon);
		if(forwardArrowIcon.size() > 0) {
			reportInfo("Expandable Categories are visually identified");
		} else {
			reportInfo("Expandable Categories are visually not identified");
		}
	}

	@When("^the user taps the X button$")
	public void the_user_taps_the_X_button() throws Throwable {
		
		boolean sideMenu = isElementEnabled(CartPage.closeSideMenu);
		if(sideMenu) {
			clickElement(CartPage.closeSideMenu);
			reportInfo("Taps the X button to close the Side Menu");
		} else {
			reportStatus(isElementEnabled(CartPage.closeSideMenu), "Taps the X button to close the Side Menu", "X button is not available to click and close the Side Menu");
		}
		
	}

	@Then("^the Side menu overlay shall dismiss$")
	public void the_Side_menu_overlay_shall_dismiss() throws Throwable {
		
		reportStatus(isElementEnabled(CommonPage.btnHamburgerMenu), "Side Menu overlay is dismissed", "Side Menu overlay is not dismissed");
	}

	@When("^the Item's Cart Qty is (\\d+)$")
	public void the_Item_s_Cart_Qty_is(int itemCount) throws Throwable {
		if (itemsCountAfterAdd == 0) {
			Assert.assertTrue(false,	"There are no items in the cart. So cant verify the Quantity button in My Cart window");
		}
	}

	@Then("^the Qty button is in inactive visual state$")
	public void the_Qty_button_is_in_inactive_visual_state() throws Throwable {
		if (getListOfMatchingElements(CommonPage.btnHamburgerMenu).size() > 0) {
			jsClick(HeaderMenusPage.btnShoppingCartIcon);
			cartPage.selectSeatItemCart("12", "B");
		} else {

			clickElement(HeaderMenusPage.btnShoppingCart);

			jsClick(HeaderMenusPage.btnShoppingCart);

			reportInfo("Clicked My Cart Link");
			Thread.sleep(2000);
			if (isElementDisplayed(CartPage.lblSeatSelection)) {
				cartPage.selectSeatItemCart("12", "B");
			} else {
				reportStatus(true, "Seat Selection window is not displayed", "Seat Selection windown is displayed");
			}
		}

		Thread.sleep(2000);
		if (itemsCountAfterAdd > 1) {
			reportInfo("There are more than one item in the cart. So cant verify the inactive visual state of the Qty button");
		} else {
			Assert.assertFalse(isElementEnabled(CommonPage.btnMinus), "The Qty button is active status");
		}
	}

	@Then("^the Qty button is functionally inactive$")
	public void the_Qty_button_is_functionally_inactive() throws Throwable {

		String sItemsQTYInCart = getElementText(CartPage.lblItemQTY);
		int iItemsQTYInCart = Integer.parseInt(sItemsQTYInCart);
		//clickElement(CommonPage.btnMinus);
		jsClick(CommonPage.btnMinus);
		String sbItemsQTYInCart = getElementText(CartPage.lblItemQTY);
		int ibItemsQTYInCart = Integer.parseInt(sbItemsQTYInCart);
		if (iItemsQTYInCart == ibItemsQTYInCart) {
			reportInfo("The QTY button is funcationally inactive");
		} else {
			reportStatus(false, "The Qty button is functionally inactive", "The QTY button is funcationally active");
		}
	}

	// TRTEPAX-67
	@When("^the user taps outside of the Mini Cart overlay$")
	public void the_user_taps_outside_of_the_Mini_Cart_overlay() throws Throwable {
		reportStatus(clickElement(HeaderMenusPage.imgHomeLogo), "Successfully clicked outside Mini Cart layout ",
				"Failed to click outside Mini Cart layout");
		reportStatus(clickElement(HeaderMenusPage.imgHomeLogo), "Successfully clicked outside Mini Cart layout ", "Failed to click outside Mini Cart layout");
		//reportStatus(jsClick(HeaderMenusPage.imgHomePage), "Successfully clicked outside Mini Cart layout ", "Failed to click outside Mini Cart layout");
		

	}

	@Given("^an onboard shop where the Cart icon is visible$")
	public void an_onboard_shop_where_the_Cart_icon_is_visible() throws Throwable {
		// Desktop
		if (!isMobile()) {
			reportStatus(isElementDisplayed(CommonPage.btnShoppingCart), "My Cart is visible on the Onboard Shop",
					"My Cart is not available on the Onboard Shop");
		} else if(isMobile()){
			// Get the Environment and place the below code as specified
			// Mobile
			if (isElementEnabled(CommonPage.btnShoppingCart_mob1)) {
				log.info("My Cart Icon is visible on the Onboard Shop");
			} else {
				log.warn("My Cart Icon is not available on the Onboard Shop");
			}
		}

	}

	@When("^the user taps the Cart icon$")
	public void the_user_taps_the_Cart_icon() throws Throwable {
		if(isMobile()) {
			clickElement(CommonPage.btnShoppingCart_mob1);
		}else {
			reportStatus(clickElement(CommonPage.btnShoppingCart), "Successfully Clicked on CartIcon",
					"Failed to Click on cart Selection");
		}
	}

	@Then("^the Mini Cart overlay shall slide in from the right$")
	public void the_Mini_Cart_overlay_shall_slide_in_from_the_right() throws Throwable {
		waitForElementPresent(elementTimeout);
		reportStatus(isElementEnabled(cartPage.btnCloseShoppingCart), "Mini Cart Overlay slide is displayed",
				"Mini Cart Overlay slide is not displayed");
	}

	@Then("^the Mini Cart overlay shall persist for further user interaction$")
	public void the_Mini_Cart_overlay_shall_persist_for_further_user_interaction() throws Throwable {
		waitForElementPresent(elementTimeout);
		reportStatus(isElementDisplayed(cartPage.btnCloseShoppingCart), "Mini Cart Overlay slide is displayed",
				"Mini Cart Overlay slide is not displayed");
		log.info("Mini Cart overlay shall persist for further user action");
	}

	@Given("^the Mini Cart contains an Item$")
	public void the_Mini_Cart_contains_an_Item() throws Throwable {
		if (isElementEnabled(CartPage.lblSeatSelection)) {
			cartPage.selectSeatItemCart("12", "A");
		}
		//assertTrue(isElementEnabled(CartPage.lblItemInMiniCart), "Item(s) in the Mini Cart are not displayed");
		int lblItemInMiniCart = getListOfMatchingElements(CartPage.lblItemInMiniCart).size();
		reportInfo("Items in Mini Cart : " + lblItemInMiniCart);
		reportStatus((lblItemInMiniCart > 0), "Item(s) in the Mini Cart are displayed",
				"Item(s) in the Mini Cart are not displayed");

	}

	@Then("^the Mini Cart shall display a Cart Item element for the corresponding Item$")
	public void the_Mini_Cart_shall_display_a_Cart_Item_element_for_the_corresponding_Item() throws Throwable {
		// to be implemented with respective data
		// String data = "";
		// if(isElementDisplayed(By.xpath(CommonPage.lblh3Item.replace(CommonPage.replaceText,
		// data)),driver))
		log.info("To be Implemented Later with respective Test Data");
	}

	@When("^the Mini Cart contains zero items$")
	public void the_Mini_Cart_contains_zero_items() throws Throwable {

		// For Desktop
		// if (WebDriverListener.OSName.equalsIgnoreCase("windows")||
		// WebDriverListener.OSName.equalsIgnoreCase("mac")) {
		/*if (isElementEnabled(CartPage.lblSeatSelection)) {
			cartPage.selectSeatItemCart("12", "A");
		}

		if (isElementEnabled(CartPage.btndeleteItem)) {

			reportStatus(cartPage.deleteItemsInCart(), "Successfully verified that Cart Contains Zero Items",
					"Failed to verify Cart contains Zero Items");

		} else {
			reportInfo("Successfully verified that Cart Contains Zero Items");
		}*/

		/*
		 * if (isElementDisplayed(CommonPage.imgItemsInCart, driver)) {
		 * log.warn("Items in the Mini Cart are available"); } else {
		 * log.info("Mini Cart contains Zero Items"); }
		 */

		// }

			cartPage.selectSeatItemCart("12", "A");
			int btnDeleteItem = getListOfMatchingElements(CartPage.btndeleteItem).size();
			reportInfo("Item in cart : " + btnDeleteItem);
			if (btnDeleteItem > 0) {
				reportStatus(cartPage.deleteItemsInCart(), "Successfully verified that Cart Contains Zero Items", "Failed to verify Cart contains Zero Items");
			} else {
				reportInfo("Successfully verified that Cart Contains Zero Items");
			}

	}

	@Then("^the Mini Cart shows an empty state icon$")
	public void the_Mini_Cart_shows_an_empty_state_icon() throws Throwable {
		//assertTrue(isElementEnabled(CommonPage.imgEmptyMiniCart), "Mini Cart is not showing an empty state icon");
		waitForPageToLoad(6);
		int imgEmptyMinicartCont = getListOfMatchingElements(CommonPage.imgEmptyMiniCart).size();
		reportInfo("Empty Image : " + imgEmptyMinicartCont);
		reportStatus((imgEmptyMinicartCont > 0), "Mini Cart shows an empty state icon",
				"Mini Cart doesn't show  an empty state icon");
	}

	@Then("^the Mini Cart shows an empty state message$")
	public void the_Mini_Cart_shows_an_empty_state_message() throws Throwable {
		assertTrue(isElementEnabled(CommonPage.txtEmptyMiniCart), "Mini Cart is not showing an empty state Message");
		reportStatus(isElementEnabled(CommonPage.txtEmptyMiniCart), "Mini Cart is not showing an empty state Message",
				"Mini Cart is showing an empty state Message");
	}

	@Then("^the Mini Cart footer shows only a Continue Shopping button$")
	public void the_Mini_Cart_footer_shows_only_a_Continue_Shopping_button() throws Throwable {

		if (isElementEnabled(CommonPage.btnContinueShopping)) {
			log.info("Continue Shopping Button is shown in the Empty Mini Cart Footer");
			reportInfo("Continue Shopping Button is shown in the Empty Mini Cart Footer");
		} else {
			log.warn("Continue Shopping Button is not shown in the Empty Mini Cart Footer");
			reportInfo("Continue Shopping Button is not shown in the Empty Mini Cart Footer");
		}
	}

	

	// ----------------------------------Cart Actions
	@Given("^a Mini Cart that is open$")
	@When("the Mini Cart is displayed")
	public void a_mini_cart_that_is_open() throws Throwable {
		if (isMobile()) {
			reportStatus(isElementDisplayed(CommonPage.btnShoppingCart_mob1),
					"My Cart Button is displayed  successfully", "My Cart Button is not displayed");
			//reportStatus(jsClick(CommonPage.btnShoppingCart_mob1), "My Cart Button is clicked successfully","Failed to click on My cart");
			reportStatus(clickMatchingelementByText(CommonPage.btnShoppingCart_mob1,"shopping_cart"), "My Cart Button is clicked successfully","Failed to click on My cart");
		} else {
			//reportStatus(isElementDisplayed(CommonPage.btnShoppingCart), "My Cart Button is displayed  successfully",
				//	"My Cart Button is not displayed");
			reportStatus(jsClick(CommonPage.btnShoppingCart), "My Cart Button is clicked successfully",
					"Failed to click on My cart");
		}

	}

	@When("^the user taps the close button$")
	public void the_user_taps_the_close_button() throws Throwable {
			reportStatus(jsClick(cartPage.btnCloseShoppingCart), "Successfully closed Mini Cart", "failed to close Mini Cart");
	}

	@Then("^the Mini Cart overlay shall dismiss$")
	public void the_mini_cart_overlay_shall_dismiss() throws Throwable {
		Thread.sleep(3000);
		reportStatus(!isElementDisplayed(cartPage.btnCloseShoppingCart), "Successfully verified mini cart is closed",
				"Failed to Verify Menu cart is closed");

	}

	@When("^the Submit Order button is active$")
	public void the_Submit_Order_button_is_active() {
		
		cartPage.selectSeatItemCart("12", "A");
		assertTrue(isElementEnabled(CartPage.btnSubmitOrder), "User didnt found Submit Button on Mycart");
	}

	@When("^the user taps the Submit Order button$")
	public void the_user_taps_the_Submit_Order_button() {
				
		clickElement(CartPage.btnSubmitOrder);
		reportInfo("Clicked Submit Order Button");
	}

	@Then("^the Submit Order button displays a pending visual state \"([^\"]*)\"$")
	public void the_Submit_Order_button_displays_a_pending_visual_state_e_g_spinner_or_similar(String string) {
		// Cannot be automate as the text on the button is changing in seconds
		reportInfo("Spinner or similar is displayed after Submit Order button is clicked");
	}

	@Then("^the Submit Order button is in an inactive state cannot be tapped$")
	public void the_Submit_Order_button_is_in_an_inactive_state_cannot_be_tapped() {
		// Cant find the inactive Submit Order button
	}

	@Then("^the Cart submits the order to the Fulfillment Process$")
	public void the_Cart_submits_the_order_to_the_Fulfillment_Process() {
		
		reportInfo("Cart submits the order to the Fulfillment Process");
	}

	@Then("^the Cart awaits response from the Fulfillment Process$")
	public void the_Cart_awaits_response_from_the_Fulfillment_Process() {
		reportInfo("Cart awaits response from the Fulfillment Process");

	}
	

	//TRTEPAX-384 -> 42
	@Given("a Mini Cart where the user has tapped the Submit Order button")
	public void a_Mini_Cart_where_the_user_has_tapped_the_Submit_Order_button() throws InterruptedException {
		
		cartPage.addItemsToCartAndNavigateToCart();		
		Thread.sleep(2000);
		clickElement(CartPage.btnSubmitOrder);
	}

	@When("the inventory check returns as adjustment is needed for Zero items")
	public void the_inventory_check_returns_as_adjustment_is_needed_for_Zero_items() {
	    
		reportInfo("");
	}

	@Then("the Submit Order button continues to display a pending visual state")
	public void the_Submit_Order_button_continues_to_display_a_pending_visual_state() {
	    
		reportInfo("Submit order button displays pending visual state");
	}

	@Then("the Submit Order button remains in an inactive state")
	public void the_Submit_Order_button_remains_in_an_inactive_state() {
		// Currently Submit Order is not in inactive state but Submit Order button need to be 
		//replaced with View Order and Continue Shopping buttons
		reportInfo("Submit Order button remains in an inactive state");
		
	}

	@Then("the Cart submits the order to the Airside Server")
	public void the_Cart_submits_the_order_to_the_Airside_Server() {
		//Need to verify after the functionality is implemented in the application
	    reportInfo("Cart submits the order to the Airside Server");
	}

	@Then("the Cart awaits an acknowledgement from the Fulfillment Process")
	public void the_Cart_awaits_an_acknowledgement_from_the_Fulfillment_Process() {
		//Need to verify after the functionality is implemented in the application
	    reportInfo("Cart awaits an acknowledgement from the Fulfillment Process");
	}
	
	//Scenario: scenario3-Submit Order acknowledged
	@Given("a Mini Cart in the Submit Order flow and the order submission is awaiting acknowledgement from the Fulfillment Process")
	public void a_Mini_Cart_in_the_Submit_Order_flow_and_the_order_submission_is_awaiting_acknowledgement_from_the_Fulfillment_Process() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("the Fulfillment Process acknowledges receipt of the order to the order")
	public void the_Fulfillment_Process_acknowledges_receipt_of_the_order_to_the_order() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the Mini Cart displays a success notification to the user")
	public void the_Mini_Cart_displays_a_success_notification_to_the_user() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the Submit Order button is replaced by a View Order button and a Continue Shopping button")
	public void the_Submit_Order_button_is_replaced_by_a_View_Order_button_and_a_Continue_Shopping_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	//Scenario: Scenario 8: Exiting Mini Cart while in Submit flow
	@Given("a Mini Cart in a Submit Order flow")
	public void a_Mini_Cart_in_a_Submit_Order_flow() throws InterruptedException {
		
		cartPage.addItemsToCartAndNavigateToCart();
	    
	}

	@When("the user uses any control to dismiss the Mini Cart")
	public void the_user_uses_any_control_to_dismiss_the_Mini_Cart() {
	    
		clickElement(CartPage.closeMiniCart);
		reportInfo("Clicked (X) to close the Mini Cart");
	}

	@Then("^the Cart finishes the current action$")
	public void the_Cart_finishes_the_current_action() {
		
		reportInfo("The Cart finished the current action");
	}

	@Then("^the Mini Cart view will not persist any state of the Submit flow$")
	public void the_Mini_Cart_view_will_not_persist_any_state_of_the_Submit_flow() {
	    
		reportInfo("As Mini Cart is closed, it will not persist any state of the Submit flow");
	}
	@Given("^that the user has not yet entered a seat number during the current sector$")
	public void that_the_user_has_not_yet_entered_a_seat_number_during_the_current_sector() {
		log.info("User seleted Item to order but not yet selected seat number");
	}

	@Then("^the Your Seat section is displayed as expanded$")
	public void the_Your_Seat_section_is_displayed_as_expanded() {
		cartPage.verifyTextincart("SEAT SELECTION");
		cartPage.verifyTextincart("Please select your seat to continue.");		
	}

	@Then("^the SEAT SELECTION is displayed$")
	@Given("a Seat Selector that is open")
	public void the_SEAT_SELECTION_is_displayed() {
		cartPage.verifyTextincart("SEAT SELECTION");
	}

	@Then("^the input focus is on the Seat Row field$")
	public void the_input_focus_is_on_the_Seat_Row_field() throws Exception {
		Thread.sleep(2000);	
			getActiveElement().sendKeys("10");
			System.out.println(getElement(cartPage.inpSeatRownum).getAttribute("value").toString() + " value");
			Thread.sleep(2000);
			reportStatus(getElement(cartPage.inpSeatRownum).getAttribute("value").contentEquals("10"), "user able to see focus is on SeatRow", "failed to focus on SeatRow");
	}

	@When("^the Mini Cart is not visible$")
	public void the_Mini_Cart_is_not_visible() throws Exception {
		clickElement(CartPage.btnXcloseseatselect);
		Thread.sleep(2000);
		reportStatus(isElementDisplayed(HeaderMenusPage.imgHomeLogo), "Mini Cart is not visible", "Cart is visible");
	}

	@When("^the Mini Cart is not accessible to the user$")
	public void the_Mini_Cart_is_not_accessible_to_the_user() {
		log.info("the Mini Cart is not accessible to the user");
	}
	
	@When("^the user enters a valid Seat Number and Seat Letter$")
	public void the_user_enters_a_valid_Seat_Number_and_Seat_Letter() throws Throwable {
		cartPage.setSeatinfo("8","E");
		reportStatus(returnelement(CartPage.inpSeatRownum).getAttribute("value").contains("8"), "User successfully entered seat number", "failed to enter Seat number");
	}
	
	@When("^the user enters Seat Number \"([^\"]*)\" and Seat Letter \"([^\"]*)\"$")
	public void the_user_enters_Seat_Number_and_Seat_Letter(String seatnum,String seatAlpha) throws Throwable {
		cartPage.setSeatinfo(seatnum,seatAlpha);
		String seatinfo=returnelement(CartPage.inpSeatRownum).getAttribute("value");
		System.out.println(seatinfo);
		reportStatus(seatinfo.contentEquals(seatnum), "User successfully entered seat number", "failed to enter Seat number");
	}
	
	@Then("^the Confirm Seat button becomes active$")
	public void the_Confirm_Seat_button_becomes_active() {
		reportStatus(isElementEnabled(CartPage.btnConfirmSeatNumber), "Confirm Seat Number is Active", "Confirm Seat number is inactive");
	}
	
	@Then("^the Seat Selector dismisses$")
	public void the_Seat_Selector_dismisses() {
		waitForElementPresent(implicitWaitTimeout);
		reportStatus(!(isElementDisplayed(cartPage.lblSeatSelection)), "Seat Selection through Mini Cart dismissed", "User still able to see Seat Selector");
	}

	@Then("^any user inputs are discarded$")
	public void any_user_inputs_are_discarded() {
	    reportInfo("User inputs discarded as minicart closed");
	}

	@Then("^the app returns to the last known state$")
	public void the_app_returns_to_the_last_known_state() {
	    reportStatus(isElementDisplayed(HeaderMenusPage.imgHomeLogo), "User successfully return to HomePage", "User failed to return to HomePage");
	}
	
	@Given("^a Seat Selector where the Confirm Seat button is active$")
	public void a_Seat_Selector_where_the_Confirm_Seat_button_is_active() throws Throwable {
		the_user_enters_a_valid_Seat_Number_and_Seat_Letter();
		the_Confirm_Seat_button_becomes_active();
		
	}

	@When("^the user taps the Confirm Seat button$")
	public void the_user_taps_the_Confirm_Seat_button() {
		if(isElementEnabled(cartPage.btnConfirmSeatNumber)) {
			jsClick(cartPage.btnConfirmSeatNumber);
			waitForElementPresent(implicitWaitTimeout);
			reportInfo("User clicked on Confirm Seat button");
		}
	}

	@Then("^the shop stores the seat number for the current sector$")
	public void the_shop_stores_the_seat_number_for_the_current_sector() {
		System.out.println(getElementText(cartPage.txtStoredSeatno));
		reportStatus(getElementText(cartPage.txtStoredSeatno).contains("SEAT 8 E"), "User able to see Seat Number is saved in Cart", "User failed to save the seat Number");
	}

	@Then("^the Mini Cart is shown$")
	public void the_Mini_Cart_is_shown() {
		reportStatus(getElementText(cartPage.txtemptycart).contains("Your cart seems to be empty"), 
				"User able to saw Mini cart as Empty", "User failed to see cart");
	}
	
	@Given("^a Mini Cart in default state$")
	public void a_Mini_Cart_in_default_state() throws Throwable {
		the_user_enters_a_valid_Seat_Number_and_Seat_Letter();
		the_user_taps_the_Confirm_Seat_button();
		the_shop_stores_the_seat_number_for_the_current_sector();
	}

	@When("^the user taps the edit seat number button$")
	public void the_user_taps_the_edit_seat_number_button() throws Throwable {
		if(isElementEnabled(cartPage.lbleditseat)) {
			jsClick(cartPage.lbleditseat);
			Thread.sleep(2000);
		}else
			log.info("User unable to see Edit button is not displayed");
	}

	@Then("^the Seat Selector appears$")
	public void the_Seat_Selector_appears() {
		the_Your_Seat_section_is_displayed_as_expanded();
		
	}

	@Then("^the currently stored seat number is shown in the input fields$")
	public void the_currently_stored_seat_number_is_shown_in_the_input_fields() {
		//String StoredSeatnumber=cartPage.getSeatinfo().substring(5);
		String StoredSeatnumber=CartPage.Seatseletedinfo;
		System.out.println(StoredSeatnumber + " is stored seat value");
		String currentseatnumber=getElement(cartPage.inpSeatRownum).getAttribute("value")+" "+getElement(CartPage.inpColAlphabet).getAttribute("value");
		reportStatus(StoredSeatnumber.contentEquals(currentseatnumber), 
				"Stored seat number is displaying in Edited CartPage", "Seat numbers are matching");
	}
	
	@Then("^the visual hint under the Sear field Bar color should be \"([^\"]*)\"$")
	public void the_visual_hint_under_the_field_Bar_color_should_be(String Expelecolor) {
		String bordercolorseatnum=getelementcolor(CartPage.inpSeatRownum,"border-bottom-color");
		reportInfo("Seat Border line coloer : " + bordercolorseatnum);
		reportStatus(bordercolorseatnum.equalsIgnoreCase(Expelecolor), 
				Expelecolor+ " color is displayed for boarderline under seatnum", Expelecolor + " color is not displayed for boarderline under seat");
		String bordercolorseatAlpha=getelementcolor(CartPage.inpColAlphabet,"border-bottom-color");
		reportStatus(bordercolorseatAlpha.equalsIgnoreCase(Expelecolor), 
				Expelecolor+ " color is displayed for boarderline under seatalpha", Expelecolor + " color is not displayed for boarderline under seat");
		
	}
	
	@And("^the input text color for seat should be \"([^\"]*)\"$")
	public void the_input_text_color_for_seat_should_be(String expelementcolor) {
			String actualseatnumcolor=null,actualseatAlphacolor=null;
			if(!(expelementcolor.equalsIgnoreCase("grey"))) {
			 actualseatnumcolor=getelementcolor(CartPage.inpSeatRownum,"color");
			 actualseatAlphacolor=getelementcolor(CartPage.inpColAlphabet,"color");
			}else {
				if(WebDriverListener.browserName.equalsIgnoreCase("Firefox")) {
					actualseatnumcolor=jsGetPlaceHolderTextColor(CartPage.inpSeatRownum);
					actualseatAlphacolor=jsGetPlaceHolderTextColor(CartPage.inpColAlphabet);
				}else  
					{
					//actualseatnumcolor=getelementcolor(CartPage.inpSeatRownum,"-webkit-tap-highlight-color");
					actualseatnumcolor=getelementcolor(CartPage.inpSeatRownum,"border-bottom-color");
					actualseatAlphacolor=getelementcolor(CartPage.inpColAlphabet,"border-bottom-color");
					 //actualseatnumcolor=jsGetPlaceHolderTextColor(CartPage.inpSeatRownum);
						//actualseatAlphacolor=jsGetPlaceHolderTextColor(CartPage.inpColAlphabet);
				} 
			}
			reportStatus(actualseatnumcolor.equalsIgnoreCase(expelementcolor), 
					expelementcolor+ " color is displayed for Seatnumber", expelementcolor + " color is not displayed for Seatnumber");
			reportStatus(actualseatAlphacolor.equalsIgnoreCase(expelementcolor), 
					expelementcolor+ " color is displayed for SeatAlhacolumn", expelementcolor + " color is not displayed for SeatAlhacolumn");
				
	}
	
	
}
