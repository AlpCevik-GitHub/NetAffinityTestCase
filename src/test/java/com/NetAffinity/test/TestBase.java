package com.NetAffinity.test;

import com.NetAffinity.utilities.BrowserUtils;
import com.NetAffinity.utilities.ConfigurationReader;
import com.NetAffinity.utilities.Driver;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    //for starting and building reports
    protected ExtentReports report;
    // to create HTML report file
    protected ExtentHtmlReporter htmlReporter;
    //to define a  test steps
    protected static ExtentTest extentLogger;

    Logger logger = LogManager.getLogger(this.getClass());

    @BeforeMethod
    public void setUp() {


        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
        logger.info("go to open www.test01.netaffinity.com ");
        report = new ExtentReports();
        //create a report path
        String projectPath = System.getProperty("user.dir");
        String path = projectPath + "/test-output/report.html";

        //initialize the html reporter with the report path
        htmlReporter = new ExtentHtmlReporter(path);
        extentLogger = report.createTest("Net Affinity");
        extentLogger.info("go to open www.test01.netaffinity.com");
        //attach the html report to report object
        report.attachReporter(htmlReporter);

        //title in report
        htmlReporter.config().setReportName("Net Affinity Report");

        //set environment information
        report.setSystemInfo("Environment", "Test");
        report.setSystemInfo("BrowserType", ConfigurationReader.getProperty("browser"));
        report.setSystemInfo("OS", System.getProperty("os.name"));
    }


    @AfterMethod
    public void tearDown() {
        BrowserUtils.getScreenshot("Test Result");
        Driver.closeDriver();
        report.flush();
        logger.info("Browser was closed.");
        extentLogger.info("Browser was closed.");
    }


}
