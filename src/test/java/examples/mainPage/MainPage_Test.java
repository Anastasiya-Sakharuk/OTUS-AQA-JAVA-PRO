package examples.mainPage;


import com.google.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.extensions.UIExtensions;
import ru.otus.pages.common.MainPage;

@ExtendWith(UIExtensions.class)
@Tag("@example")
@DisplayName("Набор тестов с главной страницей")
public class MainPage_Test {
    @Inject
    private MainPage mainPage;

    @Test
    public void mainPageOpen() {
        mainPage.open().isLoaded();
    }

}
