package billpayment;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.Test;

import components.BillPayment_component;
import framework.BrowserSetup;
import framework.LoadProperties;

public class Block2_BaznasZakat extends BrowserSetup{
	
	private String folder="BillPayment/Block2_BaznasZakat";
	private String billerName,sourceAccount,subscriberNo,amount,desc;

	public Block2_BaznasZakat() throws IOException {
		Properties prop = LoadProperties.getProperties("billpayment.properties");
		this.billerName=prop.getProperty("block2_billerName");
		this.sourceAccount=prop.getProperty("block2_sourceAccount");
		this.subscriberNo=prop.getProperty("block2_subscriberNo");
		this.amount=prop.getProperty("block2_amount");
		this.desc=prop.getProperty("block2_desc");		
	}

	public Block2_BaznasZakat(String billerName,String sourceAccount,String subscriberNo,String amount,String desc) {
		this.billerName=billerName;
		this.sourceAccount=sourceAccount;
		this.subscriberNo=subscriberNo;
		this.amount=amount;
		this.desc=desc;
	}

	@Test
	private void Test01_BaznasZakatMenu(Method method) {

		BillPayment_component.menu(billerName, folder, method.getName()+"_"+desc);
	}

	@Test(dependsOnMethods="Test01_BaznasZakatMenu")
	private void Test02_BaznasZakatSelectAccount(Method method) {

		BillPayment_component.block2_selectAccount(sourceAccount, subscriberNo, amount,desc, folder, method.getName()+"_"+desc);
	}

	@Test(dependsOnMethods="Test02_BaznasZakatSelectAccount")
	private void Test03_BaznasZakatConfirm(Method method) {	

		BillPayment_component.confirm(folder,  method.getName()+"_"+desc);
	}

	@Test(dependsOnMethods="Test03_BaznasZakatConfirm")
	private void Test04_BaznasZakatResult(Method method) {

		BillPayment_component.result(folder,  method.getName()+"_"+desc);
	}
}
