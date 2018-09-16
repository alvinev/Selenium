package components;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import framework.BrowserSetup;
import framework.Screenshot;

public class TimeDeposit_component extends BrowserSetup {


	public static void konvenMenu() {
		driver.findElement(By.xpath("//a[text()='Online Open Account'] | //a[text()='Pembukaan Rekening Online']")).click();
		Select productList = new Select(driver.findElement(By.name("productType")));
		productList.selectByValue("timeDeposit");

	}

	public static void syariahMenu() {
		driver.findElement(By.xpath("//a[text()='Sharia Online Open Account'] | //a[text()='Pembukaan Rekening Online Syariah']")).click();
		Select productList = new Select(driver.findElement(By.name("productType")));
		productList.selectByValue("timeDeposit");

	}

	public static void create(String sourceAccount,String amount,String term,String tdType,String folder,String filename) {

		//select account
		Select accountList = new Select(driver.findElement(By.name("debitAccountNumber")));	
		accountList.selectByValue(sourceAccount);

		//select branch (this is defaulted for sya :ID0020002 and konven : ID0020002)
		Select branchList = new Select(driver.findElement(By.name("bankBranch")));

		if(sourceAccount.startsWith("99"))
			branchList.selectByValue("ID0020002");
		else
			branchList.selectByValue("ID0010087");	

		//input amount
		driver.findElement(By.name("initialDeposit")).sendKeys(amount);

		//input period
		Select periodList = new Select(driver.findElement(By.name("depositPeriod")));
		periodList.selectByValue(term);

		//input td type
		Select tdTypeList = new Select(driver.findElement(By.name("maturityInstruction")));
		if(tdType.equals("aro"))tdTypeList.selectByVisibleText("ARO Pokok");
		else if(tdType.equals("non-aro"))tdTypeList.selectByVisibleText("Non ARO");
		else if (tdType.equals("aro-pi"))tdTypeList.selectByVisibleText("ARO Plus Bunga");

		Screenshot.capture(folder, filename);
		//submit
		driver.findElement(By.xpath("//input[@value='Kirim'] | //input[@value='Submit']")).click();	
	}

	public static void termAndCondition(String folder,String filename) {

		//agree tnc
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='Aplikasi Deposito Berjangka'] | //td[text()='Time Deposit Application']"))).isDisplayed();
		driver.findElement(By.name("isAgree")).click();
		Screenshot.capture(folder, filename);
		
		//submit
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
	}
	public static void confirm(String folder,String filename) {

		//input mpin
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.name("mPin"))).sendKeys("123456");

		Screenshot.capture(folder, filename);
		//submit
		driver.findElement(By.xpath("//input[@value='Kirim'] | //input[@value='Submit']")).click();	
	}

	public static void result(String folder,String filename) {

		Screenshot.capture(folder, filename);

	}

}
