import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SupertypesTest {
    public static final String API_URL_SUPERTYPES = "https://api.magicthegathering.io/v1/supertypes";

    @Test
    void shouldReturnStatus200() {
        get(API_URL_SUPERTYPES)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void shouldMatchJsonSchema() {
        get(API_URL_SUPERTYPES)
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("SupertypesSchema.json"));
    }

    @Test
    void shouldNotAcceptPostAsHttpMethod() {
        post(API_URL_SUPERTYPES)
                .then()
                .assertThat()
                .statusCode(404);
    }
}
