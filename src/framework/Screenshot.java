package framework;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Screenshot extends BrowserSetup {

	private static String rootFolder = "Screenshots";

	public static void capture(String folder,String filename) {
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File(rootFolder+"/"+folder+"/"+currentDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))+"/"+currentDateTime.format(DateTimeFormatter.ofPattern("HHmmss"))+"_"+filename+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
