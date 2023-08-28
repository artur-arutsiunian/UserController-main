package restservice;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import restservice.pojo.userCreate.RequestModel;
import restservice.pojo.userDelete.DeleteRequest;
import restservice.pojo.userGet.request.GetRequest;
import restservice.pojo.userPatch.request.PatchRequest;

public class RequestService {

    public RequestSpecification given() {
        return RestAssured.given()
                .baseUri("http://3.68.165.45")
                .basePath("/player")
                .contentType(ContentType.JSON)
                .filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new AllureRestAssured())
                .relaxedHTTPSValidation();
    }

    public Response send(RequestModel rq, String endpoint) {
        return given().queryParams((rq.toMap()))
                .get(endpoint);
    }

    public Response send(PatchRequest rq, String endpoint, int userId) {
        return given().body(rq)
                .patch(endpoint + userId);
    }

    public Response send(DeleteRequest dl, String endpoint) {
        return given().body(dl)
                .delete(endpoint);
    }

    public Response send(GetRequest gr, String endpoint) {
        return given().body(gr)
                .post(endpoint);
    }
}
