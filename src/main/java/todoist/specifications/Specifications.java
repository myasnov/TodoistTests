package todoist.specifications;

import todoist.enums.Endpoints;
import todoist.utils.PropertyUtils;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Specifications {

    public static RequestSpecification requestSpecCommon() {
        return new RequestSpecBuilder()
                .setBaseUri(Endpoints.BASE_URL.getPath())
                .setBasePath(Endpoints.BASE_PATH.getPath())
                .addHeader("Authorization", "Bearer " + PropertyUtils.getValue("token"))
                .build();
    }

    public static RequestSpecification requestSpecPost() {
        return new RequestSpecBuilder()
                .setBaseUri(Endpoints.BASE_URL.getPath())
                .setBasePath(Endpoints.BASE_PATH.getPath())
                .setContentType(ContentType.JSON)
                .addHeader("X-Request-Id", "$(uuidgen)")
                .addHeader("Authorization", "Bearer " + PropertyUtils.getValue("token"))
                .build();
    }

    public static RequestSpecification requestSpecNotSupportedVersion() {
        return new RequestSpecBuilder()
                .setBaseUri(Endpoints.BASE_URL.getPath())
                .setBasePath(Endpoints.DEPRECATED_API_VERSION_PATH.getPath())
                .addHeader("Authorization", "Bearer " + PropertyUtils.getValue("token"))
                .build();
    }

    public static RequestSpecification requestSpecUnauthorized() {
        return new RequestSpecBuilder()
                .setBaseUri(Endpoints.BASE_URL.getPath())
                .setBasePath(Endpoints.BASE_PATH.getPath())
                .build();
    }
    public static ResponseSpecification responseSpecStatus200() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();
    }

    public static ResponseSpecification responseSpecStatus400() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.TEXT)
                .expectStatusCode(400)
                .build();
    }

    public static ResponseSpecification responseSpecStatus401() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.TEXT)
                .expectStatusCode(401)
                .build();
    }

    public static ResponseSpecification responseSpecStatus404() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.TEXT)
                .expectStatusCode(404)
                .build();
    }

    public static ResponseSpecification responseSpecStatus410() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.TEXT)
                .expectStatusCode(410)
                .build();
    }

    public static void setSpec(RequestSpecification requestSpec, ResponseSpecification responseSpec){
        RestAssured.requestSpecification = requestSpec;
        RestAssured.responseSpecification = responseSpec;
    }
}
