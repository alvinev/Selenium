package fundtransfer;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.Test;

import components.FundTransfer_component;
import framework.BrowserSetup;
import framework.LoadProperties;

public class TransferOtherbank extends BrowserSetup{

	private String folder="FundTransfer/otbank";
	private String otbankMethod,sourceAccount,toAccount,amount,desc;
	
	public TransferOtherbank() throws IOException {
		
		Properties prop = LoadProperties.getProperties("fundtransfer.properties");
		this.otbankMethod=prop.getProperty("otbankMethod");
		this.sourceAccount=prop.getProperty("otbankSourceAccount");
		this.toAccount=prop.getProperty("otbankToAccount");
		this.amount=prop.getProperty("otbankAmount");
		this.desc=prop.getProperty("otbankDesc");		
	}
	
	public TransferOtherbank(String otbankMethod,String sourceAccount,String toACccount,String amount,String desc) {
		this.otbankMethod=otbankMethod;
		this.sourceAccount=sourceAccount;
		this.toAccount=toACccount;
		this.amount=amount;
		this.desc=desc;
	}

	@Test
	private void Test00_otbankMenu(Method method) {
		
		FundTransfer_component.otbankMenu();
	}

	@Test(dependsOnMethods="Test00_otbankMenu")
	private void Test01_otbankSelectAccount(Method method) {
		
		FundTransfer_component.otbankSelectAccount(otbankMethod,sourceAccount, toAccount, amount,desc,folder,method.getName()+"_"+otbankMethod);
	}
	
	@Test(dependsOnMethods="Test01_otbankSelectAccount")
	private void Test02_otbankConfirm(Method method) {		
		FundTransfer_component.confirm(folder,method.getName()+"_"+otbankMethod);
	}
	
	@Test(dependsOnMethods="Test02_otbankConfirm")
	private void Test03_otbankResult(Method method) {
		FundTransfer_component.result(folder,method.getName()+"_"+otbankMethod);		
	}
}
