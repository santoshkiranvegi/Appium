package com.epax.pages;

import java.util.List;
import static com.epax.framework.utilities.PropertyReader.getPropertyData;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epax.framework.base.DriverManager;
import com.epax.framework.base.ExecutionManager;
import com.epax.framework.utilities.WebDriverActions;
import com.epax.stepdefinitions.StepDefs;

public class CartPage extends WebDriverActions{
	//--------Cart Elements----------

    public static By btnShoppingCart=By.xpath("//label[contains(@class,'FloatingMenu_NavItem') and contains(text(),'My Cart')]");
    public static By btnCloseShoppingCart=By.xpath("//*[contains(@class,'material-icons') and contains(text(),'close')]");
    public static By btnRemoveItem = By.xpath("//div[@class='CartItem_firstRow__3T0S6']/i[contains(text(),'close')]");
    public static By lblItemName = By.xpath("//div[@class='CartItem_firstRow__3T0S6']/i[contains(text(),'close')]/preceding-sibling::h3");
    public static By lblDescription = By.xpath("//strong[contains(text(),'Description')]");
    public static By lblItemQuantity = By.xpath("//*[contains(@class,'CartItem_orderAmount__1MUPK')]");
    public static By lblItemQTY = By.cssSelector("span[class^='CartItem_orderQuantity']");
    public static By lblSubTotal = By.xpath("//*[@class='CartItem_itemPrice__1hpbR']");
    public static By imgItemsInCart = By.xpath("//span[@class='CartItem_imageBackground__1uuRR']");
    public static By imgEmptyMiniCart = By.xpath("//div[@class='CartList_emptyCart__3qs83']");
    public static By txtEmptyMiniCart = By.xpath("//span[contains(text(),'Your cart seems to be empty')]");
    public static By btnContinueShopping = By.cssSelector("button[class*='CartList_ContinueShopping']");
    public static By itmItemName = By.cssSelector("div[class^='CartItem_firstRowText']");
    public static By txtSearchTextBox = By.xpath("//input[@class='Search_searchBar__1OIh1']");
    public static By totalNumberOfItems = By.xpath("//div[@class='CategoryView_itemContainer__3_cLx']/div");
    public static By btnSubmitOrder = By.cssSelector("button[class^='CartList_Checkout']"); 
    public static By closeMiniCart = By.cssSelector("div[class*=ShoppingCart_CartHeader] div i[style^='color']");
    //public static By closeSideMenu = By.cssSelector("div[class^='FlyOutMenu_MenuHeader'] div i");
    public static By closeSideMenu = By.xpath("//i[contains(.,'close')]");
  
//	By.cssSelector("button.:contains("Submit Order")");



    //---------Cart Elements----------
   
    //Mobile My Cart
    public static By lblItemInMiniCart 		= By.xpath("//div[contains(@class,'CartItem_firstRow')]/h3");
    public static By lblSeatSelection  		= By.cssSelector("div[class*='SeatSelection_CartHeader'] h3");
    public static By lblSeatSelectioncontent=By.cssSelector("div[class*='SeatSelection_content']");
    public static By lblSeatSelected   		= By.cssSelector("div[class*='ShoppingCart_CartHeader'] h3[class*='ShoppingCart_cartTitle']");
    public static By txtSeatRowNum			=By.xpath("//input[@name='seatRow']");
    public static By txtSeatColumnName		=By.xpath("//input[@name='seatColumn']");
    public static By inpSeatRownum        	= By.xpath("//input[@name='seatRow']");
    public static By inpColAlphabet  		= By.xpath("//input[@name='seatColumn']");
    public static By btnConfirmSeatNumber 	= By.xpath("//button[text()='Confirm Seat Number']");
    public static By btnXcloseseatselect	= By.cssSelector("div[class*=SeatSelection_closeContainer] i"); 		
    public static By btndeleteItem        	= By.xpath("//button[contains(@class,'CartItem_removeButton')]/i");
    public static By boxSearchTextBox       = By.cssSelector("#addInput");
    //public static String debugorders		="https://qa-epax.bsgg.co.uk/debugorders";//delete
    public static By txtStoredSeatno     	= By.cssSelector("h3[class*=ShoppingCart_cartTitle]");
    public static By txtemptycart 			= By.cssSelector("div[class*=CartList_emptyCart] span");
    public static By lbleditseat			= By.cssSelector("div[class*=ShoppingCart_CartHeader] h3 i");
    public static String Seatseletedinfo =null;

    WebDriver driver = DriverManager.getDriver();
	static Logger log = Logger.getLogger(StepDefs.class);
	CommonPage commonPage = new CommonPage();

    // Seat Selection
    
    /**
     * @Purpose To Select Seat Item cart
     * @Constraints
     * @Input String Seat Number & String column Alphabet
     * @Output boolean : Returns True if success else false
     */
    
    public boolean selectSeatItemCart(String seatnum,String columnalphabet) {
    	boolean res = false;
		 if(isElementEnabled(CartPage.lblSeatSelection)){
		  try {
			Thread.sleep(2000);
			jsSetTextAndTriggerBubbleEvent(CartPage.txtSeatRowNum,seatnum);
			reportInfo("Entered Seat Number");
			jsSetTextAndTriggerBubbleEvent(CartPage.txtSeatColumnName,columnalphabet);
    		reportInfo("Entered Seat Alphabet");
    		if(isElementEnabled(btnConfirmSeatNumber)) {
    			clickElement(btnConfirmSeatNumber);
    			reportInfo("Clicked Confirm Seat Number");
    		}
    		Thread.sleep(4000);
    		res = true;
		  	} catch (Exception e) {
			// TODO: handle exception
			log.info("Exception Caught in Seat Selection:" + e);
		   }
		 }else if(isElementDisplayed(CartPage.lblSeatSelected)) { 
			log.info("Seat Already Selected in Cart");
			res=true;
		}
    	return res;
    }
     
    /**
     * @Purpose To Delete Items in Cart
     * @Constraints to delete all items in cart later can be updated to delete specific item
     * @Input NA
     * @Output boolean : Returns True if success else false
     */
    
	public boolean deleteItemsInCart() {
		boolean res = false;
		try {
			List<WebElement> webEles = driver.findElements(btndeleteItem);
			for (int elecount = 0; elecount <webEles.size(); elecount++) {
				//webEles.get(elecount).click();
				clickUsingJavaScript(webEles.get(elecount));
				//driver.wait(20000);
				Thread.sleep(4000);
			}
			res = true;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Exception to delete Items in Cart:" + e);
		}

		return res;
	}
    
	public void verifysubmitteditemsfromdebug() throws Throwable {
		String environment = ExecutionManager.getTestEnvironment();
		String launchdebugordersUrl = "https://" + environment + "-" + getPropertyData("baseUrl") + getPropertyData("debugorders");
		driver.get(launchdebugordersUrl);
		driver.navigate().refresh();
		//getlatest date with seat number selected and product ids
		
	}
	

	public void addItemsToCartAndNavigateToCart() throws InterruptedException {
		// Adding the Item to the cart
		List<WebElement> activeAddToCartButton = getListOfMatchingElements(L2Page.itmActiveStock);
		if(activeAddToCartButton.size() > 0) {			
			WebElement btnAddToCart = activeAddToCartButton.get(0);
			//btnAddToCart.click();
			clickUsingJavaScript(btnAddToCart);
			reportInfo("Clicked Add To Cart button to add an Item");
			Thread.sleep(2000);			
		} else {
			reportInfo("Items with Stock or Low Stock are not availabe on the page");
		}
	    
		//Entering Seat number and alphabet
		
		if (getListOfMatchingElements(CommonPage.btnHamburgerMenu).size() > 0) {
			clickElement(HeaderMenusPage.btnShoppingCartIcon);
			selectSeatItemCart("12", "B");
		} else {
			jsClick(HeaderMenusPage.btnShoppingCart);
			reportInfo("Clicked My Cart Link");
			Thread.sleep(2000);
			if (isElementEnabled(CartPage.lblSeatSelection)) {
				selectSeatItemCart("12", "B");
			} else {
				reportStatus(true, "Seat Selection window is not displayed", "Seat Selection windown is displayed");
			}
		}
	}

	public void verifyTextincart(String ExpTxt) {
		String LblActualText=null;
		waitForElementPresent(implicitWaitTimeout);
		if(ExpTxt.equalsIgnoreCase("SEAT SELECTION")) {
			LblActualText=getElementText(lblSeatSelection);
		}else if(ExpTxt.contains("Please select your seat to continue.")){
			LblActualText=getElementText(lblSeatSelectioncontent);
		}
		reportStatus(LblActualText.contains(ExpTxt),ExpTxt + " displaying on the cart",ExpTxt+" is not displaying on the cart");

	}
	
	public String setSeatinfo(String RowNum,String Alpha) throws Throwable {
		jsSetTextAndTriggerBubbleEvent(CartPage.inpSeatRownum, RowNum);
		jsSetTextAndTriggerBubbleEvent(CartPage.inpColAlphabet,Alpha);
		Thread.sleep(3000);
		Seatseletedinfo=returnelement(CartPage.inpSeatRownum).getAttribute("value")+" "+ returnelement(CartPage.inpColAlphabet).getAttribute("value");
		System.out.println(Seatseletedinfo);
		return Seatseletedinfo;
	}
	public String getSeatinfo() {
		return getElementText(txtStoredSeatno);
	}
	
	
    
}
