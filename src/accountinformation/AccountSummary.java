package accountinformation;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.AccountInformation_component;
import framework.BrowserSetup;

public class AccountSummary extends BrowserSetup {

	private String fromAccountType,account;
	private AccountInformation_component accountInformation;

	public AccountSummary() throws IOException {

		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_FROM_ACCOUNT_TYPE")
				);
	}

	public AccountSummary(String fromAccountType) throws IOException {
		
		this(DEFAULT_PROPERTIES.getProperty("DEF_AUTOMATION_USERNAME"),fromAccountType);
		
	}
	
	public AccountSummary(String username,String fromAccountType) throws IOException {
		BrowserSetup.setUser(username);
		this.fromAccountType=fromAccountType;
		this.account=user_prop.getProperty(this.fromAccountType);
		
	}
	
	@BeforeClass
	private void loadComponents() {
		
		this.accountInformation=new AccountInformation_component(driver);
	}

	@Test
	private void Test01_Account_Summary_Menu() {

		accountInformation.accountSummaryMenu();
	}
	
	@Test(dependsOnMethods="Test01_Account_Summary_Menu")
	private void Test02_Select_Account() {
		
		accountInformation.accountSummaryselectAccount(account);
	}

	@Test(dependsOnMethods="Test02_Select_Account")
	private void Test03_Account_Summary_Result() {

		accountInformation.accountSummaryResult();
	}
}
