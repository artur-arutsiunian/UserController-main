package restservicetest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.RequestService;
import restservice.helpers.AssertionsHelper;
import restservice.pojo.userCreate.request.CreateRequest;
import restservice.pojo.userCreate.response.CreateResponse;
import restservice.pojo.userGet.request.GetRequest;
import restservice.pojo.userGet.response.GetResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUserTest {

    private RequestService requestService = RequestService.getInstance();

    CreateRequest createReq = new CreateRequest.Builder()
            .buildAge("17")
            .buildGender("male")
            .buildLogin("User5")
            .buildPassword("1234567")
            .buildScreenName("Use3")
            .build();

    @Test
    @DisplayName("Receive user with correct id")
    @Description("Get a specific user by id")
    public void getUserPositiveTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("admin")
                .build();
        Response respCP = requestService.send(rCP, "supervisor");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        GetRequest gCP = new GetRequest.Builder()
                .buildPlayerId(id)
                .build();
        Response respPS = requestService.send(gCP);
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respPS);
        GetResponse actualResp = respPS.as(GetResponse.class);
        GetResponse expectedResp = rCP.toGetResponse();
        expectedResp.setId(respCP.jsonPath().get("id"));
        assertEquals(expectedResp, actualResp, "Fields aren't equal");
    }

    @Test
    @DisplayName("Receive user with wrong id")
    @Description("Get of user with wrong id")
    public void getUserNegativeTest() {
        GetRequest gCP = new GetRequest.Builder()
                .buildPlayerId(123456)
                .build();
        Response respPS = requestService.send(gCP);
        AssertionsHelper.assertStatusCodeBadRequestNegative(respPS);

        String responseBody = respPS.asString();
        Assertions.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }
}
