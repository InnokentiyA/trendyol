package com.trendyol.services.book.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice("com.trendyol.services.book")
public class ServiceExceptionHandler {

    @ExceptionHandler(value = {DuplicateBookException.class})
    protected ResponseEntity<BookServiceErrorResponse> handleDuplicateAuthorAndBookException(DuplicateBookException e) {
        var errorResponse = BookServiceErrorResponse.builder()
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(value = {InternalServerError.class})
    protected ResponseEntity<BookServiceErrorResponse> handleDuplicateAuthorAndBookException(InternalServerError e) {
        final String error = e.getMessage();
        var serviceErrorResponse = new BookServiceErrorResponse(error);
        return new ResponseEntity<>(serviceErrorResponse, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<BookServiceErrorResponse> handleServletRequestBindingException(MethodArgumentNotValidException e) {
        var errorResponse = BookServiceErrorResponse.builder()
                .message(getErrorMessages(e))
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
    }

    private String getErrorMessages(MethodArgumentNotValidException exception) {
        return exception.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
    }
}
