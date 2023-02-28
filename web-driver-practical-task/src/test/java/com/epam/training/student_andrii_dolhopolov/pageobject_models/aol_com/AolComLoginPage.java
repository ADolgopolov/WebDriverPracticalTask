package com.epam.training.student_andrii_dolhopolov.pageobject_models.aol_com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AolComLoginPage {
    private final WebDriver driver;
    public AolComLoginPage(WebDriver driver) {
        this.driver = driver;
    }
    public AolComPasswordPage inputLogin(String login) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"login-username\"]")))
                .sendKeys(login);
        driver.findElement(By.xpath("//input[@id=\"login-signin\"]")).click();
        return new AolComPasswordPage(driver);
    }
}
