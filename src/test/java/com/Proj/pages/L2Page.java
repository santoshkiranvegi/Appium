package com.epax.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.epax.framework.base.DriverManager;
import com.epax.framework.utilities.WebDriverActions;

public class L2Page extends CommonPage{
	
	WebDriver driver=DriverManager.getDriver();
	
	public void Addnumberofitemstocart(int n) {
		for(int i=1;i<=n;i++) {
			clickElement(By.xpath("(//button[contains(@class,'Item_addToCartButton')])[n]"));
		}
	}
	
	public static By ShowmoreproductsLink_Android =By.cssSelector("button[data-testid*=productListItems]");
	public static By L2SubMenu = By.cssSelector("div[id*='option-0']");
	public static By L2CatrgoryName = By.cssSelector("div[class*=CategoryButtons_category] span");
	public static By AlcoholSubMenu = By.cssSelector("a[data-testid*=Alcohol]");
    public static By imgItemOutOfStock = By.cssSelector("div[class^='Item_itemState__'] +img");
    public static By itmActiveStock = By.cssSelector("button:not([disabled]) span");
    public static By imgActiveStock = By.cssSelector("div[class*='Item_imageBackground'] :nth-child(1)");

    public static By btnFilter = By.cssSelector("[data-testid^='productList-toggleFilter-button']");
    public static By TotalItems = By.cssSelector("div[class^='Item_imageBackground']");
	
    //TRTEPAX -> 548
    public static By lblHomePageName = By.cssSelector("a[data-testid^='bread-crumb-item-home'] p");
    public static By lblLOnePageName = By.cssSelector("a[data-testid^='bread-crumb-item-retailitems'] p");
    public static By lblLTwoPageName = By.cssSelector("p[data-testid^='bread-crumb-item-alcohol']");
    public static By lblHierarchicalInItemPage = By.cssSelector("div[class^='ItemPage_breadCrumb'] a");
    

    public static By sortOrderDropDown=By.cssSelector("div[data-testid*='productListToggleSortDesktop-drop-down']");
	public static By itemPrice=By.cssSelector("div[class*='Item_price'] h4");
	public static By itemsOnPagel2=By.cssSelector("div [class*='Item_item__']");

  //Took Xpath's because unable to take css selectors "Sort OverLay"
  	public static By txtSortAsc=By.xpath("//*[text()='A-Z']");
  	public static By txtSortDes=By.xpath("//*[text()='Z-A']");		
  	public static By txtPriceLowHigh=By.xpath("//*[text()='Price Low - High']");
  	public static By txtPriceHighLow=By.xpath("//*[text()='Price High - Low']");
  	//Andriod xpaths
  	public static By btnSort_Andriod=By.cssSelector("button[data-testid*=productList-toggleSort-button]");
  	public static By sortCartHeader=By.cssSelector("h3[class*='ProductListFilter_CartHeaderHeading']");
  	public static By closeSortCart=By.xpath("//*[text()='close']");
  	public static By nameDescendingOrder=By.cssSelector("div[data-testid*='productListToggleSortMobile-option-name-descending']");
  	public static By priceAscendingOrder=By.cssSelector("div[data-testid*='productListToggleSortMobile-option-price-ascending']");
  	public static By priceDescendingOrder=By.cssSelector("div[data-testid*='productListToggleSortMobile-option-price-descending']");
  	public static By defaultSortItem=By.xpath("//*[text()='Name (A - Z)']/following-sibling::div[contains(@class,'ProductListFilter_boxActive')]");
  	public static By breadcrumb_L2Page=By.cssSelector("p[data-testid*='bread-crumb-item']");
  	public static By L2MenuItems_Mob	= By.cssSelector("div[class*=CategoryButtons_activeTab] a span"); 
  	
  	public void scrollPageDownInL2()
  	{
  		while(!isElementDisplayed(CategoryPage.lblNoMoreItems)) {			
  			scrollPageDown();
  			if(isElementDisplayed(CategoryPage.lblNoMoreItems)) {
  				break;
  			} else if(isElementDisplayed(L2Page.ShowmoreproductsLink_Android)) {
  				clickElement(L2Page.ShowmoreproductsLink_Android);
  				reportInfo("Page is scrolled to display the remaining items");
  			} else {
  				scrollPageDown();
  			}			
  		}	
  	}
  	
  	public void scrollPageUpInL2()
  	{
  		while(!isElementDisplayed(L2Page.sortOrderDropDown)) {			
  			scrollPageUp();
  			if(isElementDisplayed(L2Page.sortOrderDropDown)) {
  				break;
  			} 
  		}
  	}
			
	public void scrollDownInAndiodDevice()
	{
		while(!isElementDisplayed(CategoryPage.lblNoMoreItems)) {
			scrollPageDown();
			if(isElementDisplayed(CategoryPage.lblNoMoreItems)) {
				break;
			} else if(isElementDisplayed(L2Page.ShowmoreproductsLink_Android)) {
				clickElement(L2Page.ShowmoreproductsLink_Android);
				reportInfo("Page is scrolled to display the remaining items");
			} else {
				scrollPageDown();
			}			
		}
	}

	
	public int getTotalItemsInL2Page() {
		int totalItemsInL2Page = getListOfMatchingElements(L2Page.TotalItems).size();
		return totalItemsInL2Page;
	}
	
	public boolean Mob_displayL2MenuitemsforL1Item() {
		boolean Menuitemsdisplayed=false;
		List<String> L2names=getTextFromAllElements(L2MenuItems_Mob);
		System.out.println(L2names);
		for(String L2items:L2names) {
			if(L2items.equalsIgnoreCase("ALCOHOL")|L2items.equalsIgnoreCase("SPIRITS")) {
				Menuitemsdisplayed=true;
			}
		}
		return Menuitemsdisplayed;
	}
	


}
