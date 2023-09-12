package restservicetest.CreateTest;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import restservice.RequestService;
import restservice.helpers.AssertionsHelper;
import restservice.pojo.userCreate.request.CreateRequest;
import restservice.pojo.userCreate.response.CreateResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateSupervisorTest {

    private RequestService requestService = RequestService.getInstance();
    CreateRequest createReq = new CreateRequest.Builder()
            .buildAge("17")
            .buildGender("male")
            .buildLogin("user5")
            .buildPassword("1234567")
            .buildRole("user")
            .buildScreenName("Use3")
            .build();

    @Test
    @DisplayName("Check user creation")
    @Description("Check that user creates with all necessary fields and values")
    @Severity(value = SeverityLevel.BLOCKER)
    public void createUserPositiveTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("admin")
                .build();
        Response respCP = requestService.send(rCP, "supervisor");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);
        CreateResponse actualResp = respCP.as(CreateResponse.class);
        CreateResponse expectedResp = rCP.toCreateResponse();
        expectedResp.setId(respCP.jsonPath().get("id"));
        assertEquals(expectedResp, actualResp, "Fields aren't equal");
    }

    @Test
    @DisplayName("Send wrong age field")
    @Description("Send wrong user age")
    public void verifyingAgeNegativeTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildAge("60")
                .build();
        Response respCP = requestService.send(rCP, "supervisor");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);

        String responseBody = respCP.asString();
        Assertions.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @Test
    @DisplayName(("Send wrong password field"))
    @Description("Send wrong user password")
    public void verifyingPasswordNegativeTest() {
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildPassword("123456")
                .build();
        Response respCP = requestService.send(rCP, "supervisor");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);

        String responseBody = respCP.asString();
        Assertions.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @Test
    @DisplayName("Send 'user' role who can be created")
    @Description("Send 'user' role who can be created")
    public void verifyingRoleUserPositiveTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("user")
                .build();
        Response respCP = requestService.send(rCP, "supervisor");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);
        CreateResponse actualResp = respCP.as(CreateResponse.class);
        CreateResponse expectedResp = rCP.toCreateResponse();
        expectedResp.setId(respCP.jsonPath().get("id"));
        assertEquals(expectedResp, actualResp, "Fields aren't equal");
    }

    @Test
    @DisplayName("Send 'supervisor' role who can't be created")
    @Description("Send 'supervisor' role who can't be created")
    public void verifyingRoleSupervisorNegativeTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildRole("supervisor")
                .build();
        Response respCP = requestService.send(rCP, "supervisor");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);

        String responseBody = respCP.asString();
        Assertions.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @Test
    @DisplayName("Send 'login' which was already used")
    @Description("Send 'login' field which was used before")
    public void verifyingUniqueLoginFieldNegativeTest(){
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildLogin("user5")
                .build();
        Response respCP = requestService.send(rCP, "supervisor");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);

        String responseBody = respCP.asString();
        Assertions.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @Test
    @DisplayName("Send 'screenName' which was already used")
    @Description("Send 'screenName' field which was used before")
    public void verifyingUniqueScreenNameFieldNegative(){
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildScreenName("Use3")
                .build();
        Response respCP = requestService.send(rCP, "supervisor");
        AssertionsHelper.assertStatusCodeBadRequestNegative(respCP);

        String responseBody = respCP.asString();
        Assertions.assertTrue(responseBody.isEmpty(), "Response body should be empty.");
    }

    @Test
    @DisplayName("Send another correct user gender")
    @Description("Send another correct option gender of user")
    public void verifyingGenderFieldPositive(){
        CreateRequest rCP = new CreateRequest.Builder()
                .request(createReq)
                .buildGender("female")
                .build();
        Response respCP = requestService.send(rCP, "supervisor");
        AssertionsHelper.assertStatusCodeOKAndContentTypeOK(respCP);
        CreateResponse actualResp = respCP.as(CreateResponse.class);
        CreateResponse expectedResp = rCP.toCreateResponse();
        expectedResp.setId(respCP.jsonPath().get("id"));
        assertEquals(expectedResp, actualResp, "Fields aren't equal");
    }
}
