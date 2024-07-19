package ru.otus.components.popups;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.otus.actions.CommonActions;
import ru.otus.waiter.Waiter;

public class SearchPopup extends CommonActions<SearchPopup> implements IPopup<SearchPopup> {
    private final String searchPopupSelector = "input[type='search']";

    public SearchPopup(WebDriver driver) {
        super(driver);
        waiter = new Waiter(driver);
    }

    @Override
    public SearchPopup popupShouldNotBePresent() {
        assertThat(waiter.waitForElementNotPresentBy(By.cssSelector(searchPopupSelector)))
                .as("Search popup should not be present")
                .isTrue();
        return this;
    }

    @Override
    public SearchPopup popupShouldBeVisible() {
        assertThat(waiter.waitForElementVisible(By.cssSelector(searchPopupSelector)))
                .as("Search popup should be visible")
                .isTrue();
        return this;
    }
}
