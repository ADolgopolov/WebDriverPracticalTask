package com.epam.training.student_andrii_dolhopolov.pageobject_models.ukr_net;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.epam.training.student_andrii_dolhopolov.waits.Waits;
public class UkrNetLoginPage {
    public static final String LOGIN_PAGE_URL = "https://accounts.ukr.net/login";
    private final WebDriver driver;
    @FindBy(name = "login")
    private WebElement loginInput;
    @FindBy(name = "password")
    private WebElement passwordInput;
    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement submitButton;

    public UkrNetLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public UkrNetLoginPage openPage() {
        this.driver.get(LOGIN_PAGE_URL);
        return this;
    }
    public UkrNetLoginPage enterLogin(String login) {
        Waits.waitForElementVisibility(driver, this.loginInput).sendKeys(login);
        return this;
    }
    public UkrNetLoginPage enterPassword(String password) {
        Waits.waitForElementVisibility(driver, this.passwordInput).sendKeys(password);
        return this;
    }
    public UkrNetMainPage pushSubmitButton() {
        Waits.waitForElementVisibility(driver, this.submitButton).click();
        return new UkrNetMainPage(driver);
    }
}
