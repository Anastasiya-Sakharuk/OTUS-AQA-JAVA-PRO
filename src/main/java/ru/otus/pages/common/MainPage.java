package ru.otus.pages.common;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import ru.otus.annotations.UrlPrefix;
import ru.otus.pages.abstracts.AbsBasePage;

@UrlPrefix("/")
public class MainPage extends AbsBasePage<MainPage> {

    @Inject
    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainPage isLoaded() {
        return null;
    }
}

