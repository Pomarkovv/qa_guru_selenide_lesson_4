package api.tests;

import api.models.RegistrationRequest;
import api.models.RegistrationResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class RegresIoTest {

    private static String baseUrl = "https://reqres.in/";

    @Test
    @Tag("api")
    void regSuccessfullyTest() {
        String registrationUrl = baseUrl + "api/register";
        RegistrationRequest req = new RegistrationRequest();
        req.setEmail("eve.holt@reqres.in");
        req.setPassword("pistol");
        RegistrationResponse resp = given()
                .body(req)
                .contentType(ContentType.JSON)
                .when()
                .post(registrationUrl)
                .then()
                .statusCode(200)
                .extract().as(RegistrationResponse.class);

        assert resp.getId() == 4;
        assert resp.getToken().equals("QpwL5tke4Pnpja7X4");
    }
}
