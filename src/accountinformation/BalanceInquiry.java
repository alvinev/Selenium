package accountinformation;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.Test;

import components.AccountInformation_component;
import framework.BrowserSetup;
import framework.LoadProperties;

public class BalanceInquiry extends BrowserSetup {

	private String folder="AccountInformation/BalanceInquiry";
	private String account;
	
	public BalanceInquiry() throws IOException {
		
		Properties prop = LoadProperties.getProperties("accountinformation.properties");
		this.account=prop.getProperty("balanceInquiryAccount");
	
	}
	
	public BalanceInquiry(String account) {
			
		this.account=account;
	}
	
	@Test
	private void Test00_menu(Method method) {
		
		AccountInformation_component.balanceInquiryMenu();
		
	}
	
	@Test(dependsOnMethods="Test00_menu")
	private void Test02_selectAccount(Method method) {
		
		AccountInformation_component.balanceInquirySelectAccount(account, folder, method.getName());
		
	}
	@Test(dependsOnMethods="Test02_selectAccount")
	private void Test03_result(Method method) {
		
		AccountInformation_component.balanceInquiryResult(folder, method.getName());
		
	}
}
