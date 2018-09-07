package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import framework.BrowserSetup;
import framework.Screenshot;

public class FundTransfer_component extends BrowserSetup {
	

	public static void inbankMenu(String folder,String filename) {

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='[Logout]'] | //a[text()='[Keluar]']"))).isDisplayed();

		driver.findElement(By.partialLinkText("Transfer")).click();
		
		Screenshot.capture(folder,filename);
		driver.findElement(By.linkText("Transfer Bank Sinarmas")).click();

	}
	
	public static void otbankMenu(String folder,String filename) {

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='[Logout]'] | //a[text()='[Keluar]']"))).isDisplayed();

		driver.findElement(By.partialLinkText("Transfer")).click();
		
		Screenshot.capture(folder,filename);
		driver.findElement(By.xpath("//a[text()='Transfer Bank Lain'] | //a[text()='Transfer Other Bank']")).click();

	}


	public static void inbankSelectAccount(String sourceAccount,String toAccount,String amount,String desc,String folder,String filename) {
		Select accountList = new Select(driver.findElement( By.name("accountFrom")));
		//List<WebElement> listSource=selectSource.getOptions();
		for(WebElement we : accountList.getOptions()) {

			String account =we.getText(); 
			if(account.contains(sourceAccount))accountList.selectByVisibleText(account);

		}

		Select transferList = new Select(driver.findElement( By.name("transferList")));
		transferList.selectByValue(toAccount);

		driver.findElement(By.name("amount")).sendKeys(amount);
		
		driver.findElement(By.name("description")).sendKeys(desc);
		
		Screenshot.capture(folder, filename);
		driver.findElement(By.xpath("//input[@value='Confirm']")).click();

	}
	
	public static void otbankSelectAccount(String transferMethod,String sourceAccount,String toAccount,String amount,String desc,String folder,String filename) {
		Select accountList = new Select(driver.findElement( By.name("accountFrom")));
		
		for(WebElement we : accountList.getOptions()) {

			String account =we.getText(); 
			if(account.contains(sourceAccount))accountList.selectByVisibleText(account);

		}

		
		Select transferList = new Select(driver.findElement( By.name("transferList")));
		
		for(WebElement we : transferList.getOptions()) {

			String account =we.getText(); 
			if(account.contains(toAccount))transferList.selectByVisibleText(account);

		}
		
		String method="sknRb";
		if(transferMethod.equals("network"))method="networkRb";
		else if (transferMethod.equals("rtgs"))method="rtgsRb";
		
		driver.findElement(By.id(method)).click();

		driver.findElement(By.name("amount")).sendKeys(amount);
		
		driver.findElement(By.name("description")).sendKeys(desc);
		
		Screenshot.capture(folder, filename);
		driver.findElement(By.xpath("//input[@value='Confirm']")).click();

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
