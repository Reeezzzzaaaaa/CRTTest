package tests;

import data.DataHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetTest {

    @Test
    void shouldSuccessGetAllBooksTest() {

        given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .when()
                .get(DataHelper.getEndPoint().getEndPoint())
                .then()
                .statusCode(DataHelper.getStatusCode200().getStatusCode())
                .contentType(ContentType.JSON)
                .body(DataHelper.getFieldAll().getField(), hasSize(DataHelper.getIdBook2().getId()));
    }

    @Test
    void shouldSuccessGetFirstBookTest() {

        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .when()
                .get(DataHelper.getEndPoint().getEndPoint() + DataHelper.getEndPointWithId1().getEndPointWithId());

        assertEquals(DataHelper.getStatusCode200().getStatusCode(), response.getStatusCode());
        assertEquals(DataHelper.getIdBook1().getId(), response.jsonPath().getInt(DataHelper.getFieldId().getField()));
    }

    @Test
    void shouldSuccessGetSecondBookTest() {

        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .when()
                .get(DataHelper.getEndPoint().getEndPoint() + DataHelper.getEndPointWithId2().getEndPointWithId());

        assertEquals(DataHelper.getStatusCode200().getStatusCode(), response.getStatusCode());
        assertEquals(DataHelper.getIdBook2().getId(), response.jsonPath().getInt(DataHelper.getFieldId().getField()));
    }

    @Test
    void shouldUnSuccessGetThirdBookTest() {

        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .when()
                .get(DataHelper.getEndPoint().getEndPoint() + DataHelper.getEndPointWithId3().getEndPointWithId());

        assertEquals(DataHelper.getStatusCode404().getStatusCode(), response.getStatusCode());
        assertEquals(DataHelper.getBookWithId3NotFound().getText(), response.jsonPath().getString(DataHelper.getError().getText()));
    }
}
