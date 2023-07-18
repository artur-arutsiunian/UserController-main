package restservicetest.CreateTest;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.helpers.AssertionsHelper;
import restservice.BaseService;
import restservice.pojo.userCreate.*;
import restservicetest.BaseTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateSupervisorTest extends BaseTest {

    BaseService baseService = new BaseService();

    @Test
    @DisplayName("Check user creation")
    @Description("Check that user creates with all necessary fields and values")
    @Severity(value = SeverityLevel.BLOCKER)
    void createUserPositiveTest() {
        CreateReq rCP = getCreatePlayerParamsMap(Role.admin);
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeAndContentType(respCP);
        CreateResponseDto actualResp = new CreateResponseDto(respCP.as(CreateRes.class));
        CreateResponseDto expectedResp = new CreateResponseDto(List.of(new CreateResponseDto.Files(17, Gender.female.name(), "User5", "1234567", Role.admin.name(), "Use3")));
        assertEquals(expectedResp, actualResp, "Fields aren't equal");
    }

    @Test
    @DisplayName("Send wrong age field")
    @Description("Send wrong user age")
    void verifyingAgeNegativeTest() {
        CreateReq rCP = getCreatePlayerParamsMap("60");
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);
    }

    @Test
    @DisplayName(("Send wrong password field"))
    @Description("Send wrong user password")
    void verifyingPasswordNegativeTest() {
        CreateReq rCP = getCreatePlayerParamsMapIncorrectPassword("123456");
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);
    }

    @Test
    @DisplayName("Send 'user' role who can be created")
    @Description("Send 'user' role who can be created")
    void verifyingRoleUserPositiveTest(){
        CreateReq rCP = getCreatePlayerParamsMap(Role.user);
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeAndContentType(respCP);
        CreateResponseDto actualResp = new CreateResponseDto(respCP.as(CreateRes.class));
        CreateResponseDto expectedResp = new CreateResponseDto(List.of(new CreateResponseDto.Files(17, Gender.male.name(), "User5", "1234567", Role.user.name(), "Use3")));
        assertEquals(expectedResp, actualResp, "Fields aren't equal");
    }


    @Test
    @DisplayName("Send 'supervisor' role who can't be created")
    @Description("Send 'supervisor' role who can't be created")
    void verifyingRoleSupervisorNegativeTest(){
        CreateReq rCP = getCreatePlayerParamsMap(Role.supervisor);
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);
    }

    @Test
    @DisplayName("Send 'login' which was already used")
    @Description("Send 'login' field which was used before")
    void verifyingUniqueLoginFieldNegativeTest(){
        CreateReq rCP = getCreatePlayerParamsMapRepeatLogin("User5");
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);
    }

    @Test
    @DisplayName("Send 'screenName' which was already used")
    @Description("Send 'screenName' field which was used before")
    void verifyingUniqueScreenNameFieldNegative(){
        CreateReq rCP = getCreatePlayerParamsMapRepeatScreenName("Use3");
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);
    }

    @Test
    @DisplayName("Send another correct user gender")
    @Description("Send another correct option gender of user")
    void verifyingGenderFieldPositive(){
        CreateReq rCP = getCreatePlayerParamsMapGenderValidationPositive(Gender.female);
        Response respCP = send(rCP);
        AssertionsHelper.assertStatusCodeAndContentType(respCP);
        CreateResponseDto actualResp = new CreateResponseDto(respCP.as(CreateRes.class));
        CreateResponseDto expectedResp = new CreateResponseDto(List.of(new CreateResponseDto.Files(17, Gender.female.name(), "User5", "1234567", Role.admin.name(), "Use3")));
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

    private CreateReq getCreatePlayerParamsMap(String age){
        return getCreatePlayerParamsMap(age, Gender.male, "User5", "1234567", Role.admin,"Use3");
    }

    private CreateReq getCreatePlayerParamsMapIncorrectPassword(String password){
        return getCreatePlayerParamsMap("17", Gender.male, "User5", password, Role.admin,"Use3");
    }

    private CreateReq getCreatePlayerParamsMapRepeatLogin(String login){
        return getCreatePlayerParamsMap("17", Gender.male, login, "1234567", Role.admin,"Use3");
    }

    private CreateReq getCreatePlayerParamsMapRepeatScreenName(String screenName){
        return getCreatePlayerParamsMap("17", Gender.male, "User5", "1234567", Role.admin, screenName);
    }

    private CreateReq getCreatePlayerParamsMapGenderValidationPositive(Gender gender){
        return getCreatePlayerParamsMap("17", gender, "User5", "1234567", Role.admin, "Use3");
    }

    public Response send(RequestModel rq) {
        String CREATE_BY_SUPERVISOR = "/create/supervisor";
        return baseService.given().queryParams((rq.toMap()))
                .get(CREATE_BY_SUPERVISOR);
    }

}
