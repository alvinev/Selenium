package framework;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import components.Onboarding_component;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserSetup {

	protected static WebDriver driver;
	protected static WebDriverWait wait10,wait20;
	protected static WebDriverWait wait30;

	@BeforeClass
	protected void setup() throws IOException {
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		wait10 = new WebDriverWait(driver, 10);
		wait20 = new WebDriverWait(driver, 20);
		wait30 = new WebDriverWait(driver, 30);

		Properties prop = LoadProperties.getProperties("credential.properties");
		
		driver.get(prop.getProperty("url"));
		System.out.println("Login Page Loaded");

		Onboarding_component.login(prop.getProperty("username"),prop.getProperty("password"));
	}


	
}
