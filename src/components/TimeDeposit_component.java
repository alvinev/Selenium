package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class TimeDeposit_component {
	
	private WebDriver driver;
	private WebDriverWait wait10;
	
	public TimeDeposit_component(WebDriver driver) {
		
		this.driver=driver;
		wait10=new WebDriverWait(driver,10);
	}

	public void konvenMenu() {
		driver.findElement(By.xpath("//a[text()='Online Open Account'] | //a[text()='Pembukaan Rekening Online']")).click();
		Select productList = new Select(driver.findElement(By.name("productType")));
		productList.selectByValue("timeDeposit");
	}

	public void syariahMenu() {
		driver.findElement(By.xpath("//a[text()='Sharia Online Open Account'] | //a[text()='Pembukaan Rekening Online Syariah']")).click();
		Select productList = new Select(driver.findElement(By.name("productType")));
		productList.selectByValue("timeDeposit");

	}

	public void create(String sourceAccount,String amount,String term,String tdType) {

		//select account
		Select accountList = new Select(driver.findElement(By.name("debitAccountNumber")));	
		accountList.selectByValue(sourceAccount);

		//select branch (this is defaulted for sya :ID0020002 and konven : ID0020002)
		
		if(driver.findElements(By.name("bankBranch")).size()>0) {
		 Select branchList = new Select(driver.findElement(By.name("bankBranch"))); 
		 if(sourceAccount.startsWith("99")) branchList.selectByValue("ID0020002");
		}
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

		//submit
		driver.findElement(By.xpath("//input[@value='Kirim'] | //input[@value='Confirm']")).click();	
	}

	public void termAndCondition() {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//agree tnc
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[text()='Aplikasi Deposito Berjangka'] | //td[text()='Time Deposit Application']"))).isDisplayed();
		driver.findElement(By.name("isAgree")).click();
		
		//submit
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
	}
	public void confirm() {

		//input mpin
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.name("mPin"))).sendKeys("123456");

		//submit
		driver.findElement(By.xpath("//input[@value='Kirim'] | //input[@value='Submit']")).click();	
	}

	public void result() {

		Assert.assertEquals(true, driver.findElement(By.xpath("//td[contains(text(),'Berhasil')] | //td[contains(text(),'Successful')]")).isDisplayed());


	}

}
