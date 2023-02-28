package com.epam.training.student_andrii_dolhopolov.pageobject_models.ukr_net;

import com.epam.training.student_andrii_dolhopolov.pageobject_models.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import com.epam.training.student_andrii_dolhopolov.waits.Waits;
public class UkrNetLoginPage extends AbstractPage {
    public static final String LOGIN_PAGE_URL = "https://accounts.ukr.net/login";
    @FindBy(name = "login")
    private WebElement loginInput;
    @FindBy(name = "password")
    private WebElement passwordInput;
    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement submitButton;

    public UkrNetLoginPage(WebDriver driver) {
        super(driver);
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
