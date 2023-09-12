package restservicetest.EditTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.RequestService;
import restservice.helpers.AssertionsHelper;
import restservice.pojo.userCreate.request.CreateRequest;
import restservice.pojo.userCreate.response.CreateResponse;
import restservice.pojo.userPatch.request.PatchRequest;
import restservice.pojo.userPatch.response.PatchResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditSupervisorTest {

    private RequestService requestService = RequestService.getInstance();

    CreateRequest createReq = new CreateRequest.Builder()
            .buildAge("17")
            .buildGender("male")
            .buildLogin("user5")
            .buildPassword("1234567")
            .buildScreenName("Use3")
            .build();

    @Test
    @DisplayName("Change 'admin' on correct user age")
    @Description("Check of edit age of user with correct value")
    public void editUserAgePositiveTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("admin")
                .build();
        Response respCP = requestService.send(rCP, "supervisor");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        PatchRequest pCP = new PatchRequest.Builder()
                .buildPlayerAge(25)
                .build();
        Response respPS = requestService.send(pCP,"supervisor/", id);
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respPS);
        PatchResponse actualResp = respPS.as(PatchResponse.class);
        assertEquals(pCP.getAge(), actualResp.getAge(), "'Age' fields aren't equal");
        assertEquals(rCP.getGender(), actualResp.getGender(), "'Gender' fields aren't equal");
        assertEquals(rCP.getLogin(), actualResp.getLogin(), "'Login' fields aren't equal");
        assertEquals(rCP.getRole(), actualResp.getRole(), "'Role' fields aren't equal");
        assertEquals(rCP.getScreenName(), actualResp.getScreenName(), "'ScreenName' fields aren't equal");
    }

    @Test
    @DisplayName("Change on wrong user age")
    @Description("Check of edit age of user with incorrect value")
    public void editUserAgeNegativeTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("admin")
                .build();
        Response respCP = requestService.send(rCP, "supervisor");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        PatchRequest pCP = new PatchRequest.Builder()
                .buildPlayerAge(60)
                .build();
        Response respPS = requestService.send(pCP,"supervisor/", id);
        AssertionsHelper.assertStatusCodeBadRequestNegative(respPS);

        String responseBody = respPS.asString();
        Assertions.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @Test
    @DisplayName("Change 'user' age by supervisor")
    @Description("Change 'user' age by supervisor")
    public void editUserAgeBySupervisorPositiveTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("user")
                .build();
        Response respCP = requestService.send(rCP, "supervisor");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        PatchRequest pCP = new PatchRequest.Builder()
                .buildPlayerAge(25)
                .build();
        Response respPS = requestService.send(pCP,"supervisor/", id);
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respPS);
        PatchResponse actualResp = respPS.as(PatchResponse.class);
        assertEquals(pCP.getAge(), actualResp.getAge(), "'Age' fields aren't equal");
        assertEquals(rCP.getGender(), actualResp.getGender(), "'Gender' fields aren't equal");
        assertEquals(rCP.getLogin(), actualResp.getLogin(), "'Login' fields aren't equal");
        assertEquals(rCP.getRole(), actualResp.getRole(), "'Role' fields aren't equal");
        assertEquals(rCP.getScreenName(), actualResp.getScreenName(), "'ScreenName' fields aren't equal");
    }
}
