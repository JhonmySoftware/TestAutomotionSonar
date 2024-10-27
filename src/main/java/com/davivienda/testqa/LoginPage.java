package com.davivienda.testqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "(//input[@id='idToken1'])")
    private WebElement usernameField;

    @FindBy(xpath = "(//input[@id='idToken2'])")
    private WebElement passwordField;

    @FindBy(xpath = "(//input[@id='loginButton_0'])")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        waitForElementToBePresent(usernameField);
        writeText(usernameField, username);
        writeText(passwordField, password);
        click(loginButton);
    }
}

