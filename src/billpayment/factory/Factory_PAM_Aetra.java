package billpayment.factory;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import billpayment.PAM_Aetra;
import framework.LoadTestCase;

public class Factory_PAM_Aetra {

	@Factory(dataProvider="aetra")
	private Object[] createInstances(String fromAccountType,String subscriberNo,String desc) throws IOException {
		return new Object[] {new PAM_Aetra(fromAccountType,subscriberNo,desc)};
	}

	@DataProvider(name="aetra")
	private static Object[][] DataProvider() throws IOException {
		
		Object[][] dataArray = LoadTestCase.loadFromFile("BillPayment/PAM_Aetra.txt");
		return dataArray;
	}
}
