package com.Shopping.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.Shopping.pageobjects.ConfirmationPage;
import com.Shopping.pageobjects.Customer_LogInPge;
import com.Shopping.pageobjects.HomePage;
import com.Shopping.pageobjects.LogIn_HomePge;
import com.Shopping.pageobjects.PayementMethod;
import com.Shopping.pageobjects.ProductPge;
import com.Shopping.pageobjects.Search_ResultPge;
import com.Shopping.pageobjects.Shipping_AddressPge;

import junit.framework.Assert;

public class Tc_VerifySearchProduct extends BaseClass {

	@Test(priority = 4, enabled = true)
	public void verifySearchProduct() throws IOException, InterruptedException {
		HomePage pg = new HomePage(driver);
		pg.clickOnSignIn();
		Thread.sleep(5000);

		Customer_LogInPge pg2 = new Customer_LogInPge(driver);
		pg2.enterEmail(email);
		pg2.enterPwd(pwd);
		pg2.clickOnSignIn();
		logger.info("***Sign In Successfully***");
		Thread.sleep(5000);

		LogIn_HomePge pg3 = new LogIn_HomePge(driver);
		pg3.enterinSearchBox("Top");
		logger.info("Entered Text in search Box");
		pg3.enterSearchBtn();
		logger.info("Clicked on Search Button");
		Thread.sleep(5000);

		Search_ResultPge pg4 = new Search_ResultPge(driver);
		String searchProductName = pg4.getTextOfSearchResult();
		if (searchProductName.equals("Search results for: 'Top'")) {
			logger.info("***Search text match with expected text*** ");
			Assert.assertTrue(true);
		} else {
			logger.info("***Search text not match with expected text*** ");
			captureScreenShot(driver, "verifySearchProduct");
			Assert.assertTrue(false);
		}

		String productName = pg4.getTextOfProduct();
		if (productName.contains("Top")) {
			logger.info("Search Product Name Matched");
			Assert.assertTrue(true);

		} else {
			logger.info("Search Product Name Not Matched");
			captureScreenShot(driver, "verifySearchProduct");
			Assert.assertTrue(false);
		}

	}

	@Test(priority = 5)
	public void verifyPayment() throws IOException, InterruptedException {
		HomePage pg = new HomePage(driver);
		pg.clickOnSignIn();
		logger.info("Enter in to user login page");
		Thread.sleep(5000);

		Customer_LogInPge login = new Customer_LogInPge(driver);
		login.enterEmail(email);
		login.enterPwd(pwd);
		login.clickOnSignIn();
		Thread.sleep(3000);

		LogIn_HomePge pg1 = new LogIn_HomePge(driver);
		pg1.enterinSearchBox("Top");
		pg1.enterSearchBtn();
		Thread.sleep(3000);

		Search_ResultPge pg3 = new Search_ResultPge(driver);
		pg3.clickOnProduct();
		Thread.sleep(3000);

		ProductPge pg4 = new ProductPge(driver);
		pg4.clickOnSize();
		pg4.selectColor();
		pg4.setQuantity("2");
		pg4.addCartBtn();
		Thread.sleep(5000);
		pg4.clickOnMyCart();
		pg4.clickOnProceedCheckOut();
		Thread.sleep(20000);

		Shipping_AddressPge pg5 = new Shipping_AddressPge(driver);

		pg5.enterAddress1(address1);
		pg5.enterAddress2(address2);
		pg5.enterAddress3(address3);
		pg5.enterCity(city);
		pg5.enterZipCode(zipCode);;
		pg5.enterPhnO(phoneNum);
		pg5.selectCountry();
		pg5.selectState();
		pg5.clickOnShippingMthod();
		pg5.clickOnNextBtn();
		Thread.sleep(3000);

		PayementMethod pg6 = new PayementMethod(driver);
		pg6.clickOnPlaceOrder();
		Thread.sleep(5000);

		ConfirmationPage pg7 = new ConfirmationPage(driver);
		String msg = pg7.getSuccessTitle();
		if (msg.equals("Thank you for your purchase!")) {
			logger.info("Success msg fetch successfully");
			Assert.assertTrue(true);
		} else {
			logger.info("Unable to fetch success msg");
			captureScreenShot(driver, "verifyPayment");
		}
		pg7.clickOnContinueShipping();

	}

}
