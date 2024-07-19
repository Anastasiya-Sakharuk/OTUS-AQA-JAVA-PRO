package ru.otus.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ru.otus.components.BlockWithItemsComponent;
import ru.otus.components.HeaderComponent;
import ru.otus.components.popups.SearchPopup;
import ru.otus.pages.common.CourseItemPage;
import ru.otus.pages.common.CoursesPage;
import ru.otus.pages.common.InstructorItemPage;
import ru.otus.pages.common.MainPage;
import ru.otus.waiter.Waiter;

public class GuiceContentsModule extends AbstractModule {

    @Inject
    private WebDriver driver;
    @Inject
    private Waiter waiter;
    @Inject
    private Actions actions;

    @Provides
    public BlockWithItemsComponent getBlockWithItemsComponent() {
        return new BlockWithItemsComponent(driver);
    }

    @Provides
    public InstructorItemPage getInstructorItemPage() {
        return new InstructorItemPage(driver);
    }

    @Provides
    public MainPage getMainPage() {
        return new MainPage(driver);
    }

    @Provides
    public SearchPopup getSearchPopup() {
        return new SearchPopup(driver);
    }

    @Provides
    public HeaderComponent getHeaderComponent() {
        return new HeaderComponent(driver);
    }

    @Provides
    public CoursesPage getCoursesPage() {
        return new CoursesPage(driver);
    }

    @Provides
    public CourseItemPage getCourseItemPage() {
        return new CourseItemPage(driver);
    }
}
