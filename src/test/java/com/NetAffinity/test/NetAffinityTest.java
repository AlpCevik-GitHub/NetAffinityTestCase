package com.NetAffinity.test;

import com.NetAffinity.pages.AddPage;
import com.NetAffinity.pages.BasePage;
import com.NetAffinity.utilities.BrowserUtils;
import com.NetAffinity.utilities.ConfigurationReader;
import com.NetAffinity.utilities.Driver;
import groovy.util.logging.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import groovy.util.logging.Slf4j;

@Slf4j
public class NetAffinityTest extends TestBase {

    AddPage addPage = new AddPage();
    int actualAccountNumber;

    Logger logger = LogManager.getLogger(this.getClass());



    @Test
    public void test01() {

        System.out.println("totalNumberOfAccount= " + addPage.totalNumberOfAccount.size());


        addPage.addButton.click();
        BrowserUtils.sleep(3);
        addPage.titleInputBox.sendKeys("This is the title");
        BrowserUtils.sleep(3);
        addPage.idInputBox.sendKeys("");
        addPage.phoneInputBox.sendKeys("+353 (0) 12939906");
        BrowserUtils.sleep(3);
        addPage.emailInputBox.sendKeys("info@netaffinity.com");
        BrowserUtils.sleep(3);
        addPage.saveButton.click();
        logger.info("new account was created.");
        extentLogger.info("new account was created.");
        BrowserUtils.sleep(5);
        actualAccountNumber = addPage.totalNumberOfAccount.size();
        addPage.deleteButton.click();
        logger.info("the account was deleted.");
        extentLogger.info("the account was deleted.");

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        By popUpMessageElement = By.xpath("//span[@class='flex ng-binding']");
        WebElement toastElement = wait.until(ExpectedConditions.visibilityOfElementLocated(popUpMessageElement));

        String message = toastElement.getText();
        System.out.println("Pop-Up Message: " + message);
        BrowserUtils.sleep(5);

        int expectedAccountNumber = addPage.totalNumberOfAccount.size();
        System.out.println("expectedAccountNumber = " + expectedAccountNumber);

        Assert.assertEquals(actualAccountNumber - 1, expectedAccountNumber, "Account was not deleted!!!");
        logger.info("Deletion was verified.");
        extentLogger.info("Deletion was verified.");

        if (message.contains("Account deleted")) {
            System.out.println("Test Passed!!!");
        } else {
            System.out.println("Test Failed!!!");
        }


    }



}
