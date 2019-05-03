package fundtransfer;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.FundTransfer_component;
import framework.BrowserSetup;

public class TransferOtherbank extends BrowserSetup{

	private String otbankMethod,fromAccountType,fromAccount,toAccountType,toAccount,amount,desc;
	private FundTransfer_component fundTransfer;
	
	public TransferOtherbank() throws IOException {
		
		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_FROM_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_TO_ACCOUNT_OTBANK_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_OTBANK_METHOD"),
				DEFAULT_PROPERTIES.getProperty("DEF_OTBANK_AMOUNT"),
				DEFAULT_PROPERTIES.getProperty("DEF_OTBANK_DESC"));
	}

	public TransferOtherbank(String fromAccountType,String toAccountType,String otbankMethod,String amount,String desc) throws IOException {
		
			this(DEFAULT_PROPERTIES.getProperty("DEF_AUTOMATION_USERNAME"),fromAccountType,toAccountType,otbankMethod,amount,desc);
		}
	
	public TransferOtherbank(String username,String fromAccountType,String toAccountType,String otbankMethod,String amount,String desc) throws IOException {
		BrowserSetup.setUser(username);
		this.otbankMethod=otbankMethod;
		this.fromAccountType=fromAccountType;
		this.fromAccount=user_prop.getProperty(this.fromAccountType);
		this.toAccountType=toAccountType;
		this.toAccount=user_prop.getProperty(this.toAccountType);
		this.amount=amount;
		this.desc=desc;	
		
	}
	
	@BeforeClass
	private void loadComponents() {
		
		this.fundTransfer=new FundTransfer_component(driver);
	}
	
	@Test
	private void Test01_otbankMenu() {
		
		fundTransfer.otbankMenu();
	}

	@Test(dependsOnMethods="Test01_otbankMenu")
	private void Test02_otbankSelectAccount() {
		
		fundTransfer.otbankSelectAccount(otbankMethod,fromAccount, toAccount, amount,desc);
	}
	
	@Test(dependsOnMethods="Test02_otbankSelectAccount")
	private void Test03_otbankConfirm() {		
		fundTransfer.confirm();
	}
	
	@Test(dependsOnMethods="Test03_otbankConfirm")
	private void Test04_otbankResult() {
		fundTransfer.result();		
	}
}
