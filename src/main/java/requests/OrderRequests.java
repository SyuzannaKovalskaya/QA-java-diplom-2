package requests;

import dto.dtoResponse.DtoUserResponse;
import dto.order.DtoOrderRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

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
