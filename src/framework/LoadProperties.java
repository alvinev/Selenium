package framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {

	private static final String folder="Resources/Properties";
	
	public static Properties getProperties(String filename) throws IOException {
		Properties prop = new Properties();           
		InputStream is = new FileInputStream(folder+"/"+filename);
		prop.load(is);
		return prop;		
	}
	
	
	public static Properties getUserProperties(String username) throws IOException {
		return getProperties("User/"+username);
	}
	
	public static Properties getDefaultProperties()  {
		Properties prop = null;
		try {
			prop=getProperties("default.properties");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return prop;
	}
}

