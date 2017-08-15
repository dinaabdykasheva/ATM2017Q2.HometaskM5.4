package test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import user.User;

import static io.restassured.RestAssured.given;


/**
 * Created by Dina_Abdykasheva on 8/14/2017.
 */
public class UsersRestTest {
    @BeforeTest
    public void initTest() {
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";

    }

    @Test
    public void checkStatusCode() {
        Response response = given().get("/users").andReturn();
        int actualStatusCode = response.getStatusCode();
        System.out.println(actualStatusCode);
        Assert.assertEquals(actualStatusCode, 200, "Request isn't successful");
    }

    @Test
    public void checkResponseHeader() {
        Response response = given().get("/users").andReturn();
        String valueOfContentTypeHeader = response.getHeader("content-type");
        Assert.assertTrue(valueOfContentTypeHeader.contains("application/json; charset=utf-8"), "Header isn't correct");
    }
    @Test
    public void checkResponseBody() {
        Response response = given().get("/users").andReturn();
        User[] users = response.as(User[].class);
        Assert.assertEquals(users.length, 10, "Users amount isn't 10");
    }
}
