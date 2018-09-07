package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.BrowserSetup;

public class Onboarding_component extends BrowserSetup{

	
	public static void login(String username,String password) {

		wait20.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='Login Internet Banking']"))).isDisplayed();

		WebElement uname=driver.findElement(By.id("uname"));
		uname.clear();
		uname.sendKeys(username);
		System.out.println("username sent");

		WebElement pwd=driver.findElement(By.id("psw"));
		pwd.clear();
		pwd.sendKeys(password);
		System.out.println("password sent");

		driver.findElement(By.name("submit")).click();

	}
}
