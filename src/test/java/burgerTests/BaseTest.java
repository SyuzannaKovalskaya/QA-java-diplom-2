package burgerTests;

import dto.dtoResponse.DtoUserResponse;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import requests.UserRequests;

import static org.hamcrest.core.IsEqual.equalTo;

public class BaseTest {



    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
    }

}
