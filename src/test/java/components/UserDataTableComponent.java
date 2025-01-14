package components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class UserDataTableComponent {

    public void checkRow(String key, String value) {
        $(".table").$(byText(key)).sibling(0).shouldHave(text(value));
    }

    public void visible() {
        $("div.modal-content").shouldBe(visible);
    }
}
