package com.epam.training.student_andrii_dolhopolov;

import com.epam.training.student_andrii_dolhopolov.users_models.AolComUser;
import com.epam.training.student_andrii_dolhopolov.users_models.UkrNetUser;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.epam.training.student_andrii_dolhopolov.pageobject_models.aol_com.AolComStartPage;
import com.epam.training.student_andrii_dolhopolov.pageobject_models.ukr_net.UkrNetLoginPage;


public class EmailServiceTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    public void ukrNetCorrectLoginAndPasswordTest() {
        boolean isLoginComplete = new UkrNetLoginPage(driver)
                .openPage()
                .enterLogin(UkrNetUser.LOGIN)
                .enterPassword(UkrNetUser.PASSWORD)
                .pushSubmitButton()
                .isCompletedLogin();
        Assert.assertTrue(isLoginComplete);
    }

    @Test
    public void ukrNetWrongLoginAndPasswordTest() {
        boolean isLoginComplete = new UkrNetLoginPage(driver)
                .openPage()
                .enterLogin("user")
                .enterPassword("password")
                .pushSubmitButton()
                .isCompletedLogin();
        Assert.assertFalse(isLoginComplete);
    }

    @Test
    public void ukrNetEmptyPasswordTest() {
        boolean isLoginComplete = new UkrNetLoginPage(driver)
                .openPage()
                .enterLogin(UkrNetUser.LOGIN)
                .enterPassword("")
                .pushSubmitButton()
                .isCompletedLogin();
        Assert.assertFalse(isLoginComplete);
    }

    @Test
    public void SendReceiveEmailTest() {
        String sendMessageText = "Test message.";

        // Go to aol.com mail-service
        new AolComStartPage(driver)
                .openStartPage()
                .activateLoginPage()
                .inputLogin(AolComUser.LOGIN)
                .inputPassword(AolComUser.PASSWORD)
                .sendMail(UkrNetUser.E_MAIL_ADDRESS, "Subject.", sendMessageText);
        driver.quit();

        // Go to ukr.net mail-service
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String receivedMessageText = new UkrNetLoginPage(driver)
                .openPage()
                .enterLogin(UkrNetUser.LOGIN)
                .enterPassword(UkrNetUser.PASSWORD)
                .pushSubmitButton()
                .waitForNewMessage()
                .goToUnread()
                .returnMessageTextFromSender("dolhopolov@aol.com");

        Assert.assertEquals(sendMessageText, receivedMessageText);
    }
}
