package accountinformation;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.Test;

import components.AccountInformation_component;
import framework.BrowserSetup;
import framework.LoadProperties;

public class AccountSummary extends BrowserSetup {

	private String folder="AccountInformation/AccountSummary";
	private String account;

	public AccountSummary() throws IOException {

		Properties prop = LoadProperties.getProperties("accountinformation.properties");
		this.account=prop.getProperty("accountSummaryAccount");
	}

	public AccountSummary(String account) {

		this.account=account;
	}

	@Test
	private void Test00_menu(Method method) {

		AccountInformation_component.accountSummaryMenu();
	}
	
	@Test(dependsOnMethods="Test00_menu")
	private void Test01_selectAccount(Method method) {
		
		AccountInformation_component.accountSummaryselectAccount(account, folder, method.getName());
	}

	@Test(dependsOnMethods="Test01_selectAccount")
	private void Test02_result(Method method) {

		AccountInformation_component.accountSummaryResult(folder, method.getName());
	}
}
