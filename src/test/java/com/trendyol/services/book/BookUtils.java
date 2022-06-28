package com.trendyol.services.book;

import com.trendyol.services.book.dto.BookRequest;
import lombok.experimental.UtilityClass;


@UtilityClass
public class BookUtils {

    public static final String AUTHOR = "Leo Tolstoy";
    public static final String WAR_AND_PEACE_TITLE = "War and Peace";
    public static final String ANNA_KARENINA_TITLE = "Anna Karenina";

    public static final BookRequest WAR_AND_PEACE_REQUEST = BookRequest.builder()
            .author(AUTHOR)
            .title(WAR_AND_PEACE_TITLE)
            .build();

    public static final BookRequest ANNA_KARENINA_REQUEST = BookRequest.builder()
            .author(AUTHOR)
            .title(ANNA_KARENINA_TITLE)
            .build();

}
