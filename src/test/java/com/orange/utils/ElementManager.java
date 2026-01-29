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

import org.testng.Assert;
import org.testng.Reporter;

/**
 * This class contains reusable web-element related methods
 */
public class ElementManager {

	private static final Logger logger = LogManager.getLogger(ElementManager.class);

	private WebDriver driver;
	private WebDriverWait wait;
	private Actions actionBuilder;

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
			logger.error("Element not found: {}", byType, e);
			catchException(byType, "Element", explicitTime);
			return null;
		}
	}

	public List<WebElement> getElements(By byType, Duration explicitTime) {
		try {
			return new WebDriverWait(driver, explicitTime)
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byType));
		} catch (Exception e) {
			logger.error("Elements not found: {}", byType, e);
			catchException(byType, "Elements", explicitTime);
			return null;
		}
	}

	/* -------------------- CLICK & TYPE -------------------- */

	public void click(By byType, Duration explicitTime) {
		WebElement element = getElement(byType, explicitTime);
		element.click();
		logger.info("Clicked on element: {}", byType);
	}

	public void sendKeys(By byType, String value, Duration explicitTime) {
		WebElement element = getElement(byType, explicitTime);
		element.clear();
		element.sendKeys(value);
		logger.info("Entered value '{}' in element {}", value, byType);
	}

	/* -------------------- DROPDOWN -------------------- */

	public void selectByVisibleText(By byType, String text, Duration explicitTime) {
		WebElement element = getElement(byType, explicitTime);
		Select select = new Select(element);
		select.selectByVisibleText(text);
		logger.info("Selected '{}' from dropdown {}", text, byType);
	}

	/* -------------------- WAIT UTILITIES -------------------- */

	public void waitForInvisibility(By byType, Duration explicitTime) {
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(byType));
			logger.info("Element disappeared: {}", byType);
		} catch (Exception e) {
			logger.error("Progress bar timeout", e);
			catchException(byType, "Progress Bar", explicitTime);
		}
	}

	/* -------------------- JAVASCRIPT -------------------- */

	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		logger.info("Scrolled into view");
	}

	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
		logger.info("Clicked using JavaScript");
	}

	/* -------------------- ACTIONS -------------------- */

	public void elementDragAndDrop(WebDriver driver, WebElement sourceElement, int xAxis, int yAxis) {

		actionBuilder = new Actions(driver);
		actionBuilder.dragAndDropBy(sourceElement, xAxis, yAxis).perform();
		logger.info("Drag and drop performed");
	}

	/* -------------------- EXCEPTION HANDLING -------------------- */

	private void catchException(By byType, String elementMSG, Duration explicitTime) {
		String message = "TIMEOUT after " + explicitTime + " - " + byType;
		Reporter.log(message, true);
		logger.error(message);
		Assert.fail(message);
	}

	private void catchException(WebElement element, String elementMSG, Duration explicitTime) {
		String message = "TIMEOUT after " + explicitTime + " - " + element;
		Reporter.log(message, true);
		logger.error(message);
		Assert.fail(message);
	}
}
