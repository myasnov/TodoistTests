package tests;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import todoist.enums.Endpoints;
import todoist.prerequest.DataPreparation;
import todoist.pojo.Task;
import todoist.specifications.Specifications;
import org.junit.Test;
import todoist.utils.PropertyUtils;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetTaskTest {

    @Test
    public void getActiveTasksTest() {
        Specifications.setSpec(Specifications.requestSpecCommon(), Specifications.responseSpecStatus200());
        List<Task> tasks = given()
                .when()
                .get()
                .then().log().all()
                .extract().body().jsonPath().getList(".", Task.class);

        SoftAssertions softAssertions = new SoftAssertions();

        tasks.forEach(task -> {
            softAssertions.assertThat(task.getId()).isNotNull();
            softAssertions.assertThat(task.is_completed()).isFalse();
            softAssertions.assertThat(task.getContent()).isNotEqualTo("");
            softAssertions.assertThat(task.getProject_id()).isNotNull();
            softAssertions.assertThat(task.getCreator_id()).isNotNull();
            softAssertions.assertThat(task.getCreated_at()).isNotNull();
            softAssertions.assertThat(task.getProject_id()).isNotNull();
            softAssertions.assertThat(task.getPriority()).isNotNull();
            softAssertions.assertThat(task.getOrder()).isNotNull();
            softAssertions.assertThat(task.getUrl()).isEqualTo(PropertyUtils.getValue("taskUrl") + task.getId());
        });

        softAssertions.assertAll();
    }

    @Test
    public void getAnActiveTaskTest() {
        JsonPath tasks = DataPreparation.getActiveTestsAsJson();
        Specifications.setSpec(Specifications.requestSpecCommon(), Specifications.responseSpecStatus200());
        given()
                .when()
                .get("/" + tasks.get("[0].id"))
                .then().log().all()
                .body("is_completed", equalTo(false))
                .body("", equalTo(tasks.get("[0]")));
    }

    @Test
    public void getTaskThatNotExistTest() {
        Specifications.setSpec(Specifications.requestSpecCommon(), Specifications.responseSpecStatus404());
        Response response = given().when().get(Endpoints.NON_EXISTENT_TASK.getPath());
        Assert.assertEquals("Body for task that not exist is incorrect",
                "Task not found", response.getBody().asString());
    }

    @Test
    public void getTaskWithInvalidIdTest() {
        Specifications.setSpec(Specifications.requestSpecCommon(), Specifications.responseSpecStatus400());
        Response response = given().get(Endpoints.INVALID_ID.getPath());
        Assert.assertEquals("Body for task with invalid ID is incorrect",
                "Invalid ID", response.getBody().asString());
    }
}