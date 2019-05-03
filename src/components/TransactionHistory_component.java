package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class TransactionHistory_component {

	private WebDriver driver;
	public TransactionHistory_component(WebDriver driver) {
		this.driver =driver;
		
	}
	public void menu() {
		driver.findElement(By.xpath("//a[text()='Internet Transaction History'] | //a[text()='Riwayat Transaksi']")).click();
	}

	public void selectPayee(String account) {
		
		//select account
		Select accountList = new Select(driver.findElement(By.name("accountId")));
		for(WebElement we : accountList.getOptions()) {		
			String acc = we.getText();	
			if(acc.contains(account))accountList.selectByVisibleText(acc);		
		}		
		//from date is set to 1
		driver.findElement(By.id("buttonFrom")).click();
		driver.findElement(By.xpath("//td[text()='1']")).click();		
		
		//submit
		driver.findElement(By.xpath("//input[contains(@value,'Kirim')] | //input[contains(@value,'Submit')]")).click();	

	}
	public void result() {
				
		Assert.assertEquals(driver.findElement(By.xpath("//font[contains(text(),'History of Internet Transaction')] | //font[contains(text(),'Catatan atas transaksi melalui internet')]")).isDisplayed(), true);
		
	}

}
