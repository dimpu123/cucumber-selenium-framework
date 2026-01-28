package stepDefinitions;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orange.Loginactions.LoginActions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import java.util.List;
import java.util.Map;

public class loginwithDataTable {
	WebDriver driver;
	LoginActions loginActions;

	@Given("User is on login page of HRM")
	public void user_is_on_login_page_of_hrm() {
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	}

	@When("User enters UserName and Password")
	public void user_enter_user_name_and_password(DataTable dataTable) {
		try {
			List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
			String UserName = null, Password = null;
			for (Map<String, String> credentials : data) {
				// This is to get the first data of the set (First Row + First Column)
				UserName = credentials.get("UserName");
				// This is to get the first data of the set (First Row + Second Column)
				Password = credentials.get("Password");
			}
//			driver.findElement(By.name("username")).sendKeys(UserName);
//			driver.findElement(By.name("password")).sendKeys(Password);
			loginActions.enterUserName(driver, UserName);
			loginActions.enterPassword(driver, Password);
			System.out.println("eclipse");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("Click on the login button")
	public void click_on_the_login_button() {
		try {
			driver.findElement(By.className("orangehrm-login-button")).click();
			loginActions.clickOnLogin(driver);
			Thread.sleep(5000);
			// String results =
			// driver.findElement(By.xpath("//h6[text()='Dashboard']")).getText();
			System.out.println("-------------" + results);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("User navigate to PIM tab")
	public void user_navigate_to_pim_tab() {
		try {
			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@href='/web/index.php/pim/viewPimModule']")).click();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Then("Enter PIM tab1 details")
	public void enter_pim_tab1_details(DataTable dataTable) {

		List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);
		String employeeNameValue = null, employeeIdValue = null, supervisorValue = null, employeeStatusValue = null,
				includeValue = null, jobTitleValue = null, subUnitValue = null;

		for (Map<String, String> credentials : data) {
			// This is to get the first data of the set (First Row + First Column)
			employeeNameValue = credentials.get("EmployeeName");
			// This is to get the first data of the set (First Row + Second Column)
			employeeIdValue = credentials.get("EmployeeId");
			supervisorValue = credentials.get("SupervisorName");
			employeeStatusValue = credentials.get("EmployeeStatus");
			includeValue = credentials.get("Include");
			jobTitleValue = credentials.get("JobTitle");
			subUnitValue = credentials.get("SubUnit");

		}
		driver.findElement(By.xpath("(//input[@placeholder='Type for hints...'])[1]")).sendKeys(employeeNameValue);

		driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")).sendKeys(employeeIdValue);

		driver.findElement(By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[1]")).click();

		// Wait for the dropdown to load the options
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		String dynamicXpathofDropDowns = "//div[@role='option']//span[text()='DropDownValue']";
		dynamicXpathofDropDowns = dynamicXpathofDropDowns.replace("DropDownValue", "employeeStatusValue"); // "//div[@role='option']//span[text()='Full-Time
																											// contract
																											// ']

		try {
			// Wait for visibility of the dropdown options
			List<WebElement> dropdownStatus = wait
					.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("dynamicXpathofDropDowns")));

			// Add a delay for stability
			Thread.sleep(2000);

			// Click on the desired option
			for (WebElement option : dropdownStatus) {
				if (option.getText().equals(employeeStatusValue)) {
					option.click();

				}
			}

			driver.findElement(By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[2]")).click();
			String dynamicXpathofDropDownsInclude = "//div[@role='option']//span[text()='DropDownValue']";
			dynamicXpathofDropDownsInclude = dynamicXpathofDropDownsInclude.replace("DropDownValue", "includeValue");

			List<WebElement> dropdownInclude = wait.until(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(dynamicXpathofDropDownsInclude)));

			Thread.sleep(2000);
			// Click on the desired option
			for (WebElement option : dropdownInclude) {
				if (option.getText().equals(includeValue)) {
					option.click();

				}
			}

			driver.findElement(By.xpath("(//input[@placeholder='Type for hints...'])[2]")).sendKeys(supervisorValue);

			List<WebElement> dropdownTitle = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
					By.xpath("//div[@role='option']//span[text()='Account Assistant']")));
			for (WebElement option : dropdownTitle) {
				if (option.getText().equals(jobTitleValue)) {
					option.click();
					Thread.sleep(5000);
				}
			}
			driver.findElement(By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[4]")).click();

			List<WebElement> dropdownUnit = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
					By.xpath("//div[@role='option']//span[text()='Administration']")));
			for (WebElement option : dropdownUnit) {
				if (option.getText().equals(subUnitValue)) {
					option.click();
					Thread.sleep(5000);
				}
			}
			driver.findElement(By.xpath("//button[@type='reset']")).click();
		} catch (InterruptedException e) {
			e.printStackTrace();

		} catch (Exception e) {
		}
	}
}

//	@Then("Enters all details in PIM tab")
//	public void enters_all_details_in_pim_tab() {
//		// Enter the employee name
//		driver.findElement(By.xpath("(//input[@placeholder='Type for hints...'])[1]")).sendKeys("SeleniumJava");
//
//		// Enter the employee ID
//		driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")).sendKeys("123456");
//
//		// Click on the dropdown to open it
//		driver.findElement(By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[1]")).click();
//
//		// Wait for the dropdown to load the options
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//
//		try {
//			// Wait for visibility of the dropdown options
//			List<WebElement> dropdownStatus = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
//					By.xpath("//div[@role='option']//span[text()='Full-Time Permanent']")));
//
//			// Add a delay for stability
//			Thread.sleep(5000);
//
//			// Click on the desired option
//			for (WebElement option : dropdownStatus) {
//				if (option.getText().equals("Full-Time Permanent")) {
//					option.click();
//					break;
//				}
//			}
//			driver.findElement(By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[2]")).click();
//
//			List<WebElement> dropdownInclude = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
//					By.xpath("//div[@role='option']//span[text()='Current Employees Only']")));
//
//			Thread.sleep(2000);
//			// Click on the desired option
//			for (WebElement option : dropdownInclude) {
//				if (option.getText().equals("Current Employees Only")) {
//					option.click();
//					break;
//				}
//			}
//			driver.findElement(By.xpath("(//input[@placeholder='Type for hints...'])[2]")).sendKeys("SuneelSir");
//			Thread.sleep(3000);
//			driver.findElement(By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[3]")).click();
//			List<WebElement> dropdownTitle = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
//					By.xpath("//div[@role='option']//span[text()='Account Assistant']")));
//			for (WebElement option : dropdownTitle) {
//				if (option.getText().equals("Account Assistant")) {
//					option.click();
//					break;
//				}
//			}
//			driver.findElement(By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[4]")).click();
//
//			List<WebElement> dropdownUnit = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
//					By.xpath("//div[@role='option']//span[text()='Administration']")));
//			for (WebElement option : dropdownUnit) {
//				if (option.getText().equals("Administration")) {
//					option.click();
//					break;
//				}
//			}
//			driver.findElement(By.xpath("//button[@type='reset']")).click();
//			driver.quit();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//
//		}
//
//	}
//
//	@Then("User navigate to dashbaord of HRM")
//	public void user_navigate_to_dashbaord_of_hrm() {
//		driver.findElement(By.xpath("//span[text()='My Info']")).click();
//		driver.quit();
//	}
//}