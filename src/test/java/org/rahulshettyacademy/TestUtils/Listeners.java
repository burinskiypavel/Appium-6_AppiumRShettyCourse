package org.rahulshettyacademy.TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import org.rahulshettyacademy.utils.AppiumUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners extends AppiumUtils implements ITestListener {
    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReporterObject();
    AppiumDriver driver;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("Class name: " + iTestResult.getTestClass().getRealClass().getSimpleName());
        System.out.println("Method name: " + iTestResult.getMethod().getMethodName());
        test = extent.createTest(iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        test.fail(iTestResult.getThrowable());

        try {
            driver = (AppiumDriver) iTestResult.getTestClass().getRealClass().getField("driver")
                            .get(iTestResult.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }

        try {
            test.addScreenCaptureFromPath(getScreenshotPath(iTestResult.getMethod().getMethodName(), driver), iTestResult.getMethod().getMethodName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extent.flush();
    }
}
