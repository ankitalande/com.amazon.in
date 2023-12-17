package com.amazon.in_Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	
	Properties prop;
	String path = (System.getProperty("user.dir")+"\\src\\test\\resources\\TestData.properties");
	
	public ConfigReader() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(path);
		prop.load(fis);
	}
	
	public String getBrowser() {
		String browser = prop.getProperty("Browser");
		if(browser != null) 
			return browser;
		else 
			throw new RuntimeException("Browser Not Found");		
	}
	
	public String getUrl() {
		String url = prop.getProperty("WebURL");
		if(url != null)
			return url;
		else
			throw new RuntimeException("url not found");
	}
	
	public String getUser_Name() {
		String email = prop.getProperty("SignIn_User_Name");
		if(email != null) 
			return email;
			else
				throw new RuntimeException("User email not found");
	}
	
	public String getUser_Password() {
		String password = prop.getProperty("SignIn_User_Password");
		if(password != null)
			return password;
		else
			throw new RuntimeException("Password not found");
	}
	
}
