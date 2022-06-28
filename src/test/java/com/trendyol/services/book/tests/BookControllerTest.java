package com.trendyol.services.book.tests;

import com.trendyol.services.book.BaseApiTest;
import com.trendyol.services.book.dto.Book;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.trendyol.services.book.BookUtils.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.OK;

public class BookControllerTest extends BaseApiTest {

    private static final Book WAR_AND_PEACE_BOOK = Book.builder()
            .id(1L)
            .author(AUTHOR)
            .title(WAR_AND_PEACE_TITLE)
            .build();
    private static final Book ANNA_KARENINA_BOOK = Book.builder()
            .id(2L)
            .author(AUTHOR)
            .title(ANNA_KARENINA_TITLE)
            .build();

    @Test
    void verifyGetBookById() {
        var book = addBook(ANNA_KARENINA_REQUEST);

        var response = given()
                .spec(request)
                .basePath(String.format(GET_BOOK_BY_ID_PATH, book.getId()))
                .when()
                .put()
                .then()
                .statusCode(OK.value())
                .extract().body().as(Book.class);

        assertThat(response).isNotNull();
        assertThat(response.getAuthor()).isEqualTo(AUTHOR);
        assertThat(response.getTitle()).isEqualTo(ANNA_KARENINA_TITLE);
        assertThat(response.getId()).isNotNull();
    }

    @Test
    void verifyGetBooks() {
        addBook(WAR_AND_PEACE_REQUEST);
        addBook(ANNA_KARENINA_REQUEST);

        List<Book> bookList = Arrays.asList(given()
                .spec(request)
                .basePath(BOOKS_PATH)
                .when()
                .get()
                .then()
                .statusCode(OK.value())
                .extract().body().as(Book[].class));

        assertThat(bookList).isNotEmpty();
        assertThat(bookList).containsExactlyInAnyOrder(WAR_AND_PEACE_BOOK, ANNA_KARENINA_BOOK);
    }

    @Test
    void verifyAddBook() {
        var response = addBook(ANNA_KARENINA_REQUEST);

        assertThat(response).isNotNull();
        assertThat(response.getAuthor()).isEqualTo(AUTHOR);
        assertThat(response.getTitle()).isEqualTo(ANNA_KARENINA_TITLE);
        assertThat(response.getId()).isNotNull();
    }

}
