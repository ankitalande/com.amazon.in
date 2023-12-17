package com.amazon.in_Utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportAdd extends BaseClass{
	
	public static ExtentReports ExtentGenerater() {
		ExtentReports extent;
		
		String timeStamp = new SimpleDateFormat("dd-MM-yyy").format(new Date());
		
		 extent = new ExtentReports();
		
		ExtentSparkReporter Reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"\\Extent_Report\\ExtentReport.html");
		extent.attachReporter(Reporter);
		
		Reporter.config().setDocumentTitle("Amazon.in");
		Reporter.config().setReportName("Result");
		Reporter.config().setTheme(Theme.STANDARD);
		
		
		extent.setSystemInfo("System", "Ankita L");
		extent.setSystemInfo("Module", "com.Amazon.in");
		extent.setSystemInfo("OS", "Windows 10");
		extent.setSystemInfo("Browser", "Chrome");
		
		return extent;
		
	} 

}
