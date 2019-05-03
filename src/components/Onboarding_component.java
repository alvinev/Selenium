package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Onboarding_component {

	private WebDriver driver;
	private WebDriverWait wait20;
	
	
	public Onboarding_component(WebDriver driver) {
		
		this.driver=driver;
		wait20 = new WebDriverWait(driver, 20);	
	}

	public void login(String username,String password) {

		wait20.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='Login Internet Banking']"))).isDisplayed();

		//input username
		WebElement uname=driver.findElement(By.id("uname"));
		uname.clear();
		uname.sendKeys(username);

		//input password
		WebElement pwd=driver.findElement(By.id("psw"));
		pwd.clear();
		pwd.sendKeys(password);

		//submit
		driver.findElement(By.name("submit")).click();
		
		Assert.assertEquals(wait20.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'Welcome') | //td[contains(text(),'Selamat Datang')]]"))).isDisplayed(), true);

	}
	
	public void logout() {

		driver.findElement(By.xpath("//a[text()='[Keluar]'] | //a[text()='[Logout]']")).click();		

	}
}
