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

public class DeleteSupervisorTest extends RequestService {

    @Test
    @DisplayName("Delete 'user' by supervisor")
    @Description("Delete 'user' by supervisor")
    void deleteUserBySupervisorPositiveTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("user", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeAndContentType(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        DeleteRequest pCP = new DeleteRequest.Builder()
                .playerId(id)
                .build();
        Response respPS = send(pCP, "/delete/supervisor/");
        AssertionsHelper.assertStatusCodeNoContent(respPS);
    }

    @Test
    @DisplayName("Remove user with wrong id")
    @Description("Delete of user with wrong id")
    void deleteUserNegativeTest(){
        DeleteRequest pCP = new DeleteRequest.Builder()
                .playerId(123456)
                .build();
        Response respPS = send(pCP, "/delete/supervisor/");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respPS);
    }

    @Test
    @DisplayName("Delete 'admin' by supervisor")
    @Description("Delete 'admin' by supervisor")
    void deleteAdminBySupervisorPositiveTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("admin", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeAndContentType(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        DeleteRequest pCP = new DeleteRequest.Builder()
                .playerId(id)
                .build();
        Response respPS = send(pCP, "/delete/supervisor/");
        AssertionsHelper.assertStatusCodeNoContent(respPS);
    }

    CreateRequest createReq = new CreateRequest.Builder()
            .age("17")
            .gender("male")
            .login("user5")
            .password("1234567")
            .screenName("Use3")
            .build();
}
