package burgerTests.order;

import burgerTests.BaseTest;
import dto.DtoUser;
import dto.dtoResponse.DtoUserResponse;
import dto.ingredients.DtoIngredient;
import dto.ingredients.DtoIngredients;
import dto.order.DtoOrderRequest;
import io.restassured.response.Response;
import org.junit.Test;
import requests.IngredientRequests;
import requests.OrderRequests;
import requests.UserRequests;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsEqual.equalTo;

public class GetOrderTest extends BaseTest {

    @Test
    public void getOrderListWithAuthorizationTest() {
        DtoUser user = new DtoUser();
        Response response = UserRequests.createUser(user);
        response.then().assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
        DtoUserResponse dtoUserResponse = response.as(DtoUserResponse.class);
        this.dtoUserResponse = dtoUserResponse;
        List<String> ingredients = new ArrayList<>();
        response = IngredientRequests.getIngredientsList();
        response.then().assertThat()
                .statusCode(200)
                .body("success", equalTo(true));

        DtoIngredients dtoIngredients = response.as(DtoIngredients.class);
        DtoIngredient dtoIngredient = dtoIngredients.getIngredientWithName("Флюоресцентная булка R2-D3");
        ingredients.add(dtoIngredient.get_id());
        ingredients.add(dtoIngredient.get_id());

        DtoOrderRequest dtoOrderRequest = new DtoOrderRequest();
        dtoOrderRequest.setIngredients(ingredients);

        OrderRequests.createOrderWithAuthorization(dtoUserResponse, dtoOrderRequest);

        response.then().assertThat()
                .statusCode(200)
                .body("success", equalTo(true));

        response = OrderRequests.getOrdersWithAuthorization(dtoUserResponse);
        response.then().assertThat()
                .statusCode(200)
                .body("success", equalTo(true));

    }

    @Test
    public void getOrderListWithoutAuthorizationTest() {
        Response response = OrderRequests.getOrdersWithoutAuthorization();
        response.then().assertThat()
                .statusCode(401)
                .body("success", equalTo(false))
                .body("message", equalTo("You should be authorised"));
    }
}
