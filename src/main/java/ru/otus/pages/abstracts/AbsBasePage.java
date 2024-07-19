package ru.otus.pages.abstracts;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.otus.annotations.PageValidation;
import ru.otus.annotations.UrlPrefix;
import ru.otus.actions.CommonActions;
import java.util.List;

public abstract class AbsBasePage<T> extends CommonActions<T> {

    protected String markerLocator = "";
    protected String title;

    public AbsBasePage(WebDriver driver) {
        super(driver);
        markerLocator = pageValidation();
    }

    private String pageValidation() {
        if (getClass().isAnnotationPresent(PageValidation.class)) {
            PageValidation pageValidation = getClass().getAnnotation(PageValidation.class);
            String markerElementLocator = pageValidation.value();
            if (markerElementLocator.startsWith("template:")) {
                return markerElementLocator.replace("template:", "");
            }

            By locator = null;
            if (markerElementLocator.startsWith("/")) {
                locator = By.xpath(markerElementLocator);
            } else {
                locator = By.cssSelector(markerElementLocator);
            }

            waiter.waitForElementVisible($(locator));
        }

        return "";
    }

    public T open() {
        driver.get(getBaseUrl() + getUrlPrefix());
        return (T) this;
    }

    public T isLoaded(WebElement elementViaFindBy) {
        String pageIsNotLoadedText = String.format("Страница '%s' не загружена, вебэлемент маркера не виден на странице",
                this.getClass().getSimpleName());
        assertThat(this.waiter.waitForElementVisible(elementViaFindBy)).as(pageIsNotLoadedText).isTrue();
        return (T) this;
    }

    public T isLoaded() {
        assertThat(title)
                .as("Не установлен входной параметр, который нужен для загрузки страницы")
                .isNotEmpty();
        return (T) this;
    }

    protected String getBaseUrl() {
        return StringUtils.stripEnd(System.getProperty("webdriver.base.url", "https://otus.ru"), "/");
    }

    protected String getUrlPrefix() {
        UrlPrefix urlAnnotation = getClass().getAnnotation(UrlPrefix.class);
        if (urlAnnotation != null) {
            return urlAnnotation.value();
        }
        return "";
    }

    public List<WebElement> getListWebElements(By by) {
        return driver.findElements(by);
    }

}
