package com.epam.training.student_andrii_dolhopolov.pageobject_models.ukr_net;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class UkrNetMainPage {
    private final WebDriver driver;

    public UkrNetMainPage(WebDriver driver) {
        this.driver = driver;
    }
    public boolean isCompletedLogin() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        List<WebElement> elements = driver.findElements(By.xpath("//p[@class='login-button__user']"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        return elements.size() > 0;
    }
    public UkrNetMainPage waitForNewMessage() {
        new WebDriverWait(driver, Duration.ofMinutes(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='unread']/span[2]")));
        return this;
    }
    public UkrNetMainPage goToUnread() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("unread"))).click();
        return this;
    }
    public String returnMessageTextFromSender(String sender) {
        driver.findElement(By.xpath("//a[@data-email='" + sender + "']")).click();
        return new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='readmsg']/div[2]/section/div[2]/div[1]/span/span[2]/div/div")))
                .getText();
    }
}
