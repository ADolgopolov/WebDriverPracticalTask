package com.epam.training.student_andrii_dolhopolov.pageobject_models.ukr_net;

import com.epam.training.student_andrii_dolhopolov.pageobject_models.AbstractPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class UkrNetLoginPage extends AbstractPage {
    public static final String LOGIN_PAGE_URL = "https://accounts.ukr.net/login";
    @FindBy(name = "login")
    protected WebElement loginInput;
    @FindBy(name = "password")
    protected WebElement passwordInput;
    @FindBy(xpath = ".//button[@type='submit']")
    protected WebElement submitButton;

    public UkrNetLoginPage(WebDriver driver) {
        super(driver);
    }

    public UkrNetLoginPage openPage() {
        this.driver.get(LOGIN_PAGE_URL);
        return this;
    }
    public UkrNetLoginPage enterLogin(String login) {
        this.loginInput.sendKeys(login);
        return this;
    }
    public UkrNetLoginPage enterPassword(String password) {
        this.passwordInput.sendKeys(password);
        return this;
    }
    public UkrNetMainPage pushSubmitButton() {
        this.submitButton.click();
        return new UkrNetMainPage(driver);
    }
}
