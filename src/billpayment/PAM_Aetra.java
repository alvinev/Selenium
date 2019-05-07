package billpayment;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.BillPayment_component;
import framework.BrowserSetup;

public class PAM_Aetra extends BrowserSetup{

	private String billerName,fromAccountType,fromAccount,subscriberNo,desc;

	private BillPayment_component billpayment;

	public PAM_Aetra() throws IOException {
		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),		
				DEFAULT_PROPERTIES.getProperty("DEF_FROM_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_AETRA_SUBSCRIBER_NO"),
				DEFAULT_PROPERTIES.getProperty("DEF_AETRA_DESC")
				);
	}
	
	public PAM_Aetra(String fromAccountType,String subscriberNo,String desc) throws IOException {
		
		this(DEFAULT_PROPERTIES.getProperty("DEF_AUTOMATION_USERNAME"),fromAccountType,subscriberNo,desc);
	}
	
	public PAM_Aetra(String username,String fromAccountType,String subscriberNo,String desc) throws IOException {
		BrowserSetup.setUser(username);
		this.billerName=DEFAULT_PROPERTIES.getProperty("DEF_AETRA_BILLER_NAME");
		this.fromAccountType=fromAccountType;
		this.fromAccount=user_prop.getProperty(this.fromAccountType);
		this.subscriberNo=subscriberNo;
		this.desc=desc;
		
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
	private void Test02_Inquiry() {

		billpayment.aetra_inquiry(subscriberNo);
	}
	
	@Test(dependsOnMethods="Test02_Inquiry")
	private void Test03_Select_Account() {

		billpayment.aetra_selectAccount(fromAccount, desc);
	}

	@Test(dependsOnMethods="Test03_Select_Account")
	private void Test04_Confirm() {	

		billpayment.confirm();
	}
	
	@Test(dependsOnMethods="Test04_Confirm")
	private void Test05_PAM_Aetra_Result() {	

		billpayment.result(fromAccountType);
	}

}
