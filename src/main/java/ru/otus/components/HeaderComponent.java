package ru.otus.components;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.otus.annotations.Component;
import ru.otus.components.popups.SearchPopup;
import ru.otus.waiter.Waiter;
import java.util.List;
import java.util.regex.Pattern;

@Component("//div[./a[@href='/']]")
public class HeaderComponent extends AbsBaseComponent<HeaderComponent> {

    private By hoverElement = By.cssSelector("span[title='Обучение']");
    @FindBy(xpath = "//nav//div[./*[@viewBox]]")
    private WebElement searchButton;

    public HeaderComponent(WebDriver driver) {
        super(driver);
        waiter = new Waiter(driver);
    }

    public SearchPopup clickSearchButton() {
        waiter.waitForCondition(ExpectedConditions.elementToBeClickable(searchButton));
        searchButton.click();
        return new SearchPopup(driver);
    }

    public HeaderComponent hoverElement() {
        moveToElement(hoverElement);
        return this;
    }

    public String clickRandomCategory() {
        String categoryName = "";

        List<WebElement> listCategories = getListWebElements(By.xpath("//nav//div[3]//div//a"));
        Pattern pattern = Pattern.compile("^.+\\s*\\(\\d+\\)$");
        List<WebElement> filteredList = listCategories.stream()
                .filter(element -> pattern.matcher(element.getText()).matches())
                .toList();

        Faker faker = new Faker();
        if (!filteredList.isEmpty()) {
            int index = faker.number().numberBetween(0, filteredList.size() - 1);
            WebElement category = filteredList.get(index);
            categoryName = category.getText();
            category.click();
        }
        return categoryName;
    }
}
