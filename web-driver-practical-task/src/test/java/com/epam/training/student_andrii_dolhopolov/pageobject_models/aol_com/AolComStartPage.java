package com.epam.training.student_andrii_dolhopolov.pageobject_models.aol_com;

import com.epam.training.student_andrii_dolhopolov.pageobject_models.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AolComStartPage extends AbstractPage {
    public static final String LOGIN_PAGE_URL = "https://mail.aol.com";
    public AolComStartPage(WebDriver driver) {
        super(driver);
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
