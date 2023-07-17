package restservicetest.CreateTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.helpers.AssertionsHelper;
import restservice.BaseService;
import restservice.pojo.userCreate.*;
import restservicetest.BaseTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateAdminTest extends BaseTest{

    BaseService baseService = new BaseService();
    private final static String CREATE_BY_ADMIN = "/create/admin";

    @Test
    @DisplayName("Send wrong user role who can't be created")
    @Description("Send wrong type of role with which user can't be created")
    void verifyingRoleSupervisorNegativeTest() {
        CreateReq rCP = getCreatePlayerParamsMap(Role.supervisor);
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeForbiddenNegative(respCP);
    }

    @Test
    @DisplayName("Send correct role of user who can be created")
    @Description("Send another correct option role of user who can be created")
    void verifyingRoleAdminPositiveTest(){
        CreateReq rCP = getCreatePlayerParamsMap(Role.admin);
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeAndContentType(respCP);
        CreateResponseDto actualResp = new CreateResponseDto(respCP.as(CreateRes.class));
        CreateResponseDto expectedResp = new CreateResponseDto(List.of(new CreateResponseDto.Files(17, Gender.male.name(), "User5", "1234567", Role.admin.name(), "Use3")));
        assertEquals(expectedResp, actualResp, "Fields aren't equal");
    }

    @Test
    @DisplayName("Send correct role of user who can be created")
    @Description("Send another correct option role of user who can be created")
    void verifyingRoleUserPositiveTest(){
        CreateReq rCP = getCreatePlayerParamsMap(Role.user);
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeAndContentType(respCP);
        CreateResponseDto actualResp = new CreateResponseDto(respCP.as(CreateRes.class));
        CreateResponseDto expectedResp = new CreateResponseDto(List.of(new CreateResponseDto.Files(17, Gender.male.name(), "User5", "1234567", Role.user.name(), "Use3")));
        assertEquals(expectedResp, actualResp, "Fields aren't equal");
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
    public Response send(RequestModel rq) {
        return baseService.given().queryParams((rq.toMap()))
                .get(CREATE_BY_ADMIN);
    }
}
