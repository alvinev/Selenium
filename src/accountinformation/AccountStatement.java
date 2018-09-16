package accountinformation;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.Test;

import components.AccountInformation_component;
import framework.BrowserSetup;
import framework.LoadProperties;

public class AccountStatement extends BrowserSetup {

	private String folder="AccountInformation/AccountStatement";
	private String account,period;

	public AccountStatement() throws IOException {

		Properties prop = LoadProperties.getProperties("accountinformation.properties");
		this.account=prop.getProperty("accountStatementAccount");
		this.period=prop.getProperty("accountStatementPeriod");
	}

	public AccountStatement(String account,String period) {

		this.account=account;
		this.period=period;
	}

	@Test
	private void Test00_menu(Method method) {

		AccountInformation_component.accountStatementMenu();
	}

	@Test(dependsOnMethods="Test00_menu")
	private void Test01_selectAccountPeriod(Method method) {

		AccountInformation_component.accountStatementSelectAccountPeriod(account, period, folder, method.getName());
	}
	
	@Test(dependsOnMethods="Test01_selectAccountPeriod")
	private void Test02_result(Method method) {

		AccountInformation_component.accountStatementResult(folder, method.getName());
	}
	
}
