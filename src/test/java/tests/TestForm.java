package tests;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import components.UserDataTableComponent;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.FormPage;

import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static io.qameta.allure.Allure.step;
import static utils.TestDataUtils.*;

public class TestForm extends BaseTest {

    private final String FIRST_NAME = getRandomFirstName();
    private final String LAST_NAME = getRandomLastName();
    private final String EMAIL = getRandomEmail();
    private final String PHONE = getRandomPhone();
    private final String DAY = getRandomDay();
    private final String MONTH = getRandomMonth();
    private final String YEAR = getRandomYear();
    private final String ADDRESS = getRandomAddress();
    private final String GENDER = getRandomGender();
    private final String SUBJECT = getRandomSubject();
    private final String HOBBY = getRandomHobby();
    private final String STATE = getRandomState();
    private final String CITY = getRandomCity(STATE);

    FormPage formPage = new FormPage();
    UserDataTableComponent table = new UserDataTableComponent();

    @BeforeEach
    void openFormPage() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        step("Открываем страницу с формой", () -> {
            formPage.openFormPage()
                    .removeBanner();
        });
    }

    @AfterEach
    void afterEach() {
        Attach.screenshotAs("Last Screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }

    @Tag("smoke")
    @Test
    void fillFormSuccessfullyTest() {
        String picturePath = "files/img.png";
        Map<String, String> expectedValues = Map.of(
                "Student Name", FIRST_NAME + " " + LAST_NAME,
                "Student Email", EMAIL,
                "Gender", GENDER,
                "Mobile", PHONE,
                "Date of Birth", String.format("%s %s,%s", DAY, MONTH, YEAR),
                "Subjects", SUBJECT,
                "Hobbies", HOBBY,
                "Picture", getFileName(picturePath),
                "Address", ADDRESS,
                "State and City", STATE + " " + CITY
        );

        step("Заполняем форму данными", () -> {
            formPage.setFirstName(FIRST_NAME)
                    .setLastName(LAST_NAME)
                    .setEmail(EMAIL)
                    .setGender(GENDER)
                    .setPhone(PHONE)
                    .setDate(DAY, MONTH, YEAR)
                    .setSubject(SUBJECT)
                    .setHobby(HOBBY)
                    .setPicture(picturePath)
                    .setAddress(ADDRESS)
                    .setState(STATE)
                    .setCity(CITY);
        });
        step("Сабмитим форму", () -> {
            formPage.submitForm();
        });
        step("Проверяем данные итоговой таблицы", () -> {
            table.visible();
            expectedValues.forEach(table::checkRow);
        });
    }

    @Tag("smoke")
    @Test
    void fillFormWithMinDataSuccessfullyTest() {
        Map<String, String> expectedValues = Map.of(
                "Student Name", FIRST_NAME + " " + LAST_NAME,
                "Gender", GENDER,
                "Mobile", PHONE
        );

        step("Заполняем минимально требуемые данные", () -> {
            formPage.setFirstName(FIRST_NAME)
                    .setLastName(LAST_NAME)
                    .setGender(GENDER)
                    .setPhone(PHONE);
        });
        step("Сабмитим форму", () -> {
            formPage.submitForm();
        });
        step("Проверяем данные итоговой таблицы", () -> {
            table.visible();
            expectedValues.forEach(table::checkRow);
        });
    }

    @Tag("smoke")
    @Test
    void checkInvalidInputStyleThenOnlyFirstNameGivenTest() {
        step("Заполняем минимальное число данных", () -> {
            formPage.setFirstName(FIRST_NAME);
        });
        step("Сабмитим форму", () -> {
            formPage.submitForm();
        });
        step("Проверяем, что инпут красный", () -> {
            formPage.checkInvalidInputStyle(List.of(formPage.getLastNameInput(), formPage.getPhoneInput()));
        });
    }

    @Tag("smoke")
    @Test
    void checkInvalidPhoneLengthTest() {
        step("Вводим телефон с невалидной длинной", () -> {
            formPage.setPhone(PHONE.substring(0, PHONE.length() - 1));
        });
        step("Сабмитим форму", () -> {
            formPage.submitForm();
        });
        step("Проверяем, что инпут красный", () -> {
            formPage.checkInvalidInputStyle(List.of(formPage.getPhoneInput()));
        });
    }

    @Tag("smoke")
    @Test
    void checkValidPhoneLengthTest() {
        step("Вводим телефон с валидной длинной", () -> {
            formPage.setPhone(PHONE);
        });
        step("Сабмитим форму", () -> {
            formPage.submitForm();
        });
        step("Проверяем, что инпут зеленый", () -> {
            formPage.checkValidInputStyle(List.of(formPage.getPhoneInput()));
        });
    }
}
