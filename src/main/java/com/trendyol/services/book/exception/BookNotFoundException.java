package com.trendyol.services.book.exception;

import org.springframework.dao.DuplicateKeyException;

public class BookNotFoundException extends DuplicateKeyException {
    private static final String BOOK_NOT_FOUND_MESSAGE= "Book not found.";

    public BookNotFoundException() {
        super(BOOK_NOT_FOUND_MESSAGE);
    }
}
