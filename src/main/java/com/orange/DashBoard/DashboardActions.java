package com.orange.DashBoard;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class DashboardActions extends DashBoardELements {

	public DashboardActions(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void clickOnPimTab(WebDriver driver) {

		elementClick(driver, clicks, Duration.ofSeconds(10));

	}

	public void enterNewUserName(WebDriver driver, String newUserName) {
		elementSendKeys(driver, UserName, newUserName, Duration.ofSeconds(10));

	}

	public void enterNewUserRole(WebDriver driver, String newUserRole) {

		elementSelect(driver, userRole, newUserRole, Duration.ofSeconds(20));
	}

	public void enterNewEmpName(WebDriver driver, String newEmpName) {

		elementSelect(driver, EmployeeName, newEmpName, Duration.ofSeconds(20));
	}

}
