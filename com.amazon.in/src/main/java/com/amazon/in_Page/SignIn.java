package com.amazon.in_Page;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.amazon.in_Utility.ConfigReader;

public class SignIn {
	
	@FindBy(xpath="//a[@id=\"nav-link-accountList\"]")
	private WebElement Accounts_Lists;
	
	@FindBy(xpath="//input[@name='email']")
	private WebElement SignInEmail;
	
	@FindBy(xpath="//input[@id='continue']")
	private WebElement ContinueBtn;
	
	@FindBy(xpath="//input[@name=\"password\"]")
	private WebElement SignInPassword;
	
	@FindBy(xpath="//input[@id=\"signInSubmit\"]")
	private WebElement SignInBtn;

	private WebDriver driver;
	
/*	@FindBy(xpath="")
	private WebElement ;  */
	
	protected ConfigReader config;
	
	
	public SignIn(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);		
	}
	
	
	public void  Login() throws Exception {
		
		config = new ConfigReader();
		String email = config.getUser_Name();
		String password = config.getUser_Password();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1000));
		
		String Actual = driver.getTitle();
		String Expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		
		Assert.assertEquals(Actual, Expected);
		System.out.println("Assert passed : Landed on HomePage.");
		
		wait.until(ExpectedConditions.elementToBeClickable(Accounts_Lists)).click();
		System.out.println("Clicked on Accounts and Lists.");
		
		
		String Act = driver.getTitle();
		String Exp = "Amazon Sign In";
		
		Assert.assertEquals(Act, Exp);
		System.out.println("Assert Passed: Landed on Sign In Page");
		
		
		System.out.println("Sign in Email textbox is enabled " +SignInEmail.isEnabled() );
		SignInEmail.sendKeys(email);
		
		System.out.println("Continue butto is enabled: " + ContinueBtn.isEnabled());
		wait.until(ExpectedConditions.elementToBeClickable(ContinueBtn)).click();
		System.out.println("Clicked on ContinueBtn.");
		
		System.out.println("Sign in password textbox is enabled " +SignInPassword.isEnabled() );
		SignInPassword.sendKeys(password);
		
		System.out.println("SignIn button is enabled: " + SignInBtn.isEnabled());
		wait.until(ExpectedConditions.elementToBeClickable(SignInBtn)).click();
		System.out.println("Clicked on SignInBtn.");
		
		try {
		String act = driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']")).getText();
		String exp = "Hello, Ankita";
		
		Assert.assertEquals(act, exp);
		System.out.println("User Logged in successfully");		
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to sign In");
		}
	}
}
