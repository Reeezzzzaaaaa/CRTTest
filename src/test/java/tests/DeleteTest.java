package tests;

import data.DataHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteTest {

    @Test
    void shouldSuccessDeleteBookTest() {

        given()
                .baseUri(DataHelper.getUri().getUri())
                .log().all()
                .when()
                .delete(DataHelper.getEndPoint().getEndPoint() + DataHelper.getEndPointWithId2().getEndPointWithId())
                .then()
                .log().all()
                .contentType(ContentType.JSON);

        given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .when()
                .get(DataHelper.getEndPoint().getEndPoint())
                .then()
                .statusCode(DataHelper.getStatusCode200().getStatusCode())
                .contentType(ContentType.JSON)
                .body(DataHelper.getFieldAll().getField(), hasSize(DataHelper.getIdBook1().getId()));

        JSONObject resultJson = new JSONObject();

        resultJson.put(DataHelper.getJSONName().getJsonField(), DataHelper.getNameBookId2().getName());
        resultJson.put(DataHelper.getJSONAuthor().getJsonField(), DataHelper.getAuthorBookId2().getAuthor());
        resultJson.put(DataHelper.getJSONYear().getJsonField(), DataHelper.getYearBookId2().getYear());
        resultJson.put(DataHelper.getJSONIsElectronicBook().getJsonField(), DataHelper.getIsElectronicBookTrue().isElectronicBook());

        given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .post(DataHelper.getEndPoint().getEndPoint());
    }

    @Test
    void shouldUnSuccessDeleteMoreExistingIdBookTest() {

        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .when()
                .delete(DataHelper.getEndPoint().getEndPoint() + DataHelper.getEndPointWithId1584().getEndPointWithId());

        assertEquals(DataHelper.getStatusCode404().getStatusCode(), response.getStatusCode());
        assertEquals(DataHelper.getNotFoundId().getText(), response.jsonPath().getString(DataHelper.getError().getText()));

    }

}
