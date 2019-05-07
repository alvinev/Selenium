package billpayment;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.BillPayment_component;
import framework.BrowserSetup;

public class BaznasZakat extends BrowserSetup{
	
	private String billerName,fromAccountType,fromAccount,subscriberNo,desc,amount;

	private BillPayment_component billpayment;

	public BaznasZakat() throws IOException {
		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),		
				DEFAULT_PROPERTIES.getProperty("DEF_FROM_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_BAZNAS_SUBSCRIBER_NO"),
				DEFAULT_PROPERTIES.getProperty("DEF_BAZNAS_AMOUNT"),
				DEFAULT_PROPERTIES.getProperty("DEF_BAZNAS_DESC")
				);
	}
	
	public BaznasZakat(String fromAccountType,String subscriberNo,String amount,String desc) throws IOException {
		
		this(DEFAULT_PROPERTIES.getProperty("DEF_AUTOMATION_USERNAME"),fromAccountType,subscriberNo,amount,desc);
		
	}
	
	public BaznasZakat(String username,String fromAccountType,String subscriberNo,String amount, String desc) throws IOException {
		BrowserSetup.setUser(username);
		this.billerName=DEFAULT_PROPERTIES.getProperty("DEF_BAZNAS_BILLER_NAME");
		this.fromAccountType=fromAccountType;
		this.fromAccount=user_prop.getProperty(this.fromAccountType);
		this.subscriberNo=subscriberNo;
		this.desc=desc;
		this.amount=amount;
		
	}

	@BeforeClass
	private void loadComponents() {
		
		this.billpayment=new BillPayment_component(driver);
	}
	
	@Test
	private void Test01_Biller_Menu() {

		billpayment.menu(billerName);
	}
	
	@Test(dependsOnMethods="Test01_Biller_Menu")
	private void Test02_Select_Account() {

		billpayment.baznas_selectAccount(fromAccount, subscriberNo, amount, desc);
	}

	@Test(dependsOnMethods="Test02_Select_Account")
	private void Test03_Confirm() {	

		billpayment.confirm();
	}
	
	@Test(dependsOnMethods="Test03_Confirm")
	private void Test04_Baznas_Zakat_Result() {	

		billpayment.result(fromAccountType);
	}
}
