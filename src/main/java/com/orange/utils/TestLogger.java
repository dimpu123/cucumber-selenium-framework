package com.orange.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class having the test logger methods
 */
public class TestLogger {

	private static final Logger logger = LogManager.getLogger(TestLogger.class);

	public static void scenarioName(String scenarioName) {
		logger.info("Test case name = {}", scenarioName);
	}

	public static void beforORAfterHook(String hookName) {
		logger.info(hookName);
	}

	public static void elementIdentifier(String elementName) {
		if (elementName != null && !elementName.isEmpty())
			logger.info("Web element '{}' is identified successfully", elementName);
	}

	public static void elementClickIdentifier(String elementName) {
		if (elementName != null && !elementName.isEmpty())
			logger.info("Web element '{}' is identified and clicked", elementName);
	}

	public static void elementCheckBoxIdentifier(String message) {
		logger.info(message);
	}

	public static void elementIdentifierFail(String elementName) {
		if (elementName != null && !elementName.isEmpty())
			logger.error("Web element '{}' is not identified", elementName);
		logger.error("**********************************************************");
	}

	public static void elementSelect(String elementName) {
		if (elementName != null && !elementName.isEmpty())
			logger.info(elementName);
	}

	public static void appInfo(String message) {
		logger.info("***************************\n{}", message);
	}

	public static void testMessage(String message) {
		logger.info(message);
		logger.info("**********************************************************");
	}

	public static void appError(String message) {
		logger.error("Error:\n{}", message);
	}

	public static void beforORAfterMetod(String methodName) {
		logger.info(methodName);
	}
}
