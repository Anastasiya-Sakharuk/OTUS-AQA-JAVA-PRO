package ru.otus.driver;

import org.openqa.selenium.WebDriver;
import ru.otus.exceptions.DriverTypeNotSupported;

public interface IDriverFactory {
    WebDriver getDriver() throws DriverTypeNotSupported;
}
