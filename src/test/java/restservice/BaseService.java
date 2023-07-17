package restservice;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import restservice.pojo.userCreate.CreateReq;
import restservice.pojo.userCreate.RequestModel;

import java.util.Map;

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
