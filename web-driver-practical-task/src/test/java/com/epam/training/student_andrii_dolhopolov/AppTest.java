package com.epam.training.student_andrii_dolhopolov;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppTest 
{
    @Test
    public void ukrNetCorrectLoginAndPasswordTest() {
        System.setProperty("webdriver.chrome.driver","c:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.navigate().to("https://accounts.ukr.net/login");
        driver.findElement(By.name("login")).sendKeys("dolhopolov");
        driver.findElement(By.name("password")).sendKeys("Aa_445566");
        driver.findElement(By.xpath(".//button[@type='submit']")).click();
        Assert.assertEquals("dolhopolov@ukr.net", driver.findElement(By.xpath(".//p[@class='login-button__user']")).getText());
        driver.close();
    }
    @Test
    public void ukrNetWrongLoginAndPasswordTest() {
        System.setProperty("webdriver.chrome.driver","c:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to("https://accounts.ukr.net/login");
        driver.findElement(By.name("login")).sendKeys("dolgopolov");
        driver.findElement(By.name("password")).sendKeys("Aa_4455666");
        driver.findElement(By.xpath(".//button[@type='submit']")).click();
        Assert.assertNotNull(driver.findElement(By.xpath(".//a[contains(@href, \"recovery\")]")));
        driver.close();
    }
    @Test
    public void ukrNetEmptyPasswordTest() {
        System.setProperty("webdriver.chrome.driver","c:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.navigate().to("https://accounts.ukr.net/login");
        driver.findElement(By.name("login")).sendKeys("dolhopolov");
        driver.findElement(By.xpath(".//button[@type='submit']")).click();
        Assert.assertNotNull(driver.findElement(By.xpath(".//a[contains(@href, \"recovery\")]")));
        driver.close();
    }
    @Test
    public void SendReceiveEmailTest() {
        String messageText = "Test message.";
        System.setProperty("webdriver.chrome.driver","c:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://mail.aol.com/");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class=\"login\"]")))
                .click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"login-username\"]")))
                .sendKeys("dolhopolov");
        driver.findElement(By.xpath("//input[@id=\"login-signin\"]")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id=\"login-passwd\"]")))
                .sendKeys("Aa_445566");
        driver.findElement(By.xpath("//button[@id=\"login-signin\"]")).click();

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-test-id=\"compose-button\"]")))
                .click();

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("message-to-field")))
                .sendKeys("dolhopolov@ukr.net");
        driver.findElement(By.xpath(".//input[@data-test-id=\"compose-subject\"]")).sendKeys("Subject.");
        driver.findElement(By.xpath(".//div[@data-test-id=\"rte\"]")).sendKeys(messageText);

        driver.findElement(By.xpath("//button[@data-test-id=\"compose-send-button\"]")).click();


        driver.close();
        driver = new ChromeDriver();
        // Got ukr.net mail url
        driver.get("https://accounts.ukr.net/login");

        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.name("login")))
                .sendKeys("dolhopolov");
        driver.findElement(By.name("password")).sendKeys("Aa_445566");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // Make sure the email has arrived and is displayed as unread. Wait 10 minutes.
        Assert.assertNotNull(
                new WebDriverWait(driver, Duration.ofMinutes(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='unread']/span[2]")))
                .getText());

        // and it has the correct sender.
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("unread")))
                .click();
        Assert.assertEquals("Andrii Dolhopolov", driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div/div[2]/section/table/tbody/tr[1]/td[3]/a[1]/span")).getText());

        // Read the email
        driver.findElement(By.xpath("//a[@data-email='dolhopolov@aol.com']")).click();

        // and test that the actual content matches the 'sent' one
        Assert.assertEquals(messageText,
                new WebDriverWait(driver, Duration.ofSeconds(20))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='readmsg']/div[2]/section/div[2]/div[1]/span/span[2]/div/div")))
                        .getText());

        driver.close();
    }
}
