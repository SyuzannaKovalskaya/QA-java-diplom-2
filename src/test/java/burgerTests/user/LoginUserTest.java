package burgerTests.user;

import burgerTests.BaseTest;
import dto.DtoUser;
import dto.dtoResponse.DtoUserResponse;
import io.restassured.response.Response;
import org.junit.Test;
import requests.UserRequests;

import static org.hamcrest.core.IsEqual.equalTo;

public class LoginUserTest extends BaseTest {

    @Test
    public void createAndLoginUserTest() {
        DtoUser user = new DtoUser();
        Response response = UserRequests.createUser(user);
        response.then().assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
        dtoUserResponse = response.as(DtoUserResponse.class);

        response = UserRequests.loginUser(user);
        response.then().assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test
    public void loginUserWithInvalidLoginTest() {
        DtoUser user = new DtoUser();
        Response response = UserRequests.createUser(user);
        response.then().assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
        dtoUserResponse = response.as(DtoUserResponse.class);

        user = new DtoUser("Alex@", user.getPassword());
        response = UserRequests.loginUser(user);
        response.then().assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }

    @Test
    public void loginUserWithInvalidPasswordTest() {
        DtoUser user = new DtoUser();
        Response response = UserRequests.createUser(user);
        response.then().assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
        dtoUserResponse = response.as(DtoUserResponse.class);

        user = new DtoUser(user.getEmail(), "");
        response = UserRequests.loginUser(user);
        response.then().assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("email or password are incorrect"));
    }
}
