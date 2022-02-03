import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CardsTest {
    public static final String API_URL_CARDS = "https://api.magicthegathering.io/v1/cards";

    @Test
    void shouldReturnStatus200ForCardsEndpoint() {
        get(API_URL_CARDS + "?page=50&pageSize=50")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void shouldMatchJsonSchemaForCardsEndpoint() {
        get(API_URL_CARDS + "?page=50&pageSize=50")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("CardsPagedSchema.json"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "e60540b7-8445-53d1-bbec-1e60339e9967",
            "4c54ec0c-e9c5-5425-a827-5262128675dc",
            "6a99851f-6e0d-5720-af9f-252117baf0a1"
    })
    void shouldReturnStatus200ForCardsEndpointWithIds(String id) {
        get(API_URL_CARDS + "/" + id)
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void shouldMatchJsonSchemaForCardsEndpointWithIds() {
        get(API_URL_CARDS + "/386616")
                .then()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("CardsIdSchema.json"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "0",
            "a",
            "-1"
    })
    void shouldReturnStatus404ForNonExistingCard(String id) {
        get(API_URL_CARDS + "/" + id)
                .then()
                .assertThat()
                .statusCode(404);
    }

    @Test
    void shouldContainPaginationHeaders() {
        get(API_URL_CARDS + "?page=50&pageSize=50")
                .then()
                .assertThat()
                .header("page-size", is(notNullValue()))
                .and()
                .header("count", is(notNullValue()))
                .and()
                .header("total-count", is(notNullValue()));
    }

    @Test
    void shouldNotAcceptPostAsHttpMethod() {
        post(API_URL_CARDS)
                .then()
                .assertThat()
                .statusCode(404);
    }

}
