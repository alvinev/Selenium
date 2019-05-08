package framework;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import components.Onboarding_component;
import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(framework.TestListener.class)
public class BrowserSetup {

	protected static WebDriver driver;
	protected static WebDriverWait wait10;
	protected static WebDriverWait wait20;
	protected static WebDriverWait wait30;
	protected static JavascriptExecutor javascript;

	protected static final Properties DEFAULT_PROPERTIES= LoadProperties.getDefaultProperties();
	protected static Properties user_prop;

	private static String username;
	private static Onboarding_component onboarding;

	
	@BeforeSuite
	protected static void BrowserConfiguration() throws IOException {
		
		WebDriverManager.chromedriver().version(DEFAULT_PROPERTIES.getProperty("CHROME_DRIVER_VERSION")).setup();

		driver = new ChromeDriver();
		javascript = (JavascriptExecutor)driver;
		wait10 = new WebDriverWait(driver, 10);
		wait20 = new WebDriverWait(driver, 20);
		wait30 = new WebDriverWait(driver, 30);

		//Go open URL in browser
		driver.get(DEFAULT_PROPERTIES.getProperty("IB_URL"));
		driver.manage().window().maximize();

		onboarding=new Onboarding_component(driver);
		onboarding.login(username,user_prop.getProperty("PASSWORD"));
	}
	

	
	@AfterSuite(alwaysRun=true)
	protected static void teardown() {
		onboarding.logout();
		driver.quit();
	}

	public static WebDriver getDriver() {

		return driver;
	}
	
	protected static void setUser(String username) throws IOException {
		
		BrowserSetup.username=username;
		user_prop=LoadProperties.getUserProperties(username);
	}



}
