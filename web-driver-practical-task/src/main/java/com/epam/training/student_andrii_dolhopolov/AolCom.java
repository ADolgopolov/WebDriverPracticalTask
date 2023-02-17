package com.epam.training.student_andrii_dolhopolov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AolCom {
    public static final String loginPageUrl = "https://mail.aol.com";
    public static final String login = "dolhopolov";
    public static final String password = "Aa_445566";
    private static WebElement element = null;

    public static WebElement activateLoginPage(WebDriver driver) {
        element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class=\"login\"]")));
        return element;
    }
    public static WebElement inputLogin(WebDriver driver) {
        element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"login-username\"]")));
        return element;
    }
    public static WebElement buttonLoginSubmit(WebDriver driver) {
        element = driver.findElement(By.xpath("//input[@id=\"login-signin\"]"));
        return element;
    }
    public static WebElement inputPassword(WebDriver driver) {
        element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"login-passwd\"]")));
        return element;
    }
    public static WebElement buttonPasswordSubmit(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@id=\"login-signin\"]"));
        return element;
    }
    public static WebElement buttonCompose(WebDriver driver) {
        element = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-test-id=\"compose-button\"]")));
        return element;
    }
    public static WebElement inputComposeRecipientAddress(WebDriver driver) {
        element = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("message-to-field")));
        return element;
    }
    public static WebElement inputComposeSubject(WebDriver driver) {
        element = driver.findElement(By.xpath(".//input[@data-test-id=\"compose-subject\"]"));
        return element;
    }
    public static WebElement inputComposeText(WebDriver driver) {
        element = driver.findElement(By.xpath(".//div[@data-test-id=\"rte\"]"));
        return element;
    }
    public static WebElement buttonComposeSend(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@data-test-id=\"compose-send-button\"]"));
        return element;
    }
}
