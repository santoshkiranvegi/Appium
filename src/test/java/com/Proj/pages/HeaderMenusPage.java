package com.epax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HeaderMenusPage {
		public static By lblRetailItemsMenu = By.xpath("//div[contains(text(),'Retail Items')]");
		public static By HeadermenuBarlist1	= By.cssSelector("[data-key='0']");
	    public static By lblitemsInCart=By.cssSelector("div[data-testid*='floating-menu-desktop-my-cart-count-pip']");
	    public static By btnShoppingCartIcon = By.cssSelector("i[data-testid^='floating-menu-mobile-my-cart-icon']");
		public static By btnShoppingCart=By.xpath("//label[contains(@class,'FloatingMenu_NavItem') and contains(text(),'My Cart')]");
		public static By btnFaq=By.xpath("//label[contains(@class,'FloatingMenu_NavItem') and contains(text(),'FAQ')]|//*[contains(text(),'FAQ')]");
		public static By btnMyOrder=By.xpath("//label[contains(@class,'FloatingMenu_NavItem') and contains(span,'My Orders')]|//*[contains(text(),'My Orders')]");
		public static By lblDeals=By.xpath("//div[contains(text(),'Deals')]|//span[contains(text(),'Deals')]");
		public static By txtSearchBar=By.xpath("//input[@placeholder='Search Brands, Categories or Products']|//i[@data-testid='floating-menu-search']");
		public static By txtAlcohol=By.xpath("//*[text()='Alcohol']");
		public static By txtAlcoholInL2=By.xpath("//p[contains(text(),'Alcohol')]");
		public static By imgHomeLogo = By.cssSelector("img[class*='FloatingMenu_Home']");
		//L2 xPths
		public static By txtFAQ=By.xpath("//h4[contains(text(),'FAQ')]");
		public static By txtMyOrders=By.xpath("//*[contains(text(),'My Orders')]");
		public static By txtRetailItemsl2=By.cssSelector("div[class*='CategoriesPage_L1Header'] h3");
		//Seat Selection
		public static By txtSeatSelection=By.cssSelector("div[class*=SeatSelection_CartHeader] h3");
		public static By iconCloseSeatSelection=By.cssSelector("div[class*='SeatSelection_closeContainer'] i");
}
