package com.orange.utils;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementManager {

	private static final Logger logger = LogManager.getLogger(ElementManager.class);

	private WebDriver driver;
	private WebDriverWait wait;
	private Actions actionBuilder;

	// ✅ Default constructor (for backward compatibility)
	public ElementManager() {
	}

	// ✅ Main constructor
	public ElementManager(WebDriver driver, Duration explicitTime) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, explicitTime);
		this.actionBuilder = new Actions(driver);
	}

	/* -------------------- FIND ELEMENTS -------------------- */

	public WebElement getElement(By byType, Duration explicitTime) {
		try {
			logger.info("Waiting for element: {}", byType);
			return new WebDriverWait(driver, explicitTime).until(ExpectedConditions.visibilityOfElementLocated(byType));
		} catch (Exception e) {
			logException(byType, "Element", explicitTime);
			return null;
		}
	}

	public List<WebElement> getElements(By byType, Duration explicitTime) {
		try {
			return new WebDriverWait(driver, explicitTime)
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byType));
		} catch (Exception e) {
			logException(byType, "Elements", explicitTime);
			return null;
		}
	}

	/* -------------------- CLICK & TYPE -------------------- */

	public void elementClick(WebDriver driver, WebElement element, Duration explicitTime) {
		try {
			waitUntilVisible(driver, element, explicitTime);
			element.click();
			logger.info("Clicked on element");
		} catch (Exception e) {
			logException(element, "Click", explicitTime);
		}
	}

	public void elementSendKeys(WebDriver driver, WebElement element, String value, Duration explicitTime) {
		try {
			waitUntilVisible(driver, element, explicitTime);
			element.clear();
			element.sendKeys(value);
			logger.info("Entered value '{}'", value);
		} catch (Exception e) {
			logException(element, "SendKeys", explicitTime);
		}
	}

	/* -------------------- DROPDOWN -------------------- */

	public void elementSelectIndex(WebDriver driver, WebElement element, int index, Duration explicitTime) {
		try {
			waitUntilVisible(driver, element, explicitTime);
			new Select(element).selectByIndex(index);
			logger.info("Selected dropdown index {}", index);
		} catch (Exception e) {
			logException(element, "Dropdown Select", explicitTime);
		}
	}

	public void elementSelectText(WebDriver driver, WebElement element, String text, Duration explicitTime) {
		try {
			waitUntilVisible(driver, element, explicitTime);
			new Select(element).selectByVisibleText(text);
			logger.info("Selected dropdown text {}", text);
		} catch (Exception e) {
			logException(element, "Dropdown Select", explicitTime);
		}
	}

	/* -------------------- ACTIONS -------------------- */

	public void elementDragAndDrop(WebDriver driver, WebElement sourceElement, int xAxis, int yAxis) {
		actionBuilder = new Actions(driver);
		actionBuilder.dragAndDropBy(sourceElement, xAxis, yAxis).perform();
		logger.info("Drag and drop performed");
	}

	/* -------------------- JS -------------------- */

	public void jsClick(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		logger.info("Clicked using JavaScript");
	}

	public void scrollIntoView(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		logger.info("Scrolled into view");
	}

	/* -------------------- WAITS -------------------- */

	private void waitUntilVisible(WebDriver driver, WebElement element, Duration explicitTime) {
		new WebDriverWait(driver, explicitTime).until(ExpectedConditions.visibilityOf(element));
	}

	/* -------------------- ERROR HANDLING -------------------- */

	private void logException(Object locator, String action, Duration explicitTime) {
		String message = "TIMEOUT after " + explicitTime + " while performing " + action + " on " + locator;
		logger.error(message);
	}
}
