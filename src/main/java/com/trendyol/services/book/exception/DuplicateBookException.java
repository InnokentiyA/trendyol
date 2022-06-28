package com.trendyol.services.book.exception;

import org.springframework.dao.DuplicateKeyException;

public class DuplicateBookException extends DuplicateKeyException {
    private static final String DUBLICATE_BOOK_MESSAGE= "Book already exist.";

    public DuplicateBookException() {
        super(DUBLICATE_BOOK_MESSAGE);
    }
}
