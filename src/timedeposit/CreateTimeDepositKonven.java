package timedeposit;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.Test;

import components.TimeDeposit_component;
import framework.BrowserSetup;
import framework.LoadProperties;

public class CreateTimeDepositKonven extends BrowserSetup{

	private String folder="TimeDeposit/CreateKonven";
	private String sourceAccount,amount,term,tdType;

	public CreateTimeDepositKonven() throws IOException {
		Properties prop = LoadProperties.getProperties("timedeposit.properties");
		this.sourceAccount=prop.getProperty("sourceAccount");
		this.amount=prop.getProperty("amount");
		this.term=prop.getProperty("term");
		this.tdType=prop.getProperty("tdType");
		
	}
	
	public CreateTimeDepositKonven(String sourceAccount,String amount,String term,String tdType) {
		this.sourceAccount=sourceAccount;
		this.amount=amount;
		this.term=term;
		this.tdType=tdType;
	}


	@Test
	private void Test01_konvenMenu(Method method) {
		
		TimeDeposit_component.konvenMenu(folder,method.getName()+"_"+tdType+"_"+term);
	}

	@Test(dependsOnMethods="Test01_konvenMenu")
	private void Test02_konvenCreate(Method method) {
		
		TimeDeposit_component.create(sourceAccount, amount, term, tdType, folder, method.getName()+"_"+tdType+"_"+term);
	}
	
	@Test(dependsOnMethods="Test02_konvenCreate")
	private void Test03_konvenTermAndCondition(Method method) {
		
		TimeDeposit_component.termAndCondition(folder,method.getName()+"_"+tdType+"_"+term);
	}
	
	@Test(dependsOnMethods="Test03_konvenTermAndCondition")
	private void Test04_konvenConfirm(Method method) {		
		
		TimeDeposit_component.confirm(folder, method.getName()+"_"+tdType+"_"+term);
	}
	
	@Test(dependsOnMethods="Test04_konvenConfirm")
	private void Test05_konvenResult(Method method) {
		TimeDeposit_component.result(folder,  method.getName()+"_"+tdType+"_"+term);
	}

}
