package com.NetAffinity.pages;

import com.NetAffinity.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddPage extends BasePage {

    /** Created AddPage for Add Module, added all fo the webElement of Add Module to here
     implemented Page Object Model */
    public AddPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@name='title']")
    public WebElement titleInputBox;
    @FindBy(xpath = "//input[@name='facebook_account']")
    public WebElement idInputBox;
    @FindBy(xpath = "//input[@name='phone']")
    public WebElement phoneInputBox;
    @FindBy(xpath = "//input[@name='email']")
    public WebElement emailInputBox;
    @FindBy(xpath = "//span[.='Save']")
    public WebElement saveButton;
    @FindBy(xpath = "//span[.='Cancel']")
    public WebElement cancelButton;
}
