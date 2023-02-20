package com.epam.training.student_andrii_dolhopolov;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;




import java.time.Duration;

public class EmailServiceTest
{
    @Test
    public void ukrNetCorrectLoginAndPasswordTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to(UkrNet.loginPageUrl);
        driver.manage().window().maximize();
        UkrNet.inputLogin(driver).sendKeys(UkrNet.login);
        UkrNet.inputPassword(driver).sendKeys(UkrNet.password);
        UkrNet.buttonSubmit(driver).click();
        Assert.assertTrue(UkrNet.isLoginComplete(driver));
        driver.close();
    }
    @Test
    public void ukrNetWrongLoginAndPasswordTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to(UkrNet.loginPageUrl);
        driver.manage().window().maximize();
        UkrNet.inputLogin(driver).sendKeys("user");
        UkrNet.inputPassword(driver).sendKeys("password");
        UkrNet.buttonSubmit(driver).click();
        Assert.assertFalse(UkrNet.isLoginComplete(driver));
        driver.close();
    }
    @Test
    public void ukrNetEmptyPasswordTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to(UkrNet.loginPageUrl);
        driver.manage().window().maximize();
        UkrNet.inputLogin(driver).sendKeys("user");
        UkrNet.inputPassword(driver).sendKeys("");
        UkrNet.buttonSubmit(driver).click();
        Assert.assertFalse(UkrNet.isLoginComplete(driver));
        driver.close();
    }
    @Test
    public void SendReceiveEmailTest() {
        String messageText = "Test message.";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        // Go to aol.com mail
        driver.get(AolCom.loginPageUrl);
        driver.manage().window().maximize();

        AolCom.activateLoginPage(driver).click();
        AolCom.inputLogin(driver).sendKeys(AolCom.login);
        AolCom.buttonLoginSubmit(driver).click();
        AolCom.inputPassword(driver).sendKeys(AolCom.password);
        AolCom.buttonPasswordSubmit(driver).click();

        AolCom.buttonCompose(driver).click();
        AolCom.inputComposeRecipientAddress(driver).sendKeys("dolhopolov@ukr.net");
        AolCom.inputComposeSubject(driver).sendKeys("Subject.");
        AolCom.inputComposeText(driver).sendKeys(messageText);
        AolCom.buttonComposeSend(driver).click();
        driver.close();

        driver = new ChromeDriver();
        // Go to ukr.net mail
        driver.navigate().to(UkrNet.loginPageUrl);
        driver.manage().window().maximize();
        UkrNet.inputLogin(driver).sendKeys(UkrNet.login);
        UkrNet.inputPassword(driver).sendKeys(UkrNet.password);
        UkrNet.buttonSubmit(driver).click();

        // Make sure the email has arrived and is displayed as unread. Wait 10 minutes.
        UkrNet.waitForNewMessage(driver);
        UkrNet.buttonUnread(driver).click();
        Assert.assertEquals(messageText, UkrNet.returnMessageTextFromSender(driver, "dolhopolov@aol.com"));
        driver.close();
    }
}
