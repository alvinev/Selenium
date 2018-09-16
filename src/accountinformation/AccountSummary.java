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
	private void Test01_menu(Method method) {

		AccountInformation_component.accountSummaryMenu(account, folder, method.getName());
	}

	@Test(dependsOnMethods="Test01_menu")
	private void Test02_result(Method method) {

		AccountInformation_component.accountSummaryResult(folder, method.getName());
	}
}
