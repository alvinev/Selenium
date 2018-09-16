package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import framework.BrowserSetup;
import framework.Screenshot;

public class TransactionHistory_component extends BrowserSetup {


	public static void menu(String folder,String filename) {

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='[Logout]'] | //a[text()='[Keluar]']"))).isDisplayed();

		driver.findElement(By.xpath("//a[text()='Internet Transaction History'] | //a[text()='Riwayat Transaksi']")).click();

		Screenshot.capture(folder,filename);


	}

	public static void selectPayee(String account,String folder,String filename) {

		Select accountList = new Select(driver.findElement(By.name("accountId")));

		for(WebElement we : accountList.getOptions()) {
			
			String acc = we.getText();	
			if(acc.contains(account))accountList.selectByVisibleText(acc);
			
		}
		
		driver.findElement(By.id("buttonFrom")).click();

		driver.findElement(By.xpath("//td[text()='1']")).click();
		
		Screenshot.capture(folder, filename);
		
		driver.findElement(By.xpath("//input[contains(@value,'Kirim')] | //input[contains(@value,'Submit')]")).click();	


	}
	public static void result(String folder,String filename) {
		
		Screenshot.capture(folder, filename);
	}

}
