package transactionhistory;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.Test;

import components.TransactionHistory_component;
import framework.BrowserSetup;
import framework.LoadProperties;

public class TransactionHistory extends BrowserSetup{

	
	private String account;
	private String folder = "TransactionHistory";
	
	public TransactionHistory() throws IOException {
	
		Properties prop = LoadProperties.getProperties("transactionhistory.properties");
		this.account=prop.getProperty("account");		
	}
	
	public TransactionHistory(String account) {
		
		this.account=account;
	}
	
	@Test
	private void Test00_menu(Method method) {
	
		TransactionHistory_component.menu();
	}
	
	@Test(dependsOnMethods="menu")
	private void Test01_selectPayee(Method method) {
		
		TransactionHistory_component.selectPayee(account, folder, method.getName());
	}
	
	@Test(dependsOnMethods="selectPayee")
	private void Test02_result(Method method) {
		
		TransactionHistory_component.result(folder, method.getName());
	}

}
