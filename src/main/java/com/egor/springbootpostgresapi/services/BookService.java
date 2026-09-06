package com.egor.springbootpostgresapi.services;

import com.egor.springbootpostgresapi.domain.entities.BookEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.Optional;

public interface BookService {
    BookEntity createOrUpdateBook(String isbn, BookEntity book);

    Page<BookEntity> findAll(Pageable pageable);

    Optional<BookEntity> findOne(String isbn);

    boolean isExists(String isbn);

    BookEntity partialUpdate(String isbn, BookEntity bookEntity);

    void delete(String isbn);
}
