package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import framework.BrowserSetup;
import framework.Screenshot;

public class TimeDeposit_component extends BrowserSetup {


	public static void konvenMenu(String folder,String filename) {

		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='[Logout]'] | //a[text()='[Keluar]']"))).isDisplayed();

		driver.findElement(By.xpath("//a[text()='Online Open Account'] | //a[text()='Pembukaan Rekening Online']")).click();

		Screenshot.capture(folder, filename);
		Select productList = new Select(driver.findElement(By.name("productType")));
		productList.selectByValue("timeDeposit");

	}

	public static void create(String sourceAccount,String amount,String term,String tdType,String folder,String filename) {

		Select accountList = new Select(driver.findElement(By.name("debitAccountNumber")));	
		accountList.selectByValue(sourceAccount);

		Select branchList = new Select(driver.findElement(By.name("bankBranch")));
		branchList.selectByValue("ID0010087");

		driver.findElement(By.name("initialDeposit")).sendKeys(amount);

		Select periodList = new Select(driver.findElement(By.name("depositPeriod")));
		periodList.selectByValue(term);

		Select tdTypeList = new Select(driver.findElement(By.name("maturityInstruction")));
		if(tdType.equals("aro"))tdTypeList.selectByVisibleText("ARO Pokok");
		else if(tdType.equals("non-aro"))tdTypeList.selectByVisibleText("Non ARO");
		else if (tdType.equals("aro-pi"))tdTypeList.selectByVisibleText("ARO Plus Bunga");

		Screenshot.capture(folder, filename);
		driver.findElement(By.xpath("//input[@value='Confirm']")).click();
	}
	
	public static void termAndCondition(String folder,String filename) {

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='Aplikasi Deposito Berjangka'] | //td[text()='Time Deposit Application']"))).isDisplayed();
		
		driver.findElement(By.name("isAgree")).click();
		
		Screenshot.capture(folder, filename);
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
	}
	public static void confirm(String folder,String filename) {

		wait10.until(ExpectedConditions.presenceOfElementLocated(By.name("mPin"))).sendKeys("123456");

		Screenshot.capture(folder, filename);
		driver.findElement(By.xpath("//input[@value='Kirim'] | //input[@value='Submit']")).click();	
	}
	
	public static void result(String folder,String filename) {

		Screenshot.capture(folder, filename);

	}
	
}
