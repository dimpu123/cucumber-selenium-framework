package com.bureauveritas.Application;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class bureauActions extends bureauElements {

	public bureauActions(WebDriver driver) {
		super(driver);

	}

	public void enterfirstname(WebDriver driver, String newfirstname) {

		elementSendKeys(driver, firstname, newfirstname, Duration.ofSeconds(100));

	}

	public void enterlastname(WebDriver driver, String newlastname) {

		elementSendKeys(driver, lastname, newlastname, Duration.ofSeconds(200));

	}

	public void enteremail(WebDriver driver, String newemail) {
		elementSendKeys(driver, email, newemail, Duration.ofSeconds(100));

	}

	public void enterphonenumber(WebDriver driver, String phoneno) {
		elementSendKeys(driver, phonenumber, phoneno, Duration.ofSeconds(100));
	}

	public void country() {

		elementSelectIndex(driver, country, 2, Duration.ofSeconds(200));

	}

	public void sex() {
		elementSelectIndex(driver, sex, 2, Duration.ofSeconds(200));

	}

	public void howDidYouLearnAboutThisOpportunity() {
		Select s2 = new Select(howDidYouLearnAboutThisOpportunity);
		s2.selectByIndex(2);
		System.out.println(s2);

	}

	public void providedetails() {
		Select s3 = new Select(providedetails);
		s3.selectByIndex(3);
		System.out.println(s3);
	}

	public void Ethnicites(WebDriver driver) {

		elementClick(driver, Ethnicity, Duration.ofSeconds(200));

	}

	public void Race(WebDriver driver) {
		elementClick(driver, RadioRace, Duration.ofSeconds(200));
	}

	public void miltary(WebDriver driver) {
		elementClick(driver, veteran, Duration.ofSeconds(200));
	}

	public void submit(WebDriver driver) {
		elementClick(driver, submission, Duration.ofSeconds(200));
	}
}