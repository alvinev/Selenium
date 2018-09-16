package fundtransfer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.Test;

import components.FundTransfer_component;
import framework.BrowserSetup;
import framework.LoadProperties;

public class TransferInbank extends BrowserSetup {

	private String folder="FundTransfer/Inbank";
	private String sourceAccount,toAccount,amount,desc;
	
	public TransferInbank() throws IOException {
		
		Properties prop = LoadProperties.getProperties("fundtransfer.properties");
		this.sourceAccount=prop.getProperty("inbankSourceAccount");
		this.toAccount=prop.getProperty("inbankToAccount");
		this.amount=prop.getProperty("inbankAmount");
		this.desc=prop.getProperty("inbankDesc");		
	}
	
	public TransferInbank(String sourceAccount,String toACccount,String amount,String desc) {
			
		this.sourceAccount=sourceAccount;
		this.toAccount=toACccount;
		this.amount=amount;
		this.desc=desc;
	}

	@Test
	private void Test00_inbankMenu(Method method) {
		
		FundTransfer_component.inbankMenu();
	}

	@Test(dependsOnMethods="Test00_inbankMenu")
	private void Test01_inbankSelectAccount(Method method) {
		
		FundTransfer_component.inbankSelectAccount(sourceAccount, toAccount, amount,desc,folder,method.getName());
	}
	
	@Test(dependsOnMethods="Test01_inbankSelectAccount")
	private void Test02_inbankConfirm(Method method) {		
		FundTransfer_component.confirm(folder,method.getName());
	}
	
	@Test(dependsOnMethods="Test02_inbankConfirm")
	private void Test03_inbankResult(Method method) {
		FundTransfer_component.result(folder,method.getName());		
	}
}
