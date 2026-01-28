package com.orange.DashBoard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orange.utils.ElementManager;

public class DashBoardELements extends ElementManager {

	WebDriver driver;

	public DashBoardELements(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "(//span[@class=\"oxd-text oxd-text--span oxd-main-menu-item--name\"])[1]")

	public WebElement clicks;

	@FindBy(xpath = "(//input[@class=\"oxd-input oxd-input--active\"])[2]")

	public WebElement UserName;

	@FindBy(xpath = "(//div[@class=\"oxd-select-text-input\"])[1]")

	public WebElement userRole;

	@FindBy(xpath = "//div[@class='oxd-autocomplete-text-input oxd-autocomplete-text-input--active']")

	public WebElement EmployeeName;

	@FindBy(xpath = "//h6[text()='Dashboard']")

	public WebElement Dashboard;

}
