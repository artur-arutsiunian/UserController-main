package restservicetest.DeleteTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.RequestService;
import restservice.helpers.AssertionsHelper;
import restservice.pojo.userCreate.request.CreateRequest;
import restservice.pojo.userCreate.response.CreateResponse;
import restservice.pojo.userDelete.DeleteRequest;

public class DeleteSupervisorTest {

    private RequestService requestService = RequestService.getInstance();

    CreateRequest createReq = new CreateRequest.Builder()
            .buildAge("17")
            .buildGender("male")
            .buildLogin("user5")
            .buildPassword("1234567")
            .buildScreenName("Use3")
            .build();

    @Test
    @DisplayName("Delete 'user' by supervisor")
    @Description("Delete 'user' by supervisor")
    public void deleteUserBySupervisorPositiveTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("user")
                .build();
        Response respCP = requestService.send(rCP, "supervisor");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        DeleteRequest pCP = new DeleteRequest.Builder()
                .buildPlayerId(id)
                .build();
        Response respPS = requestService.send(pCP, "supervisor");
        AssertionsHelper.assertStatusCodeNoContent(respPS);

        String responseBody = respPS.asString();
        Assertions.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @Test
    @DisplayName("Remove user with wrong id")
    @Description("Delete of user with wrong id")
    public void deleteUserNegativeTest() {
        DeleteRequest pCP = new DeleteRequest.Builder()
                .buildPlayerId(123456)
                .build();
        Response respPS = requestService.send(pCP, "supervisor");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respPS);

        String responseBody = respPS.asString();
        Assertions.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @Test
    @DisplayName("Delete 'admin' by supervisor")
    @Description("Delete 'admin' by supervisor")
    public void deleteAdminBySupervisorPositiveTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("admin")
                .build();
        Response respCP = requestService.send(rCP, "supervisor");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        DeleteRequest pCP = new DeleteRequest.Builder()
                .buildPlayerId(id)
                .build();
        Response respPS = requestService.send(pCP, "supervisor");
        AssertionsHelper.assertStatusCodeNoContent(respPS);

        String responseBody = respPS.asString();
        Assertions.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }
}
