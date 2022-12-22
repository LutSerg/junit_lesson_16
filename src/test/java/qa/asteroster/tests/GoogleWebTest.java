package qa.asteroster.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GoogleWebTest {
    @BeforeEach
    void setUp() {
        open("https://www.google.ru/");
    }

    @Tag("CRITICAL")
    @CsvSource({
            "selenide, https://ru.selenide.org",
            "jUnit, https://junit.org"
    })
    @ParameterizedTest(name = "Проверка наличия URL {1} в результатах выдачи гугла по запросу {0}")
    void googleSearchTest(String SearchQuery, String ExpectedUrl) {
        $("[name=q]").setValue(SearchQuery).pressEnter();
        $("#res").shouldHave(Condition.text(ExpectedUrl));
    }

    @ParameterizedTest(name = "Проверка наличия кнопок из списка {0} най сайте селенида при локали {0}")
    @Tag("BLOCKER")
    void selenideButtonsTest(String locale, List<String> buttons) {

    }


    @DisplayName("Проверка вывода попап окна для поиска по фото")
    @Tag("BLOCKER")
    @Test
    void googleSearchByPhotoPopup () {
        $("img[alt='Поиск с помощью камеры']").click();
        $(byText("Выполните поиск по изображению в Google Объективе"))
                .shouldBe(Condition.visible);
    }
}
