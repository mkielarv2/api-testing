import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class FormatsTest {
    public static final String API_URL_FORMATS = "https://api.magicthegathering.io/v1/formats";

    @Test
    void shouldReturnStatus200() {
        get(API_URL_FORMATS)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void shouldMatchJsonSchema() {
        get(API_URL_FORMATS)
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("FormatsSchema.json"));
    }

    @Test
    void shouldNotAcceptPostAsHttpMethod() {
        post(API_URL_FORMATS)
                .then()
                .assertThat()
                .statusCode(404);
    }
}
