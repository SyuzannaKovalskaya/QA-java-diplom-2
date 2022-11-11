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

public class CreateOrderTest extends BaseTest {

    @Test
    public void createNewOrderWithAuthorizationAndWithIngredientsTest() {
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
    }

    @Test
    public void createNewOrderWithAuthorizationAndWithoutIngredientsTest() {
        DtoUser user = new DtoUser();
        Response response = UserRequests.createUser(user);
        response.then().assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
        DtoUserResponse dtoUserResponse = response.as(DtoUserResponse.class);
        this.dtoUserResponse = dtoUserResponse;

        OrderRequests.createOrderWithAuthorization(dtoUserResponse, null)
                .then().assertThat()
                .statusCode(400)
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    public void createNewOrderWithoutAuthorizationAndWithIngredientsTest() {
        List<String> ingredients = new ArrayList<>();
        Response response = IngredientRequests.getIngredientsList();
        response.then().assertThat()
                .statusCode(200)
                .body("success", equalTo(true));

        DtoIngredients dtoIngredients = response.as(DtoIngredients.class);
        DtoIngredient dtoIngredient = dtoIngredients.getIngredientWithName("Флюоресцентная булка R2-D3");
        ingredients.add(dtoIngredient.get_id());
        ingredients.add(dtoIngredient.get_id());

        DtoOrderRequest dtoOrderRequest = new DtoOrderRequest();
        dtoOrderRequest.setIngredients(ingredients);

        OrderRequests.createOrderWithoutAuthorization(dtoOrderRequest);

        response.then().assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test
    public void createNewOrderWithoutAuthorizationAndWithoutIngredientsTest() {
        OrderRequests.createOrderWithoutAuthorization(null)
                .then().assertThat()
                .statusCode(400)
                .body("success", equalTo(false))
                .body("message", equalTo("Ingredient ids must be provided"));
    }


    @Test
    public void createNewOrderWithAuthorizationAndWithInvalidIngredientsTest() {
        DtoUser user = new DtoUser();
        Response response = UserRequests.createUser(user);
        response.then().assertThat()
                .statusCode(200)
                .body("success", equalTo(true));
        DtoUserResponse dtoUserResponse = response.as(DtoUserResponse.class);
        this.dtoUserResponse = dtoUserResponse;
        List<String> ingredients = new ArrayList<>();
        ingredients.add("123");

        DtoOrderRequest dtoOrderRequest = new DtoOrderRequest();
        dtoOrderRequest.setIngredients(ingredients);

        OrderRequests.createOrderWithAuthorization(dtoUserResponse, dtoOrderRequest)
                .then().assertThat()
                .statusCode(500);
    }
}
