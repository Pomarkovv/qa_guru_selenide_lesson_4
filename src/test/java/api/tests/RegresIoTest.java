package api.tests;

import api.models.MissingPasswordResponse;
import api.models.RegistrationRequest;
import api.models.RegistrationResponse;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static api.specs.LoginSpec.*;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;

public class RegresIoTest {

    @Test
    @Tag("api")
    void regSuccessfullyTest() {
        RegistrationRequest req = new RegistrationRequest();
        req.setEmail("eve.holt@reqres.in");
        req.setPassword("pistol");

        RegistrationResponse resp = step("Выполняем авторизационный запрос", () ->
                given(loginRequestSpec)
                        .body(req)

                        .when()
                        .post()

                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(RegistrationResponse.class)
        );
        step("Проверяем ответ", () -> {
            assert resp.getId() == 4;
            assert resp.getToken().equals("QpwL5tke4Pnpja7X4");
        });
    }

    @Test
    @Tag("api")
    void missingPassportTest() {
        RegistrationRequest req = new RegistrationRequest();
        req.setEmail("eve.holt@reqres.in");

        MissingPasswordResponse resp = step("Выполняем авторизационный запрос", () ->
                given(loginRequestSpec)
                        .body(req)

                        .when()
                        .post()

                        .then()
                        .spec(loginResponseMissingPasswordSpec)
                        .extract().as(MissingPasswordResponse.class)
        );
        step("Проверяем ответ", () -> {
            assert resp.getError().equals("Missing password");
        });
    }


}
