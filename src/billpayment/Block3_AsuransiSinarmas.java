package billpayment;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.Test;

import components.BillPayment_component;
import framework.BrowserSetup;
import framework.LoadProperties;

public class Block3_AsuransiSinarmas extends BrowserSetup {

	private String folder="BillPayment/Block3_AsuransiSinarmas";
	private String billerName,sourceAccount,subscriberNo,amount,desc;

	public Block3_AsuransiSinarmas() throws IOException {
		Properties prop = LoadProperties.getProperties("billpayment.properties");
		this.billerName=prop.getProperty("block3_billerName");
		this.sourceAccount=prop.getProperty("block3_sourceAccount");
		this.subscriberNo=prop.getProperty("block3_subscriberNo");
		this.amount=prop.getProperty("block3_amount");
		this.desc=prop.getProperty("block3_desc");		
	}

	public Block3_AsuransiSinarmas(String billerName,String sourceAccount,String subscriberNo,String amount,String desc) {
		this.billerName=billerName;
		this.sourceAccount=sourceAccount;
		this.subscriberNo=subscriberNo;
		this.amount=amount;
		this.desc=desc;
	}

	@Test
	private void Test01_AsuransiSinarmasMenu(Method method) {

		BillPayment_component.menu(billerName, folder, method.getName()+"_"+desc);
	}

	@Test(dependsOnMethods="Test01_AsuransiSinarmasMenu")
	private void Test02_AsuransiSinarmasSelectAccount(Method method) {

		BillPayment_component.block3_selectAccount(sourceAccount, subscriberNo, amount,desc, folder, method.getName()+"_"+desc);
	}

	@Test(dependsOnMethods="Test02_AsuransiSinarmasSelectAccount")
	private void Test03_AsuransiSinarmasConfirm(Method method) {	

		BillPayment_component.confirm(folder,  method.getName()+"_"+desc);
	}

	@Test(dependsOnMethods="Test03_AsuransiSinarmasConfirm")
	private void Test04_AsuransiSinarmasResult(Method method) {

		BillPayment_component.result(folder,  method.getName()+"_"+desc);
	}
}
