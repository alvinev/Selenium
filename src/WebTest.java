import java.util.List;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import framework.BrowserSetup;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTest extends BrowserSetup{

/*	protected WebDriver driver;
	protected WebDriverWait wait10,wait20,wait30;*/

	@Test
	private void login() {

		wait20.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='Login Internet Banking']"))).isDisplayed();

		WebElement username=driver.findElement(By.id("uname"));
		username.clear();
		username.sendKeys("alvinega12");
		System.out.println("username sent");

		WebElement password=driver.findElement(By.id("psw"));
		password.clear();
		password.sendKeys("Ab123456");
		System.out.println("password sent");

		driver.findElement(By.name("submit")).click();

	}

	@Test(dependsOnMethods="login")
	private void afterLogin() {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='[Logout]'] | //a[text()='[Keluar]']"))).isDisplayed();

		driver.findElement(By.partialLinkText("Transfer")).click();
		driver.findElement(By.linkText("Transfer Bank Sinarmas")).click();
	}

	@Test(dependsOnMethods="afterLogin")
	private void selectAccount() {

		Select selectSource = new Select(driver.findElement( By.name("accountFrom")));
		//List<WebElement> listSource=selectSource.getOptions();
		for(WebElement we : selectSource.getOptions()) {

			String sourceAccount =we.getText(); 
			if(sourceAccount.contains("0171029279"))selectSource.selectByVisibleText(sourceAccount);

		}

		Select selectTransferList = new Select(driver.findElement( By.name("transferList")));
		selectTransferList.selectByValue("0008401659");

		driver.findElement(By.name("amount")).sendKeys("1000");

		driver.findElement(By.xpath("//input[@value='Confirm']")).click();

	}
	
	@Test(dependsOnMethods="selectAccount")
	private void confirm() {
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.name("mPin"))).sendKeys("123456");
		
		driver.findElement(By.xpath("//input[@value='Konfirmasi']")).click();
		
	}



}
