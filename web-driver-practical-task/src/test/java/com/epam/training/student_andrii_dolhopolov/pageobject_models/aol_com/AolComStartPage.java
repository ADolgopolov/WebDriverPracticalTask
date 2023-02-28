package com.epam.training.student_andrii_dolhopolov.pageobject_models.aol_com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AolComStartPage {
    public static final String LOGIN_PAGE_URL = "https://mail.aol.com";
    private final WebDriver driver;
    public AolComStartPage(WebDriver driver) {
        this.driver = driver;
    }
    public AolComStartPage openStartPage() {
        driver.get(LOGIN_PAGE_URL);
        return this;
    }
    public AolComLoginPage activateLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class=\"login\"]")))
            .click();
        return new AolComLoginPage(driver);
    }
}
