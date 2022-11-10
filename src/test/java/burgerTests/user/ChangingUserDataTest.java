package burgerTests.user;

//Изменение данных пользователя:
//с авторизацией,
//без авторизации,
//Для обеих ситуаций нужно проверить, что любое поле можно изменить.
//Для неавторизованного пользователя — ещё и то, что система вернёт ошибку.

import dto.DtoUser;
import dto.dtoResponse.DtoUserResponse;
import help.GenerateUser;
import io.restassured.response.Response;
import org.junit.Test;
import requests.UserRequests;

import static org.hamcrest.core.IsEqual.equalTo;

public class ChangingUserDataTest extends BaseUserTest {
    @Test
    public void changeUserDataWithoutAuthorizationTest() {
        DtoUser user = new DtoUser();
        dtoUserResponse = UserRequests.createUser(user).as(DtoUserResponse.class);

        Response response = UserRequests.updateUserDataWithoutToken(user);
        response.then().assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }

    @Test
    public void changeUserDataWithAuthorizationTest() {
        DtoUser user = new DtoUser();
        Response response = UserRequests.createUser(user);
        DtoUserResponse dtoUserResponse = response.as(DtoUserResponse.class);
        this.dtoUserResponse = dtoUserResponse;

        DtoUser userEmail = new DtoUser(GenerateUser.generateEmail(), user.getPassword(), user.getName());
        UserRequests.updateUserWithToken(dtoUserResponse, userEmail)
                .then()
                .statusCode(200)
                .body("success", equalTo(true))
                .body("user.email", equalTo(userEmail.getEmail()));
    }
}