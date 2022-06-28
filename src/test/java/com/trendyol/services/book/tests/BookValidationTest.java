package com.trendyol.services.book.tests;

import com.trendyol.services.book.BaseApiTest;
import com.trendyol.services.book.dto.Book;
import com.trendyol.services.book.dto.BookRequest;
import com.trendyol.services.book.exception.BookServiceErrorResponse;
import com.trendyol.services.book.BookUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static com.trendyol.services.book.BookUtils.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.*;

public class BookValidationTest extends BaseApiTest {

    @Test
    void verifyDuplicateBookException() {
        addBook(WAR_AND_PEACE_REQUEST);

        var serviceErrorResponse = given()
                .spec(request)
                .basePath(BOOKS_PATH)
                .body(WAR_AND_PEACE_REQUEST)
                .when()
                .put()
                .then().statusCode(CONFLICT.value())
                .extract().as(BookServiceErrorResponse.class);

        assertThat(serviceErrorResponse).isNotNull();
        assertThat(serviceErrorResponse.getMessage()).isEqualTo("Book already exist.");
    }

    @Test
    void verifyTitleIsRequiredField() {
        var bookRequest = BookRequest.builder().author(AUTHOR).build();

        BookServiceErrorResponse serviceErrorResponse = given()
                .spec(request)
                .basePath(BOOKS_PATH)
                .body(bookRequest)
                .when()
                .put()
                .then()
                .statusCode(BAD_REQUEST.value())
                .extract()
                .as(BookServiceErrorResponse.class);

        assertThat(serviceErrorResponse).extracting(BookServiceErrorResponse::getMessage).isEqualTo("Field 'title' cannot be empty");
    }

    @Test
    void verifyAuthorIsRequiredField() {
        var bookRequest = BookRequest.builder().title(WAR_AND_PEACE_TITLE).build();

        BookServiceErrorResponse serviceErrorResponse = given()
                .spec(request)
                .basePath(BOOKS_PATH)
                .body(bookRequest)
                .when()
                .put()
                .then()
                .statusCode(BAD_REQUEST.value())
                .extract().as(BookServiceErrorResponse.class);

        assertThat(serviceErrorResponse).isNotNull();
        assertThat(serviceErrorResponse.getMessage()).isEqualTo("Field 'author' cannot be empty");
    }

}
