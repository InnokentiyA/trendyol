package com.trendyol.services.book.service;

import com.trendyol.services.book.dto.BookRequest;
import com.trendyol.services.book.dto.Book;

import java.util.List;


public interface IBook {

    Book getBookById(Long bookId);

    Book createBook(BookRequest authorAndBookDto);

    List<Book> getBooks();

    void deleteBooks();
}
