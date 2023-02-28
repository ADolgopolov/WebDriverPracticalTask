package com.epam.training.student_andrii_dolhopolov.pageobject_models.aol_com;

import com.epam.training.student_andrii_dolhopolov.pageobject_models.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AolComPasswordPage extends AbstractPage {
    public AolComPasswordPage(WebDriver driver) {
        super(driver);
    }
    public  AolComMainPage inputPassword(String password) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"login-passwd\"]")))
            .sendKeys(password);
        driver.findElement(By.xpath("//button[@id=\"login-signin\"]")).click();
        return new AolComMainPage(driver);
    }
}
