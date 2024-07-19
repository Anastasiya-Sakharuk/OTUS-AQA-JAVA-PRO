package ru.otus.coursesPage;

import com.google.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.extensions.UIExtensions;
import ru.otus.pages.common.CourseItemPage;
import ru.otus.pages.common.CoursesPage;

@ExtendWith(UIExtensions.class)
@Tag("@homework1")
@DisplayName("Набор тестов - взаимодействие из каталога курсов")
public class CoursesPage_Test {
    @Inject
    private CoursesPage coursesPage;

    @Inject
    private CourseItemPage courseItemPage;

    @Test
    @Tag("@scenario1")
    @DisplayName("Открытие страницы конкретного курса")
    public void openCourseCardByClick() {
        String courseName = "HTML/CSS";
        coursesPage
                .open()
                .isLoaded()
                .clickCourseByName(courseName);
        courseItemPage.isLoaded(courseName);
    }

    @Test
    @Tag("@scenario2")
    @DisplayName("Поиск мин и макс даты начала курсов на странице 'Все курсы' и проверка названия и даты начала на странице этого курса")
    public void filterCoursesWithDateAndCheck() {
        coursesPage.open()
                .setTheEarliestCoursesList()
                .checkDataOfCoursesListWithDataOfCoursePageViaJSOUP()
                .setTheLatestCoursesList()
                .checkDataOfCoursesListWithDataOfCoursePageViaJSOUP();
    }
}
