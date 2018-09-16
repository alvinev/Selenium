package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import framework.BrowserSetup;
import framework.Screenshot;

public class BillPayment_component extends BrowserSetup{

	public static void menu(String billerName,String folder,String filename) {
		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='[Logout]'] | //a[text()='[Keluar]']"))).isDisplayed();

		driver.findElement(By.xpath("//a[text()='Pembayaran/Pembelian'] | //a[text()='Payment/Purchase']")).click();
		
		Screenshot.capture(folder,filename);
		
		driver.findElements(By.className("select2-choice")).get(1).click();
		
		driver.findElement(By.id("s2id_autogen2_search")).sendKeys(billerName);
		
		driver.findElement(By.xpath("//i[text()='"+billerName+"']")).click();;
		
		
	}
	public static void block1_selectAccount(String sourceAccount,String subscriberNo,String desc,String folder,String filename) {
		
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.name("subscriberNo"))).sendKeys(subscriberNo);
		
		driver.findElement(By.xpath("//input[@value='Retrieve'] | //input[@value='Lihat Tagihan']")).click();
		
		Select accountList = new Select(driver.findElement(By.name("accountFrom")));
		
		for(WebElement we : accountList.getOptions()) {

			String account =we.getText(); 
			if(account.contains(sourceAccount))accountList.selectByVisibleText(account);

		}
		driver.findElement(By.name("description")).sendKeys(desc);
		
		Screenshot.capture(folder, filename);
		driver.findElement(By.xpath("//input[@value='Kirim'] | //input[@value='Submit']")).click();	
	}
	
	public static void block2_selectAccount(String sourceAccount,String subscriberNo,String amount,String desc,String folder,String filename) {
		
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.name("subscriberNo"))).sendKeys(subscriberNo);
		
		Select accountList = new Select(driver.findElement(By.name("accountFrom")));
		
		for(WebElement we : accountList.getOptions()) {

			String account =we.getText(); 
			if(account.contains(sourceAccount))accountList.selectByVisibleText(account);

		}
		driver.findElement(By.name("amount")).sendKeys(amount);
		driver.findElement(By.name("description")).sendKeys(desc);
		
		Screenshot.capture(folder, filename);
		driver.findElement(By.xpath("//input[@value='Kirim'] | //input[@value='Submit']")).click();	
	
	}
	public static void block3_selectAccount(String sourceAccount,String subscriberNo,String amount,String desc,String folder,String filename) {
		
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.name("subscriberNo"))).sendKeys(subscriberNo);
		
		Select accountList = new Select(driver.findElement(By.name("accountFrom")));
		
		for(WebElement we : accountList.getOptions()) {

			String account =we.getText(); 
			if(account.contains(sourceAccount))accountList.selectByVisibleText(account);

		}
		driver.findElement(By.name("amount")).sendKeys(amount);
		driver.findElement(By.name("description")).sendKeys(desc);
		
		Screenshot.capture(folder, filename);
		driver.findElement(By.xpath("//input[@value='Kirim'] | //input[@value='Submit']")).click();	
	
	}
	
	public static void confirm(String folder,String filename) {
		
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.name("mPin"))).sendKeys("123456");

		Screenshot.capture(folder, filename);
		driver.findElement(By.xpath("//input[@value='Konfirmasi'] | //input[@value='Confirm']")).click();
		
	}
	
	public static void result(String folder,String filename) {
		
		Screenshot.capture(folder, filename);
		
	}
	
}
