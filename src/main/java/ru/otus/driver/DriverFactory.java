package ru.otus.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import ru.otus.driver.impl.ChromeWebDriver;
import ru.otus.driver.impl.IDriver;
import ru.otus.exceptions.DriverTypeNotSupported;
import ru.otus.listeners.ActionsListener;
import java.util.Locale;

public class DriverFactory implements IDriverFactory {
    private String browserType = System.getProperty("browser", "chrome").toLowerCase(Locale.ROOT);

    @Override
    public WebDriver getDriver() {
        switch (this.browserType) {
            case "chrome": {
                WebDriverManager.chromiumdriver().setup();
                IDriver<ChromeOptions> browserSettings = new ChromeWebDriver();
                return new EventFiringDecorator<>(new ActionsListener())
                        .decorate(new ChromeDriver(browserSettings.getDriverOptions()));
            }
            default:
                try {
                    throw new DriverTypeNotSupported(this.browserType);
                } catch (DriverTypeNotSupported ex) {
                    ex.printStackTrace();
                    return null;
                }
        }
    }
}
