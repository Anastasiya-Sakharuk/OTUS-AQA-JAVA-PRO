package ru.otus.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.otus.annotations.Component;
import ru.otus.pages.common.InstructorItemPage;

@Component("xpath://section[.//*[text()='%s']]")
public class BlockWithItemsComponent extends AbsBaseComponent<BlockWithItemsComponent> {

    public BlockWithItemsComponent(WebDriver driver) {
        super(driver);
    }

    public InstructorItemPage clickItem(String name) {
        getComponentEntity().findElement(By.xpath(String.format(".//a[.//div[text()='%s']]", name))).click();

        return new InstructorItemPage(driver);
    }

    public String getItemName(int index) {
        return getComponentEntity()
                .findElements(By.xpath(".//a[./div]"))
                .get(--index).findElement(By.xpath(".//div[2]"))
                .getText();
    }
}
