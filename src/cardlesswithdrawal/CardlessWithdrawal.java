package cardlesswithdrawal;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.Test;

import components.CardlessWithdrawal_component;
import framework.BrowserSetup;
import framework.LoadProperties;

public class CardlessWithdrawal extends BrowserSetup {

	private String folder="CardlessWithdrawal";
	private String sourceAccount,phoneNo,amount,desc;
	
	public CardlessWithdrawal() throws IOException {
		Properties prop = LoadProperties.getProperties("cardlesswithdrawal.properties");
		sourceAccount=prop.getProperty("sourceAccount");
		phoneNo=prop.getProperty("phoneNo");
		amount=prop.getProperty("amount");
		desc=prop.getProperty("desc");
	}
	
	public CardlessWithdrawal(String sourceAccount,String phoneNo,String amount,String desc) {			
		this.sourceAccount=sourceAccount;
		this.phoneNo=phoneNo;
		this.amount=amount;
		this.desc=desc;
	}

	@Test
	private void Test00_cardlessWithdrawMenu(Method method) {
		
		CardlessWithdrawal_component.menu();
	}

	@Test(dependsOnMethods="Test00_cardlessWithdrawMenu")
	private void Test01_cardlessWithdrawSelectAccount(Method method) {
		
		CardlessWithdrawal_component.selectPayee(sourceAccount, phoneNo, amount, desc, folder, method.getName());
	}
	
	@Test(dependsOnMethods="Test01_cardlessWithdrawSelectAccount")
	private void Test02_cardlessWithdrawConfirm(Method method) {	
		
		CardlessWithdrawal_component.confirm(folder,method.getName());
	}
	
	@Test(dependsOnMethods="Test02_cardlessWithdrawConfirm")
	private void Test03_cardlessWithdrawResult(Method method) {
		CardlessWithdrawal_component.result(folder,method.getName());
	}
}
