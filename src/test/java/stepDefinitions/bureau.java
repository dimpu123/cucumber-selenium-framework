package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.bureauveritas.Application.bureauActions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class bureau {

	WebDriver driver;
	bureauActions bureauActions;

	@Given("User is in application page")
	public void user_is_in_application_page() {

	    driver = new EdgeDriver();

		driver.get("https://bureauveritas-stg.phenompro.com/gb/en/apply?jobSeqNo=BVYBVZGB165967EXTERNALENGB");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

	}

	@When("User enters details")
	public void user_enters_details() {
		try {

			bureauActions = new bureauActions(driver);
			bureauActions.enterfirstname(driver, "sai");
			bureauActions.enterlastname(driver, "santhosh");
			bureauActions.enteremail(driver, "saisanthosh123@yopmail.com");
			bureauActions.enterphonenumber(driver, "9490720556");
			bureauActions.country();

			bureauActions.howDidYouLearnAboutThisOpportunity();
			bureauActions.providedetails();
			bureauActions.sex();
			bureauActions.Ethnicites(driver);
			bureauActions.Race(driver);
			bureauActions.miltary(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@When("click submit button")
	public void click_submit_button() {

		bureauActions.submit(driver);

	}

	@Then("close the browser")
	public void close_the_browser() {

		driver.close();

	}

}
