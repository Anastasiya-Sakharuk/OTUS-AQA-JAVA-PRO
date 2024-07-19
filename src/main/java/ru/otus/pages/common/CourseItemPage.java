package ru.otus.pages.common;

import org.openqa.selenium.WebDriver;
import ru.otus.annotations.PageValidation;
import ru.otus.annotations.UrlPrefix;
import ru.otus.exceptions.UrlIsNeededParametersException;
import ru.otus.pages.abstracts.AbsBasePage;
import ru.otus.waiter.Waiter;

@UrlPrefix("/lessons/{prefixName}/")
@PageValidation("template:xpath://h1[text()='%s']")
public class CourseItemPage extends AbsBasePage<CourseItemPage> {
    public CourseItemPage(WebDriver driver) {
        super(driver);
        waiter = new Waiter(driver);
    }

    @Override
    public CourseItemPage open() {
        throw new UrlIsNeededParametersException("{prefixName}. Use method - open(prefixName)");
    }

    public CourseItemPage isLoaded(String elementViaFindBy) {
        return null;
    }

}
