package restservicetest.CreateTest;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.RequestService;
import restservice.helpers.AssertionsHelper;
import restservice.pojo.userCreate.request.CreateRequest;
import restservice.pojo.userCreate.response.CreateResponse;
import restservice.pojo.userCreate.response.CreateResponseDto;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateSupervisorTest extends RequestService {
    @Test
    @DisplayName("Check user creation")
    @Description("Check that user creates with all necessary fields and values")
    @Severity(value = SeverityLevel.BLOCKER)
    void createUserPositiveTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("admin", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeAndContentType(respCP);
        CreateResponseDto actualResp = new CreateResponseDto(respCP.as(CreateResponse.class));
        CreateResponseDto expectedResp = new CreateResponseDto(List.of(new CreateResponseDto.Files(17, "male", "User5", "1234567", "user", "Use3")));
        assertEquals(expectedResp, actualResp, "Fields aren't equal");
    }

    @Test
    @DisplayName("Send wrong age field")
    @Description("Send wrong user age")
    void verifyingAgeNegativeTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .ageOnly("60", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);
    }

    @Test
    @DisplayName(("Send wrong password field"))
    @Description("Send wrong user password")
    void verifyingPasswordNegativeTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .passwordOnly("123456", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);
    }

    @Test
    @DisplayName("Send 'user' role who can be created")
    @Description("Send 'user' role who can be created")
    void verifyingRoleUserPositiveTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("user", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeAndContentType(respCP);
        CreateResponseDto actualResp = new CreateResponseDto(respCP.as(CreateResponse.class));
        CreateResponseDto expectedResp = new CreateResponseDto(List.of(new CreateResponseDto.Files(17, "male", "User5", "1234567", "user", "Use3")));
        assertEquals(expectedResp, actualResp, "Fields aren't equal");
    }

    @Test
    @DisplayName("Send 'supervisor' role who can't be created")
    @Description("Send 'supervisor' role who can't be created")
    void verifyingRoleSupervisorNegativeTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("supervisor", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);
    }

    @Test
    @DisplayName("Send 'login' which was already used")
    @Description("Send 'login' field which was used before")
    void verifyingUniqueLoginFieldNegativeTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .loginOnly("user5", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);
    }

    @Test
    @DisplayName("Send 'screenName' which was already used")
    @Description("Send 'screenName' field which was used before")
    void verifyingUniqueScreenNameFieldNegative(){
        CreateRequest rCP = new CreateRequest.Builder()
                .screenNameOnly("Use3", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);
    }

    @Test
    @DisplayName("Send another correct user gender")
    @Description("Send another correct option gender of user")
    void verifyingGenderFieldPositive(){
        CreateRequest rCP = new CreateRequest.Builder()
                .genderOnly("female", createReq)
                .build();
        Response respCP = send(rCP, "/create/supervisor");
        AssertionsHelper.assertStatusCodeAndContentType(respCP);
        CreateResponseDto actualResp = new CreateResponseDto(respCP.as(CreateResponse.class));
        CreateResponseDto expectedResp = new CreateResponseDto(List.of(new CreateResponseDto.Files(17, "female", "User5", "1234567", "user", "Use3")));
        assertEquals(expectedResp, actualResp, "Fields aren't equal");
    }

    CreateRequest createReq = new CreateRequest.Builder()
            .age("17")
            .gender("male")
            .login("User5")
            .password("1234567")
            .role("user")
            .screenName("Use3")
            .build();
}
