package restservicetest.CreateTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.RequestService;
import restservice.helpers.AssertionsHelper;
import restservice.pojo.userCreate.request.CreateRequest;

public class CreateUserTest extends RequestService {

    @Test
    @DisplayName("Send wrong user role who can't be created")
    @Description("Send wrong type of role with which user can't be created")
    void verifyingRoleSupervisorNegativeTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("supervisor", createReq)
                .build();
        Response respCP = send(rCP, "/create/user");
        AssertionsHelper.assertStatusCodeForbiddenNegative(respCP);
    }

    @Test
    @DisplayName("Send wrong user role who can't be created")
    @Description("Send wrong type of role with which user can't be created")
    void verifyingRoleAdminNegativeTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("admin", createReq)
                .build();
        Response respCP = send(rCP, "/create/user");
        AssertionsHelper.assertStatusCodeForbiddenNegative(respCP);
    }

    @Test
    @DisplayName("Send wrong user role who can't be created")
    @Description("Send wrong type of role with which user can't be created")
    void verifyingRoleUserNegativeTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("user", createReq)
                .build();
        Response respCP = send(rCP, "/create/user");
        AssertionsHelper.assertStatusCodeForbiddenNegative(respCP);
    }

    CreateRequest createReq = new CreateRequest.Builder()
            .age("17")
            .gender("male")
            .login("user5")
            .password("1234567")
            .screenName("Use3")
            .build();
}
