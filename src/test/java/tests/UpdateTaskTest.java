package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import todoist.helpers.TaskHelper;
import todoist.pojo.Task;
import todoist.prerequest.JsonDataPreparation;
import todoist.specifications.Specifications;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class UpdateTaskTest {

    @Test
    public void updateATaskTest() {
        JsonPath taskInfo = JsonDataPreparation.getActiveTestsAsJson();
        Specifications.setSpec(Specifications.requestSpecPost(), Specifications.responseSpecStatus200());
        Task updatedTask = given()
                .body(new HashMap<String, String>() {{
                    put("content", TaskHelper.generateRandomString());
                }})
                .when()
                .post("/" + taskInfo.get("[0].id"))
                .then().log().all()
                .extract().body().as(Task.class);

        Assert.assertEquals("Task id is incorrect", taskInfo.get("[0].id"), updatedTask.getId());
        Assert.assertEquals("Task url is incorrect", taskInfo.get("[0].url"), updatedTask.getUrl());
        Assert.assertEquals("Task is not active", taskInfo.get("[0].is_completed"), updatedTask.is_completed());
        Assert.assertEquals("Task due string is incorrect", taskInfo.get("[0].description"), updatedTask.getDescription());
        Assert.assertEquals("Task due string is incorrect", taskInfo.get("[0].priority"), updatedTask.getPriority());
        Assert.assertEquals("Task due string is incorrect", taskInfo.get("[0].order"), updatedTask.getOrder());
        Assert.assertNotEquals("Task name is incorrect", taskInfo.get("[0].content"), updatedTask.getContent());
    }

    @Test
    public void updateATaskUsingEmptyReqParamTest() {
        JsonPath taskInfo = JsonDataPreparation.getActiveTestsAsJson();
        Specifications.setSpec(Specifications.requestSpecPost(), Specifications.responseSpecStatus400());
        Response response = given()
                .body(new HashMap<String, String>() {{
                    put("content", "");
                }})
                .when()
                .post("/" + taskInfo.get("[0].id"))
                .then().log().all()
                .extract().response();

        Assert.assertEquals("Response body for updated test with empty req param is incorrect",
                "Invalid argument value", response.getBody().asString());
    }

    @Test
    public void updateATaskUsingEmptyBodyTest() {
        JsonPath taskInfo = JsonDataPreparation.getActiveTestsAsJson();
        Specifications.setSpec(Specifications.requestSpecPost(), Specifications.responseSpecStatus400());
        Response response = given()
                .when()
                .post("/" + taskInfo.get("[0].id"))
                .then().log().all()
                .extract().response();

        Assert.assertEquals("Response body for updated test with empty body is incorrect",
                "At least one of supported fields should be set and non-empty", response.getBody().asString());
    }
}
