package billpayment.factory;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import billpayment.BaznasZakat;
import framework.LoadTestCase;

public class Factory_BaznasZakat {

	@Factory(dataProvider="baznas")
	private Object[] createInstances(String fromAccountType,String subscriberNo,String amount,String desc) throws IOException {
		return new Object[] {new BaznasZakat(fromAccountType,subscriberNo,amount,desc)};
	}

	@DataProvider(name="baznas")
	private static Object[][] DataProvider() throws IOException {
		
		Object[][] dataArray = LoadTestCase.loadFromFile("BillPayment/BaznasZakat.txt");
		return dataArray;
	}
}
