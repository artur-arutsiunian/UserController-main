package restservice;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseService {

    public RequestSpecification given() {
        return RestAssured.given()
                .baseUri("http://3.68.165.45")
                .basePath("/player")
                .contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
                .relaxedHTTPSValidation();

    }
}
