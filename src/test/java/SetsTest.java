import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SetsTest {
    public static final String API_URL_SETS = "https://api.magicthegathering.io/v1/sets";

    @Test
    void shouldReturnStatus200() {
        get(API_URL_SETS + "?page=50&pageSize=50")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void shouldMatchJsonSchema() {
        get(API_URL_SETS + "?page=50&pageSize=50")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("SetsPagedSchema.json"));
    }

    @Test
    void shouldReturnStatus200WithIds() {
        get(API_URL_SETS + "/ktk")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void shouldMatchJsonSchemaWithIds() {
        get(API_URL_SETS + "/ktk")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("SetsIdSchema.json"));
    }

    @Test
    void shouldReturnStatus404ForNonExistingCard() {
        get(API_URL_SETS + "/0")
                .then()
                .assertThat()
                .statusCode(404);
    }

    @Test
    void shouldNotAcceptPostAsHttpMethod() {
        post(API_URL_SETS)
                .then()
                .assertThat()
                .statusCode(404);
    }
}
