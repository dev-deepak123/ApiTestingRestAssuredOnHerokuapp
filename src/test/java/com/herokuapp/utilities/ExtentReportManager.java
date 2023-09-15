package com.herokuapp.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ExtentReportManager implements ITestListener{

	//implement extent report below is listner which perform this
	public ExtentReports extent;
	public ExtentTest test;
	public ExtentSparkReporter sparkReporter;
	String repName;
	@Override
	public void onTestStart(ITestResult result) {
		//before each test case
		test = extent.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.createNode(result.getName());
		test.log(Status.PASS, "Test case"+result.getMethod().getMethodName()+"is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.log(Status.FAIL, "Test case"+result.getMethod().getMethodName()+"is failed");
		test.log(Status.FAIL, result.getThrowable().getMessage());

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test=extent.createTest(result.getName());
		test.createNode(result.getName());
		test.log(Status.SKIP, "Test case"+result.getMethod().getMethodName()+"is skipped");
		test.log(Status.SKIP, result.getThrowable().getMessage());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName ="Test-Reports-"+timeStamp +".html" ;

		sparkReporter = new ExtentSparkReporter(".\\reporrts\\"+repName);
		sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
		sparkReporter.config().setReportName("herokuapp user api");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
	}

	@Override
	public void onFinish(ITestContext context) {
		//close extent
		extent.flush();
	}

}
