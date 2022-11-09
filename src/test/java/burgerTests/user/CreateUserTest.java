package burgerTests.user;

import burgerTests.BaseTest;
import dto.DtoUser;
import dto.dtoResponse.DtoUserResponse;
import io.restassured.response.Response;
import org.junit.Test;
import requests.UserRequests;

import static org.hamcrest.core.IsEqual.equalTo;

public class CreateUserTest extends BaseUserTest {

    //    @Test
//    public void createNewUniqueUserTest() {
//        String email = String.format("Alex%d", ((int) (Math.random() * (9999 - 1111) + 1111))) + "@gmail.com";
//        String password = "123456";
//        DtoUser user = new DtoUser(email, password, "Bot");
//        Response response = UserRequests.createUser(user);
//        response.then().assertThat()
//                .statusCode(200)
//                .body("success", equalTo(true));
//    }

    @Test
    public void createAndDeleteNewUniqueUserTest() {
        DtoUser user = new DtoUser();
        Response response = UserRequests.createUser(user);
        dtoUserResponse = response.as(DtoUserResponse.class);


    }

    @Test
    public void createUserWhoIsAlreadyRegistered() {
        DtoUser user = new DtoUser();
        Response response = UserRequests.createUser(user);
        response.then().assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
        dtoUserResponse = response.as(DtoUserResponse.class);

        response = UserRequests.createUser(user);
        response.then().assertThat()
                .statusCode(403)
                .and()
                .body("success", equalTo(false))
                .body(" message", equalTo("User already exists"));
    }

    @Test
    public void createUserWithoutEmail() {
        DtoUser user = new DtoUser("", "123456", "Alex");
        Response response = UserRequests.createUser(user);
        response.then().assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    public void createUserWithoutPassword() {
        DtoUser user = new DtoUser("Alex4455@gmail.com", "", "Alex");
        Response response = UserRequests.createUser(user);
        response.then().assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }

    @Test
    public void createUserWithoutName() {
        DtoUser user = new DtoUser("Alex4455@gmail.com", "123456", "");
        Response response = UserRequests.createUser(user);
        response.then().assertThat()
                .statusCode(403)
                .body("success", equalTo(false))
                .body("message", equalTo("Email, password and name are required fields"));
    }


}