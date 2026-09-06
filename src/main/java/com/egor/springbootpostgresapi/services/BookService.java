package com.egor.springbootpostgresapi.services;

import com.egor.springbootpostgresapi.domain.entities.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity createOrUpdateBook(String isbn, BookEntity book);

    List<BookEntity> findAll();

    Optional<BookEntity> findOne(String isbn);

    boolean isExists(String isbn);

    BookEntity partialUpdate(String isbn, BookEntity bookEntity);

    void delete(String isbn);
}
