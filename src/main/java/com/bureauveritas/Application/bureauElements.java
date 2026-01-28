package com.bureauveritas.Application;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.orange.utils.ElementManager;

public class bureauElements extends ElementManager {

	WebDriver driver;

	public bureauElements(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = ("firstName"))
	public WebElement firstname;

	@FindBy(xpath = ("//input[@id='lastName']"))
	public WebElement lastname;

	@FindBy(xpath = ("//input[@id='email']"))
	public WebElement email;

	@FindBy(xpath = ("//input[@id='phoneNumber']"))
	public WebElement phonenumber;

	@FindBy(xpath = ("//select[@id=\"country\"]"))
	public WebElement country;

	@FindBy(xpath = ("//select[@id='custsex\']"))
	public WebElement sex;

	@FindBy(xpath = ("//select[@id='howDidYouLearnAboutThisOpportunity']"))
	public WebElement howDidYouLearnAboutThisOpportunity;

	@FindBy(xpath = ("//select[@id='custdetail1']]"))
	public WebElement providedetails;

	@FindBy(xpath = ("//span[@class='radio-text' and text()='Hispanic or Latino'][1]"))
	public WebElement Ethnicity;

	@FindBy(xpath = (("//span[@class='radio-text'][3]")))
	public WebElement RadioRace;

	@FindBy(xpath = (("//span[@class='radio-text'])[12]")))
	public WebElement veteran;

	@FindBy(xpath = ("//button[@class='btn primary-button btn-submit']"))
	public WebElement submission;

}