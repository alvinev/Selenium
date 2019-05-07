package timeDeposit.factory;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import timeDeposit.TimeDeposit;

public class Factory_TimeDeposit {

	@Factory(dataProvider="timeDeposit")
	private Object[] createInstances(String fromAccountType,String amount,String term,String tdType) throws IOException {
		return new Object[] {new TimeDeposit(fromAccountType,amount,term,tdType)};
	}

	@DataProvider(name="timeDeposit")
	private static Object[][] DataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("TimeDeposit/TimeDeposit.txt");
		return dataArray;
	}
}
