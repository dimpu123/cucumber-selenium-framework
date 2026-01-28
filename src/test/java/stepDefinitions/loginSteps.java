package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import com.orange.DashBoard.DashboardActions;
import com.orange.Loginactions.LoginActions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class loginSteps {

	WebDriver driver;
	LoginActions loginActions;
	DashboardActions dashboardActions;

	@Given("User is on login page")
	public void user_is_on_login_page() {

		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
		System.out.println("Rebel star prabhas");

	}

	@When("User enter UserName and Password")
	public void user_enter_user_name_and_password() {
		try {
//			driver.findElement(By.name("username")).sendKeys("Admin");
//			driver.findElement(By.name("password")).sendKeys("admin123");

			loginActions = new LoginActions(driver);
			dashboardActions = new DashboardActions(driver);
			loginActions.enterUserName(driver, "Admin");
			loginActions.enterPassword(driver, "admin123");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Mahendra singh Dhoni");

	}

	@When("Click on login button")
	public void click_on_login_button() {

		try {

//			driver.findElement(By.className("orangehrm-login-button")).click();
//			Thread.sleep(5000);
//			String result = driver.findElement(By.xpath("//h6[text()='Dashboard']")).getText();
//			System.out.println("-------" + result);

			loginActions.clickOnLogin(driver);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Messi");

	}

	@Then("User navigate to dashboard page")
	public void user_navigate_to_dashboard_page() {
		driver.findElement(By.xpath("//span[text()='My Info']")).click();

		System.out.println("Nadal");

	}

	@Then("click on PIM tab")
	public void click_on_pim_tab() {
		// driver.findElement(By.xpath("//span[text()='PIM']")).click();

		//DashboardActions dashboardActions = new DashboardActions(driver);
		dashboardActions.clickOnPimTab(driver);

//		loginActions.elementVisibilityClick(driver, null, Duration.ofSeconds(20));
//		System.out.println("element is visible");

	}

	@Then("Enter the PIM details")
	public void enter_the_pim_details() {
		dashboardActions.enterNewUserName(driver, "sai");
		dashboardActions.enterNewUserRole(driver, "Admin");
		dashboardActions.enterNewEmpName(driver, "santhosh");

	}

}
