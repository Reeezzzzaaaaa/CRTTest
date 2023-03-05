package tests;

import data.DataHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PutTest {

    JSONObject resultJson = new JSONObject();

    @AfterEach
    void initialData() {

        resultJson.put(DataHelper.getJSONName().getJsonField(),DataHelper.getNameBookId1().getName());
        resultJson.put(DataHelper.getJSONAuthor().getJsonField(),DataHelper.getAuthorBookId1().getAuthor());
        resultJson.put(DataHelper.getJSONYear().getJsonField(),DataHelper.getYearBookId1().getYear());
        resultJson.put(DataHelper.getJSONIsElectronicBook().getJsonField(),DataHelper.getIsElectronicBookFalse().isElectronicBook());

        given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .put(DataHelper.getEndPoint().getEndPoint() + DataHelper.getEndPointWithId1().getEndPointWithId());
    }

    @Test
    void shouldSuccessPutFirstBookTest() {

        resultJson.put(DataHelper.getJSONName().getJsonField(), DataHelper.getNameTolkin().getName());
        resultJson.put(DataHelper.getJSONAuthor().getJsonField(), DataHelper.getAuthorTolkin().getAuthor());
        resultJson.put(DataHelper.getJSONYear().getJsonField(), DataHelper.getYearTolkin().getYear());
        resultJson.put(DataHelper.getJSONIsElectronicBook().getJsonField(), DataHelper.getIsElectronicBookTrue().isElectronicBook());
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .put(DataHelper.getEndPoint().getEndPoint() + DataHelper.getEndPointWithId1().getEndPointWithId());

        assertEquals(DataHelper.getStatusCode200().getStatusCode(), response.getStatusCode());
        assertEquals(DataHelper.getNameTolkin().getName(), response.jsonPath().getString(DataHelper.getFieldName().getField()));
        assertEquals(DataHelper.getAuthorTolkin().getAuthor(), response.jsonPath().getString(DataHelper.getFieldAuthor().getField()));
        assertEquals(DataHelper.getYearTolkin().getYear(), response.jsonPath().getInt(DataHelper.getFieldYear().getField()));
        assertEquals(DataHelper.getIsElectronicBookTrue().isElectronicBook(), response.jsonPath().getBoolean(DataHelper.getFieldIsElectronicBook().getField()));
        assertEquals(DataHelper.getIdBook1().getId(), response.jsonPath().getInt(DataHelper.getFieldId().getField()));

    }
    @Test
    void shouldSuccessPutWithOutNameBookTest() {

        resultJson.put(DataHelper.getJSONAuthor().getJsonField(), DataHelper.getAuthorTolkin().getAuthor());
        resultJson.put(DataHelper.getJSONYear().getJsonField(), DataHelper.getYearTolkin().getYear());
        resultJson.put(DataHelper.getJSONIsElectronicBook().getJsonField(), DataHelper.getIsElectronicBookTrue().isElectronicBook());
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .put(DataHelper.getEndPoint().getEndPoint() + DataHelper.getEndPointWithId1().getEndPointWithId());

        assertEquals(DataHelper.getStatusCode400().getStatusCode(), response.getStatusCode());
        assertEquals(DataHelper.getNameIsRequired().getText(), response.jsonPath().getString(DataHelper.getError().getText()));

    }
    @Test
    void shouldUnSuccessPutWithOutAuthorBookTest() {

        resultJson.put(DataHelper.getJSONName().getJsonField(), DataHelper.getNameTolkin().getName());
        resultJson.put(DataHelper.getJSONYear().getJsonField(), DataHelper.getYearTolkin().getYear());
        resultJson.put(DataHelper.getJSONIsElectronicBook().getJsonField(), DataHelper.getIsElectronicBookTrue().isElectronicBook());
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .put(DataHelper.getEndPoint().getEndPoint() + DataHelper.getEndPointWithId1().getEndPointWithId());

        assertEquals(DataHelper.getStatusCode400().getStatusCode(), response.getStatusCode());
        assertEquals(DataHelper.getAuthorIsRequired().getText(), response.jsonPath().getString(DataHelper.getError().getText()));
    }
    @Test
    void shouldUnSuccessPutWithOutYearBookTest() {

        resultJson.put(DataHelper.getJSONName().getJsonField(), DataHelper.getNameTolkin().getName());
        resultJson.put(DataHelper.getJSONAuthor().getJsonField(), DataHelper.getAuthorTolkin().getAuthor());
        resultJson.put(DataHelper.getJSONIsElectronicBook().getJsonField(), DataHelper.getIsElectronicBookTrue().isElectronicBook());
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .put(DataHelper.getEndPoint().getEndPoint() + DataHelper.getEndPointWithId1().getEndPointWithId());

        assertEquals(DataHelper.getStatusCode400().getStatusCode(), response.getStatusCode());
        assertEquals(DataHelper.getYearIsRequired().getText(), response.jsonPath().getString(DataHelper.getError().getText()));

    }
    @Test
    void shouldUnSuccessPutWithOutIsElectronicBookBookTest() {

        resultJson.put(DataHelper.getJSONName().getJsonField(), DataHelper.getNameTolkin().getName());
        resultJson.put(DataHelper.getJSONAuthor().getJsonField(), DataHelper.getAuthorTolkin().getAuthor());
        resultJson.put(DataHelper.getJSONYear().getJsonField(), DataHelper.getYearTolkin().getYear());
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .put(DataHelper.getEndPoint().getEndPoint() + DataHelper.getEndPointWithId1().getEndPointWithId());

        assertEquals(DataHelper.getStatusCode400().getStatusCode(), response.getStatusCode());
        assertEquals(DataHelper.getIsElectronicBookIsRequired().getText(), response.jsonPath().getString(DataHelper.getError().getText()));

    }
    @Test
    void shouldSuccessPutAuthorWithYOAuthorBookTest() {

        resultJson.put(DataHelper.getJSONName().getJsonField(), DataHelper.getNameBookId1().getName());
        resultJson.put(DataHelper.getJSONAuthor().getJsonField(), DataHelper.getAuthorPetr().getAuthor());
        resultJson.put(DataHelper.getJSONYear().getJsonField(), DataHelper.getYearBookId1().getYear());
        resultJson.put(DataHelper.getJSONIsElectronicBook().getJsonField(),DataHelper.getIsElectronicBookFalse().isElectronicBook());
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .put(DataHelper.getEndPoint().getEndPoint() + DataHelper.getEndPointWithId1().getEndPointWithId());

        assertEquals(DataHelper.getAuthorPetr().getAuthor(), response.jsonPath().getString(DataHelper.getFieldAuthor().getField()));

    }
    @Test
    void shouldUnSuccessPutAuthorWithOtherSymbolsAuthorBookTest() {

        resultJson.put(DataHelper.getJSONName().getJsonField(), DataHelper.getNameBookId1().getName());
        resultJson.put(DataHelper.getJSONAuthor().getJsonField(), DataHelper.getAuthorOtherSymbols().getAuthor());
        resultJson.put(DataHelper.getJSONYear().getJsonField(), DataHelper.getYearBookId1().getYear());
        resultJson.put(DataHelper.getJSONIsElectronicBook().getJsonField(),DataHelper.getIsElectronicBookFalse().isElectronicBook());
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .put(DataHelper.getEndPoint().getEndPoint() + DataHelper.getEndPointWithId1().getEndPointWithId());

        assertEquals(DataHelper.getAuthorIsRequired().getText(), response.jsonPath().getString(DataHelper.getError().getText()));
        assertEquals(DataHelper.getStatusCode400().getStatusCode(), response.getStatusCode());

    }
}
