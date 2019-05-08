package framework;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.BaseTestMethod;

import io.qameta.allure.Attachment;

public class TestListener implements ITestListener{

	private Object instance;
	private String methodCase;

	private static final String SCREENSHOT_FOLDER="Screenshots";

	//Change this flag to true to save screenshot on Screenshots folder
	private static boolean SCREENSHOT_FLAG=false;

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

		
		Object currentInstance=result.getInstance();
		if(instance==null||!currentInstance.equals(instance)) {

			this.instance = currentInstance;
			this.methodCase="";

			System.out.println("Testing: "+instance.getClass().getSimpleName());
			
			//Initialize case if instance is empty or different from last run instance
			try {
				setMethodCase();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Print called method to console output
		System.out.println(methodCase+result.getMethod().getMethodName());
		setMethodName(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub

		if(instance!=null && SCREENSHOT_FLAG==true) {
			WebDriver driver=BrowserSetup.getDriver();
			saveScreenshotOnDisk(driver, result.getMethod().getMethodName());
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub

		if(instance!=null) {

			System.out.println("FAILED: "+result.getMethod().getMethodName());
			WebDriver driver=BrowserSetup.getDriver();
			saveScreenshotFailedTest(driver);
			
			//revisit url in case test is failed
			driver.get(LoadProperties.getDefaultProperties().getProperty("IB_URL"));
			
			if(SCREENSHOT_FLAG==true)
				saveScreenshotOnDisk(driver, result.getMethod().getMethodName());
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {		
		// TODO Auto-generated method stub
		setMethodName(result);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

	//Set Case name stored in variable mCaseName
	private void setMethodCase() throws Exception{

		if(instance==null) throw new Exception("InstanceIsNullException");

		methodCase+="CASE(";

		for(Field field : instance.getClass().getDeclaredFields()) {

			if (Modifier.isPrivate(field.getModifiers())) {
				field.setAccessible(true);
			}
			try {
				String fieldName=field.getName();
				Object  obj = field.get(instance);

				if(obj==null) throw new Exception(fieldName+".ValueMissingExceptionIn");
				String value=obj.toString();
				
				switch (fieldName)
				{	
				case "fromAccountType":
					methodCase+=",FROM:"+value.substring(value.indexOf("_")+1).toLowerCase();
					break;
				case "toAccountType":
					methodCase+=",TO:"+value.substring(value.indexOf("_")+1).toLowerCase();
					break;
				case "transferMethod":
					methodCase+=",METHOD:"+value;
					break;				
				case "tdType":
					methodCase+=",TYPE:"+value;
					break;	
				default:
					break;
				}

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		methodCase+=")_";
		methodCase=methodCase.replace("(,", "(");

	}

	//Set custom method Name in testng report
	private void setMethodName(ITestResult result) {
		try {
			BaseTestMethod baseTestMethod = (BaseTestMethod) result.getMethod();
			Field f = baseTestMethod.getClass().getSuperclass().getDeclaredField("m_methodName");
			f.setAccessible(true);
			f.set(baseTestMethod, methodCase+result.getMethod().getMethodName());
		} catch (Exception e) {

			Reporter.log("Exception : " + e.getMessage());
		}
	}


	@Attachment(value = "Screenshot on failure", type = "image/png")
	private byte[] saveScreenshotFailedTest(WebDriver driver) {

		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	public void saveScreenshotOnDisk(WebDriver driver,String filename) {

		filename=filename.replace(":", "_");
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		LocalDateTime currentDateTime = LocalDateTime.now();

		long windowHeight= (long)javascript.executeScript("return window.innerHeight");
		long scrollHeight=(long)javascript.executeScript("return document.body.scrollHeight");

		//capture screen for every scrollable page until bottom of page
		for(int i=0;i<=(scrollHeight/windowHeight);i++) {

			if(i>0) {
				javascript.executeScript("window.scrollTo(0,"+ windowHeight+")");
				filename+="_"+i;
			}
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

			try {
				FileUtils.copyFile(scrFile, new File(SCREENSHOT_FOLDER+"/"+currentDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+"/"+currentDateTime.format(DateTimeFormatter.ofPattern("HHmmss"))+"_"+filename+".png"));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
