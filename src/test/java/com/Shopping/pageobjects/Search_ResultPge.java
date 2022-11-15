package com.Shopping.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Search_ResultPge {
	
	WebDriver ldriver;
	public Search_ResultPge(WebDriver rdriver ) {
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(xpath = "//span[normalize-space()=\"Search results for: 'Top'\"]")
	WebElement textofsearchResult;
	
	@FindBy(linkText = "Electra Bra Top")
	WebElement productName;
	
	
	public String getTextOfSearchResult()
	{
		String textserch=textofsearchResult.getText();
		return textserch;
	}
	
	public String getTextOfProduct()
	{
		String textProduct=productName.getText();
		return textProduct;
	}
	public void clickOnProduct() {
		productName.click();
	}

}
