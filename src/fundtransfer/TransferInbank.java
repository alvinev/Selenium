package fundtransfer;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import components.FundTransfer_component;
import framework.BrowserSetup;

public class TransferInbank extends BrowserSetup {

	private String fromAccountType,fromAccount,toAccountType,toAccount,amount,desc;
	private FundTransfer_component fundTransfer;

	public TransferInbank() throws IOException {

		this(
				DEFAULT_PROPERTIES.getProperty("DEF_USERNAME"),
				DEFAULT_PROPERTIES.getProperty("DEF_FROM_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_TO_ACCOUNT_TYPE"),
				DEFAULT_PROPERTIES.getProperty("DEF_INBANK_AMOUNT"),
				DEFAULT_PROPERTIES.getProperty("DEF_INBANK_DESC")
				);
	}

	public TransferInbank(String fromAccountType,String toAccountType,String amount,String desc) throws IOException {
			
		this(DEFAULT_PROPERTIES.getProperty("DEF_AUTOMATION_USERNAME"),fromAccountType,toAccountType,amount,desc);
		}
	
	public TransferInbank(String username,String fromAccountType,String toAccountType,String amount,String desc) throws IOException {
		BrowserSetup.setUser(username);
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
	private void Test01_inbankMenu() {

		fundTransfer.inbankMenu();
	}

	@Test(dependsOnMethods="Test01_inbankMenu")
	private void Test02_inbankSelectAccount() {

		fundTransfer.inbankSelectAccount(fromAccount, toAccount, amount,desc);
	}

	@Test(dependsOnMethods="Test02_inbankSelectAccount")
	private void Test03_inbankConfirm() {		
		fundTransfer.confirm();
	}

	@Test(dependsOnMethods="Test03_inbankConfirm")
	private void Test04_inbankResult() {
		fundTransfer.result();		
	}
}
