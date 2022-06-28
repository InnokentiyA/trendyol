package com.trendyol.services.book.exception;

public class InternalServerError extends RuntimeException {
    public InternalServerError(String errorMessage) {
        super(errorMessage);
    }
}
