package Amazon.Testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Amazon.resources.ExtentReportsNG;

public class Listeners extends baseTest implements ITestListener{
	ExtentTest test;
	ExtentReports extent = ExtentReportsNG.getReport();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //unique Thread id 
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Test Passed : " + result.getName());
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

	    System.out.println("Test Failed : " + result.getName());
	    extentTest.get().fail(result.getThrowable());

	    String filePath = null;

	    try {
	        driver = (WebDriver) result.getTestClass()
	                .getRealClass()
	                .getField("driver")
	                .get(result.getInstance());
	    } catch (Exception e1) {
	        e1.printStackTrace();
	    }

	    try {
	        filePath = getScreenshot(result.getMethod().getMethodName(), driver);
	        extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("Test Skipped : " + result.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Execution Finished");
		extent.flush();
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("Test Failed But Within Success Percentage : " + result.getName());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		System.out.println("Test Failed Due To Timeout : " + result.getName());
	}

	

}
