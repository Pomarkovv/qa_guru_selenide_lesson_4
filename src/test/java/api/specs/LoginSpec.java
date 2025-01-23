package api.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static web.helpers.CustomAllureListener.withCustomTemplates;

public class LoginSpec {
    public static RequestSpecification loginRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType(ContentType.JSON)
            .baseUri("https://reqres.in/")
            .basePath("api/register");

    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();

    public static ResponseSpecification loginResponseMissingPasswordSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(LogDetail.STATUS)
            .log(LogDetail.BODY)
            .build();
}
