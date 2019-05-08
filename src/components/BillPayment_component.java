package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class BillPayment_component {

	private WebDriver driver;
	private WebDriverWait wait10;

	public BillPayment_component(WebDriver driver) {

		this.driver=driver;
		wait10 = new WebDriverWait(driver,10);

	}

	public void menu(String billerName) {

		//go to biller menu
		driver.findElement(By.xpath("//a[text()='Pembayaran/Pembelian'] | //a[text()='Payment/Purchase']")).click();

		//select biller 
		driver.findElements(By.className("select2-choice")).get(1).click();
		driver.findElement(By.id("s2id_autogen2_search")).sendKeys(billerName);
		driver.findElement(By.xpath("//i[text()='"+billerName+"']")).click();;

	}

	public void aetra_inquiry(String subscriberNo) {

		//input subscriber no
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.name("subscriberNo"))).sendKeys(subscriberNo);
		driver.findElement(By.xpath("//input[@value='Retrieve'] | //input[@value='Lihat Tagihan']")).click();
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	public void aetra_selectAccount(String sourceAccount,String desc) {

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//select account
		Select accountList = new Select(driver.findElement(By.name("accountFrom")));
		for(WebElement we : accountList.getOptions()) {
			String account =we.getText(); 

			if(account.contains(sourceAccount))accountList.selectByVisibleText(account);
		}
		//input desc
		driver.findElement(By.name("description")).sendKeys(desc);

		//submit
		driver.findElement(By.xpath("//input[@value='Kirim'] | //input[@value='Submit']")).click();	
	}

	public void baznas_selectAccount(String sourceAccount,String subscriberNo,String amount,String desc) {

		//input subscriber no
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.name("subscriberNo"))).sendKeys(subscriberNo);

		//select account
		Select accountList = new Select(driver.findElement(By.name("accountFrom")));
		for(WebElement we : accountList.getOptions()) {
			String account =we.getText(); 
			if(account.contains(sourceAccount))accountList.selectByVisibleText(account);
		}
		//input amount
		driver.findElement(By.name("amount")).sendKeys(amount);

		//input desc
		driver.findElement(By.name("description")).sendKeys(desc);

		//submit
		driver.findElement(By.xpath("//input[@value='Kirim'] | //input[@value='Submit']")).click();	

	}
	public void asuransi_selectAccount(String sourceAccount,String subscriberNo,String amount,String desc) {

		//input subscriber no
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.name("subscriberNo"))).sendKeys(subscriberNo);

		//select account
		Select accountList = new Select(driver.findElement(By.name("accountFrom")));
		for(WebElement we : accountList.getOptions()) {
			String account =we.getText(); 
			if(account.contains(sourceAccount))accountList.selectByVisibleText(account);
		}
		//input amount
		driver.findElement(By.name("amount")).sendKeys(amount);

		//input desc
		driver.findElement(By.name("description")).sendKeys(desc);

		//submit
		driver.findElement(By.xpath("//input[@value='Kirim'] | //input[@value='Submit']")).click();	

	}

	public void confirm() {

		//input mpin
		wait10.until(ExpectedConditions.presenceOfElementLocated(By.name("mPin"))).sendKeys("123456");

		//confirm
		driver.findElement(By.xpath("//input[@value='Konfirmasi'] | //input[@value='Confirm']")).click();

	}

	public void result(String fromAccountType) {

		if(fromAccountType.contains("NORMAL"))
			Assert.assertEquals(true, driver.findElement(By.xpath("//td[contains(text(),'Berhasil')] | //td[contains(text(),'Successful')]")).isDisplayed());
		else 	
			Assert.assertEquals(true, driver.findElement(By.xpath("//div[contains(text(),'rekening anda tidak aktif')]")).isDisplayed());

	}

}
