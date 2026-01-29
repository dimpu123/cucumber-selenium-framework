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

	public boolean click(By byType, Duration explicitTime) {
		WebElement element = getElement(byType, explicitTime);
		if (element != null) {
			element.click();
			logger.info("Clicked on element: {}", byType);
			return true;
		}
		return false;
	}

	public boolean sendKeys(By byType, String value, Duration explicitTime) {
		WebElement element = getElement(byType, explicitTime);
		if (element != null) {
			element.clear();
			element.sendKeys(value);
			logger.info("Entered value '{}' in element {}", value, byType);
			return true;
		}
		return false;
	}

	/* -------------------- DROPDOWN -------------------- */

	public boolean selectByVisibleText(By byType, String text, Duration explicitTime) {
		WebElement element = getElement(byType, explicitTime);
		if (element != null) {
			new Select(element).selectByVisibleText(text);
			logger.info("Selected '{}' from dropdown {}", text, byType);
			return true;
		}
		return false;
	}

	/* -------------------- WAIT UTILITIES -------------------- */

	public boolean waitForInvisibility(By byType, Duration explicitTime) {
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(byType));
			logger.info("Element disappeared: {}", byType);
			return true;
		} catch (Exception e) {
			logException(byType, "Progress Bar", explicitTime);
			return false;
		}
	}

	/* -------------------- JAVASCRIPT -------------------- */

	public void scrollIntoView(WebElement element) {
		if (element != null) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			logger.info("Scrolled into view");
		}
	}

	public void jsClick(WebElement element) {
		if (element != null) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
			logger.info("Clicked using JavaScript");
		}
	}

	/* -------------------- ACTIONS -------------------- */

	public boolean elementDragAndDrop(WebElement sourceElement, int xAxis, int yAxis) {
		try {
			actionBuilder.dragAndDropBy(sourceElement, xAxis, yAxis).perform();
			logger.info("Drag and drop performed");
			return true;
		} catch (Exception e) {
			logger.error("Drag and drop failed", e);
			return false;
		}
	}

	/* -------------------- LOGGING ONLY (NO ASSERTIONS) -------------------- */

	private void logException(Object locator, String elementMSG, Duration explicitTime) {
		String message = "TIMEOUT after " + explicitTime + " - " + elementMSG + " : " + locator;
		logger.error(message);
	}
}
