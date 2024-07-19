package examples.searchPopup;

import com.google.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ru.otus.components.HeaderComponent;
import ru.otus.components.popups.SearchPopup;
import ru.otus.extensions.UIExtensions;
import ru.otus.pages.common.MainPage;

@ExtendWith(UIExtensions.class)
@Tag("@example")
@DisplayName("Набор тестов с хэдером главной страницы")
public class SearchPopup_Test {

    @Inject
    private MainPage mainPage;
    @Inject
    private SearchPopup searchPopup;

    @Inject
    private HeaderComponent headerComponent;

    @Test
    public void searchPopupVisible() {
        mainPage.open();
        searchPopup.popupShouldNotBePresent();
        headerComponent.clickSearchButton()
                .popupShouldBeVisible();
    }
}
