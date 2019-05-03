package accountinformation;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.AccountInformation_component;
import framework.BrowserSetup;

public class BalanceInquiry extends BrowserSetup {

	private String fromAccountType,account;
	private AccountInformation_component accountInformation;

	public BalanceInquiry() throws IOException {

		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_FROM_ACCOUNT_TYPE"));

	}
	
	public BalanceInquiry(String fromAccountType) throws IOException {
		
		this(DEFAULT_PROPERTIES.getProperty("DEF_AUTOMATION_USERNAME"),fromAccountType);
		
	}

	public BalanceInquiry(String username,String fromAccountType) throws IOException {
		BrowserSetup.setUser(username);
		this.fromAccountType=fromAccountType;
		this.account=user_prop.getProperty(this.fromAccountType);
		
	}

	@BeforeClass
	private void loadComponents() {
		
		this.accountInformation=new AccountInformation_component(driver);
	}
	
	@Test
	private void Test01_menu() {

		accountInformation.balanceInquiryMenu();

	}

	@Test(dependsOnMethods="Test01_menu")
	private void Test02_selectAccount() {

		accountInformation.balanceInquirySelectAccount(account);

	}
	
	@Test(dependsOnMethods="Test02_selectAccount")
	private void Test03_result() {

		accountInformation.balanceInquiryResult();

	}
}
