package com.amazon.in_Page;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class Add_to_Cart {
	
	protected WebDriver driver;
	String currentWindowHandle;
	String firstItem;
		
	@FindBy(xpath="//input[@name='field-keywords']")
	private WebElement SearchBar;
	
	@FindBy(xpath="//input[@id='nav-search-submit-button']")
	private WebElement SearchIcon;
	
	@FindBy(xpath = "(//span[@class='a-size-medium a-color-base a-text-normal'])[1]")
	private WebElement iphone;
	
	@FindBy(xpath="//span[@id='productTitle']")    //h1[@id='title']
	private WebElement FistItem_name;
	
	@FindBy(xpath="//span[@class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay']")
	private WebElement price;
	
	@FindBy(xpath="//input[@name='submit.add-to-cart']")
	private WebElement CartBtn;
	
	@FindBy(xpath= " //*[@id=\"attachDisplayAddBaseAlert\"]/div/h4")
	private WebElement Added_to_Cart;
	
	@FindBy(xpath="//span[@id='attach-sidesheet-view-cart-button']")
	private WebElement CART;

	
	public Add_to_Cart(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void HomePage() throws Exception {
		
		Thread.sleep(2000);
		
		String Exp = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
		String Act = driver.getTitle();
		
		Assert.assertEquals(Act, Exp);
		System.out.println("Successfully landed on Amazon.in homepage..");
	}
	
	public void SearchElement() throws Exception {
		SearchBar.isDisplayed();
		System.out.println("Searchbar is displayed.");
		
		SearchBar.sendKeys("iphone");
		
		SearchIcon.isEnabled();
		System.out.println("SearchIcon is enabled");
		
		SearchIcon.click();
		System.out.println("Clicked on SearchIcon");
		System.out.println("Searching Iphone");
        
	}
	
	public void Result() throws Exception {
		
		String firstItem = driver.findElement(By.xpath("(//div[@class='a-section a-spacing-none puis-padding-right-small s-title-instructions-style'])[1]")).getText();
		System.out.println("First Iphone in list: " + firstItem);
				
		Thread.sleep(3000);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", iphone);
		System.out.println("Clicked on First iphone.");
		
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		
		String PriceTag = price.getText();
		System.out.println("Price for selected iPhone is: "+ PriceTag);
		
	}
	
	public void Add_Cart() {
		
		JavascriptExecutor execute = (JavascriptExecutor)driver;
		execute.executeScript("arguments[0].scrollTo", CartBtn);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
		wait.until(ExpectedConditions.elementToBeClickable(CartBtn)).click();
		System.out.println("Clicked on Add to Cart button.");
		
		try {
			Added_to_Cart.isDisplayed();
			System.out.println("Item added successfully in Cart");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Failed to add item in cart.");
		}
		
		System.out.println("Cart buttin is enabled: " + CART.isEnabled());
		CART.click();
		System.out.println("Clicked on cart button.");
		
		SoftAssert softassert = new SoftAssert();
		softassert.assertTrue(driver.getTitle().contains("Amazon.in Shopping Cart"));
		softassert.assertAll("Landed on Shopping cart page successfully.");
			
	}
	
	
	
	
	
	
	

}
