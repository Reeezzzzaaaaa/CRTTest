package tests;

import data.DataHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostTest {

    JSONObject resultJson = new JSONObject();

    @AfterEach
    void initialData() {

        given()
                .baseUri(DataHelper.getUri().getUri())
                .when()
                .delete(DataHelper.getEndPoint().getEndPoint() + DataHelper.getEndPointWithId3().getEndPointWithId());
    }

    @Test
    void shouldSuccessPostOnlyNameBookTest() {


        resultJson.put(DataHelper.getJSONName().getJsonField(), DataHelper.getNameBookHistory().getName());
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .post(DataHelper.getEndPoint().getEndPoint());

        assertEquals(DataHelper.getStatusCode201().getStatusCode(), response.getStatusCode());
        assertEquals(DataHelper.getNameBookHistory().getName(), response.jsonPath().getString(DataHelper.getFieldName().getField()));
        assertEquals(DataHelper.getAuthorBookEmpty().getAuthor(), response.jsonPath().getString(DataHelper.getFieldAuthor().getField()));
        assertEquals(DataHelper.getYearBookZero().getYear(), response.jsonPath().getInt(DataHelper.getFieldYear().getField()));
        assertEquals(DataHelper.getIsElectronicBookFalse().isElectronicBook(), response.jsonPath().getBoolean(DataHelper.getFieldIsElectronicBook().getField()));
        assertEquals(DataHelper.getIdBook3().getId(), response.jsonPath().getInt(DataHelper.getFieldId().getField()));
    }

    @Test
    void shouldUnSuccessPostWithOutJSONTest() {
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(DataHelper.getNotJSONFile().getText())
                .when()
                .post(DataHelper.getEndPoint().getEndPoint());

        assertEquals(DataHelper.getStatusCode400().getStatusCode(), response.getStatusCode());
    }

    @Test
    void shouldUnSuccessPostWithEmptyFieldNameBookTest() {

        resultJson.put(DataHelper.getJSONName().getJsonField(), DataHelper.getNameBookEmpty().getName());
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .post(DataHelper.getEndPoint().getEndPoint());


        assertEquals(DataHelper.getStatusCode400().getStatusCode(), response.getStatusCode());
    }

    @Test
    void shouldSuccessPostNameWithAllFieldBookTest() {

        resultJson.put(DataHelper.getJSONName().getJsonField(), DataHelper.getNameBookHistory().getName());
        resultJson.put(DataHelper.getJSONAuthor().getJsonField(), DataHelper.getAuthorBookRobert().getAuthor());
        resultJson.put(DataHelper.getJSONYear().getJsonField(), DataHelper.getYearBook1886().getYear());
        resultJson.put(DataHelper.getJSONIsElectronicBook().getJsonField(), DataHelper.getIsElectronicBookTrue().isElectronicBook());
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .post(DataHelper.getEndPoint().getEndPoint());

        assertEquals(DataHelper.getStatusCode201().getStatusCode(), response.getStatusCode());
        assertEquals(DataHelper.getNameBookHistory().getName(), response.jsonPath().getString(DataHelper.getFieldName().getField()));
        assertEquals(DataHelper.getAuthorBookRobert().getAuthor(), response.jsonPath().getString(DataHelper.getFieldAuthor().getField()));
        assertEquals(DataHelper.getYearBook1886().getYear(), response.jsonPath().getInt(DataHelper.getFieldYear().getField()));
        assertEquals(DataHelper.getIsElectronicBookTrue().isElectronicBook(), response.jsonPath().getBoolean(DataHelper.getFieldIsElectronicBook().getField()));
        assertEquals(DataHelper.getIdBook3().getId(), response.jsonPath().getInt(DataHelper.getFieldId().getField()));

    }

    @Test
    void shouldUnSuccessPostNameWithFutureYearBookTest() {

        resultJson.put(DataHelper.getJSONName().getJsonField(), DataHelper.getNameBookHistory().getName());
        resultJson.put(DataHelper.getJSONYear().getJsonField(), DataHelper.getYearBook2024().getYear());
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .post(DataHelper.getEndPoint().getEndPoint());

        assertEquals(DataHelper.getStatusCode400().getStatusCode(), response.getStatusCode());
    }

    @Test
    void shouldUnSuccessPostNameWithNegativeValueYearBookTest() {
        JSONObject resultJson = new JSONObject();
        resultJson.put(DataHelper.getJSONName().getJsonField(), DataHelper.getNameBookHistory().getName());
        resultJson.put(DataHelper.getJSONYear().getJsonField(), DataHelper.getYearBookNegative().getYear());
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .post(DataHelper.getEndPoint().getEndPoint());

        assertEquals(DataHelper.getStatusCode400().getStatusCode(), response.getStatusCode());
    }

    @Test
    void shouldUnSuccessPostWithOutNameBookTest() {

        resultJson.put(DataHelper.getJSONAuthor().getJsonField(), DataHelper.getAuthorBookRobert().getAuthor());
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .post(DataHelper.getEndPoint().getEndPoint());

        assertEquals(DataHelper.getStatusCode400().getStatusCode(), response.getStatusCode());
        assertEquals(DataHelper.getNameIsRequired().getText(), response.jsonPath().getString(DataHelper.getError().getText()));

    }

    @Test
    void shouldSuccessPostWithWrongIdBookTest() {

        resultJson.put(DataHelper.getJSONName().getJsonField(), DataHelper.getNameBookHistory().getName());
        resultJson.put(DataHelper.getJSONId().getJsonField(), DataHelper.getIdBook1().getId());
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .post(DataHelper.getEndPoint().getEndPoint());

        assertEquals(DataHelper.getStatusCode201().getStatusCode(), response.getStatusCode());
        assertEquals(DataHelper.getIdBook3().getId(), response.jsonPath().getInt(DataHelper.getFieldId().getField()));

    }

    @Test
    void shouldSuccessPostWithWrongIdMoreExistingBookTest() {

        resultJson.put(DataHelper.getJSONName().getJsonField(), DataHelper.getNameBookHistory().getName());
        resultJson.put(DataHelper.getJSONId().getJsonField(), DataHelper.getIdBook156().getId());
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .post(DataHelper.getEndPoint().getEndPoint());

        assertEquals(DataHelper.getStatusCode201().getStatusCode(), response.getStatusCode());
        assertEquals(DataHelper.getIdBook3().getId(), response.jsonPath().getInt(DataHelper.getFieldId().getField()));

    }

    @Test
    void shouldSuccessPostAuthorWithYOAuthorBookTest() {

        resultJson.put(DataHelper.getJSONName().getJsonField(), DataHelper.getNameBookId1().getName());
        resultJson.put(DataHelper.getJSONAuthor().getJsonField(), DataHelper.getAuthorPetr().getAuthor());
        resultJson.put(DataHelper.getJSONYear().getJsonField(), DataHelper.getYearBookId1().getYear());
        resultJson.put(DataHelper.getJSONIsElectronicBook().getJsonField(), DataHelper.getIsElectronicBookFalse().isElectronicBook());
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .post(DataHelper.getEndPoint().getEndPoint());

        assertEquals(DataHelper.getStatusCode201().getStatusCode(), response.getStatusCode());
        assertEquals(DataHelper.getAuthorPetr().getAuthor(), response.jsonPath().getString(DataHelper.getFieldAuthor().getField()));

    }

    @Test
    void shouldSuccessPostAuthorWithOtherSymbolsAuthorBookTest() {

        resultJson.put(DataHelper.getJSONName().getJsonField(), DataHelper.getNameBookId1().getName());
        resultJson.put(DataHelper.getJSONAuthor().getJsonField(), DataHelper.getAuthorOtherSymbols().getAuthor());
        resultJson.put(DataHelper.getJSONYear().getJsonField(), DataHelper.getYearBookId1().getYear());
        resultJson.put(DataHelper.getJSONIsElectronicBook().getJsonField(), DataHelper.getIsElectronicBookFalse().isElectronicBook());
        Response response = given()
                .baseUri(DataHelper.getUri().getUri())
                .contentType(ContentType.JSON)
                .body(resultJson.toString())
                .when()
                .post(DataHelper.getEndPoint().getEndPoint());

        assertEquals(DataHelper.getAuthorIsRequired().getText(), response.jsonPath().getString(DataHelper.getError().getText()));

    }
}
