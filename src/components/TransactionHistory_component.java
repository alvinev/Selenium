package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import framework.BrowserSetup;
import framework.Screenshot;

public class TransactionHistory_component extends BrowserSetup {

	public static void menu() {
		driver.findElement(By.xpath("//a[text()='Internet Transaction History'] | //a[text()='Riwayat Transaksi']")).click();
	}

	public static void selectPayee(String account,String folder,String filename) {
		
		//select account
		Select accountList = new Select(driver.findElement(By.name("accountId")));
		for(WebElement we : accountList.getOptions()) {		
			String acc = we.getText();	
			if(acc.contains(account))accountList.selectByVisibleText(acc);		
		}		
		//from date is set to 1
		driver.findElement(By.id("buttonFrom")).click();
		driver.findElement(By.xpath("//td[text()='1']")).click();		
		Screenshot.capture(folder, filename);
		
		//submit
		driver.findElement(By.xpath("//input[contains(@value,'Kirim')] | //input[contains(@value,'Submit')]")).click();	

	}
	public static void result(String folder,String filename) {
		
		Screenshot.capture(folder, filename);
		Assert.assertEquals(driver.findElement(By.xpath("//font[contains(text(),'History of Internet Transaction')] | //font[contains(text(),'Catatan atas transaksi melalui internet')]")).isDisplayed(), true);
		
	}

}
