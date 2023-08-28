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

public class EditUserTest extends RequestService {

    @Test
    @DisplayName("Change 'user' age by user")
    @Description("Change 'user' age by user")
    void editUserAgeByUserPositiveTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("user", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeAndContentType(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        PatchRequest pCP = new PatchRequest.Builder()
                .age(25)
                .build();
        Response respPS = send(pCP,"/update/user/", id);
        AssertionsHelper.assertStatusCodeAndContentType(respPS);
        PatchResponse actualResp = respPS.as(PatchResponse.class);
        PatchResponse expectedResp = new PatchResponse(25, "male", id, "User5", "user","Use3");
        assertEquals(expectedResp, actualResp, "'Age' fields isn't equal");
    }

    @Test
    @DisplayName("Change 'admin' age by user'")
    @Description("Change 'admin' age by user'")
    void editAdminAgeByUserNegativeTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("admin", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeAndContentType(respCP);

        int id = respCP.as(CreateResponse.class).getId();

        PatchRequest pCP = new PatchRequest.Builder()
                .age(25)
                .build();
        Response respPS = send(pCP,"/update/user/", id);
        AssertionsHelper.assertStatusCodeForbiddenNegative(respPS);
    }

    CreateRequest createReq = new CreateRequest.Builder()
            .age("17")
            .gender("male")
            .login("User5")
            .password("1234567")
            .screenName("Use3")
            .build();
}
