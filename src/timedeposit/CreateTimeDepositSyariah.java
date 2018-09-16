package timedeposit;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.Test;

import components.TimeDeposit_component;
import framework.BrowserSetup;
import framework.LoadProperties;

public class CreateTimeDepositSyariah extends BrowserSetup{

	private String folder="TimeDeposit/CreateSyariah";
	private String sourceAccount,amount,term,tdType;

	public CreateTimeDepositSyariah() throws IOException {
		Properties prop = LoadProperties.getProperties("timedeposit.properties");
		this.sourceAccount=prop.getProperty("sourceAccountSyariah");
		this.amount=prop.getProperty("amountSyariah");
		this.term=prop.getProperty("termSyariah");
		this.tdType=prop.getProperty("tdTypeSyariah");
	}
	
	public CreateTimeDepositSyariah(String sourceAccount,String amount,String term,String tdType) {
		this.sourceAccount=sourceAccount;
		this.amount=amount;
		this.term=term;
		this.tdType=tdType;
	}

	@Test
	private void Test01_syariahMenu(Method method) {
		
		TimeDeposit_component.syariahMenu(folder,method.getName()+"_"+tdType+"_"+term);
	}

	@Test(dependsOnMethods="Test01_syariahMenu")
	private void Test02_syariahCreate(Method method) {
		
		TimeDeposit_component.create(sourceAccount, amount, term, tdType, folder, method.getName()+"_"+tdType+"_"+term);
	}
	
	@Test(dependsOnMethods="Test02_syariahCreate")
	private void Test03_syariahTermAndCondition(Method method) {
		
		TimeDeposit_component.termAndCondition(folder,method.getName()+"_"+tdType+"_"+term);
	}
	
	@Test(dependsOnMethods="Test03_syariahTermAndCondition")
	private void Test04_syariahConfirm(Method method) {		
		
		TimeDeposit_component.confirm(folder, method.getName()+"_"+tdType+"_"+term);
	}
	
	@Test(dependsOnMethods="Test04_syariahConfirm")
	private void Test05_syariahResult(Method method) {
		TimeDeposit_component.result(folder,  method.getName()+"_"+tdType+"_"+term);
	}

}