package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

import framework.BrowserSetup;
import framework.Screenshot;

public class AccountInformation_component extends BrowserSetup{

	
	public static void balanceInquiryMenu(String folder,String filename) {
		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='[Logout]'] | //a[text()='[Keluar]']"))).isDisplayed();

		driver.findElement(By.xpath("//a[text()='Account Information'] | //a[text()='Informasi Rekening']")).click();

		driver.findElement(By.xpath("//a[text()='Info Saldo'] | //a[text()='Balance Inquiry']")).click();
		
		Screenshot.capture(folder, filename);
	
	}
	
	public static void balanceInquirySelectAccount(String account,String folder,String filename) {
		
		Select listAccount= new Select(driver.findElement(By.name("accNo")));
		listAccount.selectByValue(account);
		
		Screenshot.capture(folder, filename);
		
		driver.findElement(By.xpath("//input[@value='Kirim'] | //input[@value='Submit']")).click();	
		
	}
	
	public static void balanceInquiryResult(String folder,String filename) {
		
		Screenshot.capture(folder, filename);
	}
	
	public static void accountSummaryMenu(String account,String folder,String filename) {
		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='[Logout]'] | //a[text()='[Keluar]']"))).isDisplayed();

		driver.findElement(By.xpath("//a[text()='Account Information'] | //a[text()='Informasi Rekening']")).click();
	
		driver.findElement(By.xpath("//a[text()='Account Summary'] | //a[text()='Informasi Detail Rekening']")).click();
	
		WebElement acc =  driver.findElement(By.xpath("//input[@onclick=\"doSubmit('"+account+"');\"]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", acc);
		
        Screenshot.capture(folder, filename);
        
        acc.click();
       // driver.findElement(By.xpath("//input[@onclick=\"doSubmit('"+account+"');]\"")).click();
	}
	
	public static void accountSummaryResult(String folder,String filename) {
		
		Screenshot.capture(folder, filename);
		
	}
	
	public static void accountStatementMenu(String folder,String filename) {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='[Logout]'] | //a[text()='[Keluar]']"))).isDisplayed();

		driver.findElement(By.xpath("//a[text()='Account Information'] | //a[text()='Informasi Rekening']")).click();

		driver.findElement(By.xpath("//a[text()='Account Statement'] | //a[text()='Mutasi Rekening']")).click();
		
		Screenshot.capture(folder, filename);	
	}
	
	public static void accountStatementSelectAccountPeriod(String account,String period,String folder,String filename) {
		
		Select accountList = new Select(driver.findElement(By.name("accountNumber")));
		accountList.selectByValue(account);
		
		driver.findElement(By.xpath("//input[@value='"+period+"']")).click();
		
		if(period.equals("range")) {
			driver.findElement(By.id("buttonFrom")).click();
			driver.findElement(By.xpath("//td[text()='1']")).click();
		}
		
		Screenshot.capture(folder, filename);
		
		driver.findElement(By.xpath("//input[contains(@value,'Kirim')] | //input[contains(@value,'Submit')]")).click();	
	}
	
	public static void accountStatementResult(String folder,String filename) {
		
		Screenshot.capture(folder, filename);
	}
}
