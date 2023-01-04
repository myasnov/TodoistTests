package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import todoist.pojo.Task;
import todoist.prerequest.DataPreparation;
import todoist.specifications.Specifications;
import todoist.utils.PropertyUtils;

import static io.restassured.RestAssured.given;

public class CreateTaskTest {

    @Test
    public void createNewTaskTest(){
        JsonPath newTaskInfo = DataPreparation.getJsonFromFile("newTaskInfo.json");
        Specifications.setSpec(Specifications.requestSpecPost(), Specifications.responseSpecStatus200());

        Task newTask = given()
                .body(newTaskInfo.getMap(""))
                .when()
                .post("")
                .then().log().all()
                .extract().body().as(Task.class);

        Assert.assertNotNull("Task id is null", newTask.getId());
        Assert.assertEquals("Task url is incorrect", newTask.getUrl(), PropertyUtils.getValue("taskUrl") + newTask.getId());
        Assert.assertFalse("Task is not active", newTask.is_completed());
        Assert.assertEquals("Task name is incorrect", newTaskInfo.get("content"), newTask.getContent());
        Assert.assertEquals("Task due string is incorrect", newTaskInfo.get("due_string"), newTask.getDue().getString());
        Assert.assertEquals("Task due lang is incorrect", newTaskInfo.get("due_lang"), newTask.getDue().getLang());
        Assert.assertEquals("Task priority is incorrect", newTaskInfo.get("priority"), newTask.getPriority());
    }


    @Test
    public void createTaskWithMissingReqParamTest(){
        JsonPath invalidNewTaskInfo = DataPreparation.getJsonFromFile("invalidNewTaskInfo.json");
        Specifications.setSpec(Specifications.requestSpecPost(), Specifications.responseSpecStatus400());
        Response response = given()
                .body(invalidNewTaskInfo.getMap(""))
                .when()
                .post("")
                .then().log().all()
                .extract().response();

        Assert.assertEquals("Response body for new task with missing param is incorrect",
                "Required argument is missing", response.getBody().asString());
    }
}
