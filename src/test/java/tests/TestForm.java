package tests;

import com.codeborne.selenide.Configuration;
import components.UserDataTableComponent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.FormPage;

import java.util.List;
import java.util.Map;

public class TestForm {

    private static final String
            FIRST_NAME = "Ivan",
            LAST_NAME = "Pomarkov",
            EMAIL = "ivanpomarkovse@gmail.com",
            PHONE = "9293088508",
            DAY = "14",
            MONTH = "March",
            YEAR = "2002",
            ADDRESS = "Kansk, Krasnoyarsiy krai, Karla Marksa, 39",
            GENDER = "Male",
            DATE_OF_BIRTH = "14 March,2002",
            SUBJECT = "Maths",
            HOBBY = "Music",
            PICTURE_PATH = "files/img.png",
            PICTURE_NAME = "img.png",
            STATE = "Haryana",
            CITY = "Panipat";

    FormPage formPage = new FormPage();
    UserDataTableComponent table = new UserDataTableComponent();

    @BeforeAll
    static void setup() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFormSuccessfullyTest() {
        Map<String, String> expectedValues = Map.of(
                "Student Name", FIRST_NAME + " " + LAST_NAME,
                "Student Email", EMAIL,
                "Gender", GENDER,
                "Mobile", PHONE,
                "Date of Birth", DATE_OF_BIRTH,
                "Subjects", SUBJECT,
                "Hobbies", HOBBY,
                "Picture", PICTURE_NAME,
                "Address", ADDRESS,
                "State and City", STATE + " " + CITY
        );

        formPage.openFormPage()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setGender(GENDER)
                .setPhone(PHONE)
                .setDate(DAY, MONTH, YEAR)
                .setSubject(SUBJECT)
                .setHobby(HOBBY)
                .setPicture(PICTURE_PATH)
                .setAddress(ADDRESS)
                .setState(STATE)
                .setCity(CITY);
        formPage.submitForm();

        table.visible();
        expectedValues.forEach(table::checkRow);
    }

    @Test
    void fillFormWithMinDataSuccessfullyTest() {
        Map<String, String> expectedValues = Map.of(
                "Student Name", FIRST_NAME + " " + LAST_NAME,
                "Gender", GENDER,
                "Mobile", PHONE
        );

        formPage.openFormPage()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setGender(GENDER)
                .setPhone(PHONE);
        formPage.submitForm();

        table.visible();
        expectedValues.forEach(table::checkRow);
    }

    @Test
    void checkInvalidInputStyleThenOnlyFirstNameGivenTest() {
        formPage.openFormPage()
                .setFirstName(FIRST_NAME)
                .submitForm();

        formPage.checkInvalidInputStyle(List.of(formPage.getLastNameInput(), formPage.getPhoneInput()));
    }

    @Test
    void checkInvalidPhoneLengthTest() {
        formPage.openFormPage()
                .setPhone("983123123")
                .submitForm();

        formPage.checkInvalidInputStyle(List.of(formPage.getPhoneInput()));
    }

    @Test
    void checkValidPhoneLengthTest() {
        formPage.openFormPage()
                .setPhone("9831231234")
                .submitForm();

        formPage.checkValidInputStyle(List.of(formPage.getPhoneInput()));
    }
}