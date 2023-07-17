package restservicetest.EditTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.helpers.AssertionsHelper;
import restservice.BaseService;
import restservice.pojo.userCreate.*;
import restservice.pojo.userPatch.PatchReq;
import restservice.pojo.userPatch.PatchRes;
import restservicetest.BaseTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EditAdminTest extends BaseTest {

    BaseService baseService = new BaseService();
    private final static String CREATE_BY_SUPERVISOR = "/create/supervisor/";
    private final static String EDIT_BY_ADMIN = "/update/admin/";

    @Test
    @DisplayName("Change 'user' age by admin")
    @Description("Change 'user' age by admin")
    void editUserAgeByAdminPositiveTest(){
        CreateReq rCP = getCreatePlayerParamsMap(Role.user);
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeAndContentType(respCP);

        int id = respCP.as(CreateRes.class).getId();

        PatchReq pCP = getPatchRequest(25);
        Response respPS = send(pCP, id);
        AssertionsHelper.assertStatusCodeAndContentType(respPS);
        PatchRes actualResp = respPS.as(PatchRes.class);
        PatchRes expectedResp = new PatchRes(25, Gender.male, id, "User5", Role.user,"Use3");
        assertEquals(expectedResp, actualResp, "'Age' fields isn't equal");
    }

    @Test
    @DisplayName("Change 'admin' age by admin'")
    @Description("Change 'admin' age by admin'")
    void editAdminAgeByAdminPositiveTest(){
        CreateReq rCP = getCreatePlayerParamsMap(Role.admin);
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeAndContentType(respCP);

        int id = respCP.as(CreateRes.class).getId();

        PatchReq pCP = getPatchRequest(25);
        Response respPS = send(pCP, id);
        AssertionsHelper.assertStatusCodeAndContentType(respPS);
        PatchRes actualResp = respPS.as(PatchRes.class);
        PatchRes expectedResp = new PatchRes(25, Gender.male, id, "User5", Role.admin,"Use3");
        assertEquals(expectedResp, actualResp, "'Age' fields isn't equal");
    }

    private CreateReq getCreatePlayerParamsMap(String age, Gender gender, String login, String password, Role role, String screenName) {
        CreateReq createReq = new CreateReq();
        createReq.setAge(age);
        createReq.setGender(gender);
        createReq.setLogin(login);
        createReq.setPassword(password);
        createReq.setRole(role);
        createReq.setScreenName(screenName);
        return createReq;
    }

    private CreateReq getCreatePlayerParamsMap(Role role){
        return getCreatePlayerParamsMap("17", Gender.male, "User5", "1234567", role,"Use3");
    }

    private PatchReq getPatchRequest(int age){
        PatchReq patchReq = new PatchReq();
        patchReq.setAge(age);
        return patchReq;
    }

    public Response send(RequestModel rq) {
        return baseService.given().queryParams((rq.toMap()))
                .get(CREATE_BY_SUPERVISOR);
    }

    public Response send(PatchReq rq, int userId) {
        return baseService.given().body(rq)
                .patch(EDIT_BY_ADMIN + userId);
    }
}
