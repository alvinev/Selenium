package components;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import framework.BrowserSetup;
import framework.Screenshot;

public class CardlessWithdrawal_component extends BrowserSetup {

	public static void menu(String folder,String filename) {
		
		wait30.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='[Logout]'] | //a[text()='[Keluar]']"))).isDisplayed();

		driver.findElement(By.xpath("//a[text()='Tarik Tunai Tanpa Kartu'] | //a[text()='Cardless Withdrawal']")).click();
		
		Screenshot.capture(folder,filename);	
		
	}
	
	public static void selectPayee(String sourceAccount,String phoneNo,String amount,String desc,String folder,String filename) {
		
		phoneNo="85"+phoneNo;
		FundTransfer_component.inbankSelectAccount(sourceAccount, phoneNo, amount, desc, folder, filename);
	}
	
	public static void confirm(String folder,String filename) {
		FundTransfer_component.confirm(folder, filename);
	}
	
	public static void result(String folder,String filename) {
		
		FundTransfer_component.result(folder, filename);
	}
}
