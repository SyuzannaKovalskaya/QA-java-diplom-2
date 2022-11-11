package burgerTests;

import dto.dtoResponse.DtoUserResponse;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import requests.UserRequests;

import static org.hamcrest.core.IsEqual.equalTo;

public class BaseTest {
    public DtoUserResponse dtoUserResponse;

    @After
    public void afterTest() {
        if (null != dtoUserResponse) {
            UserRequests.deleteUser(dtoUserResponse)
                    .then().statusCode(202)
                    .body("success", equalTo(true))
                    .body("message", equalTo("User successfully removed"));
        }
    }


    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

}
