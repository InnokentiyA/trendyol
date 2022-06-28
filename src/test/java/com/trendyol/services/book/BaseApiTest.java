package com.trendyol.services.book;

import com.trendyol.services.book.dto.Book;
import com.trendyol.services.book.dto.BookRequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {TestingApplication.class}
)
public abstract class BaseApiTest {

    protected final static long PORT = 4000;
    protected final static String BASE_PATH = "http://localhost:" + PORT;
    protected final static String BOOKS_PATH = "api/books";
    protected final static String GET_BOOK_BY_ID_PATH = BOOKS_PATH + "/%s";

    @LocalServerPort
    @Value("${server.port}")
    protected int serverPort;

    protected RequestSpecification request = new RequestSpecBuilder()
            .setBaseUri(BASE_PATH)
            .setContentType(ContentType.JSON)
            .build();

    protected Book addBook(BookRequest bookRequest) {
        return given()
                .spec(request)
                .basePath(BOOKS_PATH)
                .body(bookRequest)
                .when()
                .put()
                .then()
                .statusCode(OK.value())
                .extract().body().as(Book.class);
    }

    @AfterEach
    void clearData() {
        given()
                .spec(request)
                .basePath(BOOKS_PATH)
                .when()
                .delete()
                .then()
                .statusCode(OK.value());
    }
}
