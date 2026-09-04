package com.egor.springbootpostgresapi.services;

import com.egor.springbootpostgresapi.domain.entities.BookEntity;

import java.util.List;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity book);

    List<BookEntity> findAll();
}
