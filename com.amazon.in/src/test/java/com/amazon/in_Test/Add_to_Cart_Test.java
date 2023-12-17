package com.amazon.in_Test;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.amazon.in_Page.Add_to_Cart;
import com.amazon.in_Utility.BaseClass;

@Listeners(com.amazon.in_Utility.Listners.class)

public class Add_to_Cart_Test extends BaseClass{
	
	
	@Test
	public static void AddToCart() throws Exception {
		
		Add_to_Cart cart = new Add_to_Cart(driver);
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		
		cart.HomePage();
		cart.SearchElement();
		cart.Result();
		cart.Add_Cart();
	}

}
