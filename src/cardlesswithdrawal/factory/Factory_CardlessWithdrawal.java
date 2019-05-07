package cardlesswithdrawal.factory;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Factory;

import cardlesswithdrawal.CardlessWithdrawal;
import framework.LoadTestCase;

public class Factory_CardlessWithdrawal {

	@Factory(dataProvider="cardless")
	private Object[] createInstances(String fromAccountType,String amount,String desc) throws IOException {
		return new Object[] {new CardlessWithdrawal(fromAccountType,amount,desc)};
	}

	@DataProvider(name="cardless")
	private static Object[][] DataProvider() throws IOException {
		Object[][] dataArray = LoadTestCase.loadFromFile("CardlessWithdrawal/CardlessWithdrawal.txt");
		return dataArray;
	}
}
