package transactionhistory.factory;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import transactionhistory.TransactionHistory;

public class Factory_TransactionHistory {

	@Factory(dataProvider="transactionHistory")
	private Object[] createInstances(String fromAccountType) throws IOException {
		return new Object[] {new TransactionHistory(fromAccountType)};
	}

	@DataProvider(name="transactionHistory")
	private static Object[][] DataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("TransactionHistory/TransactionHistory.txt");
		return dataArray;
	}
}
