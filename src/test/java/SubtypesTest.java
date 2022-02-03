import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SubtypesTest {
    public static final String API_URL_SUBTYPES = "https://api.magicthegathering.io/v1/subtypes";

    @Test
    void shouldReturnStatus200() {
        get(API_URL_SUBTYPES)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void shouldMatchJsonSchema() {
        get(API_URL_SUBTYPES)
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("SubtypesSchema.json"));
    }

    @Test
    void shouldNotAcceptPostAsHttpMethod() {
        post(API_URL_SUBTYPES)
                .then()
                .assertThat()
                .statusCode(404);
    }
}
