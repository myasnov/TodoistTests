package tests;

import todoist.specifications.Specifications;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PreviousApiVersionTest {

    @Test
    public void v1IsNoLongerSupportedTest() {
        Specifications.setSpec(Specifications.requestSpecNotSupportedVersion(), Specifications.responseSpecStatus410());
        Response response = given().get();
        Assert.assertEquals("Response body for deprecated API version request is incorrect",
                "Gone", response.getBody().asString());
    }
}
