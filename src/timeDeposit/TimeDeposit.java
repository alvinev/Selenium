package timedeposit;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.TimeDeposit_component;
import framework.BrowserSetup;

public class CreateTimeDepositKonven extends BrowserSetup{

	private String fromAccountType,fromAccount,amount,term,tdType;
	private TimeDeposit_component timeDeposit;

	public CreateTimeDepositKonven() throws IOException {
		
		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_KONVEN_FROM_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_AMOUNT"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_TERM"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_TYPE")
				);
	}
	
	public CreateTimeDepositKonven(String fromAccountType,String amount,String term,String tdType) throws IOException {
	
	this(DEFAULT_PROPERTIES.getProperty("DEF_AUTOMATION_USERNAME"),fromAccountType,amount,term,tdType);
	
	}
	
	public CreateTimeDepositKonven(String username,String fromAccountType,String amount,String term,String tdType) throws IOException {
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
	private void Test01_konvenMenu() {
		
		timeDeposit.konvenMenu();
	}

	@Test(dependsOnMethods="Test01_konvenMenu")
	private void Test02_konvenCreate() {
		
		timeDeposit.create(fromAccount, amount, term, tdType);
	}
	
	@Test(dependsOnMethods="Test02_konvenCreate")
	private void Test03_konvenTermAndCondition() {
		
		timeDeposit.termAndCondition();
	}
	
	@Test(dependsOnMethods="Test03_konvenTermAndCondition")
	private void Test04_konvenConfirm() {		
		
		timeDeposit.confirm();
	}
	
	@Test(dependsOnMethods="Test04_konvenConfirm")
	private void Test05_konvenResult() {
		timeDeposit.result();
	}

}
