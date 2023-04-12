package com.innofin.test.listeners;

import com.aventstack.extentreports.Status;
import com.innofin.api.helper.Log;
import com.innofin.web.annotations.FrameworkAnnotation;
import com.innofin.web.driver.DriverManager;
import com.innofin.web.enums.AuthorType;
import com.innofin.web.enums.CategoryType;
import com.innofin.web.helpers.CaptureHelpers;
import com.innofin.web.report.AllureManager;
import com.innofin.web.report.ExtentReportManager;
import com.innofin.web.utils.BrowserInfoUtils;
import com.innofin.web.utils.IconUtils;
import com.innofin.web.utils.WebUI;
import com.innofin.web.utils.ZipUtils;
import org.testng.*;

import static com.innofin.web.constants.FrameworkConstants.*;

public class TestListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    static int count_totalTCs;
    static int count_passedTCs;
    static int count_skippedTCs;
    static int count_failedTCs;

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName()
                : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        //System.out.println(method.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        //System.out.println(method.getTestMethod().getMethodName());
    }

    @Override
    public void onStart(ISuite iSuite) {
        Log.info("Start suite testing for " + iSuite.getName());
        iSuite.setAttribute("WebDriver", DriverManager.getDriver());
        //Gọi hàm startRecord video trong CaptureHelpers class
        CaptureHelpers.startRecord(iSuite.getName());
        ExtentReportManager.initReports();
    }

    @Override
    public void onFinish(ISuite iSuite) {
        Log.info("End suite testing " + iSuite.getName());
        WebUI.stopSoftAssertAll();
        //Kết thúc và thực thi Extents Report
        ExtentReportManager.flushReports();
        ZipUtils.zip();
        //Gọi hàm stopRecord video trong CaptureHelpers class
        CaptureHelpers.stopRecord();
    }

    public AuthorType[] getAuthorType(ITestResult iTestResult) {
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(FrameworkAnnotation.class) == null) {
            return null;
        }
        AuthorType authorType[] = iTestResult.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(FrameworkAnnotation.class).author();
        return authorType;
    }

    public CategoryType[] getCategoryType(ITestResult iTestResult) {
        if (iTestResult.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(FrameworkAnnotation.class) == null) {
            return null;
        }
        CategoryType categoryType[] = iTestResult.getMethod().getConstructorOrMethod().getMethod()
                .getAnnotation(FrameworkAnnotation.class).category();
        return categoryType;
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info("Test case: " + getTestName(iTestResult) + " test is starting...");
        count_totalTCs = count_totalTCs + 1;

        ExtentReportManager.createTest(iTestResult.getName());
        ExtentReportManager.addAuthors(getAuthorType(iTestResult));
        ExtentReportManager.addCategories(getCategoryType(iTestResult));
        ExtentReportManager.addDevices();

        ExtentReportManager.info(BOLD_START + IconUtils.getOSIcon() + " "
                + BrowserInfoUtils.getOSInfo() + BOLD_END);
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info("Test case: " + getTestName(iTestResult) + " is passed.");
        count_passedTCs = count_passedTCs + 1;

        if (screenshot_passed_steps.equals(YES)) {
            CaptureHelpers.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
        }

        AllureManager.saveTextLog("Test case: " + getTestName(iTestResult) + " is passed.");
        //ExtentReports log operation for passed tests.
        ExtentReportManager.logMessage(Status.PASS, "Test case: " + getTestName(iTestResult) + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.error("Test case: " + getTestName(iTestResult) + " is failed.");
        count_failedTCs = count_failedTCs + 1;

        if (screenshot_failed_steps.equals(YES)) {
            CaptureHelpers.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
        }

        //Allure report screenshot file and log
        Log.error("FAIL !! Screenshot for test case: " + getTestName(iTestResult));
        Log.error(iTestResult.getThrowable());

        AllureManager.takeScreenshotToAttachOnAllureReport();

        //Extent report screenshot file and log
        ExtentReportManager.addScreenShot(Status.FAIL, getTestName(iTestResult));
        ExtentReportManager.logMessage(Status.FAIL, "Test case: " + getTestName(iTestResult) + " is failed.");
        ExtentReportManager.logMessage(Status.FAIL, iTestResult.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.warn("Test case: " + getTestName(iTestResult) + " is skipped.");
        count_skippedTCs = count_skippedTCs + 1;

        if (screenshot_skipped_steps.equals(YES)) {
            CaptureHelpers.captureScreenshot(DriverManager.getDriver(), getTestName(iTestResult));
        }

        ExtentReportManager.logMessage(Status.SKIP, "Test case: " + getTestName(iTestResult) + " is skipped.");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.error("Test failed but it is in defined success ratio " + getTestName(iTestResult));
        ExtentReportManager.logMessage("Test failed but it is in defined success ratio " + getTestName(iTestResult));
    }
}

