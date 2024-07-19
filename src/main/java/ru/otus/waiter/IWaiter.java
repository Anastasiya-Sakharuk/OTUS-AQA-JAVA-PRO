package ru.otus.waiter;

import org.openqa.selenium.support.ui.ExpectedCondition;

public interface IWaiter {
    boolean waitForCondition(ExpectedCondition condition);
}
