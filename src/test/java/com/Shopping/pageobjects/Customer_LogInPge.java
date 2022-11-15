package com.Shopping.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Customer_LogInPge {

	WebDriver ldriver;

	public Customer_LogInPge(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);

	}
	
	@FindBy(id="email")
	WebElement emailTextBox;
	
	@FindBy(id="pass")
	WebElement pwdTextBox;
	
	@FindBy(xpath = "//*[@id=\"send2\"]/span")
	WebElement signInBtn;
	
	public void enterEmail(String email)
	{
		emailTextBox.sendKeys(email);
	}
	
	public void enterPwd(String pass)
	{
		pwdTextBox.sendKeys(pass);
	}
	public void clickOnSignIn()
	{
		signInBtn.click();
	}

}
