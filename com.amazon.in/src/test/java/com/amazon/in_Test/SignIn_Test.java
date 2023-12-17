package com.amazon.in_Test;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Listeners;

import com.amazon.in_Page.SignIn;
import com.amazon.in_Utility.BaseClass;
import org.testng.annotations.Test;


@Listeners(com.amazon.in_Utility.Listners.class)

public class SignIn_Test extends BaseClass{
	
	
	@Test
	public void LoginClass() throws Exception {
		
		SignIn login = new SignIn(driver);
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		login.Login();
	}

}
