package com.NetAffinity.pages;

import com.NetAffinity.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public abstract class BasePage {

    /*
    In this class we will store WebElements common to all pages
     */

    public BasePage(){

        PageFactory.initElements(Driver.getDriver(),this);

    }

    @FindBy(xpath = "//button[@class='md-fab md-primary md-button md-default-theme']")
    public WebElement addButton;

    @FindBy(xpath = "(//span[.='Delete'])[1]")
    public WebElement deleteButton;

    @FindBy(xpath = "//div[@class='flex flex-100']")
    public List<WebElement> totalNumberOfAccount;

    @FindBy(xpath = "//span[@class='flex ng-binding']")
    public WebElement popUpMessage;

}
