package restservicetest.DeleteTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.helpers.AssertionsHelper;
import restservice.BaseService;
import restservice.pojo.userCreate.*;
import restservice.pojo.userDelete.DeleteReq;
import restservicetest.BaseTest;

public class DeleteUserTest extends BaseTest {

    BaseService baseService = new BaseService();
    private final static String CREATE_BY_SUPERVISOR = "/create/supervisor/";
    private final static String DELETE_BY_USER = "/delete/user/";

    @Test
    @DisplayName("Remove user by role 'user'")
    @Description("Remove user by role 'user'")
    void deleteUserByUserNegativeTest(){
        CreateReq rCP = getCreatePlayerParamsMap(Role.user);
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeAndContentType(respCP);

        int id = respCP.as(CreateRes.class).getId();

        DeleteReq pCP = getDeleteRequest(id);
        Response respPS = send(pCP);
        AssertionsHelper.assertStatusCodeForbiddenNegative(respPS);
    }

    @Test
    @DisplayName("Remove admin by role 'user'")
    @Description("Remove admin by role 'user'")
    void deleteAdminByUserNegativeTest(){
        CreateReq rCP = getCreatePlayerParamsMap(Role.admin);
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeAndContentType(respCP);

        int id = respCP.as(CreateRes.class).getId();

        DeleteReq pCP = getDeleteRequest(id);
        Response respPS = send(pCP);
        AssertionsHelper.assertStatusCodeForbiddenNegative(respPS);
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

    private DeleteReq getDeleteRequest(int playerId){
        DeleteReq deleteReq = new DeleteReq();
        deleteReq.setPlayerId(playerId);
        return deleteReq;
    }

    public Response send(RequestModel rq) {
        return baseService.given().queryParams((rq.toMap()))
                .get(CREATE_BY_SUPERVISOR);
    }

    public Response send(DeleteReq dl) {
        return baseService.given().body(dl)
                .delete(DELETE_BY_USER);
    }
}
