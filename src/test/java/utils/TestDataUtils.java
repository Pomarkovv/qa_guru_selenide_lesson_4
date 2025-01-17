package utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class TestDataUtils {

    static final Locale RU_LOCALE = new Locale("ru");
    static final Locale ENG_LOCALE = new Locale("en");

    static Faker faker = new Faker(ENG_LOCALE);

    private static String getRandomStringFromList(List<String> list) {
        int randomIndex = new Random().nextInt(list.size());
        return list.get(randomIndex);
    }

    public static String getRandomFirstName(){
        return faker.name().firstName();
    }

    public static String getRandomLastName(){
        return faker.name().lastName();
    }

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomGender() {
        List<String> genders = List.of("Male", "Female", "Other");
        return getRandomStringFromList(genders);
    }

    public static String getRandomPhone() {
        return new Faker(RU_LOCALE).phoneNumber().phoneNumber().replaceAll("[^0-9]", "").substring(Integer.parseInt("1"));
    }

    public static String getRandomDay() {
        return String.format("%02d", new Random().nextInt(31) + 1);
    }

    public static String getRandomMonth() {
        String month = new SimpleDateFormat("MMMM", Locale.ENGLISH).format(faker.date().birthday());
        return month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
    }

    public static String getRandomYear() {
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        return yearFormat.format(faker.date().birthday());
    }

    public static String getRandomSubject() {
        List<String> subjects = List.of("English", "Chemistry", "Computer Science", "Commerce", "Economics", "Social Studies");
        return getRandomStringFromList(subjects);
    }

    public static String getRandomHobby() {
        List<String> hobbies = List.of("Reading", "Music", "Sports");
        return getRandomStringFromList(hobbies);
    }

    public static String getRandomAddress() {
        return faker.address().streetAddress();
    }

    public static String getRandomState() {
        List<String> states = List.of("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
        return getRandomStringFromList(states);
    }

    public static String getRandomCity(String state) {
        List<String> ncrCities = List.of("Delhi", "Gurgaon", "Noida");
        List<String> uttarCities = List.of("Agra", "Lucknow", "Haryana", "Merrut");
        List<String> haryanaCities = List.of("Karnal", "Panipat");
        List<String> rajasthanCities = List.of("Jaipur", "Jaiselmer");

        return switch (state) {
            case "NCR" -> getRandomStringFromList(ncrCities);
            case "Uttar Pradesh" -> getRandomStringFromList(uttarCities);
            case "Haryana" -> getRandomStringFromList(haryanaCities);
            case "Rajasthan" -> getRandomStringFromList(rajasthanCities);
            default -> "";
        };
    }

    public static String getFileName(String filePath) {
        int lastSlashIndex = filePath.lastIndexOf('/');
        return filePath.substring(lastSlashIndex + 1);
    }
}
