package examples.teacherBlock;

import com.google.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.components.BlockWithItemsComponent;
import ru.otus.extensions.UIExtensions;
import ru.otus.pages.common.InstructorItemPage;
import ru.otus.pages.common.MainPage;

@ExtendWith(UIExtensions.class)
@Tag("@example")
@DisplayName("Набор тестов со страницей преподавателя")
public class TeachersBlock_Test {

    @Inject
    private MainPage mainPage;
    @Inject
    private BlockWithItemsComponent blockWithItemsComponent;
    @Inject
    private InstructorItemPage instructorItemPage;

    @Test
    public void openTeacherCardByClick() {
        mainPage.open().isLoaded();

        String name = blockWithItemsComponent
                .setTitle("Преподаватели")
                .getItemName(1);
        blockWithItemsComponent
                .clickItem(name)
                .pageShouldBeOpened(name);
    }
}
