package tests;

import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import todoist.specifications.Specifications;

import static io.restassured.RestAssured.given;

public class UnauthorizedRequestTest {

    @Test
    public void unauthorizedRequestTest(){
        Specifications.setSpec(Specifications.requestSpecUnauthorized(), Specifications.responseSpecStatus401());
        Response response = given().get();
        Assert.assertEquals("Response body for unauthorized request is incorrect",
                "Forbidden", response.getBody().asString());
    }
}
