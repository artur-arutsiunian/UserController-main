package restservicetest.DeleteTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.RequestService;
import restservice.helpers.AssertionsHelper;
import restservice.pojo.userCreate.request.CreateRequest;
import restservice.pojo.userCreate.response.CreateResponse;
import restservice.pojo.userDelete.DeleteRequest;

public class DeleteUserTest extends RequestService {

    @Test
    @DisplayName("Remove user by role 'user'")
    @Description("Remove user by role 'user'")
    void deleteUserByUserNegativeTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("user", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeAndContentType(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        DeleteRequest pCP = new DeleteRequest.Builder()
                .playerId(id)
                .build();
        Response respPS = send(pCP, "/delete/user/");
        AssertionsHelper.assertStatusCodeForbiddenNegative(respPS);
    }

    @Test
    @DisplayName("Remove admin by role 'user'")
    @Description("Remove admin by role 'user'")
    void deleteAdminByUserNegativeTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("admin", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeAndContentType(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        DeleteRequest pCP = new DeleteRequest.Builder()
                .playerId(id)
                .build();
        Response respPS = send(pCP, "/delete/user/");
        AssertionsHelper.assertStatusCodeForbiddenNegative(respPS);
    }

    CreateRequest createReq = new CreateRequest.Builder()
            .age("17")
            .gender("male")
            .login("user5")
            .password("1234567")
            .screenName("Use3")
            .build();
}
