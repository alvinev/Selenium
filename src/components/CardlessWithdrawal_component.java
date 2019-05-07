package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CardlessWithdrawal_component  {

	private WebDriver driver;
	private FundTransfer_component fundTransfer;
	
	public CardlessWithdrawal_component(WebDriver driver) {
		
		this.driver=driver;
		fundTransfer=new FundTransfer_component(driver);
	}
	
	public void menu() {
		
		if(!(driver.findElements(By.name("accountFrom")).size()>0))
		driver.findElement(By.xpath("//a[text()='Tarik Tunai Tanpa Kartu'] | //a[text()='Cardless Withdrawal']")).click();	
		
	}
	
	public void selectPayee(String sourceAccount,String phoneNo,String amount,String desc) {
		//add prefix to phone no
		phoneNo="85"+phoneNo;
		fundTransfer.inbankSelectAccount(sourceAccount, phoneNo, amount, desc);
	}
	
	public void confirm(String toAccountType) {
		fundTransfer.confirm(toAccountType);
	}
	
	public void result(String fromAccountType,String toAccountType) {
		
		fundTransfer.result(fromAccountType,toAccountType);
	}
}
