package com.trendyol.services.book.controller;

import com.trendyol.services.book.dto.BookRequest;
import com.trendyol.services.book.dto.Book;
import com.trendyol.services.book.service.IBook;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class BookController {

    private final IBook bookService;

    @PutMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody @Valid BookRequest bookRequest) {
        final Book book = bookService.createBook(bookRequest);
        return new ResponseEntity<>(book, OK);
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> books = bookService.getBooks();
        return new ResponseEntity<>(books, OK);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> findBookById(@PathVariable(name = "bookId") Long bookId) {
        final Book book = bookService.getBookById(bookId);
        return new ResponseEntity<>(book, OK);
    }

    @DeleteMapping("/books")
    public ResponseEntity<Void> deleteBooks() {
        bookService.deleteBooks();
        return new ResponseEntity<>(OK);
    }

}
