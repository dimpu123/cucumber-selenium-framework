package stepDefinitions;

import base.BaseTest;
import pages.HomePage;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class HomeSteps extends BaseTest {

	HomePage homePage;

	@Given("check urlss")
	public void check_url() {
		homePage = new HomePage(driver);
		homePage.openHomePage();
	}

	@When("redirects the link")
	public void redirects_the_link() {
		System.out.println("Title is: " + homePage.getTitle());
	}

	@Then("open the home page")
	public void open_the_home_page() {
		Assert.assertTrue(homePage.getTitle().length() > 0);
	}
}
