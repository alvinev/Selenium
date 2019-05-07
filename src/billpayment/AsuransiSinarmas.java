package billpayment;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.BillPayment_component;
import framework.BrowserSetup;

public class AsuransiSinarmas extends BrowserSetup {

	private String billerName,fromAccountType,fromAccount,subscriberNo,desc,amount;

	private BillPayment_component billpayment;

	public AsuransiSinarmas() throws IOException {
		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),		
				DEFAULT_PROPERTIES.getProperty("DEF_FROM_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_ASURANSI_SUBSCRIBER_NO"),
				DEFAULT_PROPERTIES.getProperty("DEF_ASURANSI_AMOUNT"),
				DEFAULT_PROPERTIES.getProperty("DEF_ASURANSI_DESC")
				);
	}
	
	public AsuransiSinarmas(String fromAccountType,String subscriberNo,String amount,String desc) throws IOException {
		
		this(DEFAULT_PROPERTIES.getProperty("DEF_AUTOMATION_USERNAME"),fromAccountType,subscriberNo,amount,desc);
	}
	
	public AsuransiSinarmas(String username,String fromAccountType,String subscriberNo,String amount, String desc) throws IOException {
		BrowserSetup.setUser(username);
		this.billerName=DEFAULT_PROPERTIES.getProperty("DEF_ASURANSI_BILLER_NAME");
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

		billpayment.asuransi_selectAccount(fromAccount, subscriberNo, amount, desc);
	}

	@Test(dependsOnMethods="Test02_Select_Account")
	private void Test03_Confirm() {	

		billpayment.confirm();
	}
	
	@Test(dependsOnMethods="Test03_Confirm")
	private void Test04_Asuransi_Sinarmas_Result() {	

		billpayment.result(fromAccountType);
	}
}
