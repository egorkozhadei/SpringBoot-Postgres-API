package com.egor.springbootpostgresapi.services;

import com.egor.springbootpostgresapi.domain.entities.BookEntity;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity book);
}
