package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {

    private static final Faker faker = new Faker(new Locale("en"));

    DataHelper() {
    }

    public static Uri getUri() {
        return new Uri("http://localhost:5000/");
    }


    public static EndPoint getEndPoint() {
        return new EndPoint("api/books");
    }


    public static EndPointWithId getEndPointWithIdEmpty() {
        return new EndPointWithId("");
    }

    public static EndPointWithId getEndPointWithId1() {
        return new EndPointWithId("/1");
    }

    public static EndPointWithId getEndPointWithId2() {
        return new EndPointWithId("/2");
    }

    public static EndPointWithId getEndPointWithId3() {
        return new EndPointWithId("/3");
    }

    public static EndPointWithId getEndPointWithId1584() {
        return new EndPointWithId("/1534");
    }


    public static JSONField getJSONName() {
        return new JSONField("name");
    }

    public static JSONField getJSONAuthor() {
        return new JSONField("author");
    }

    public static JSONField getJSONYear() {
        return new JSONField("year");
    }

    public static JSONField getJSONIsElectronicBook() {
        return new JSONField("isElectronicBook");
    }

    public static JSONField getJSONId() {
        return new JSONField("id");
    }


    public static Name getNameBookHistory() {
        return new Name("Странная история доктора Джекила и мистера Хайда");
    }

    public static Name getNameBookEmpty() {
        return new Name("");
    }

    public static Name getNameBookId1() {
        return new Name("Чистый код");
    }

    public static Name getNameBookId2() {
        return new Name("Алгоритмы: построение и анализ");
    }

    public static Name getNameTolkin() {
        return new Name("Сильмариллион");
    }


    public static Author getAuthorBookEmpty() {
        return new Author("");
    }

    public static Author getAuthorBookRobert() {
        return new Author("Роберт Льюис Стивенсон");
    }

    public static Author getAuthorBookId1() {
        return new Author("Роберт Мартин");
    }

    public static Author getAuthorBookId2() {
        return new Author("Томас Кормен, Чарльз Лейзерсон");
    }

    public static Author getAuthorTolkin() {
        return new Author("Джон Рональд Руэл Толкин");
    }

    public static Author getAuthorPetr() {
        return new Author("Пётр");
    }

    public static Author getAuthorOtherSymbols() {
        return new Author("Петр(&^()1951");
    }


    public static Year getYearBookZero() {
        return new Year(0);
    }

    public static Year getYearBook1886() {
        return new Year(1886);
    }

    public static Year getYearBook2024() {
        return new Year(2024);
    }

    public static Year getYearBookNegative() {
        return new Year(-1);
    }

    public static Year getYearBookId1() {
        return new Year(1998);
    }

    public static Year getYearBookId2() {
        return new Year(1989);
    }

    public static Year getYearTolkin() {
        return new Year(1977);
    }


    public static IsElectronic getIsElectronicBookFalse() {
        return new IsElectronic(false);
    }

    public static IsElectronic getIsElectronicBookTrue() {
        return new IsElectronic(true);
    }


    public static Id getIdBook1() {
        return new Id(1);
    }

    public static Id getIdBook2() {
        return new Id(2);
    }

    public static Id getIdBook3() {
        return new Id(3);
    }

    public static Id getIdBook156() {
        return new Id(156);
    }


    public static StatusCode getStatusCode200() {
        return new StatusCode(200);
    }

    public static StatusCode getStatusCode201() {
        return new StatusCode(201);
    }

    public static StatusCode getStatusCode400() {
        return new StatusCode(400);
    }

    public static StatusCode getStatusCode404() {
        return new StatusCode(404);
    }


    public static Field getFieldName() {
        return new Field("book.name");
    }

    public static Field getFieldAuthor() {
        return new Field("book.author");
    }

    public static Field getFieldYear() {
        return new Field("book.year");
    }

    public static Field getFieldIsElectronicBook() {
        return new Field("book.isElectronicBook");
    }

    public static Field getFieldId() {
        return new Field("book.id");
    }

    public static Field getFieldAll() {
        return new Field("books");
    }

    public static Text getNotJSONFile() {
        return new Text("<name>Колобок</name>");
    }

    public static Text getError() {
        return new Text("error");
    }

    public static Text getBookWithId3NotFound() {
        return new Text("Book with id 3 not found");
    }

    public static Text getNameIsRequired() {
        return new Text("Name is required");
    }

    public static Text getAuthorIsRequired() {
        return new Text("Author is required");
    }

    public static Text getYearIsRequired() {
        return new Text("Year is required");
    }

    public static Text getIsElectronicBookIsRequired() {
        return new Text("IsElectronicBook is required");
    }

    public static Text getNotFoundId() {
        return new Text("Book with id 1534 not found");
    }


    @Value
    public static class JSONField {
        String jsonField;
    }

    @Value
    public static class Name {
        String name;
    }

    @Value
    public static class Author {
        String author;
    }

    @Value
    public static class Year {
        int year;
    }

    @Value
    public static class IsElectronic {
        boolean isElectronicBook;
    }

    @Value
    public static class Id {
        int id;
    }

    @Value
    public static class Uri {
        String uri;
    }

    @Value
    public static class EndPoint {
        String endPoint;
    }

    @Value
    public static class EndPointWithId {
        String endPointWithId;
    }

    @Value
    public static class StatusCode {
        int statusCode;
    }

    @Value
    public static class Field {
        String field;
    }

    @Value
    public static class Text {
        String text;
    }
}
