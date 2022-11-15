package com.Shopping.testcases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Shopping.pageobjects.Customer_LogInPge;
import com.Shopping.pageobjects.HomePage;
import com.Shopping.pageobjects.LogIn_HomePge;
import com.Shopping.utilities.ReadExcelFile;


import junit.framework.Assert;

public class TC_LoginDDT_003 extends BaseClass{
	
	@Test(dataProvider = "LoginData")
	public void verifylogin(String Email,String pasword) throws IOException, InterruptedException
	{
		HomePage pg = new HomePage(driver);
		pg.clickOnSignIn();
		
		logger.info("Sign In page Opened");

		Customer_LogInPge pg1 = new Customer_LogInPge(driver);
		pg1.enterEmail(Email);
		pg1.enterPwd(pasword);
		pg1.clickOnSignIn();
		Thread.sleep(5000);

		String name = pg.getSignInName();
		System.out.println("sign in name is: "+name);
		if (name.equals("Welcome, Sanket Moon!")) {
			logger.info("***Name Match With Expected Result***");
			Assert.assertTrue(true);
		} else {
			logger.info("***Name On Sign In Page Not Match With Expected Result***");
			captureScreenShot(driver, "verifyLoginPage");
			Assert.assertTrue(false);
		}
		
		LogIn_HomePge logInpge=new LogIn_HomePge(driver);
		logInpge.clickOnDropDown();
		logInpge.clickOnSignOut();
		
		
	}
	
	@DataProvider(name="LoginData")
	public String[][] LoginDataProvider()
	{
		String path=System.getProperty("user.dir")+"\\Test Data\\Test Data.xlsx";
		int ttlrow=ReadExcelFile.getRowCount(path, "Sheet1");
		int ttlcol=ReadExcelFile.getColCount(path, "Sheet1");
		
		String data[][]=new String [ttlrow-1][ttlcol];
		for(int i=1;i<ttlrow;i++)
		{
			for(int j=0;j<ttlcol;j++)
			{
				data[i-1][j]=ReadExcelFile.getCellValue(path, "Sheet1", i, j);
			}
		}
		return data;
	}

}
