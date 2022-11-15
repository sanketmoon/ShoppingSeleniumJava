package com.Shopping.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PayementMethod {
	WebDriver ldriver;
	public PayementMethod(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath="//button[normalize-space()=\"Place Order\"]")
	WebElement placeOrderBtn;
	
	public void clickOnPlaceOrder()
	{
		placeOrderBtn.click();
	}

}
