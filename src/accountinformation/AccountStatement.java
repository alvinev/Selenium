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
	private void Test01_menu(Method method) {

		AccountInformation_component.accountStatementMenu(folder, method.getName());
	}

	@Test(dependsOnMethods="Test01_menu")
	private void Test02_selectAccountPeriod(Method method) {

		AccountInformation_component.accountStatementSelectAccountPeriod(account, period, folder, method.getName());
	}
	
	@Test(dependsOnMethods="Test02_selectAccountPeriod")
	private void Test03_result(Method method) {

		AccountInformation_component.accountStatementResult(folder, method.getName());
	}
	
}
