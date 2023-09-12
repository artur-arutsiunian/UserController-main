package restservicetest.CreateTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.helpers.AssertionsHelper;
import restservice.RequestService;
import restservice.pojo.userCreate.request.CreateRequest;
import restservice.pojo.userCreate.response.CreateResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateAdminTest {

    private RequestService requestService = RequestService.getInstance();

    CreateRequest createReq = new CreateRequest.Builder()
            .buildAge("17")
            .buildGender("male")
            .buildLogin("user5")
            .buildPassword("1234567")
            .buildScreenName("Use3")
            .build();

    @Test
    @DisplayName("Send wrong user role who can't be created")
    @Description("Send wrong type of role with which user can't be created")
    public void verifyingRoleSupervisorNegativeTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("supervisor")
                .build();
        Response respCP = requestService.send(rCP, "admin");
        AssertionsHelper.assertStatusCodeForbiddenNegative(respCP);

        String responseBody = respCP.asString();
        Assertions.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @Test
    @DisplayName("Send correct role of user who can be created")
    @Description("Send another correct option role of user who can be created")
    public void verifyingRoleAdminPositiveTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("admin")
                .build();
        Response respCP = requestService.send(rCP, "admin");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);
        CreateResponse actualResp = respCP.as(CreateResponse.class);
        CreateResponse expectedResp = rCP.toCreateResponse();
        expectedResp.setId(respCP.jsonPath().get("id"));
        assertEquals(expectedResp, actualResp, "Fields aren't equal");
    }

    @Test
    @DisplayName("Send correct role of user who can be created")
    @Description("Send another correct option role of user who can be created")
    public void verifyingRoleUserPositiveTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("user")
                .build();
        Response respCP = requestService.send(rCP, "admin");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);
        CreateResponse actualResp = respCP.as(CreateResponse.class);
        CreateResponse expectedResp = rCP.toCreateResponse();
        expectedResp.setId(respCP.jsonPath().get("id"));
        assertEquals(expectedResp, actualResp, "Fields aren't equal");
    }
}
