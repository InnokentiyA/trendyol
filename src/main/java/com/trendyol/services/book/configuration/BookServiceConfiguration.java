package com.trendyol.services.book.configuration;

import com.trendyol.services.book.repository.BookRepository;
import com.trendyol.services.book.service.IBook;
import com.trendyol.services.book.service.BookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookServiceConfiguration {

    @Bean
    public IBook bookService(BookRepository bookRepository){
        return new BookService(bookRepository);
    }
}
