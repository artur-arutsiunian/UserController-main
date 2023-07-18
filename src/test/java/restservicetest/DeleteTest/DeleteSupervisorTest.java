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


public class DeleteSupervisorTest extends BaseTest {
    BaseService baseService = new BaseService();

    @Test
    @DisplayName("Delete 'user' by supervisor")
    @Description("Delete 'user' by supervisor")
    void deleteUserBySupervisorPositiveTest(){
        CreateReq rCP = getCreatePlayerParamsMap(Role.user);
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeAndContentType(respCP);

        int id = respCP.as(CreateRes.class).getId();

        DeleteReq pCP = getDeleteRequest(id);
        Response respPS = send(pCP);
        AssertionsHelper.assertStatusCodeNoContent(respPS);
    }

    @Test
    @DisplayName("Remove user with wrong id")
    @Description("Delete of user with wrong id")
    void deleteUserNegativeTest(){
        DeleteReq pCP = getDeleteRequest(1233214);
        Response respPS = send(pCP);
        AssertionsHelper.assertStatusCodeBadRequestNegative(respPS);
    }

    @Test
    @DisplayName("Delete 'admin' by supervisor")
    @Description("Delete 'admin' by supervisor")
    void deleteAdminBySupervisorPositiveTest(){
        CreateReq rCP = getCreatePlayerParamsMap(Role.admin);
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeAndContentType(respCP);

        int id = respCP.as(CreateRes.class).getId();

        DeleteReq pCP = getDeleteRequest(id);
        Response respPS = send(pCP);
        AssertionsHelper.assertStatusCodeNoContent(respPS);
    }

    private CreateReq getCreatePlayerParamsMap(String age, Gender gender, String login, String password,  Role role, String screenName) {
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
        String CREATE_BY_SUPERVISOR = "/create/supervisor/";
        return baseService.given().queryParams((rq.toMap()))
                .get(CREATE_BY_SUPERVISOR);
    }

    public Response send(DeleteReq dl) {
        String DELETE_BY_SUPERVISOR = "/delete/supervisor/";
        return baseService.given().body(dl)
                .delete(DELETE_BY_SUPERVISOR);
    }
}
