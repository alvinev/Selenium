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
		this.sourceAccount=prop.getProperty("sourceAccountKonven");
		this.amount=prop.getProperty("amountKonven");
		this.term=prop.getProperty("termKonven");
		this.tdType=prop.getProperty("tdTypeKonven");	
	}
	
	public CreateTimeDepositKonven(String sourceAccount,String amount,String term,String tdType) {
		
		this.sourceAccount=sourceAccount;
		this.amount=amount;
		this.term=term;
		this.tdType=tdType;
	}

	@Test
	private void Test00_konvenMenu(Method method) {
		
		TimeDeposit_component.konvenMenu();
	}

	@Test(dependsOnMethods="Test00_konvenMenu")
	private void Test01_konvenCreate(Method method) {
		
		TimeDeposit_component.create(sourceAccount, amount, term, tdType, folder, method.getName()+"_"+tdType+"_"+term);
	}
	
	@Test(dependsOnMethods="Test01_konvenCreate")
	private void Test02_konvenTermAndCondition(Method method) {
		
		TimeDeposit_component.termAndCondition(folder,method.getName()+"_"+tdType+"_"+term);
	}
	
	@Test(dependsOnMethods="Test02_konvenTermAndCondition")
	private void Test03_konvenConfirm(Method method) {		
		
		TimeDeposit_component.confirm(folder, method.getName()+"_"+tdType+"_"+term);
	}
	
	@Test(dependsOnMethods="Test03_konvenConfirm")
	private void Test04_konvenResult(Method method) {
		TimeDeposit_component.result(folder,  method.getName()+"_"+tdType+"_"+term);
	}

}
