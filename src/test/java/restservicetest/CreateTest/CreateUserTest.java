package restservicetest.CreateTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.RequestService;
import restservice.helpers.AssertionsHelper;
import restservice.pojo.userCreate.request.CreateRequest;

public class CreateUserTest {

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
    public void verifyingRoleSupervisorNegativeTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("supervisor")
                .build();
        Response respCP = requestService.send(rCP, "user");
        AssertionsHelper.assertStatusCodeForbiddenNegative(respCP);

        String responseBody = respCP.asString();
        Assertions.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @Test
    @DisplayName("Send wrong user role who can't be created")
    @Description("Send wrong type of role with which user can't be created")
    public void verifyingRoleAdminNegativeTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("admin")
                .build();
        Response respCP = requestService.send(rCP, "user");
        AssertionsHelper.assertStatusCodeForbiddenNegative(respCP);

        String responseBody = respCP.asString();
        Assertions.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @Test
    @DisplayName("Send wrong user role who can't be created")
    @Description("Send wrong type of role with which user can't be created")
    public void verifyingRoleUserNegativeTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("user")
                .build();
        Response respCP = requestService.send(rCP, "user");
        AssertionsHelper.assertStatusCodeForbiddenNegative(respCP);

        String responseBody = respCP.asString();
        Assertions.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }
}
