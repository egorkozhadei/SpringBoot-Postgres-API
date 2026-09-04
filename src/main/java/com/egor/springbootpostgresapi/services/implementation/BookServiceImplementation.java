package com.egor.springbootpostgresapi.services.implementation;

import com.egor.springbootpostgresapi.domain.entities.BookEntity;
import com.egor.springbootpostgresapi.repositories.BookRepository;
import com.egor.springbootpostgresapi.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookServiceImplementation implements BookService {
    private BookRepository bookRepository;

    public BookServiceImplementation(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

    @Override
    public List<BookEntity> findAll() {
        return StreamSupport.stream(bookRepository.findAll().spliterator(), false).collect(Collectors.toList());


    }
}
