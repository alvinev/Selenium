package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;


public class AccountInformation_component {

	private WebDriver driver;
	
	public AccountInformation_component(WebDriver driver) {
		
		this.driver=driver;
		
	}
	
	
	public void balanceInquiryMenu() {
		
		if(!driver.findElement(By.xpath("//a[text()='Info Saldo'] | //a[text()='Balance Inquiry']")).isDisplayed())
		driver.findElement(By.xpath("//a[text()='Account Information'] | //a[text()='Informasi Rekening']")).click();
		
		
		driver.findElement(By.xpath("//a[text()='Info Saldo'] | //a[text()='Balance Inquiry']")).click();

	}

	public void balanceInquirySelectAccount(String account) {

		//select account
		Select listAccount= new Select(driver.findElement(By.name("accNo")));
		listAccount.selectByValue(account);

		//submit
		driver.findElement(By.xpath("//input[@value='Kirim'] | //input[@value='Submit']")).click();	

	}

	public void balanceInquiryResult() {

		//assert result is success
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Saldo Efektif'] | //td[text='Available Balance']")).isDisplayed(), true);

	}

	public void accountSummaryMenu() {
		
		if(!driver.findElement(By.xpath("//a[text()='Account Summary'] | //a[text()='Informasi Detail Rekening']")).isDisplayed())
		driver.findElement(By.xpath("//a[text()='Account Information'] | //a[text()='Informasi Rekening']")).click();
		
		driver.findElement(By.xpath("//a[text()='Account Summary'] | //a[text()='Informasi Detail Rekening']")).click();

	}
	
	public void accountSummaryselectAccount(String account) {

		//find the account in account summary
		WebElement acc =  driver.findElement(By.xpath("//input[@onclick=\"doSubmit('"+account+"');\"]"));
		
		//click selected account to see detail
		acc.click();

	}

	public void accountSummaryResult() {

		//assert result is success
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Working Balance'] | //td[text()='Saldo Aktif']")).isDisplayed(), true);
	}

	public void accountStatementMenu() {
		
		if(!driver.findElement(By.xpath("//a[text()='Account Statement'] | //a[text()='Mutasi Rekening']")).isDisplayed())
		driver.findElement(By.xpath("//a[text()='Account Information'] | //a[text()='Informasi Rekening']")).click();
		driver.findElement(By.xpath("//a[text()='Account Statement'] | //a[text()='Mutasi Rekening']")).click();
	}

	public void accountStatementSelectAccountPeriod(String account,String period) {

		//value is today,curMonth,1MonthAgo,2MonthAgo,3MonthAgo,range
		
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

		//submit
		driver.findElement(By.xpath("//input[contains(@value,'Kirim')] | //input[contains(@value,'Submit')]")).click();	
	}

	public void accountStatementResult() {

		//assert result is success
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Saldo Akhir'] | //td[text()='Ending Balance']")).isDisplayed(), true);

	}
}
