package com.amazon.in_Utility;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v118.log.Log;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	protected static WebDriver driver;
	protected ConfigReader config;
	
	
	@BeforeSuite
	public void suiterun() {
		System.out.println("---------- Suite Execution Start------------");
		Reporter.log("-----------Suite Session Stared-------------");
		
	}
	
	
	@BeforeMethod
	public void setupApplication() throws Exception {
		
		Reporter.log("------ Browser Session Started -----------");
		System.out.println(" ------------------ Class execution Started ----------------------");
		WebDriverManager.chromedriver().setup();
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("use-fake-ui-for-media-stream");
		options.addArguments("--disable-notifications");
		
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browserName", "Chrome");
		caps.setCapability("platform", "WIn10");
		caps.setCapability("version", "latest");
		caps.setCapability(ChromeOptions.CAPABILITY, options);
		
		
		config = new ConfigReader();
		String url = config.getUrl();
		
		driver.get(url);
		
		driver.manage().timeouts().pageLoadTimeout(80, TimeUnit.SECONDS);
		System.out.println("==== URL provided ====");
		
		driver.manage().timeouts().implicitlyWait(70,TimeUnit.SECONDS);
			
		Reporter.log("-----Application Started-------");
		
		DevTools devtools = ((HasDevTools)driver).getDevTools();
		devtools.createSession();
		devtools.send(Log.enable());
		devtools.addListener(Log.entryAdded(), LogEnter ->{
			try {
				Thread.sleep(2000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			System.out.println("-----------------------------------------------------------------------------------------");
				System.out.println("Level : " + LogEnter.getLevel());
				System.out.println("Text  : "+ LogEnter.getText());
				System.out.println("Broken URL : "+ LogEnter.getUrl());			
		});
	}
		
		
		@AfterMethod
		public void closeApplication() {
			driver.quit();
			Reporter.log("------- Browser Session End -----------");
		}
		
		@AfterSuite
		public void suiteEnd() {
			
		}
	

}

