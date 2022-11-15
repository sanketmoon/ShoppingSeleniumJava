package com.Shopping.utilities;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListners implements ITestListener {

	ExtentSparkReporter htmlreporter;
	ExtentReports report;
	ExtentTest test;

	public void configReport() {
		ReadConfig readconfig = new ReadConfig();
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		String reportName = "Shopping-" + timestamp + ".html";
		htmlreporter = new ExtentSparkReporter(System.getProperty("user.dir") + "//Extent Report//" + reportName);
		report = new ExtentReports();
		report.attachReporter(htmlreporter);

		report.setSystemInfo("Machine", "PC");
		report.setSystemInfo("OS", "Windows 10");
		report.setSystemInfo("browser", readconfig.getBrowser());
		report.setSystemInfo("Tester Name", "Sanket");

		htmlreporter.config().setDocumentTitle("Shopping");
		htmlreporter.config().setReportName("Shooping Project");
		htmlreporter.config().setTheme(Theme.STANDARD);

	}

	public void onStart(ITestContext Result) {
		configReport();
		System.out.println("Start method invoked....");
	}

	public void onFinish(ITestContext Result) {
		System.out.println("test completed");
		report.flush();
	}

	public void onTestFailure(ITestResult Result) {
		System.out.println("Name of test case failed: " + Result.getName());
		test = report.createTest(Result.getName());
		test.log(Status.FAIL,
				MarkupHelper.createLabel("Name of failed Test Case: " + Result.getName(), ExtentColor.RED));
		String path = System.getProperty("user.dir") + "//Screen Shots//" + Result.getName() + ".png";
		File screenshot = new File(path);
		if (screenshot.exists()) {
			test.fail("Capture screenShot is Below:"+test.addScreenCaptureFromPath(path));
		}

	}

	public void onTestSuccess(ITestResult Result) {
		System.out.println("Name of Pass Test Case: " + Result.getName());
		test = report.createTest(Result.getName());
		test.log(Status.PASS,
				MarkupHelper.createLabel("Name of Pass Test Case :" + Result.getName(), ExtentColor.GREEN));
	}

	public void onSkipped(ITestResult Result) {
		System.out.println("Name of the Test Case Skipped: " + Result.getName());
		test = report.createTest(Result.getName());
		test.log(Status.SKIP,
				MarkupHelper.createLabel("Name of the test case skipped:- " + Result.getName(), ExtentColor.YELLOW));
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult Result) {

	}

}
