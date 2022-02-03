import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TypesTest {
    public static final String API_URL_TYPES = "https://api.magicthegathering.io/v1/types";

    @Test
    void shouldReturnStatus200() {
        get(API_URL_TYPES + "?page=50&pageSize=50")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void shouldMatchJsonSchema() {
        get(API_URL_TYPES)
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("TypesSchema.json"));
    }

    @Test
    void shouldNotAcceptPostAsHttpMethod() {
        post(API_URL_TYPES)
                .then()
                .assertThat()
                .statusCode(404);
    }
}
