package ru.otus.mainPage;

import com.google.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.components.HeaderComponent;
import ru.otus.extensions.UIExtensions;
import ru.otus.pages.common.CoursesPage;
import ru.otus.pages.common.MainPage;

@ExtendWith(UIExtensions.class)
@Tag("@homework1")
@DisplayName("Набор тестов - взаимодействие из главной страницей")
public class MainPage_Test {
    @Inject
    private MainPage mainPage;
    @Inject
    private HeaderComponent headerComponent;
    @Inject
    private CoursesPage coursesPage;

    @Test
    @Tag("@scenario3")
    @DisplayName("Выбор случайной категории курсов из меню 'Обучение' с переходом на страницу 'Все курсы' с включённым фильтром 'Направление' выбранной категории")
    public void openCoursesCategory() {
        mainPage.open();

        String categoryName = headerComponent
                .hoverElement()
                .clickRandomCategory();

        coursesPage.categoryCheck(categoryName);

    }


}
