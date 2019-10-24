package com.epax.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.epax.framework.base.DriverManager;
import com.epax.framework.base.ExecutionManager;
import com.epax.framework.utilities.WebDriverActions;

public class L1Page extends CommonPage{
	WebDriver driver=DriverManager.getDriver();

	public static By L1MenuSelector=By.cssSelector(".css-1hwfws3");
	public static By L1Menu = By.cssSelector("div[data-key='0']");
	public static By L1Menu_xpath = By.xpath("//div[@data-key='0']");
	//public static By breadcrumb_L2Page=By.xpath("//div[@class='CategoryPage_breadCrumb__3cwjN']/p");
	public static By firstSeemoreitems_link=By.xpath("//div[contains(@class,'CategoriesPage_L1Header')]/following-sibling::div[1]/a[text()='see more items']");
	public static By l1showmoreitemlink = By.cssSelector("p[class^='CategoriesPage_break'] a");
	public static By txtSearchTextBox = By.cssSelector("#addInput");
	public static By topOfTheList = By.cssSelector("div[class^='FlyOutMenu_DynamicMenuHeading']");
	public static By lblL2NameInL1Page = By.cssSelector("div[class^='CategoriesPage_title'] :nth-child(1)");
	public static By lblL2NameInL1Page_Mobile = By.cssSelector("p[class^='CategoriesPage_title'] :nth-child(1)");
	//Need to verify in TRTEPAX-131
	public static By lblCategoryL1PageName = By.cssSelector("div[class^='CategoriesPage_L1Header'] h3");
	public static By lblCategoryL1description = By.cssSelector("div[class^='CategoriesPage_L1Header'] p");
	public static By L1headertitle =By.cssSelector("div[class*='CategoriesPage_L1Header'] h3");

	public void iscatL2itemdisplayed(String arg1) throws Exception {
		//Thread.sleep(3000);
		waitForPageToLoad(elementTimeout);
		waitForElementVisibility(By.xpath("//p[contains(text(),'" + arg1 + "')]"));
	}
	
	public void clickseemorelink(String arg1) {
		clickElement(By.xpath("//p[contains(text(),'" + arg1 + "')]/../../a"));
		reportInfo("Clicked " + arg1);
	}
	
	public WebElement iscatL2itemdisplayedonL2Page(String arg1) {
		return driver.findElement(By.xpath("//div[@class='CategoriesPage_Wrapper__26Bw9']/p/p/p[1][contains(.,'"+arg1+"')]"));
	}
	
	public void Mob_displayMenuitems() {
		if (getListOfMatchingElements(CommonPage.btnHamburgerMenu).size() > 0) {
			clickElement(CommonPage.btnHamburgerMenu);
			reportInfo("Clicked Hamburger Menu");
			waitForElementPresent(implicitWaitTimeout);
			reportInfo("L1 Page Name : " + getElementText(L2Page.L2CatrgoryName).trim());
			clickElement(L2Page.L2CatrgoryName);
			reportInfo("Clicked L2 Category Name");
		}
	}

}
