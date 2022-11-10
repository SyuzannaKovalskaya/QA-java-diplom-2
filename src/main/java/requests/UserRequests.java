package requests;

import dto.DtoUser;
import dto.dtoResponse.DtoUserResponse;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserRequests {
    public static Response createUser(DtoUser user) {
        return given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post("/api/auth/register");
    }

    public static Response deleteUser(DtoUserResponse dtoUserResponse) {
        //String token = createUser(user).getHeader("authorization");
        return given().log().all()
                .header("Content-type", "application/json")
                .header("authorization", dtoUserResponse.getToken())
                .when()
                .delete("/api/auth/user");
    }

    public static Response loginUser(DtoUser user) {
        return given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post("/api/auth/login");

    }

    public static Response updateUserDataWithoutToken(DtoUser user) {
        return given().log().all()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .patch("/api/auth/user");
    }

    public static Response updateUserWithToken(DtoUserResponse dtoUserResponse, DtoUser dtoUser) {
        return given().log().all()
                .header("Content-type", "application/json")
                .header("authorization", dtoUserResponse.getToken())
                .body(dtoUser)
                .when()
                .patch("/api/auth/user");
    }
}
