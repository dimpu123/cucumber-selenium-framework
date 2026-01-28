package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseTest {

	public static WebDriver driver; // 🔥 change here

	public void launchBrowser() {
		System.setProperty("webdriver.edge.driver",
				"C:\\Users\\Sai santhosh\\Downloads\\edgedriver_win64\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.manage().window().maximize();
	}

	public void quitBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}
}
