package fundtransfer.factory;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import framework.LoadTestCase;
import fundtransfer.TransferInbank;

public class Factory_TransferInbank {
	
	@Factory(dataProvider="inbank")
	private Object[] createInstances(String fromAccountType,String toAccountType,String amount,String desc) throws IOException {
		return new Object[] {new TransferInbank(fromAccountType,toAccountType,amount,desc)};
	}

	@DataProvider(name="inbank")
	private static Object[][] DataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("FundTransfer/TransferInbank.txt");
		return dataArray;
	}
}
