package com.orange.utils;

import org.apache.log4j.Logger;

/**
 * This class having the test logger methods
 */
public class TestLogger {
	/**
	 * Initializing the logger class instance
	 */
	public static Logger logger = Logger.getLogger(TestLogger.class.getName());

	/**
	 * Based on this method name user knows the which method is executing
	 * 
	 * @param methodName Test case name
	 */
	public static void scenarioName(String scenarioName) {
		logger.info("Test case name = " + scenarioName);
	}

	/**
	 * Generally this method will be used for Before and After methods and Based on
	 * this method name user knows the which method is executing
	 * 
	 * @param methodName Test case name
	 */
	public static void beforORAfterHook(String hookName) {
		logger.info(hookName);
	}

	/**
	 * This method will help you to identify web-element
	 * 
	 * @param elementName Value of the WebElement
	 */
	public static void elementIdentifier(String elementName) {
		if (elementName != null && !elementName.isEmpty())
			logger.info("Web element = '" + elementName + "' is identified successfully");
	}

	/**
	 * This method will help you to identify the which element was clicked
	 * 
	 * @param elementName Value of the WebElement
	 */
	public static void elementClickIdentifier(String elementName) {
		if (elementName != null && !elementName.isEmpty())
			logger.info("Web element = '" + elementName + "' is identified and clicked");
	}

	/**
	 * This method will help to identify the check-boxes
	 * 
	 * @param message Developer message
	 */
	public static void elementCheckBoxIdentifier(String message) {
		logger.info(message);
	}

	/**
	 * This method will help to identify the failed web-elements
	 * 
	 * @param elementName Value of the WebElement
	 */
	public static void elementIdentifierFail(String elementName) {
		if (elementName != null && !elementName.isEmpty())
			logger.info("Web element = '" + elementName + "' is not identified");
		logger.info("**********************************************************");
	}

	/**
	 * This method will identify the web-elements
	 * 
	 * @param elementName Value of the WebElement
	 */
	public static void elementSelect(String elementName) {
		if (elementName != null && !elementName.isEmpty())
			logger.info(elementName);
	}

	/**
	 * This method was mostly used in try catch block
	 * 
	 * @param message Developer message
	 */
	public static void appInfo(String message) {
		logger.info("*************************** " + "\n" + message);
	}

	/**
	 * This method print/show the user message
	 * 
	 * @param message Developer message
	 */
	public static void testMessage(String message) {
		logger.info(message);
		logger.info("**********************************************************");
	}

	/**
	 * @author: Satyananda Paital error log
	 */
	public static void appError(String message) {
		logger.error("Error: " + "\n" + message);
	}

	/**
	 * Generally this method will be used for Before and After methods and Based on
	 * this method name user knows the which method is executing
	 * 
	 * @param methodName Test case name
	 */
	public static void beforORAfterMetod(String methodName) {
		logger.info(methodName);
	}
}
