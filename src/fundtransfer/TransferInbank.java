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
	private void Test01_inbankMenu(Method method) {
		
		FundTransfer_component.inbankMenu(folder,method.getName()+"_"+desc);
	}

	@Test(dependsOnMethods="Test01_inbankMenu")
	private void Test02_inbankSelectAccount(Method method) {
		
		FundTransfer_component.inbankSelectAccount(sourceAccount, toAccount, amount,desc,folder,method.getName()+"_"+desc);
	}
	
	@Test(dependsOnMethods="Test02_inbankSelectAccount")
	private void Test03_inbankConfirm(Method method) {		
		FundTransfer_component.confirm(folder,method.getName()+"_"+desc);
	}
	
	@Test(dependsOnMethods="Test03_inbankConfirm")
	private void Test04_inbankResult(Method method) {
		FundTransfer_component.result(folder,method.getName()+"_"+desc);		
	}
}
