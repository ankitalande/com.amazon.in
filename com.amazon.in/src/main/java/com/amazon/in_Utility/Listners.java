package com.amazon.in_Utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Listners extends BaseClass implements ITestListener{
	
	ExtentReports extent = ExtentReportAdd.ExtentGenerater();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	/**
	 * On test start it will interact with object repository class and start capturing actual result
	 */

	public void onTestStart(ITestResult result) {
	ObjRepo.Test=extent.createTest(result.getTestClass().getName()+ "::"+ result.getMethod().getMethodName());
		extentTest.set(ObjRepo.Test);
		 Log.info("");
		 Log.info(result.getMethod().getMethodName() + " Started");
		 Log.info(result.getMethod().getDescription());
	}
	/**
	 * On test pass it will show status of @test as pass in green color
	 */

   public void onTestSuccess(ITestResult result) {
	 
		String logtext="<b>TestMethod "+result.getMethod().getMethodName() + "PASS </b>";
		Markup m=MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
		extentTest.get().log(Status.PASS, m);
		Log.info("Failed because of "+result.getStatus());
	}
   /**
	 * On test failure this method can take screen shot of page where script gets fail
	 * also capture exceptions with getmessage();
	 */

	public void onTestFailure(ITestResult result) {
		
	    Log.info("Failed because of "+result.getThrowable());
		String methodname=result.getMethod().getMethodName();
		String msg=result.getThrowable().getMessage();
		//String Errmsg=Arrays.toString(result.getThrowable().getStackTrace());
		extentTest.get().fail("<details><summary><b><font color=red>" +"Exception occured ,Click to see Details :"+"</font> </b></summary>"
		+msg.replaceAll(",", "<br>")+"<details>\n");

		try {
			extentTest.get().fail("<b><font color=red>" +"Screenshot of failure" +"</font></b>",
					MediaEntityBuilder.createScreenCaptureFromBase64String(Base64()).build());
		}catch(Exception e) {
			extentTest.get().fail(e+"Test Case Failed ,Unable To Attach Screenshot");
		}
		String logtext="<b> TestMethod "+methodname+   "Fail</b>";
		Markup m=MarkupHelper.createLabel(logtext, ExtentColor.RED);
		extentTest.get().log(Status.FAIL, m);	
	}
	/**
	 * On test skip this method can find root cause(logs) for skipping test
	 */
    public void onTestSkipped(ITestResult result) {
    	Log.info("Skipped because of "+result.getThrowable());
		String logtext="<b>TestMethod "+result.getMethod().getMethodName() + "PASS </b>";
		Markup m=MarkupHelper.createLabel(logtext, ExtentColor.YELLOW);
		extentTest.get().log(Status.SKIP, m);
	}
    /**
	 * On test finish this method can flush overridden methods
	 */
	
	public void onFinish(ITestContext context) {
			extent.flush();
	}
    public String Base64() {
	return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
}
		
	
}
