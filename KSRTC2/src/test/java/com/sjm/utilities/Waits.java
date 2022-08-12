package com.sjm.utilities;

import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Waits {

	private final static Logger log = LogManager.getLogger(Waits.class);
	private static WebDriverWait wait = null;

	public static void waitFor(long timeintervel) {

		try {

			Thread.sleep(timeintervel);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void waitForPageLoad(WebDriver driver) {

		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(pageLoadCondition);

		} catch (Throwable error) {
			Assert.fail("Timeout waiting for Page Load Request to complete.");

		}
	}

	public static boolean waitForVisibilityOfElement(WebElement element, WebDriver driver) throws Exception {

		wait = null;

		try {
			fluentWait(element, driver);
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (NoSuchElementException e) {
			return false;
		} catch (StaleElementReferenceException f) {
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static WebElement fluentWait(final WebElement element, WebDriver driver) {

		waitForPageLoad(driver);
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					// Check for condition in every 2 seconds
					.pollingEvery(Duration.ofSeconds(1))
					// Till time out i.e. 60 seconds
					.withTimeout(Duration.ofSeconds(20)).ignoring(NoSuchElementException.class);

			wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					log.info("Rechecking at time = " + new Date() + "The element---->" + element);
					return element;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception in Fluent wait method");

		}

		return element;

	}

	public static boolean waitElementToBeClickable(WebElement element, WebDriver driver) {
		wait = null;

		try {
			fluentWait(element, driver);
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(element));

			return true;

		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
