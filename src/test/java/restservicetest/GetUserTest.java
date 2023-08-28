package restservicetest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.RequestService;
import restservice.helpers.AssertionsHelper;
import restservice.pojo.userCreate.request.CreateRequest;
import restservice.pojo.userCreate.response.CreateResponse;
import restservice.pojo.userGet.request.GetRequest;
import restservice.pojo.userGet.response.GetResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUserTest extends RequestService {

    @Test
    @DisplayName("Receive user with correct id")
    @Description("Get a specific user by id")
    void getUserPositiveTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("admin", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeAndContentType(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        GetRequest gCP = new GetRequest.Builder()
                .playerId(id)
                .build();
        Response respPS = send(gCP, "/get");
        AssertionsHelper.assertStatusCodeAndContentType(respPS);
        GetResponse actualResp = respPS.as(GetResponse.class);
        GetResponse expectedResp = new GetResponse(17, "male", id, "User5", "1234567", "admin","Use3");
        assertEquals(expectedResp, actualResp, "Fields aren't equal");
    }

    @Test
    @DisplayName("Receive user with wrong id")
    @Description("Get of user with wrong id")
    void getUserNegativeTest(){
        GetRequest gCP = new GetRequest.Builder()
                .playerId(123456)
                .build();
        Response respPS = send(gCP, "/get");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respPS);
    }

    CreateRequest createReq = new CreateRequest.Builder()
            .age("17")
            .gender("male")
            .login("User5")
            .password("1234567")
            .screenName("Use3")
            .build();
}
