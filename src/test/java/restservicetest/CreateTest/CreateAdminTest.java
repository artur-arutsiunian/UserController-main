package restservicetest.CreateTest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.helpers.AssertionsHelper;
import restservice.RequestService;
import restservice.pojo.userCreate.request.CreateRequest;
import restservice.pojo.userCreate.response.CreateResponse;
import restservice.pojo.userCreate.response.CreateResponseDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateAdminTest extends RequestService {

    @Test
    @DisplayName("Send wrong user role who can't be created")
    @Description("Send wrong type of role with which user can't be created")
    void verifyingRoleSupervisorNegativeTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("supervisor", createReq)
                .build();
        Response respCP = send(rCP, "/create/admin");
        AssertionsHelper.assertStatusCodeForbiddenNegative(respCP);
    }

    @Test
    @DisplayName("Send correct role of user who can be created")
    @Description("Send another correct option role of user who can be created")
    void verifyingRoleAdminPositiveTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("admin", createReq)
                .build();
        Response respCP = send(rCP, "/create/admin");
        AssertionsHelper.assertStatusCodeAndContentType(respCP);
        CreateResponseDto actualResp = new CreateResponseDto(respCP.as(CreateResponse.class));
        CreateResponseDto expectedResp = new CreateResponseDto(List.of(new CreateResponseDto.Files(17, "male", "User5", "1234567", "admin", "Use3")));
        assertEquals(expectedResp, actualResp, "Fields aren't equal");
    }

    @Test
    @DisplayName("Send correct role of user who can be created")
    @Description("Send another correct option role of user who can be created")
    void verifyingRoleUserPositiveTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .roleOnly("user", createReq)
                .build();
        Response respCP = send(rCP, "/create/admin");
        AssertionsHelper.assertStatusCodeAndContentType(respCP);
        CreateResponseDto actualResp = new CreateResponseDto(respCP.as(CreateResponse.class));
        CreateResponseDto expectedResp = new CreateResponseDto(List.of(new CreateResponseDto.Files(17, "male", "User5", "1234567", "user", "Use3")));
        assertEquals(expectedResp, actualResp, "Fields aren't equal");
    }

        CreateRequest createReq = new CreateRequest.Builder()
                .age("17")
                .gender("male")
                .login("user5")
                .password("1234567")
                .screenName("Use3")
                .build();
    }
