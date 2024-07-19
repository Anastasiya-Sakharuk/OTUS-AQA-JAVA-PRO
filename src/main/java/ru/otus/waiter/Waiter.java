package ru.otus.waiter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Waiter implements IWaiter {
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    public Waiter(WebDriver driver) {
        this.driver = driver;
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Override
    public boolean waitForCondition(ExpectedCondition condition) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            this.webDriverWait.until(condition);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    public boolean waitForElementVisible(WebElement element) {
        return waitForCondition(ExpectedConditions.visibilityOf(element));
    }
    public boolean waitForElementVisible(By by) {
        return waitForElementVisible(this.webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by)));
    }
    public boolean waitForElementNotPresentBy(By locator) {
        return waitForCondition(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

}
