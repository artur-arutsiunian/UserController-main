package restservicetest.EditTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.RequestService;
import restservice.helpers.AssertionsHelper;
import restservice.pojo.userCreate.request.CreateRequest;
import restservice.pojo.userCreate.response.CreateResponse;
import restservice.pojo.userPatch.request.PatchRequest;
import restservice.pojo.userPatch.response.PatchResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditSupervisorTest extends RequestService {

    @Test
    @DisplayName("Change 'admin' on correct user age")
    @Description("Check of edit age of user with correct value")
    void editUserAgePositiveTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("admin", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeAndContentType(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        PatchRequest pCP = new PatchRequest.Builder()
                .age(25)
                .build();
        Response respPS = send(pCP,"/update/supervisor/", id);
        AssertionsHelper.assertStatusCodeAndContentType(respPS);
        PatchResponse actualResp = respPS.as(PatchResponse.class);
        PatchResponse expectedResp = new PatchResponse(25, "male", id, "user5", "admin","Use3");
        assertEquals(expectedResp, actualResp, "'Age' fields isn't equal");
    }

    @Test
    @DisplayName("Change on wrong user age")
    @Description("Check of edit age of user with incorrect value")
    void editUserAgeNegativeTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("admin", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeAndContentType(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        PatchRequest pCP = new PatchRequest.Builder()
                .age(60)
                .build();
        Response respPS = send(pCP,"/update/supervisor/", id);
        AssertionsHelper.assertStatusCodeBadRequestNegative(respPS);
    }

    @Test
    @DisplayName("Change 'user' age by supervisor")
    @Description("Change 'user' age by supervisor")
    void editUserAgeBySupervisorPositiveTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("user", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeAndContentType(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        PatchRequest pCP = new PatchRequest.Builder()
                .age(25)
                .build();
        Response respPS = send(pCP,"/update/supervisor/", id);
        AssertionsHelper.assertStatusCodeAndContentType(respPS);
        PatchResponse actualResp = respPS.as(PatchResponse.class);
        PatchResponse expectedResp = new PatchResponse(25, "male", id, "user5", "user","Use3");
        assertEquals(expectedResp, actualResp, "'Age' fields isn't equal");
    }

    CreateRequest createReq = new CreateRequest.Builder()
            .age("17")
            .gender("male")
            .login("user5")
            .password("1234567")
            .screenName("Use3")
            .build();
}
