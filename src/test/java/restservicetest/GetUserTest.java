package restservicetest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.helpers.AssertionsHelper;
import restservice.BaseService;
import restservice.pojo.userCreate.*;
import restservice.pojo.userGet.GetReq;
import restservice.pojo.userGet.GetRes;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetUserTest extends BaseTest {

    BaseService baseService = new BaseService();

    @Test
    @DisplayName("Receive user with correct id")
    @Description("Get a specific user by id")
    void getUserPositiveTest() {
        CreateReq rCP = getCreatePlayerParamsMap(Role.admin);
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeAndContentType(respCP);

        int id = respCP.as(CreateRes.class).getId();

        GetReq gCP = getRequest(id);
        Response respPS = send(gCP);
        AssertionsHelper.assertStatusCodeAndContentType(respPS);
        GetRes actualResp = respPS.as(GetRes.class);
        GetRes expectedResp = new GetRes(17, Gender.male, id, "User5", "1234567", Role.admin,"Use3");
        assertEquals(expectedResp, actualResp, "'Age' fields isn't equal");
    }

    @Test
    @DisplayName("Receive user with wrong id")
    @Description("Get of user with wrong id")
    void getUserNegativeTest(){
        GetReq gCP = getRequest(2322333);
        Response respPS = send(gCP);
        AssertionsHelper.assertStatusCodeBadRequestNegative(respPS);
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

    private GetReq getRequest(int playerId){
        GetReq getReq = new GetReq();
        getReq.setPlayerId(playerId);
        return getReq;
    }

    public Response send(RequestModel rq) {
        String CREATE_BY_SUPERVISOR = "/create/supervisor/";
        return baseService.given().queryParams((rq.toMap()))
                .get(CREATE_BY_SUPERVISOR);
    }

    public Response send(GetReq gr) {
        String GET_BY_SUPERVISOR = "/get";
        return baseService.given().body(gr)
                .post(GET_BY_SUPERVISOR);
    }
}
