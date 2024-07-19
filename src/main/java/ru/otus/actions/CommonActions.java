package ru.otus.actions;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import ru.otus.waiter.Waiter;

public class CommonActions<T> {
    protected WebDriver driver;
    @Inject
    protected Waiter waiter;
    @Inject
    protected Actions actions;

    public CommonActions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected WebElement $(By locator) {
        return driver.findElement(locator);
    }

    public void moveToElement(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement($(by))
                .build()
                .perform();
    }
}
