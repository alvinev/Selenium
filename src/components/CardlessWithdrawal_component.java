package components;

import org.openqa.selenium.By;

import framework.BrowserSetup;

public class CardlessWithdrawal_component extends BrowserSetup {

	public static void menu() {
		
		driver.findElement(By.xpath("//a[text()='Tarik Tunai Tanpa Kartu'] | //a[text()='Cardless Withdrawal']")).click();	
		
	}
	
	public static void selectPayee(String sourceAccount,String phoneNo,String amount,String desc,String folder,String filename) {
		//add prefix to phone no
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
