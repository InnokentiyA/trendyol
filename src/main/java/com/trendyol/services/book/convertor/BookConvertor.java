package com.trendyol.services.book.convertor;

import com.trendyol.services.book.dto.Book;
import com.trendyol.services.book.entity.BookEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BookConvertor {
    public static Book convertFromEntityToDto(BookEntity bookEntity) {
        return Book.builder()
                .id(bookEntity.getId())
                .author(bookEntity.getAuthor())
                .title(bookEntity.getTitle())
                .build();
    }
}
