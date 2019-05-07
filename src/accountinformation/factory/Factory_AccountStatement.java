package accountinformation.factory;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import accountinformation.AccountStatement;
import framework.LoadTestCase;


public class Factory_AccountStatement {

	@Factory(dataProvider="accountStatement")
	private Object[] createInstances(String fromAccountType,String period) throws IOException {
		return new Object[] {new AccountStatement(fromAccountType,period)};
	}

	@DataProvider(name="accountStatement")
	private static Object[][] menuDataProvider() throws IOException {
		
		Object[][] dataArray = LoadTestCase.loadFromFile("AccountInformation/AccountStatement.txt");
		return dataArray;
	}
}
