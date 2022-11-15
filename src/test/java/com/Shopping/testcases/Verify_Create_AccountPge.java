package com.Shopping.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Shopping.pageobjects.Create_AccountPage;
import com.Shopping.pageobjects.Customer_LogInPge;
import com.Shopping.pageobjects.HomePage;
import com.Shopping.pageobjects.LogIn_HomePge;
import com.Shopping.pageobjects.LogOut;
import com.Shopping.pageobjects.My_AccountPage;

public class Verify_Create_AccountPge extends BaseClass {

	@Test(priority = 1, enabled = true)
	public void verifyCreatAccount() throws InterruptedException, IOException {

		HomePage pg = new HomePage(driver);
		pg.clickOnCreatAccn();

		logger.info("***Create Account Page Open***");

		Create_AccountPage pg2 = new Create_AccountPage(driver);
		pg2.enterFistname(firstName);
		pg2.enterLatname(lastname);
		pg2.clickOncheckBox();
		pg2.enterEmail(email);
		pg2.enterpwd(pwd);
		pg2.enterconfpwd(confpwd);
		pg2.clickOnCreatBtn();

		Thread.sleep(5000);

		logger.info("Account is Created");

		My_AccountPage pg3 = new My_AccountPage(driver);
		String username = pg3.getUserName();
		if (username.equals("Welcome, Sanket1 Moon!")) {
			logger.info("***user name verify successfully***");
			Assert.assertTrue(true);
		} else {
			logger.info("***user name not matched***");
			captureScreenShot(driver, "verifyCreatAccount");
			Assert.assertTrue(false);
		}
		
		

	}

	@Test(priority = 2, enabled = true)
	public void verifyLoginPage() throws IOException, InterruptedException {
		HomePage pg = new HomePage(driver);
		pg.clickOnSignIn();

		logger.info("Sign In page Opened");

		Customer_LogInPge pg1 = new Customer_LogInPge(driver);
		pg1.enterEmail(email);
		pg1.enterPwd(pwd);
		pg1.clickOnSignIn();
		Thread.sleep(10000);

		String name = pg.getSignInName();
		System.out.println("sign in name is: " + name);
		if (name.equals("Welcome, Sanket1 Moon!")) {
			logger.info("***Name Match With Expected Result***");
			Assert.assertTrue(true);
		} else {
			logger.info("***Name On Sign In Page Not Match With Expected Result***");
			captureScreenShot(driver, "verifyLoginPage");
			Thread.sleep(3000);
			Assert.assertTrue(false);
		}
		
	}

	@Test(priority = 3, enabled = true)
	public void verifysignOut() throws IOException, InterruptedException {
		HomePage pg1 = new HomePage(driver);
		pg1.clickOnSignIn();
		Customer_LogInPge pg2 = new Customer_LogInPge(driver);
		pg2.enterEmail(email);
		pg2.enterPwd(pwd);
		pg2.clickOnSignIn();
		Thread.sleep(5000);

		LogIn_HomePge pg3 = new LogIn_HomePge(driver);
		pg3.clickOnDropDown();
		pg3.clickOnSignOut();

		LogOut pg4 = new LogOut(driver);
		String signOutName = pg4.getSignoutText();

		if (signOutName.equals("You are signed out")) {
			logger.info("Sign Out Completed");
			Assert.assertTrue(true);
		} else {
			logger.info("Sign Out-Failed");
			captureScreenShot(driver, "verifysignOut");
			logger.info("ScreenShot Captured");
			Assert.assertTrue(false);
		}
	}

}
