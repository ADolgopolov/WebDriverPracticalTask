package com.epam.training.student_andrii_dolhopolov.waits;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    public static WebElement waitForElementVisibility(WebDriver driver, WebElement webElement) {
        return new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOf(webElement));
    }
}
