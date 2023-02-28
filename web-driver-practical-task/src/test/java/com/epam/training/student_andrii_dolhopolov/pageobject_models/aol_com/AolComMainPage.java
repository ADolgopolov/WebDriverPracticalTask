package com.epam.training.student_andrii_dolhopolov.pageobject_models.aol_com;

import com.epam.training.student_andrii_dolhopolov.pageobject_models.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AolComMainPage extends AbstractPage {
    public AolComMainPage(WebDriver driver) {
        super(driver);
    }
    public void sendMail(String address, String subject, String mailText) {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-test-id=\"compose-button\"]")))
                .click();
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("message-to-field")))
                .sendKeys(address);
        driver.findElement(By.xpath(".//input[@data-test-id=\"compose-subject\"]"))
                .sendKeys(subject);
        driver.findElement(By.xpath(".//div[@data-test-id=\"rte\"]"))
                .sendKeys(mailText);
        driver.findElement(By.xpath("//button[@data-test-id=\"compose-send-button\"]"))
                .click();
    }
}
