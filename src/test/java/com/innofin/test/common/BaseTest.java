package com.innofin.test.common;

import com.innofin.test.listeners.TestListener;
import com.innofin.web.driver.DriverManager;
import com.innofin.web.driver.TargetFactory;
import com.innofin.web.helpers.PropertiesHelpers;
import com.innofin.web.report.AllureManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.*;

@Listeners({TestListener.class})
public class BaseTest {

    @BeforeSuite
    public void beforeSuite() {
        AllureManager.setAllureEnvironmentInformation();
        PropertiesHelpers.loadAllFiles(); //Config and Locators
    }

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        DriverManager.quit();
    }


    public WebDriver createBrowser(@Optional("chrome") String browser) {
        PropertiesHelpers.loadAllFiles();
        WebDriver driver = ThreadGuard.protect(new TargetFactory().createInstance(browser));
        DriverManager.setDriver(driver);
        return DriverManager.getDriver();

    }

}