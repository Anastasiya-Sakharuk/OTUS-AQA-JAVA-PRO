package ru.otus.pages.common;

import static org.assertj.core.api.Assertions.assertThat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.otus.annotations.UrlPrefix;
import ru.otus.pages.abstracts.AbsBasePage;
import ru.otus.waiter.Waiter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@UrlPrefix("/catalog/courses")
public class CoursesPage extends AbsBasePage<CoursesPage> {
    public static final String REGEX_REMOVE_NUMBERS_COURSES = "\\s*\\(\\d+\\)$";
    private final String hrefXpath = "//section[2]//a";
    private final String nameXpath = "//section[2]//a//h6";
    private final String dateXpath = "//section[2]//a//h6/following-sibling::div//div[text() and not(contains(text(),'О дате старта'))]";
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter
            .ofPattern("dd MMMM, yyyy", new Locale("ru"));
    @FindBy(xpath = "//main//h1[.//*[text()='Каталог']]")
    private WebElement isLoadedElement;

    @FindBy(xpath = "//section[.//h1//div[text()='Каталог']]")
    private WebElement coursesBlock;
    private List<WebElement> coursesList;

    public CoursesPage(WebDriver driver) {
        super(driver);
        waiter = new Waiter(driver);
    }

    public CoursesPage isLoaded() {
        this.isLoaded(this.isLoadedElement);
        return this;
    }

    public CoursesPage checkDataOfCoursesListWithDataOfCoursePageViaJSOUP() {
        this.coursesList
                .forEach(el -> {
                    String href = el.findElement(By.xpath(hrefXpath)).getAttribute("href");
                    String courseName = el.findElement(By.xpath(nameXpath)).getAttribute("innerText").trim();
                    LocalDate elDate = parseDateToLocaleDate(el.findElement(By.xpath(dateXpath)).getText().trim());

                    Jsoup.newSession();
                    Document coursePage = null;
                    try {
                        coursePage = Jsoup.connect(href).get();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assertThat(
                            coursePage.selectXpath("//h1").stream()
                                    .map(element -> element.text().trim())
                                    .anyMatch(x -> x.equals(courseName))
                    )
                            .as(String.format("Нужного заголовка '%s' курса не было найдено на '%s'",
                                    courseName, href))
                            .isTrue();

                    assertThat(
                            coursePage.selectXpath("//section//div[contains(@class, 'galmep')]/div/div[1]//p "
                                            + "| //p[contains(text(), 'Начало занятий')]/ancestor::div[contains(@class, 'content-item container')]//p[contains(@class, 'item-text')]")
                                    .stream()
                                    .map(element -> element.text().trim())
                                    .anyMatch(x -> this.parseDateToLocaleDate(x + ", 2024").equals(elDate))
                    )
                            .as(String.format("Нужной даты '%s' начала курса не было найдено на '%s'",
                                    elDate.format(dateTimeFormatter), href))
                            .isTrue();
                });
        return this;
    }

    public CoursesPage setTheEarliestCoursesList() {
        setSortedDateCoursesList(false);
        return this;
    }

    public CoursesPage setTheLatestCoursesList() {
        setSortedDateCoursesList(true);
        return this;
    }

    private void setSortedDateCoursesList(boolean trueIsMaxFalseIsMin) {
        LocalDate filterDate = this.coursesBlock.findElements(By.xpath(dateXpath)).stream()
                .map(el -> parseDateToLocaleDate(el.getText()))
                .min((l1, l2) -> trueIsMaxFalseIsMin ? l2.compareTo(l1) : l1.compareTo(l2)).get();

        this.coursesList = this.coursesBlock.findElements(By.xpath(dateXpath)).stream()
                .filter(el -> filterDate.compareTo(parseDateToLocaleDate(el.getText())) == 0)
                .map(o -> o.findElement(By.xpath("./ancestor-or-self::a")))
                .collect(Collectors.toList());
    }

    private LocalDate parseDateToLocaleDate(String text) {
        return LocalDate.parse(text.replaceAll("\\s*·.*$", "").trim(), dateTimeFormatter);
    }

    public void clickCourseByName(String courseName) {
        this.coursesBlock.findElements(By.xpath("//main//h1[.//*[text()='Каталог']]"))
                .stream()
                .filter(element -> element.getText().equals(courseName))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public void categoryCheck(String categoryName) {
        String clearCategoryName = categoryName.replaceAll(REGEX_REMOVE_NUMBERS_COURSES, "");
        WebElement label = driver.findElement(By.xpath("//label[contains(text(), '" + clearCategoryName + "')]"));
        String checkboxId = label.getAttribute("for");
        WebElement checkbox = driver.findElement(By.id(checkboxId));

        assertThat(checkbox.isSelected())
                .as("Checkbox with id '%s' should be selected", checkboxId)
                .isTrue();
    }
}

