package com.orange.utils;

import java.time.Duration;
import java.util.List;

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
 * This class have the web-element related methods
 */
public class ElementManager {

	private WebDriverWait wait;
	private Actions actionBuilder;
	private String elementMSG = "";

	/**
	 * This method is used to interact with a web element by "Clicking on it" using
	 * the Selenium WebDriver findElement(By), WebDriverWait & elementToBeClickable.
	 * If the web element does not exist timeout exception will be thrown. This
	 * method will also log the locator path in the Log4J log file.
	 * 
	 * @param driver       Is the WebDriver object being used for example (Browsers:
	 *                     Chrome, FireFox or IE etc...)
	 * @param element      Is the name of the variable used to store the locator
	 *                     path of the web element to interact with
	 * @param explicitTime Is the time to wait for individual web element to exists
	 */
	public void elementClick(WebDriver driver, WebElement element, Duration explicitTime) {
		try {
			wait = new WebDriverWait(driver, explicitTime);
			if (wait.until(ExpectedConditions.elementToBeClickable(element)) != null) {
				if (element.isDisplayed() && element.isEnabled()) {
					TestLogger.elementClickIdentifier(element.getText());
					element.click();
				}
			}
		} catch (Exception e) {
			catchException(element, elementMSG, explicitTime);
		}
	}

	/**
	 * This method is used to interact with a web element by "Clicking on it" using
	 * the Selenium WebDriver findElement(By), WebDriverWait & elementToBeClickable.
	 * If the web element does not exist timeout exception will be thrown. This
	 * method will also log the locator path in the Log4J log file.
	 * 
	 * @param driver       Is the WebDriver object being used for example (Browsers:
	 *                     Chrome, FireFox or IE etc...)
	 * @param element      Is the name of the variable used to store the locator
	 *                     path of the web element to interact with
	 * @param explicitTime Is the time to wait for individual web element to exists
	 */
	public void elementVisibilityClick(WebDriver driver, WebElement element, Duration explicitTime) {
		try {
			wait = new WebDriverWait(driver, explicitTime);
			if (wait.until(ExpectedConditions.visibilityOf(element)) != null) {
				if (element.isDisplayed() && element.isEnabled()) {
					TestLogger.elementClickIdentifier(element.getText());
					element.click();
				}
			}
		} catch (Exception e) {
			catchException(element, elementMSG, explicitTime);
		}
	}

	/**
	 * This method is used for pass the value to edit fields using the Selenium
	 * WebDriver findElement(By), WebDriverWait & elementToBeClickable. If the web
	 * element does not exist timeout exception will be thrown. This method will
	 * also log the locator path in the Log4J log file.
	 * 
	 * @param driver       Is the WebDriver object being used for example (Browsers:
	 *                     Chrome, FireFox or IE etc...)
	 * @param element      Is the name of the variable used to store the locator
	 *                     path of the web element to interact with
	 * @param value        Is the pass the value to element
	 * @param explicitTime Is the time to wait for individual web element to exists
	 */
	public void elementSendKeys(WebDriver driver, WebElement element, String value, Duration explicitTime) {
		try {
			wait = new WebDriverWait(driver, explicitTime);
			if (wait.until(ExpectedConditions.elementToBeClickable(element)) != null) {
				if (element.isDisplayed() && element.isEnabled()) {
					element.clear();
					element.sendKeys(value);
				}
			}
		} catch (Exception e) {
			catchException(element, elementMSG, explicitTime);
		}
	}

	/**
	 * This method is used to interact with a web element by "Hovering over it"
	 * using the Selenium WebDriver findElement(By), WebDriverWait &
	 * hoverOverRegistrar. If the web element does not exist a screenshot will be
	 * taken and a timeout exception will be thrown. This method will also log the
	 * locator path in the Log4J log file.
	 * 
	 * @param driver       Is the WebDriver object being used for example (Browsers:
	 *                     Chrome, FireFox or IE etc...)
	 * @param element      Is the name of the variable used to store the locator
	 *                     path of the web element to interact with
	 * @param scroll       If set to true the element will scroll into view if set
	 *                     to false scrolling will be disabled
	 * @param explicitTime Is the time to wait for individual web element to exists
	 */
	public void elementMouseHover(WebDriver driver, WebElement element, Duration explicitTime) {
		try {
			wait = new WebDriverWait(driver, explicitTime);
			if (wait.until(ExpectedConditions.elementToBeClickable(element)) != null) {
				if (element != null) {
					actionBuilder = new Actions(driver);
					actionBuilder.moveToElement(element).build();
					actionBuilder.perform();
					TestLogger.elementSelect("Successfully Mouse hover on element");
				} else {
					TestLogger.elementSelect("Element was not visible, So UnSuccessfully of mouse hover");
				}
			}
		} catch (Exception e) {
			catchException(element, elementMSG, explicitTime);
		}
	}

	/***
	 * This method will help you to scroll the web-page
	 * 
	 * @param driver
	 * @param scroll
	 */
	public void elementScroll(WebDriver driver, Boolean scroll) {
		if (scroll) {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)", "");
		} else {
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight*0.5)", "");
		}
	}

	/**
	 * This method is used to mandatory to set a 'check box' check or un-check using
	 * the Selenium WebDriver findElement(By) & WebDriverWait. This method will also
	 * log the locator path in the Log4J log file. boolean bChecked (true, false}
	 * provide final stage of check box {checked, Unchecked}
	 * 
	 * @param driver       Is the WebDriver object being used for example (Browsers:
	 *                     Chrome, FireFox or IE etc...)
	 * @param element      Is the name of the variable used to store the locator
	 *                     path of the web element to verify
	 * @param check        Is used to check or un-check the check box
	 * @param explicitTime Is the time to wait for individual web element to exists
	 */
	public void elementSetCheckbox(WebDriver driver, String elementName, WebElement element, boolean check,
			Duration explicitTime) {
		try {
			wait = new WebDriverWait(driver, explicitTime);
			if (wait.until(ExpectedConditions.elementToBeClickable(element)) != null) {
				if (element.isSelected()) {
					if (!check) {
						element.click();
						if (elementName.isEmpty()) {
							TestLogger.elementCheckBoxIdentifier(
									"'" + element.getText() + "'" + "checkbox is identified and checkbox un-checked");
						} else {
							TestLogger.elementCheckBoxIdentifier(
									"'" + elementName + "'" + "checkbox is identified and checkbox un-checked");
						}
					}

				} else {
					if (check) {
						element.click();
						if (elementName.isEmpty()) {
							TestLogger.elementCheckBoxIdentifier(
									"'" + element.getText() + "'" + "checkbox is identified and checkbox checked");
						} else {
							TestLogger.elementCheckBoxIdentifier(
									"'" + elementName + "'" + "checkbox is identified and checkbox checked");
						}
					}
				}
			}

		} catch (Exception e) {
			catchException(element, elementMSG, explicitTime);
		}
	}

	/**
	 * This method is used to interact with a web element by "Capturing text" using
	 * the Selenium WebDriver findElement(By), WebDriverWait & GetText. If the web
	 * element does not exist a screenshot will be taken and a timeout exception
	 * will be thrown. This method will also log the locator path in the Log4J log
	 * file.
	 * 
	 * @param driver       is the WebDriver object being used for example (Browsers:
	 *                     Chrome, FireFox or IE etc...)
	 * @param byType       is the name of the variable used to store the locator
	 *                     path of the web element to interact with
	 * @param explicittime Is the time to wait for individual web element to exists
	 * @return returns a string of text
	 */
	public String elementGetText(WebDriver driver, WebElement element, Duration explicittime) {
		String returnText = null;
		try {
			wait = new WebDriverWait(driver, explicittime);
			if (wait.until(ExpectedConditions.elementToBeClickable(element)) != null) {
				if (element.isDisplayed() && element.isEnabled()) {
					TestLogger.elementIdentifier(element.getText());
					returnText = element.getText();
				}
			}
		} catch (Exception e) {
			catchException(element, elementMSG, explicittime);
		}
		return returnText;
	}

	/**
	 * This method is used to interact with a web element of type input by
	 * "Capturing the value" using the Selenium WebDriver findElement(By),
	 * WebDriverWait & GetAttribute("value"). If the web element does not exist a
	 * screenshot will be taken and a timeout exception will be thrown. This method
	 * will also log the locator path in the Log4J log file.
	 * 
	 * @param driver       is the WebDriver object being used for example (Browsers:
	 *                     Chrome, FireFox or IE etc...)
	 * @param element      is the name of the variable used to store the locator
	 *                     path of the web element to interact with
	 * @param attribute    is the attribute of webelement for ex:<button name="btnK"
	 *                     id="gbqfba" aria-label="Google Search> here we can use
	 *                     any of those attribute and get the value 'name' or 'id'
	 *                     or 'aria-label'
	 * @param explicittime Is the time to wait for individual web element to exists
	 * @return returns a string of text equal to the value in the input box
	 */
	public String elementGetValue(WebDriver driver, WebElement element, String attribute, Duration explicittime) {
		String returnText = null;
		try {
			wait = new WebDriverWait(driver, explicittime);
			if (wait.until(ExpectedConditions.visibilityOf(element)) != null) {
				if (element.isDisplayed() && element.isEnabled()) {
					returnText = element.getAttribute(attribute);
				}
			}
		} catch (Exception e) {
			catchException(element, elementMSG, explicittime);
		}
		return returnText;
	}

	/**
	 * This method is used to interact with a List by providing the optionValue
	 * using the Selenium WebDriver findElement(By), WebDriverWait &
	 * selectByVisibleText. If the web element does not exist a screenshot will be
	 * taken and a timeout exception will be thrown. This method will also log the
	 * locator path in the Log4J log file.
	 * 
	 * @param driver       Is the WebDriver object being used for example (Browsers:
	 *                     Chrome, FireFox or IE etc...)
	 * @param element      Is the name of the variable used to store the locator
	 *                     path of the web element to interact with
	 * @param optionValue  Is the string variable being passed that will be
	 *                     entered/typed into a field
	 * @param explicittime Is the time to wait for individual web element to exists
	 * 
	 */
	public void elementSelect(WebDriver driver, WebElement element, String optionValue, Duration explicittime) {

		try {
			wait = new WebDriverWait(driver, explicittime);
			if (wait.until(ExpectedConditions.visibilityOf(element)) != null) {
				new Select(element).selectByVisibleText(optionValue);
			}
		} catch (Exception e) {
			catchException(element, optionValue, explicittime);
		}
	}

	/**
	 * This method is used to interact with a List by providing the optionValue
	 * using the Selenium WebDriver findElement(By), WebDriverWait & selectByIndex.
	 * If the web element does not exist a screenshot will be taken and a timeout
	 * exception will be thrown. This method will also log the locator path in the
	 * Log4J log file.
	 * 
	 * @param driver       Is the WebDriver object being used for example (Browsers:
	 *                     Chrome, FireFox or IE etc...)
	 * @param element      Is the name of the variable used to store the locator
	 *                     path of the web element to interact with
	 * @param optionValue  Is the int variable being passed that will be
	 *                     entered/typed into a field
	 * @param explicittime Is the time to wait for individual web element to exists
	 * 
	 */
	public void elementSelectIndex(WebDriver driver, WebElement element, int optionValue, Duration explicittime) {

		try {
			wait = new WebDriverWait(driver, explicittime);
			if (wait.until(ExpectedConditions.visibilityOf(element)) != null) {
				new Select(element).selectByIndex(optionValue);
			}
		} catch (Exception e) {
			catchException(element, elementMSG, explicittime);
		}
	}

	/**
	 * This method is used to interact with a List by providing the optionValue
	 * using the Selenium WebDriver findElement(By), WebDriverWait &
	 * selectByVisibleText. If the web element does not exist a screenshot will be
	 * taken and a timeout exception will be thrown. This method will also log the
	 * locator path in the Log4J log file.
	 * 
	 * @param driver       Is the WebDriver object being used for example (Browsers:
	 *                     Chrome, FireFox or IE etc...)
	 * @param element      Is the name of the variable used to store the locator
	 *                     path of the web element to interact with
	 * @param optionValue  Is the string variable being passed that will be
	 *                     entered/typed into a field
	 * @param explicittime Is the time to wait for individual web element to exists
	 * 
	 */
	public void elementCSSSelect(WebDriver driver, WebElement element, String optionValue, Duration explicittime) {

		try {
			wait = new WebDriverWait(driver, explicittime);
			if (wait.until(ExpectedConditions.elementToBeSelected(element)) != null) {
				new Select(element).selectByVisibleText(optionValue);
			}
		} catch (Exception e) {
			catchException(element, optionValue, explicittime);
		}
	}

	/**
	 * This method is return boolean value based on the value to availability and
	 * fields using the Selenium WebDriver findElement(By), WebDriverWait &
	 * elementToBeClickable. If the web element does not exist timeout exception
	 * will be thrown. This method will also log the locator path in the Log4J log
	 * file.
	 * 
	 * @param driver       Is the WebDriver object being used for example (Browsers:
	 *                     Chrome, FireFox or IE etc...)
	 * @param elements     Is the name of the variable used to store the locator
	 *                     path of the web element to interact with
	 * @param value
	 * @param explicitTime Is the time to wait for individual web element to exists
	 */
	public boolean elementAvailability(WebDriver driver, List<WebElement> elements, String value,
			Duration explicitTime) {

		boolean elementIdentifier = false;
		try {
			elementIdentifier = elements.size() > 0;
			if (elementIdentifier) {
				TestLogger.elementIdentifier(value);
			} else {
				TestLogger.elementIdentifierFail(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return elementIdentifier;
	}

	/**
	 * This method is used to find the progress bar. If the web element does not
	 * exist timeout exception will be thrown. This method will also log the locator
	 * path in the Log4J log file.
	 * 
	 * @param driver       Is the WebDriver object being used for example (Browsers:
	 *                     Chrome, FireFox or IE etc...)
	 * @param byType       Is the name of the variable used to store the locator
	 *                     path of the web element to interact with
	 * @param explicittime Is the time to wait for individual web element to exists
	 */
	public void elementProgressBar(WebDriver driver, By byType, Duration explicitTime) {

		try {
			boolean isProgressPresent = driver.findElements(byType).size() > 0;
			if (isProgressPresent) {
				new WebDriverWait(driver, explicitTime).until(ExpectedConditions.invisibilityOfElementLocated(byType));
			}
		} catch (Exception e) {
			elementMSG = "Progress Bar";
			catchException(byType, elementMSG, explicitTime);
		}
	}

	/**
	 * This method is used to perform the Drag and Drop
	 */
	public void elementDragAndDrop(WebDriver driver, WebElement sourceElement, int xAxis, int yAxis) {
		actionBuilder = new Actions(driver);
		actionBuilder.dragAndDropBy(sourceElement, xAxis, yAxis).build().perform();
	}

	/**
	 * This method is used to catch the exception and print the error message on
	 * Reporter and Asserts
	 * 
	 * @param byType
	 * @param elementMSG
	 * @param explicittime
	 */

	private void catchException(By byType, String elementMSG, Duration explicittime) {
		if (!elementMSG.isEmpty() && elementMSG.length() > 1) {
			Reporter.log("TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - "
					+ elementMSG, true);
			TestLogger.elementIdentifierFail(elementMSG);
			Assert.fail("TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - "
					+ elementMSG);
			elementMSG = "";
		} else {
			Reporter.log(
					"TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - " + byType,
					true);
			TestLogger.elementIdentifierFail("" + byType);
			Assert.fail(
					"TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - " + byType);
		}
	}

	/**
	 * This method is used to catch the exception and print the error message on
	 * Reporter and Asserts
	 * 
	 * @param byType
	 * @param elementMSG
	 * @param explicittime
	 */

	private void catchException(WebElement element, String elementMSG, Duration explicittime) {
		if (!elementMSG.isEmpty() && elementMSG.length() > 1) {
			Reporter.log("TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - "
					+ elementMSG, true);
			TestLogger.elementIdentifierFail(elementMSG);
			Assert.fail("TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - "
					+ elementMSG);
			elementMSG = "";
		} else {
			Reporter.log(
					"TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - " + element,
					true);
			TestLogger.elementIdentifierFail("" + element);
			Assert.fail(
					"TIMEOUT EXCEPTION element does not exist after waiting " + explicittime + " seconds - " + element);
		}
	}

}
