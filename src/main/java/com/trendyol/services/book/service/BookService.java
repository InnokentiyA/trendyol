package com.trendyol.services.book.service;

import com.trendyol.services.book.convertor.BookConvertor;
import com.trendyol.services.book.dto.Book;
import com.trendyol.services.book.dto.BookRequest;
import com.trendyol.services.book.entity.BookEntity;
import com.trendyol.services.book.exception.BookNotFoundException;
import com.trendyol.services.book.exception.DuplicateBookException;
import com.trendyol.services.book.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BookService implements IBook {

    private final BookRepository bookRepository;

    @Override
    @SneakyThrows
    public Book getBookById(Long bookId) {
        return BookConvertor.convertFromEntityToDto(bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new));
    }

    @Override
    public Book createBook(BookRequest bookRequest) {
        if (bookRepository.findBookEntityByAuthorAndTitle(bookRequest.getAuthor(), bookRequest.getTitle()) != null) {
            throw new DuplicateBookException();
        }
        BookEntity bookEntity = bookRepository.save(BookEntity.builder()
                .author(bookRequest.getAuthor())
                .title(bookRequest.getTitle())
                .build());
        return BookConvertor.convertFromEntityToDto(bookEntity);
    }

    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll().stream()
                .map(BookConvertor::convertFromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteBooks() {
        bookRepository.deleteAll();
    }
}
