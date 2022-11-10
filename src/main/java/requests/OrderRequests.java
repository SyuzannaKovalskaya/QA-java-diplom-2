package requests;

import dto.DtoUser;
import dto.dtoResponse.DtoUserResponse;
import dto.ingredients.DtoIngredient;
import dto.order.DtoOrderRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

//Создание заказа:
//с авторизацией,+
//без авторизации,+
//с ингредиентами,
//без ингредиентов,
//с неверным хешем ингредиентов.
//Получение заказов конкретного пользователя:
//авторизованный пользователь,
//неавторизованный пользователь.


public class OrderRequests {
    public static Response createOrderWithAuthorization(DtoUserResponse dtoUserResponse, DtoOrderRequest dtoOrderRequest) {
        if (null != dtoOrderRequest) {
            return given().log().all()
                    .header("Content-type", "application/json")
                    .header("authorization", dtoUserResponse.getToken())
                    .and()
                    .body(dtoOrderRequest)
                    .when()
                    .post("/api/orders");
        }
        return given().log().all()
                .header("Content-type", "application/json")
                .header("authorization", dtoUserResponse.getToken())
                .when()
                .post("/api/orders");
    }

    public static Response createOrderWithoutAuthorization(DtoOrderRequest dtoOrderRequest) {
        if (null != dtoOrderRequest) {
            return given().log().all()
                    .header("Content-type", "application/json")
                    .and()
                    .body(dtoOrderRequest)
                    .when()
                    .post("/api/orders");
        }
        return given().log().all()
                .header("Content-type", "application/json")
                .when()
                .post("/api/orders");
    }

    public static Response getOrdersWithAuthorization(DtoUserResponse dtoUserResponse) {
        return given().log().all()
                .header("Content-type", "application/json")
                .header("authorization", dtoUserResponse.getToken())
                .when()
                .get("/api/orders");
    }

    public static Response getOrdersWithoutAuthorization() {
        return given().log().all()
                .header("Content-type", "application/json")
                .when()
                .get("/api/orders");
    }
}
