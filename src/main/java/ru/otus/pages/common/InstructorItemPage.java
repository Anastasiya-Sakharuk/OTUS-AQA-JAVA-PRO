package ru.otus.pages.common;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.otus.annotations.PageValidation;
import ru.otus.exceptions.UrlIsNeededParametersException;
import ru.otus.pages.abstracts.AbsBasePage;
import ru.otus.waiter.Waiter;

@PageValidation("template://div[text()='%s']")
public class InstructorItemPage extends AbsBasePage<InstructorItemPage> {

    public InstructorItemPage(WebDriver driver) {
        super(driver);
        waiter = new Waiter(driver);
    }

    public InstructorItemPage open() {
        throw new UrlIsNeededParametersException("{id}. Use method - open(id)");
    }

    public InstructorItemPage isLoaded() {
        return null;
    }

    public InstructorItemPage pageShouldBeOpened(String name) {
        String locator = String.format(markerLocator, name);
        assertThat(waiter.waitForElementVisible($(By.xpath(locator))))
                .as("Error")
                .isTrue();
        return this;
    }
}
