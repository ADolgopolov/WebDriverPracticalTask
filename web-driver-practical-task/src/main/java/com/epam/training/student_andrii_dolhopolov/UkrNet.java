package com.epam.training.student_andrii_dolhopolov;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class UkrNet {
    public static final String loginPageUrl = "https://accounts.ukr.net/login";
    public static final String login = "dolhopolov";
    public static final String password = "Aa_445566";
    private static WebElement element = null;

    public static WebElement inputLogin(WebDriver driver) {
        element = driver.findElement(By.name("login"));
        return element;
    }
    public static WebElement inputPassword(WebDriver driver) {
        element = driver.findElement(By.name("password"));
        return element;
    }
    public static WebElement buttonSubmit(WebDriver driver) {
        element = driver.findElement(By.xpath(".//button[@type='submit']"));
        return element;
    }
    public static boolean isLoginComplete(WebDriver driver) {
        boolean isCorrectUserName;
        try{
            isCorrectUserName = Objects.equals("dolhopolov@ukr.net", driver.findElement(By.xpath(".//p[@class='login-button__user']")).getText());
        }catch (NoSuchElementException e){
            return false;
        }
        return isCorrectUserName;
    }
    public static void waitForNewMessage(WebDriver driver) {
        new WebDriverWait(driver, Duration.ofMinutes(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='unread']/span[2]")));
    }
    public static WebElement buttonUnread(WebDriver driver) {
        element = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("unread")));
        return element;
    }
    public static String returnMessageTextFromSender(WebDriver driver, String sender) {
        driver.findElement(By.xpath("//a[@data-email='" + sender + "']")).click();
        return new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='readmsg']/div[2]/section/div[2]/div[1]/span/span[2]/div/div")))
                .getText();
    }
}
