package todoist.prerequest;

import io.restassured.path.json.JsonPath;
import todoist.specifications.Specifications;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JsonDataPreparation {
    public static JsonPath getActiveTestsAsJson(){
        Specifications.setSpec(Specifications.requestSpecCommon(), Specifications.responseSpecStatus200());
        return given()
                .when()
                .get()
                .then().log().all()
                .extract().body().jsonPath();
    }

    public static JsonPath getJsonFromFile(String fileName){
        return new JsonPath(new File("src/test/resources/" + fileName));
    }
}
