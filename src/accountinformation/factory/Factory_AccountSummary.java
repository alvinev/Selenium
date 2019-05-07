package accountinformation.factory;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import accountinformation.AccountSummary;
import framework.LoadTestCase;

public class Factory_AccountSummary {

	@Factory(dataProvider="accountSummary")
	private Object[] createInstances(String fromAccountType) throws IOException {
		return new Object[] {new AccountSummary(fromAccountType)};
	}

	@DataProvider(name="accountSummary")
	private static Object[][] accountSummary() throws IOException {
		
		Object[][] dataArray = LoadTestCase.loadFromFile("AccountInformation/AccountSummary.txt");
		return dataArray;
	}
}
