package com.trendyol.services.book.repository;

import com.trendyol.services.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    BookEntity findBookEntityByAuthorAndTitle(String author, String title);
}
