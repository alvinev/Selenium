package billpayment;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.Test;

import components.BillPayment_component;
import framework.BrowserSetup;
import framework.LoadProperties;

public class Block1_WaterAetra extends BrowserSetup{

	private String folder="BillPayment/Block1_Water";
	private String billerName,sourceAccount,subscriberNo,desc;


	public Block1_WaterAetra() throws IOException {
		Properties prop = LoadProperties.getProperties("billpayment.properties");
		this.billerName=prop.getProperty("block1_billerName");
		this.sourceAccount=prop.getProperty("block1_sourceAccount");
		this.subscriberNo=prop.getProperty("block1_subscriberNo");
		this.desc=prop.getProperty("block1_desc");		
	}

	public Block1_WaterAetra(String billerName,String sourceAccount,String subscriberNo,String desc) {
		this.billerName=billerName;
		this.sourceAccount=sourceAccount;
		this.subscriberNo=subscriberNo;
		this.desc=desc;
	}

	@Test
	private void Test01_WaterAetraMenu(Method method) {

		BillPayment_component.menu(billerName, folder, method.getName()+"_"+desc);
	}

	@Test(dependsOnMethods="Test01_WaterAetraMenu")
	private void Test02_waterAetraSelectAccount(Method method) {

		BillPayment_component.block1_selectAccount(sourceAccount, subscriberNo, desc, folder, method.getName()+"_"+desc);
	}

	@Test(dependsOnMethods="Test02_waterAetraSelectAccount")
	private void Test03_waterAetraConfirm(Method method) {	

		BillPayment_component.confirm(folder,  method.getName()+"_"+desc);
	}

	@Test(dependsOnMethods="Test03_waterAetraConfirm")
	private void Test04_waterAetraResult(Method method) {

		BillPayment_component.result(folder,  method.getName()+"_"+desc);
	}
}
