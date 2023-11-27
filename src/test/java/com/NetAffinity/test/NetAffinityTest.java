package com.NetAffinity.test;

import com.NetAffinity.pages.AddPage;
import com.NetAffinity.utilities.BrowserUtils;
import com.NetAffinity.utilities.Driver;
import groovy.util.logging.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.annotations.Test;

// used log4j annotation @Slf4j to get logs
@Slf4j
public class NetAffinityTest extends TestBase {


     /*
        in this class, we put test steps, @Test annotation is used to be able to run method
     */

    AddPage addPage = new AddPage();
    int actualAccountNumber;

    Logger logger = LogManager.getLogger(this.getClass());



    @Test
    public void test01() {

        System.out.println("totalNumberOfAccount= " + addPage.totalNumberOfAccount.size());
        /* in case of no account on the main page, being able to see the automation of delete function
           firstly we add new account for avoiding the situation of no account.
        */
        // click the add button
        addPage.addButton.click();
        // implement thread sleep to prevent form web page and selenium driver inconsistencies
        BrowserUtils.sleep(3);
        // enter info for record
        addPage.titleInputBox.sendKeys("This is the title");
        BrowserUtils.sleep(3);
        // enter info for record
        addPage.idInputBox.sendKeys("");
        // enter info for record
        addPage.phoneInputBox.sendKeys("+353 (0) 12939906");
        BrowserUtils.sleep(3);
        // enter info for record
        addPage.emailInputBox.sendKeys("info@netaffinity.com");
        BrowserUtils.sleep(3);
        // click save button
        addPage.saveButton.click();
        // logs for log4j
        logger.info("new account is created.");
        // logs for extent logger
        extentLogger.info("new account is created.");
        BrowserUtils.sleep(5);
        // get total accounts number for comparing
        actualAccountNumber = addPage.totalNumberOfAccount.size();
        // click delete button
        addPage.deleteButton.click();
        logger.info("the account is deleted.");
        extentLogger.info("the account is deleted.");

        // used Explicit wait for catching pop up deletion message
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        By popUpMessageElement = By.xpath("//span[@class='flex ng-binding']");
        WebElement toastElement = wait.until(ExpectedConditions.visibilityOfElementLocated(popUpMessageElement));

        String message = toastElement.getText();
        System.out.println("Pop-Up Message: " + message);
        BrowserUtils.sleep(5);
        // After deletion, get the remaining of account number
        int expectedAccountNumber = addPage.totalNumberOfAccount.size();
        System.out.println("expectedAccountNumber = " + expectedAccountNumber);
        // To verify deletion we use TestNG Verification
        Assert.assertEquals(actualAccountNumber - 1, expectedAccountNumber, "Account is not deleted!!!");
        logger.info("Deletion is verified.");
        extentLogger.info("Deletion is verified.");
        // Second verification with pop up message
        if (message.contains("Account deleted")) {
            System.out.println("Test Passed!!!");
        } else {
            System.out.println("Test Failed!!!");
        }


    }



}
