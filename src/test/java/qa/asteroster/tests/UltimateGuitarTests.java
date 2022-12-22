package qa.asteroster.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UltimateGuitarTests {

    @Test
    void ultimateGuitarTest() {
        open("https://www.ultimate-guitar.com/");
        $(".Qh0h8").click();
        $(".Ctrll").shouldHave(Condition.text("Explore tab catalog"));
    }


    @ValueSource(strings = {"audioslave", "slipknot"})
    @ParameterizedTest(name = "Проверка наличия группы {0} в выпадающем списке при использовании поиска")
    void searchArtistTest(String searchArtist) {
        open("https://www.ultimate-guitar.com/");
        $(".Pry8g").click();
        $(".Xp1h4").setValue(searchArtist);
        $(".YWhZa").shouldHave(Condition.text(searchArtist));
    }

    @CsvSource(
            {
            "audioslave, audioslave chords & tabs",
            "slipknot, slipknot chords & tabs"
            }
    )
    @ParameterizedTest(name = "Проверка, что на странице табулатур отображается фраза {1} при поиске группы {0}")
    void chordAndTabsArtistPageTest(String artistSearch, String artistChordsAndTabs){
        open("https://www.ultimate-guitar.com/");
        $(".Pry8g").click();
        $(".Xp1h4").setValue(artistSearch).pressEnter();
        $(".XwpqK").shouldHave(Condition.text(artistChordsAndTabs));
    }

    static Stream<Arguments> artistListTest () {
        return Stream.of(
                Arguments.of("brick in the wall", List.of("Pink Floyd", "Korn", "Misc Mashups", "Before You Exit", "The Tiger Lillies")),
                Arguments.of("show me how to live", List.of("Audioslave", "Chris Cornell"))
        );
    }
    @MethodSource
    @ParameterizedTest (name = "отображение связанных артистов {1} при вводе в поиск названия песни {0}")
    void artistListTest(String song, List<String> listOfArtists) {
        open("https://www.ultimate-guitar.com/");
        $(".Pry8g").click();
        $(".Xp1h4").setValue(song).pressEnter();
        //$(".XwpqK").shouldHave(Condition.text("brick in the wall chords & tabs"));
        $$("._hlnN").filter(Condition.visible)
                .shouldHave(CollectionCondition.texts(listOfArtists));

    }
}
