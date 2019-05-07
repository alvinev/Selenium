package billpayment.factory;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import billpayment.AsuransiSinarmas;
import framework.LoadTestCase;

public class Factory_AsuransiSinarmas {

	@Factory(dataProvider="asuransi")
	private Object[] createInstances(String fromAccountType,String subscriberNo,String amount,String desc) throws IOException {
		return new Object[] {new AsuransiSinarmas(fromAccountType,subscriberNo,amount,desc)};
	}

	@DataProvider(name="asuransi")
	private static Object[][] DataProvider() throws IOException {
		
		Object[][] dataArray = LoadTestCase.loadFromFile("BillPayment/AsuransiSinarmas.txt");
		return dataArray;
	}
}
