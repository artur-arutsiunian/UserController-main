package restservicetest.CreateTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.helpers.AssertionsHelper;
import restservice.BaseService;
import restservice.pojo.userCreate.CreateReq;
import restservice.pojo.userCreate.Gender;
import restservice.pojo.userCreate.RequestModel;
import restservice.pojo.userCreate.Role;
import restservicetest.BaseTest;

public class CreateUserTest extends BaseTest {
    BaseService baseService = new BaseService();
    private final static String CREATE_BY_USER = "/create/user";

    @Test
    @DisplayName("Send wrong user role who can't be created")
    @Description("Send wrong type of role with which user can't be created")
    void verifyingRoleSupervisorNegativeTest(){
        CreateReq rCP = getCreatePlayerParamsMap(Role.supervisor);
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeForbiddenNegative(respCP);
    }

    @Test
    @DisplayName("Send wrong user role who can't be created")
    @Description("Send wrong type of role with which user can't be created")
    void verifyingRoleAdminNegativeTest(){
        CreateReq rCP = getCreatePlayerParamsMap(Role.admin);
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeForbiddenNegative(respCP);
    }

    @Test
    @DisplayName("Send wrong user role who can't be created")
    @Description("Send wrong type of role with which user can't be created")
    void verifyingRoleUserNegativeTest(){
        CreateReq rCP = getCreatePlayerParamsMap(Role.user);
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeForbiddenNegative(respCP);
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
    public Response send(RequestModel rq) {
        return baseService.given().queryParams((rq.toMap()))
                .get(CREATE_BY_USER);
    }
}
