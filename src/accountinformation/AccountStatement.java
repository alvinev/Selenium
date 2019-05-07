package accountinformation;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.AccountInformation_component;
import framework.BrowserSetup;

public class AccountStatement extends BrowserSetup {

	private String fromAccountType,account,period;
	private AccountInformation_component accountInformation;

	public AccountStatement() throws IOException {

		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_FROM_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_TRX_HISTORY_PERIOD")
				);
	}
	
	public AccountStatement(String fromAccountType,String period) throws IOException {
		this(DEFAULT_PROPERTIES.getProperty("DEF_AUTOMATION_USERNAME"),fromAccountType,period);
		
	}

	public AccountStatement(String username,String fromAccountType,String period) throws IOException {

		BrowserSetup.setUser(username);
		
		this.fromAccountType=fromAccountType;
		this.account=user_prop.getProperty(this.fromAccountType);
		this.period=period;
		
	}
	
	
	@BeforeClass
	private void loadComponents() {
		
		this.accountInformation=new AccountInformation_component(driver);
				
	}


	@Test
	private void Test01_Account_Statement_Menu() {

		accountInformation.accountStatementMenu();
	}

	@Test(dependsOnMethods="Test01_Account_Statement_Menu")
	private void Test02_Select_Account_Period() {

		accountInformation.accountStatementSelectAccountPeriod(account, period);
	}
	
	@Test(dependsOnMethods="Test02_Select_Account_Period")
	private void Test03_Account_Statement_Result() {

		accountInformation.accountStatementResult();
	}
	
}
