package transactionhistory;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.TransactionHistory_component;
import framework.BrowserSetup;

public class TransactionHistory extends BrowserSetup{

	
	private String fromAccountType,fromAccount;
	private TransactionHistory_component transactionHistory;
	
	public TransactionHistory() throws IOException {
	
		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_FROM_ACCOUNT_TYPE")
				);	
	}
	
	public TransactionHistory(String fromAccountType) throws IOException {
		
		this(DEFAULT_PROPERTIES.getProperty("DEF_AUTOMATION_USERNAME"),fromAccountType);	
	}
	
	public TransactionHistory(String username,String fromAccountType) throws IOException {
		BrowserSetup.setUser(username);
		this.fromAccountType=fromAccountType;
		this.fromAccount=user_prop.getProperty(this.fromAccountType);
		
		
	}
	
	@BeforeClass
	private void loadComponents() {
		
		transactionHistory=new TransactionHistory_component(driver);
	}
	
	@Test
	private void Test01_menu() {
	
		transactionHistory.menu();
	}
	
	@Test(dependsOnMethods="Test01_menu")
	private void Test02_selectPayee() {
		
		transactionHistory.selectPayee(fromAccount);
	}
	
	@Test(dependsOnMethods="Test02_selectPayee")
	private void Test03_result() {
		
		transactionHistory.result();
	}

}
