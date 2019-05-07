package accountinformation.factory;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import accountinformation.BalanceInquiry;
import framework.LoadTestCase;

public class Factory_BalanceInquiry {

	@Factory(dataProvider="balanceInquiry")
	private Object[] createInstances(String fromAccountType) throws IOException {
		return new Object[] {new BalanceInquiry(fromAccountType)};
	}

	@DataProvider(name="balanceInquiry")
	private static Object[][] BalanceInquiry() throws IOException {
		
		Object[][] dataArray = LoadTestCase.loadFromFile("AccountInformation/BalanceInquiry.txt");
		return dataArray;
	}
}
