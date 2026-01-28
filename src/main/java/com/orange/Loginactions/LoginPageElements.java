package com.orange.Loginactions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orange.utils.ElementManager;

public class LoginPageElements extends ElementManager {

	WebDriver driver;

	public LoginPageElements(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(name = "username")
	public WebElement loginUserName;

	@FindBy(name = "password")
	public WebElement loginpassword;

	@FindBy(className = "orangehrm-login-button")
	public WebElement click;

	@FindBy(xpath = "//p[text()='Forgot your password? ']")
	public WebElement forgotPassword;

	@FindBy(xpath = "(//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"])[1]")
	public WebElement visible;

}
