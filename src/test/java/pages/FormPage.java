package pages;

import com.codeborne.selenide.SelenideElement;
import components.CalendarComponent;
import components.UserDataTableComponent;

import java.util.List;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FormPage {

    private static final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            phoneInput = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            pictureInput = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateInput = $("#react-select-3-input"),
            cityInput = $("#react-select-4-input"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            submitButton = $("#submit");

    CalendarComponent calendar = new CalendarComponent();
    UserDataTableComponent table = new UserDataTableComponent();

    public FormPage openFormPage() {
        open("/automation-practice-form");
        return this;
    }

    public FormPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public FormPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public FormPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public FormPage setEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public FormPage setGender(String gender) {
        genderInput.$(byText(gender)).click();
        return this;
    }

    public FormPage setPhone(String phone) {
        phoneInput.setValue(phone);
        return this;
    }

    public FormPage setDate(String day, String month, String year) {
        dateOfBirthInput.click();
        calendar.setDate(day, month, year);
        return this;
    }

    public FormPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    public FormPage setHobby(String hobby) {
        hobbiesInput.$(byText(hobby)).click();
        return this;
    }

    public FormPage setPicture(String path) {
        pictureInput.uploadFromClasspath(path);
        return this;
    }

    public FormPage setAddress(String address) {
        addressInput.setValue(address);
        return this;
    }

    public FormPage setState(String state) {
        stateInput.setValue(state).pressEnter();
        return this;
    }

    public FormPage setCity(String city) {
        cityInput.setValue(city).pressEnter();
        return this;
    }

    public SelenideElement getLastNameInput() {
        return lastNameInput;
    }

    public SelenideElement getPhoneInput() {
        return phoneInput;
    }

    public void submitForm() {
        submitButton.click();
    }

    public void checkUserDataTableField(String key, String value) {
        table.checkRow(key, value);
    }

    public void checkInvalidInputStyle(List<SelenideElement> elements) {
        for (SelenideElement el : elements) {
            el.shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        }
    }

    public void checkValidInputStyle(List<SelenideElement> elements) {
        for (SelenideElement el : elements) {
            el.shouldHave(cssValue("border-color", "rgb(40, 167, 69)"));
        }
    }
}
