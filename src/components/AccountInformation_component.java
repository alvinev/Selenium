package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import framework.BrowserSetup;
import framework.Screenshot;

public class AccountInformation_component extends BrowserSetup{

	public static void balanceInquiryMenu() {
		driver.findElement(By.xpath("//a[text()='Account Information'] | //a[text()='Informasi Rekening']")).click();
		driver.findElement(By.xpath("//a[text()='Info Saldo'] | //a[text()='Balance Inquiry']")).click();

	}

	public static void balanceInquirySelectAccount(String account,String folder,String filename) {

		//select account
		Select listAccount= new Select(driver.findElement(By.name("accNo")));
		listAccount.selectByValue(account);

		Screenshot.capture(folder, filename);

		//submit
		driver.findElement(By.xpath("//input[@value='Kirim'] | //input[@value='Submit']")).click();	

	}

	public static void balanceInquiryResult(String folder,String filename) {
		Screenshot.capture(folder, filename);
		//assert result is success
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Saldo Efektif'] | //td[text='Available Balance']")).isDisplayed(), true);

	}

	public static void accountSummaryMenu() {
		driver.findElement(By.xpath("//a[text()='Account Information'] | //a[text()='Informasi Rekening']")).click();
		driver.findElement(By.xpath("//a[text()='Account Summary'] | //a[text()='Informasi Detail Rekening']")).click();

	}
	
	public static void accountSummaryselectAccount(String account,String folder,String filename) {

		//find the account in account summary
		WebElement acc =  driver.findElement(By.xpath("//input[@onclick=\"doSubmit('"+account+"');\"]"));
		Screenshot.capture(folder, filename);

		//click selected account to see detail
		acc.click();

	}

	public static void accountSummaryResult(String folder,String filename) {
		Screenshot.capture(folder, filename);
		
		//assert result is success
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Working Balance'] | //td[text()='Saldo Aktif']")).isDisplayed(), true);
	}

	public static void accountStatementMenu() {
		driver.findElement(By.xpath("//a[text()='Account Information'] | //a[text()='Informasi Rekening']")).click();
		driver.findElement(By.xpath("//a[text()='Account Statement'] | //a[text()='Mutasi Rekening']")).click();
	}

	public static void accountStatementSelectAccountPeriod(String account,String period,String folder,String filename) {

		//select account
		Select accountList = new Select(driver.findElement(By.name("accountNumber")));
		accountList.selectByValue(account);

		//select period
		driver.findElement(By.xpath("//input[@value='"+period+"']")).click();

		//if period is range, from date is set to date 1
		if(period.equals("range")) {
			driver.findElement(By.id("buttonFrom")).click();
			driver.findElement(By.xpath("//td[text()='1']")).click();
		}

		Screenshot.capture(folder, filename);

		//submit
		driver.findElement(By.xpath("//input[contains(@value,'Kirim')] | //input[contains(@value,'Submit')]")).click();	
	}

	public static void accountStatementResult(String folder,String filename) {
		
		Screenshot.capture(folder, filename);
		
		//assert result is success
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Saldo Akhir'] | //td[text()='Ending Balance']")).isDisplayed(), true);

	}
}
