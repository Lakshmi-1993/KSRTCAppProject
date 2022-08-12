package com.sjm.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.asserts.SoftAssert;

import static com.sjm.utilities.Waits.*;


public class GenericLibraries {

	private final static Logger log = LogManager.getLogger(GenericLibraries.class);
      private static SoftAssert softAssert=null;
	

	public static String getElementText(WebElement element, WebDriver driver) {
		
		String actTxt = null;
		try {
			if (waitElementToBeClickable(element, driver)) {

				return actTxt = element.getText().trim().toString();
				
			}

		} catch (Exception e) {

			
		}
		return actTxt;
	}



	public static boolean setValue(WebElement element, String value, WebDriver driver) {
		
		try {
			if (!waitElementToBeClickable(element, driver)) {

				return false;
			}
			element.clear();

			element.sendKeys(value);
			
			return true;

		} catch (Exception e) {

			log.fatal("Failed to send keys because cannot focus element");

			e.printStackTrace();
			
			return false;
		}
	}


	
	public static boolean clickElement(WebElement element, WebDriver driver) {
		
		if (!waitElementToBeClickable(element, driver)) {

			return false;
		}

		try {

			element.click();

			log.info(element + " Element is clicked");
			
			return true;
		} catch (StaleElementReferenceException e) {

			log.fatal("StaleElementReferenceException");

		} catch (Exception e) {

			
			return false;
		}

		return true;
	}

	
	public static boolean isDisplayed(WebElement element) {
		
		boolean elementPresent = false;

		try {
			elementPresent = element.isDisplayed();

			
			return elementPresent;

		} catch (NoSuchElementException e) {

			log.fatal("No such element displayed");

			
			return false;
		}

		catch (StaleElementReferenceException f) {
			
			return false;
		}
	}

	
	public static void assertion(boolean pass) {
		
		if (pass) {
			assertionPass();
		} else {
			assertionFail();

		}
		
	}

	
	public static boolean isEnabled(WebElement element) {
		
		try {
			if (isDisplayed(element) && element.isEnabled()) {
				return true;
			}
		} catch (Exception e) {

			log.fatal("invisibilityOfElementLocated exception");

			e.printStackTrace();
			
			return false;
		}
		return false;

	}


	public static  void assertionPass() {
	
		softAssert = null;
		softAssert = new SoftAssert();
		softAssert.assertTrue(true);
		
		softAssert.assertAll();
	}

	public static void assertionFail() {
		
		softAssert = null;
		softAssert = new SoftAssert();
		softAssert.assertFalse(true);
		softAssert.assertAll();
	}

	
	
	
}
