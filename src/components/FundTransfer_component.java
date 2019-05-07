package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class FundTransfer_component {

	private WebDriver driver;
	private WebDriverWait wait10,wait30;

	public FundTransfer_component(WebDriver driver) {
		this.driver=driver;
		wait10=new WebDriverWait(driver,10);
		wait30=new WebDriverWait(driver,30);

	}

	public void inbankMenu() {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='[Logout]'] | //a[text()='[Keluar]']"))).isDisplayed();

			if(!driver.findElement(By.xpath("//a[text()='Transfer Bank Sinarmas']")).isDisplayed())

			driver.findElement(By.partialLinkText("Transfer")).click();

		driver.findElement(By.linkText("Transfer Bank Sinarmas")).click();

	}

	public void otbankMenu() {
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='[Logout]'] | //a[text()='[Keluar]']"))).isDisplayed();
		
		if(!driver.findElement(By.xpath("//a[text()='Transfer Bank Lain'] | //a[text()='Transfer Other Bank']")).isDisplayed())
			driver.findElement(By.partialLinkText("Transfer")).click();

		driver.findElement(By.xpath("//a[text()='Transfer Bank Lain'] | //a[text()='Transfer Other Bank']")).click();
	}

	public void inbankSelectAccount(String sourceAccount,String toAccount,String amount,String desc) {
		//select account
		Select accountList = new Select(driver.findElement( By.name("accountFrom")));
		for(WebElement we : accountList.getOptions()) {
			String account =we.getText(); 
			if(account.contains(sourceAccount))accountList.selectByVisibleText(account);
		}
	
		//select transfer list
		Select transferList = new Select(driver.findElement( By.name("transferList")));
		transferList.selectByValue(toAccount);

	
		//input amount
		driver.findElement(By.name("amount")).clear();
		driver.findElement(By.name("amount")).sendKeys(amount);
		
		//input desc
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys(desc);

		//confirm
		driver.findElement(By.xpath("//input[@value='Konfirmasi'] | //input[@value='Confirm']")).click();	

	}

	public void otbankSelectAccount(String transferMethod,String sourceAccount,String toAccount,String amount,String desc) {
		//select account
		Select accountList = new Select(driver.findElement( By.name("accountFrom")));
		for(WebElement we : accountList.getOptions()) {
			String account =we.getText(); 
			if(account.contains(sourceAccount))accountList.selectByVisibleText(account);
		}

		//select transfer list
		Select transferList = new Select(driver.findElement( By.name("transferList")));
		for(WebElement we : transferList.getOptions()) {
			String account =we.getText(); 
			if(account.contains(toAccount))transferList.selectByVisibleText(account);
		}
		//select transfer method
		String method="sknRb";
		if(transferMethod.equals("network"))method="networkRb";
		else if (transferMethod.equals("rtgs"))method="rtgsRb";
		driver.findElement(By.id(method)).click();

		//input amount
		driver.findElement(By.name("amount")).clear();
		driver.findElement(By.name("amount")).sendKeys(amount);

		//input desc
		driver.findElement(By.name("description")).clear();
		driver.findElement(By.name("description")).sendKeys(desc);

		//confirm
		driver.findElement(By.xpath("//input[@value='Konfirmasi'] | //input[@value='Confirm']")).click();	

	}


	public void confirm(String toAccountType) {
		
		if(toAccountType.contains("FOREX"))driver.findElement(By.xpath("//input[@name='isAgree']")).click();
		//input mpin
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.name("mPin"))).sendKeys("123456");

		//confirm
		driver.findElement(By.xpath("//input[@value='Konfirmasi'] | //input[@value='Confirm']")).click();	
	}

	public void result(String fromAccountType,String toAccountType) {
		if(fromAccountType.contains("NORMAL")&&!toAccountType.contains("BLOCK"))
			Assert.assertEquals(true, driver.findElement(By.xpath("//td[contains(text(),'Sukses')] | //td[contains(text(),'Process')] | //td[contains(text(),'Proses')] | //td[contains(text(),'Successful') ]")).isDisplayed());
		else 	
			Assert.assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'rekening anda tidak aktif')] | //td[contains(text(),'Process')] | //td[contains(text(),'Proses')] ")).isDisplayed());

	}
}
