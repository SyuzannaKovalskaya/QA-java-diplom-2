package burgerTests.user;

import burgerTests.BaseTest;
import dto.dtoResponse.DtoUserResponse;
import org.junit.After;
import requests.UserRequests;

import static org.hamcrest.core.IsEqual.equalTo;

public class BaseUserTest extends BaseTest {
    public DtoUserResponse dtoUserResponse;

    @After
    public void afterTest() {
        if (null != dtoUserResponse.getToken()) {
            UserRequests.deleteUser(dtoUserResponse)
                    .then().statusCode(202)
                    .body("success", equalTo(true))
                    .body("message", equalTo("User successfully removed"));
        }
    }
}
