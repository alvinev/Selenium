package timeDeposit;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.TimeDeposit_component;
import framework.BrowserSetup;

public class TimeDeposit extends BrowserSetup{

	private String fromAccountType,fromAccount,amount,term,tdType;
	private TimeDeposit_component timeDeposit;

	public TimeDeposit() throws IOException {
		
		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_SYARIAH_FROM_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_AMOUNT"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_TERM"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_TYPE")
				);
	}
	
	public TimeDeposit(String fromAccountType,String amount,String term,String tdType) throws IOException {
	
	this(DEFAULT_PROPERTIES.getProperty("DEF_AUTOMATION_USERNAME"),fromAccountType,amount,term,tdType);
	
	}
	
	public TimeDeposit(String username,String fromAccountType,String amount,String term,String tdType) throws IOException {
		BrowserSetup.setUser(username);
		this.fromAccountType=fromAccountType;
		this.fromAccount=user_prop.getProperty(this.fromAccountType);
		this.amount=amount;
		this.term=term;
		this.tdType=tdType;	
			
	}
	
	@BeforeClass
	private void loadComponents() {
		
		this.timeDeposit=new TimeDeposit_component(driver);
	}
	
	@Test
	private void Test01_Time_Deposit_Menu() {
		
		timeDeposit.timeDepositMenu(fromAccountType);
	}

	@Test(dependsOnMethods="Test01_Time_Deposit_Menu")
	private void Test02_Select_Account() {
		
		timeDeposit.create(fromAccount, amount, term, tdType);
	}
	
	@Test(dependsOnMethods="Test02_Select_Account")
	private void Test03_Term_And_Condition() {
		
		timeDeposit.termAndCondition();
	}
	
	@Test(dependsOnMethods="Test03_Term_And_Condition")
	private void Test04_Confirm() {		
		
		timeDeposit.confirm();
	}
	
	@Test(dependsOnMethods="Test04_Confirm")
	private void Test05_Time_Deposit_Result() {
		timeDeposit.result(fromAccountType);
	}

}
