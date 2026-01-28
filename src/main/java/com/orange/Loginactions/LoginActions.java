package com.orange.Loginactions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class LoginActions extends LoginPageElements {

	public LoginActions(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void enterUserName(WebDriver driver, String userName) {
		elementSendKeys(driver, loginUserName, userName, Duration.ofSeconds(20));

	}

	public void enterPassword(WebDriver driver, String password) {
		elementSendKeys(driver, loginpassword, password, Duration.ofSeconds(10));
	}

	public void clickOnLogin(WebDriver driver) {

		elementClick(driver, click, Duration.ofSeconds(10));

	}

	public void visibility(WebDriver driver, String visiblity) {

		elementVisibilityClick(driver, visible,Duration.ofSeconds(10));
	}

}
