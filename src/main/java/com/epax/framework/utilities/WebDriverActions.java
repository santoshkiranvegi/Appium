package com.epax.framework.utilities;

import com.epax.framework.base.DriverManager;
import com.epax.framework.base.ExecutionManager;

import cucumber.api.Scenario;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.*;

public class WebDriverActions {
    WebDriver driver = DriverManager.getDriver();
    public static Scenario s ;
    static Logger log = Logger.getLogger(WebDriverActions.class);
    public static final int elementTimeout = 10; //seconds
	public static final int pageLoadTimeout = 60; //seconds
	public static final int implicitWaitTimeout = 3; //Seconds
	public static String isMobile="";
	
	public boolean isMobile() {
		if(isMobile.equalsIgnoreCase("YES")) {
			return true;
		}else
			return false;
	}

    public WebElement getElement(By locator){
        WebElement element = null ;
        try {
            element=driver.findElement(locator);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Exception caught to find Matching Element for :"+locator);
        }
        finally {
            return element;
        }
    }
    public List<WebElement> getListOfMatchingElements(By locator){
    	List<WebElement> elements = null ;
    	try {
    		elements=driver.findElements(locator);
    	      System.out.println(elements.size());
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception caught to find Matching Elements for :"+locator);
			elements=Collections.<WebElement>emptyList(); // to avoid null pointer exception
		}
      return elements;
    }

    public List<String> getTextFromAllElements(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        List<String> txtList = new ArrayList<>();
        for (WebElement element : elements) {
            String text = null;
            try {
                text = element.getText();
            } catch (Exception ex) {

            }
            txtList.add(text);
        }
        return txtList;
    }

    public boolean waitForAllPageRequests() {
        WebDriverWait wait = new WebDriverWait(driver, 100);
        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
                }
                catch (Exception e) {
                    // no jQuery present
                    return true;
                }
            }
        };
        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public void waitForElementVisibility(By elementLocator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
    }
    public void waitForElementClickable(By elementLocator){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
    }
    public boolean isElementDisplayed(By elementLocator){
    	boolean status = false;
    	try {
    		status=driver.findElement(elementLocator).isDisplayed();
    	} catch(JavascriptException je) {
    		isElementEnabled(elementLocator);
    		status = true;
    	}catch (Exception e) {
    		//e.printStackTrace();
    		System.out.println("Exception Message :"+e.getMessage());
    		status = false;
        }
       return status;
    }
       
    public WebElement returnelement(By elementLocator) {
    	WebDriverWait wait=new WebDriverWait(driver, 20);
    			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(elementLocator)));
    }
       
    
    public boolean clickElement(By locator) {
        /*boolean isClicked = false;
        for(int attempt=0;attempt<5;attempt++) {
            try {
                if(!isClicked)
                driver.findElement(locator).click();
                isClicked = true;
            }catch(ElementNotVisibleException ex) {
                scrollElementIntoView(locator);
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("Attempt : "+attempt);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    	
        return isClicked;*/
    	
    	boolean isClicked = false;
    	for(int attempt=0;attempt<3;attempt++) {
    	try {
    		if(!isClicked) {
    			waitForElementPresent(elementTimeout);
            WebElement element = driver.findElement(locator);
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);
            isClicked = true;
    		}
			
		} catch (Exception e) {
			isClicked = false;
		}
    	}
    	return isClicked;
    	
    }
    
    public boolean mouseHover(By locator) {
    	boolean isMouseHovered=false;
    	WebElement webElement = driver.findElement(locator);
    	try {
    		if(isElementEnabled(locator)) {
    			Actions actions = new Actions(driver);    	
            	actions.moveToElement(webElement).build().perform();
                isMouseHovered=true;
    		} else {
    			System.out.println("Element is not displayed");
    		}
    		    		
    	} catch(ElementNotVisibleException e) {
    		e.printStackTrace();
    	}
    	catch(Exception e){
    	   e.printStackTrace();
        }
    	finally{
    	    return isMouseHovered;
        }
    	
    }

    public boolean jsClick(By locator){
    	//boolean res = true;
    	boolean isClicked = false;
    	for(int attempt=0;attempt<3;attempt++) {
    	try {
    		if(!isClicked) {
    		//waitForElementClickable(locator);
    			waitForElementPresent(elementTimeout);
            WebElement element = driver.findElement(locator);
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);
            isClicked = true;
    		}
			
		} catch (Exception e) {
			isClicked = false;
		}
    	}
    	return isClicked;
    }

    public String getElementText(By locator){
    	 String eleText="";
        try {
            eleText = driver.findElement(locator).getText();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            return eleText;
        }
    }

    
    public boolean isElementVisbile(By locator) {
    	boolean status=false;
    	try{
    		status=driver.findElement(locator).isDisplayed();
    	}catch(NoSuchElementException e) {
    		reportInfo("Element Not Visible");
    	}
    	return status;
    }
    
    public boolean isElementEnabled(By locator) {
        boolean isEnabled = false;
        try {
            isEnabled=driver.findElement(locator).isEnabled();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            return isEnabled;
        }
    }

    public void scrollElementToTop(By locator)  {
        try {
            WebElement webElement = driver.findElement(locator);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
            Thread.sleep(1000);
        }catch(Exception ex){
        }
    }
    /**
   	 * purpose: To scroll to view web element
   	 * @param: WebElement
        * @return 
   	 */
       public boolean scrollElementIntoView(By locator)  {
       	boolean res = true;
           try {
               WebElement webElement = driver.findElement(locator);
               ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);
               Thread.sleep(1000);
           }catch(Exception ex){
           	res = false;
           }
           return res;
       }
       
       /**
      	 * purpose: To scroll to view web element
      	 * @param: WebElement
           * @return 
      	 */
          public boolean scrollElementIntoView(WebElement element)  {
          	boolean res = true;
              try {                 
                  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
                  Thread.sleep(1000);
              }catch(Exception ex){
              	res = false;
              }
              return res;
          }
          
          /**
  		 * Description : Returns the html tag property value which is given in parameter
  		 * Parameters : WebElement, String
  		 * returns : String
  		 */
  		public String getAttributeValue(WebElement element, String attributeName) {
  			
  			String attributeValue = element.getAttribute(attributeName);
  			return attributeValue;
  		}
  		
  		/**
  		 * Description : Returns the html tag property value which is given in parameter
  		 * Parameters : By, String
  		 * returns : String
  		 */
  		public String getAttributeValue(By locator, String attributeName) {
  			
  			WebElement element = driver.findElement(locator);
  			String attributeValue = element.getAttribute(attributeName);
  			return attributeValue;
  		}

    
    public void typeUsingJavaScript(By locator, String data) {

		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].value='" + data + "';", element);
	}
	
	public void typeUsingJavaScriptInHTMLInnerText(By locator, String data) {

		WebElement element = driver.findElement(locator);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].innerHTML ='" + data + "';", element);
	}
    public void waitTillElementVisible(By locator) throws InterruptedException
    {
    	WebDriverWait wait=new WebDriverWait(driver, 20);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(locator));    	    	
    }
    @SuppressWarnings("finally")
	public boolean launchUrl(String url){
        boolean isLaunched=false;
        try{
        	driver.get(url);
            isLaunched=true;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
        	driver.navigate().to(url);// incase of exceptions if any
            return isLaunched;
        }
    }

    public void reportInfo(String info){
        ExecutionManager.getTestScenario().write(info);
    }
    
    public void reportStatus(boolean status, String expectedResult, String actualResult) {
    	if(status) {
    		assertTrue(status, expectedResult);
    		reportInfo(expectedResult);
    	} else {
    		//assertFalse(status, actualResult);
    		assertTrue(status, actualResult);
    		reportInfo(actualResult);
    	}
    }

    public Long getSizeHeight(){
        Long screenHeight = (Long)((JavascriptExecutor) driver).executeScript("return document.documentElement.clientWidth");
        return screenHeight;
    }

    public String getDeviceOrientation(){
        String deviceOrientation=null;
        try {
            Long portableDeviceOrientation = (Long) ((JavascriptExecutor) driver).executeScript("return window.orientation");
           if(portableDeviceOrientation!=null){
               switch (portableDeviceOrientation.intValue()) {
                   case 0:
                       deviceOrientation="portrait";
                       break;
                   case 180:
                       deviceOrientation="portrait";
                       break;
                   case -90:
                       deviceOrientation="landscape";
                       break;
                   case 90:
                       deviceOrientation="landscape";
                       break;
               }
           }
           else {
               deviceOrientation = (String) ((JavascriptExecutor) driver).executeScript("return window.screen.orientation.type");
           }
        }
        catch(WebDriverException ex) {
               deviceOrientation = (String) ((JavascriptExecutor) driver).executeScript("return window.screen.msOrientation");
         }
        finally{
            return deviceOrientation.split("-")[0];
        }
    }

       
    public void type( By locator, String testdata)  {

    }
    
    public void scrollPageDown() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("Page is scrolled down to bottom of the page");
    }
    public void scrollPageUp() {
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -window.innerHeight)");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("Page is scrolled up to top of the page");
    }
    public void clearAndType( By locator, String inputText)  {
		try {
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(inputText);
		} catch (Exception e) {
			 e.printStackTrace();
			throw e;
		}
	}
    
    public void clearAndTypeAndTab( By locator, String inputText)  {
		try {
			driver.findElement(locator).clear();
			driver.findElement(locator).click();
			driver.findElement(locator).sendKeys(inputText);
			WebElement ele=driver.findElement(locator);
			ele.sendKeys(Keys.TAB);
		} catch (Exception e) {
			 e.printStackTrace();
			throw e;
		}
	}
    
    /**
	 * purpose: To wait for page load
	 * @param timeOutInSeconds
	 */
	public void waitForPageToLoad(int timeOutInSeconds) {
		boolean flag=false;
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String jsCommand = "return document.readyState";
			
			// Validate readyState before doing any waits
			if (js.executeScript(jsCommand).toString().equals("complete")) {
				flag=true;
			} else {
				for (int i = 0; i < timeOutInSeconds; i++) {
					Thread.sleep(1000);
					flag=js.executeScript(jsCommand).toString().equals("complete")?true:false;
					if(flag)break;
				} //for-loop
			} //if-else
			
			if(!flag) {
				log.error("Failed to Load Complete Page....");
			}
		} catch (Exception e) {
			log.error("Exception to Load Complete Page....");
		}
	}
	
	/**
	 * purpose: To click Web element using java script
	 * @param: WebElement
	 */
	 public boolean clickUsingJavaScript(WebElement element){
	    	boolean res = true;
	    	try {
	            JavascriptExecutor executor = (JavascriptExecutor)driver;
	            executor.executeScript("arguments[0].click();", element);
			} catch (Exception e) {
				res = false;
			}
	    	return res;
	    }
	 
	 
	 public boolean mouseHoverJavaScript(By locator) {
		 waitForElementClickable(locator);
         WebElement element = driver.findElement(locator);
	 String strJavaScript = "var element = arguments[0]; var mouseEventObj = document.createEvent('MouseEvents'); mouseEventObj.initEvent( 'mouseover', true, true ); element.dispatchEvent(mouseEventObj);";
	 ((JavascriptExecutor) driver).executeScript(strJavaScript, element);
	return true;
	 }
	 
	 public WebElement getActiveElement() {
		 return driver.switchTo().activeElement();
	 }


    public void jsSetTextAndTriggerBubbleEvent(By elementLocator, String inputText){
	     try {
	         WebElement textElement=getElement(elementLocator);
             String javaScript =  "var element=arguments[0];\n"+
                     "var value=arguments[1];\n"+
                     "function setNativeValue(element, value) {\n" +
                     "  const valueSetter = Object.getOwnPropertyDescriptor(element, 'value').set;\n" +
                     "  const prototype = Object.getPrototypeOf(element);\n" +
                     "  const prototypeValueSetter = Object.getOwnPropertyDescriptor(prototype, 'value').set;\n" +
                     "\n" +
                     "  if (valueSetter && valueSetter !== prototypeValueSetter) {\n" +
                     "    prototypeValueSetter.call(element, value);\n" +
                     "  } else {\n" +
                     "    valueSetter.call(element, value);\n" +
                     "  }\n" +
                     "};" +

                     "setNativeValue(element,value);" +
                     "element.dispatchEvent(new Event('input', { bubbles: true }));";

             ((JavascriptExecutor) driver).executeScript(javaScript,textElement,inputText);
         }
	     catch(Exception ex){
	        ex.printStackTrace();
         }
    }

	 public void jsSetTextAndTriggerBubbleEvent(){
	     String javaScript="function setNativeValue(element, value) {\n" +
                 "  const valueSetter = Object.getOwnPropertyDescriptor(element, 'value').set;\n" +
                 "  const prototype = Object.getPrototypeOf(element);\n" +
                 "  const prototypeValueSetter = Object.getOwnPropertyDescriptor(prototype, 'value').set;\n" +
                 "\n" +
                 "  if (valueSetter && valueSetter !== prototypeValueSetter) {\n" +
                 "    prototypeValueSetter.call(element, value);\n" +
                 "  } else {\n" +
                 "    valueSetter.call(element, value);\n" +
                 "  }\n" +
                 "};" +
                 "var element=document.getElementsByName('seatRow')[0];" +
                 "setNativeValue(document.getElementsByName('seatRow')[0],'8');" +
                 "a.dispatchEvent(new Event('input', { bubbles: true }));" +
                 "var b=document.getElementsByName('seatColumn')[0];" +
                 "setNativeValue(document.getElementsByName('seatColumn')[0],'E');" +
                 "b.dispatchEvent(new Event('input', { bubbles: true }));";
         ((JavascriptExecutor) driver).executeScript(javaScript);
     }
	 
	 public String getelementcolor(By locator,String fieldtype) {
			String colorRGB=driver.findElement(locator).getCssValue(fieldtype);
			System.out.println(colorRGB);
			String color="none";
			if(colorRGB.contains("rgba(0, 0, 0, 0.6)")|colorRGB.contains("rgba(0, 0, 0, 0)")) {
				color="grey";
			}else if(colorRGB.contains("rgb(0, 181, 82)")|colorRGB.contains("rgba(0, 181, 82, 1)")) {
				color="Green";
			}else if(colorRGB.contains("rgb(242, 153, 74)")|colorRGB.contains("rgba(242, 153, 74, 1)")) {
				color="Amber";
		  }
		return color;
	 }

    public void jsMouseOver(By locator) {
	     try {
             WebElement HoverElement = driver.findElement(locator);
             try {
                 String mouseOverScript = "if(document.createEvent){" +
                         "var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover',true,false);" +
                         "arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
                 ((JavascriptExecutor) driver).executeScript(mouseOverScript,HoverElement);
             }
             catch (StaleElementReferenceException e) {
                 System.out.println("Element with " + HoverElement + "is not attached to the page document"+ e.getStackTrace());
             } catch (NoSuchElementException e) {
                 System.out.println("Element " + HoverElement + " was not found in DOM"+ e.getStackTrace());
             } catch (Exception e) {
                 e.printStackTrace();
                 System.out.println("Error occurred while hovering"+ e.getStackTrace());
             }
         }
	     catch(Exception ex){
	         ex.printStackTrace();
        }
    }
    
    /**
	 * purpose: To Click on an Element in its Collection or Single element By Matching its Text
	 * @param :-Element Identifier, Name
	 */
    
	public boolean clickMatchingelementByText(By elementList, String itemName) {
		boolean res = false;
		try {
			Thread.sleep(implicitWaitTimeout*1000);
			List<WebElement> matchelements = getListOfMatchingElements(elementList);
			if (matchelements.size() > 0) {
				for (int elecount = 0; elecount < matchelements.size(); elecount++) { // to get index of element
					System.out.println(matchelements.get(elecount).getText());
					if (matchelements.get(elecount).getText().equalsIgnoreCase(itemName)) {
						// reqindex = elecount;
						clickUsingJavaScript(matchelements.get(elecount));
						res = true;
						break;
					}
					if (itemName.equalsIgnoreCase("anyitem")) { // click first item
						clickUsingJavaScript(matchelements.get(elecount));
						res = true;
						break;
					}
				}

			}
		} catch (Exception e) {
			System.out.println("Exception Caught To click on Element Identifier :" + elementList + "  With String name:"
					+ itemName);
		}

		return res;

	}
	
	public void waitForElementPresent(int seconds) {
		
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}
	}

	public String jsGetElementAttribute(By locator, String attribute) {
		String attributeValue=null;
		try {
			WebElement HoverElement = driver.findElement(locator);
			try {
				String readAttribute = "var style=window.getComputedStyle(arguments[0]);var readAttribute;if(arguments[1].includes('borderBottomColor')){readAttribute=style.borderBottomColor}else{readAttribute=style.color};return readAttribute;";
				attributeValue=(String)((JavascriptExecutor) driver).executeScript(readAttribute,HoverElement,attribute);
				System.out.println(attributeValue);
				if(attributeValue.contains("rgb(226, 226, 227)")) {
					attributeValue="grey";
				}
			}
			catch (StaleElementReferenceException e) {
				System.out.println("Element with " + HoverElement + "is not attached to the page document"+ e.getStackTrace());
			} catch (NoSuchElementException e) {
				System.out.println("Element " + HoverElement + " was not found in DOM"+ e.getStackTrace());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error occurred while hovering"+ e.getStackTrace());
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return attributeValue;
	}

	public String jsGetPlaceHolderTextColor(By locator) {
		String attributeValue=null;
		try {
			WebElement HoverElement = driver.findElement(locator);
			try {
				String readAttribute = "var style=window.getComputedStyle(arguments[0],'::placeholder');var readAttribute;readAttribute=style.color;return readAttribute;";
				attributeValue=(String)((JavascriptExecutor) driver).executeScript(readAttribute,HoverElement);
				System.out.println(attributeValue);
				if(attributeValue.contains("rgb(226, 226, 227)")) {
					attributeValue="grey";
				}
			}
			catch (StaleElementReferenceException e) {
				System.out.println("Element with " + HoverElement + "is not attached to the page document"+ e.getStackTrace());
			} catch (NoSuchElementException e) {
				System.out.println("Element " + HoverElement + " was not found in DOM"+ e.getStackTrace());
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error occurred while hovering"+ e.getStackTrace());
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return attributeValue;
	}

}
