package timedeposit;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.TimeDeposit_component;
import framework.BrowserSetup;

public class CreateTimeDepositSyariah extends BrowserSetup{

	private String fromAccountType,fromAccount,amount,term,tdType;
	private TimeDeposit_component timedeposit;

	public CreateTimeDepositSyariah() throws IOException {

		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_SYARIAH_FROM_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_AMOUNT"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_TERM"),
				DEFAULT_PROPERTIES.getProperty("DEF_TD_TYPE")
				
				);
	}
	
	public CreateTimeDepositSyariah(String fromAccountType,String amount,String term,String tdType) throws IOException {
		
	this(DEFAULT_PROPERTIES.getProperty("DEF_AUTOMATION_USERNAME"),fromAccountType,amount,term,tdType);
	
	}

	public CreateTimeDepositSyariah(String username,String fromAccountType,String amount,String term,String tdType) throws IOException {
		BrowserSetup.setUser(username);
		this.fromAccountType=fromAccountType;
		this.fromAccount=user_prop.getProperty(this.fromAccountType);
		this.amount=amount;
		this.term=term;
		this.tdType=tdType;

		
	}

	@BeforeClass
	private void loadComponents() {
		
		this.timedeposit= new TimeDeposit_component(driver);
	}
	
	@Test
	private void Test01_syariahMenu() {

		timedeposit.syariahMenu();
	}

	@Test(dependsOnMethods="Test01_syariahMenu")
	private void Test02_syariahCreate() {

		timedeposit.create(fromAccount, amount, term, tdType);
	}

	@Test(dependsOnMethods="Test02_syariahCreate")
	private void Test03_syariahTermAndCondition() {

		timedeposit.termAndCondition();
	}

	@Test(dependsOnMethods="Test03_syariahTermAndCondition")
	private void Test04_syariahConfirm() {		

		timedeposit.confirm();
	}

	@Test(dependsOnMethods="Test04_syariahConfirm")
	private void Test05_syariahResult() {
		timedeposit.result();
	}

}
