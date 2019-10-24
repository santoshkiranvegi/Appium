package com.epax.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.epax.framework.base.DriverManager;
import com.epax.framework.utilities.WebDriverActions;
import com.epax.stepdefinitions.StepDefs;

import cucumber.api.java.en.When;

// This is the common pages for the element locators. The locators will be segregated into multiple page classes
public class CommonPage extends WebDriverActions {
    //--------Cart Elements----------
    public static By btnShoppingCart=By.xpath("//label[contains(@class,'FloatingMenu_NavItem') and contains(text(),'My Cart')]");
	public static By btnShoppingCart_mob1 =By.xpath("//*[@class='material-icons' and text()='shopping_cart']");
    //---------Cart Elements----------
    public static By lblPageRoot = By.id("App");
    public static By lblTotalItemsInCart=By.cssSelector("div[class*='FloatingMenu_OrderCountPip']");
    public static By btnHamburgerMenu=By.cssSelector("i[data-testid*=floating-menu-mobile] +i");
    public static By btnMobileBistroMenu=By.xpath("//span[contains(text(),'Bistro')]");
    public static By btnBistroMenu=By.xpath("//div[contains(@class,'FloatingMenu_QuickAccessMenuBAr')]//div[./*[contains(text(),'Bistro')]]");
    public static By btnSpiritsMenu=By.xpath("//div[./*[contains(text(),'Spirits')]]");
    public static By lblItemOnPage=By.cssSelector("button:not([disabled]) span");
    public static By txtItemDescription=By.cssSelector("p[class*=ItemPage_DescriptionSmallText]");
    public static By lblItemOutOfStock=By.cssSelector("div[class^='Item_itemState__']");
    public static By btnEnabledAddToCartl2=By.cssSelector("div[class*='ItemPage_OrderAmountWrapper']>button");
    public static By btnDisabledAddToCart=By.cssSelector("button[class*='Item_addToCartButtonDisabled']");
    public static By lblOutOfStock=By.cssSelector("div[class*='ItemPage_itemState']");
	public static By btnAddToCart=By.xpath("(//button[contains(@class,'Item_addToCartButton')])[1]");
   	public static By btnAddToCartl2=By.cssSelector("button[class*=ItemPage_AddToCart] span");
    public static By lblDisplayedItemInfo=By.xpath("//div[contains(@class,'Item_itemInfo')]/h3");
    
    public static By btnMinus = By.xpath("//i[contains(text(),'remove')]/..");
    public static By btnPlus = By.xpath("//i[contains(text(),'add')]/..");
    public static By lblSubTotal = By.cssSelector("div[class^='CartItem_itemPrice']");
    public static By btnRemoveItem = By.cssSelector("button[class^='CartItem_removeButton'] i");
    public static By lblItemName = By.cssSelector("div[class*='CategoryPage_itemContainer']> div:nth-of-type(1) img");
    public static By lblItemlowstk= By.cssSelector("[class*='Item_itemStateLowStock']");
    public static By lblDescription = By.xpath("//strong[contains(text(),'Description')]");
    public static By txtSortSelector = By.xpath("//div[@class=' css-1hwfws3']/div[@class=' css-zlqabr']");
    public static By btnExitMenu = By.xpath("//div[@class='FlyOutMenu_MenuHeader__1wfH1']/div/i[contains(text(),'close')]|//div[contains(@class,'SeatSelection_closeContainer')]//i");
    public static By btnBackIcon = By.xpath("//i[contains(text(), 'arrow_back_ios')]");
    public static By imgEmptyMiniCart = By.cssSelector("div[class^='CartList_emptyCart']");
    public static By txtEmptyMiniCart = By.xpath("//span[contains(text(),'Your cart seems to be empty')]");
    public static By btnContinueShopping = By.xpath("//button[contains(text(),'Continue Shopping')]");
    public static By txtSearchTextBox = By.xpath("//input[contains(@class,'Search_searchBar')]");
    public static By totalNumberOfItems = By.cssSelector("a[data-testid*=item]");
    //Mobile My Cart
    public static By btnMyCartIcon = By.xpath("//i[contains(@class,'material-icons md-24 md-dark ') and contains(text(),'shopping_cart')]");
    public static By lblMyCart = By.xpath("//h3[contains(text(),'MY CART')]");
    public static By lblItemInMiniCart = By.xpath("div[@class='CartItem_firstRow__3T0S6']/h3");
  //Recommendations
    public static By lblRecommended      =By.xpath("//h3[contains(text(),'RECOMMENDED PRODUCTS')]");
    public static By listRecommendedItems=By.xpath("//h3[contains(text(),'RECOMMENDED PRODUCTS')]/..//div[contains(@class,'SuggestedItemsView_Masonary')][1]/div[contains(@class,'Item')]");
    //Substitutions to update Later
    public static By lblSubstitutions    =By.xpath("//h3[contains(text(),'SUGGESTED SUBSTITUTIONS')]");
    public static By listSubstitutionItems =By.xpath("//h3[contains(text(),'SUGGESTED SUBSTITUTIONS')]/..//div[contains(@class,'SuggestedItemsView_Masonary')][2]/div[contains(@class,'Item')]");
    //L2 Filters
    public static By classFilterinp						=By.xpath("//div[contains(@class,'ProductListFilter_rangeInput')]");
    public static By inplowvalfilter					=By.xpath("//div[contains(@class,'ProductListFilter_rangeInput')]/label[1]/input");
    public static By inphighvalfilter					=By.xpath("//div[contains(@class,'ProductListFilter_rangeInput')]/label[2]/input");
    public static By lblItemcount						=By.xpath("//div[contains(@class,'CategoryPage_categoryInformation')]/p");
    public static By btnShowMoreProd					=By.xpath("//div[contains(@class,'CategoryPage_endOfPage')]/button");
    public static By lblDisplayedprodItemPrice			=By.xpath("//div[contains(@class,'Item_price')]//h4");
	public static final String replaceText              ="<<ReplaceText>>";	
    public static String chkBoxFilterItem               ="//p[contains(text(),'"+replaceText+"')]/../div[contains(@class,'Filter_box')]/i";
    public static By lnkItemListL2Page                  =By.xpath("//div[contains(@class,'itemContainer')]//a");
    public static By lblDisplayedprodItemName			=By.xpath("//div[contains(@class,'itemContainer')]//a//h3");
    public static By lblItemL3Page                      =By.xpath("//div[contains(@class,'ItemPage_addToCart')]/h2");
    
    public static By lblForwardArrowIcon = By.cssSelector("div[class^='CategoryButtons_tabChild'] div i");
    public static String byText            = "//*[contains(text(),'"+replaceText+"')]";
    public static By listMobileMenu   = By.cssSelector("div[class*=CategoryButtons_activeTab] span ");
    public static By containerCategoryItemPage = By.cssSelector("div[class*=CategoryPage_itemContainer]");
    // Common Methods:
    WebDriver driver = DriverManager.getDriver();
    static Logger log = Logger.getLogger(StepDefs.class);
    /**
	 * @Purpose To select required item in L2 Page
	 * @Constraints
	 * @Input String ItemName
	 * @Output boolean : Returns True if success else false
	 */
    
    
	public boolean clickrequiredItemL2Page(String itemName) {
		boolean res = false;
		//int reqindex = 0;
		//boolean flag = false;
		try {
			// to load all products
			loadAllproductsCategoryPage();
			//List<String> eleList = getTextFromAllElements(CommonPage.lblDisplayedprodItemName);
			List<WebElement> matchelements = getListOfMatchingElements(CommonPage.lblDisplayedprodItemName);
			if (matchelements.size() > 0 ) {
				for (int elecount = 0; elecount < matchelements.size(); elecount++) { // to get index of element

					if (matchelements.get(elecount).getText().equalsIgnoreCase(itemName)) {
						//reqindex = elecount;
						clickUsingJavaScript(matchelements.get(elecount));
						res = true;
						break;
					}
					if (itemName.equalsIgnoreCase("anyitem")) { // click first item
						clickUsingJavaScript(matchelements.get(elecount));
						//reqindex = 1;
						res = true;
						//itemName = eleList.get(elecount);
						break;
					}
				}
				/*
				 * List<WebElement> eleLinks =
				 * getListOfMatchingElements(CommonPage.lnkItemListL2Page); if (flag ) {
				 * //eleLinks.get(reqindex).click();
				 * clickUsingJavaScript(eleLinks.get(reqindex));
				 * waitForPageToLoad(pageLoadTimeout); if
				 * (getElementText(CommonPage.lblItemL3Page).equalsIgnoreCase(itemName)) { res =
				 * true; } } else { res = false; }
				 */
			}
		} catch (Exception ex) {
			log.info("Exception Caught in Validation for Filter price Ranges:" + ex);
			res = false;
		}
		return res;
	}
	
	/**
	 * @Purpose To Verify Item Displayed  With in Limits for Filtered Price
	 * @Constraints
	 * @Input Double lower and upper limits By Locator List of Elements
	 * @Output boolean : Returns True if success else false
	 */
		public boolean compareItemPriceInLimits(double lowval, double highval, By locatorlist) {
			boolean res = true;
			try {
				// to load all products
				while (isElementDisplayed(CommonPage.btnShowMoreProd)) {
					scrollElementIntoView(CommonPage.btnShowMoreProd);
				}
				List<String> eleList = getTextFromAllElements(locatorlist);
				if (eleList.size() > 0 && eleList.size() == Integer
						.parseInt(getElementText(CommonPage.lblItemcount).replaceAll("[^0-9]", ""))) {
					for (int elecount = 0; elecount < eleList.size(); elecount++) {

						if ((lowval <= Double.parseDouble(eleList.get(elecount).split("\\$")[1]))
								&& (Double.parseDouble(eleList.get(elecount).toString()) <= highval)) {

						} else {
							s.write("Found an Item with Price" + eleList.get(elecount).toString()
									+ "Which is not with in Range of Low Val :" + lowval + " & High Value:" + highval);
							res = false;
							break;
						}
					}
				} else {
					s.write("Found no Items with in Range of Low Val:" + lowval + " & High Value:" + highval);
					res = false;
				}

			} catch (Exception ex) {
				log.info("Exception Caught in Validation for Filter price Ranges:" + ex);
				res = false;
			}

			return res;
		}
		
		

/**
	 * @Purpose To Load All products on Category Page
	 * @Constraints
	 * @Input NA
	 * @Output boolean : Returns True if success else false
	 */
	public boolean loadAllproductsCategoryPage() {
		boolean res = false;
		
		if(isElementDisplayed(containerCategoryItemPage)) { // only for Category page
		for (int scrollcount = 0; scrollcount < 8; scrollcount++) {
			scrollPageDown();
			if (isElementDisplayed(CategoryPage.lblNoMoreItems)) {
				if (scrollElementIntoView(CategoryPage.lblNoMoreItems)) {
					System.out.println("Element No More Items  Found ");
					res = true;
					break;
				} else {
					scrollPageDown();
				}
			} else if (isElementEnabled(L2Page.ShowmoreproductsLink_Android)) {
				clickElement(L2Page.ShowmoreproductsLink_Android);
				reportInfo("Page is scrolled to display the remaining items");
			} else {
				scrollPageDown();
			}
		}
		
	}
		return res;
	}
	
	
}
