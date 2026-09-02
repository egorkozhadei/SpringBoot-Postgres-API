package com.egor.postgres.repositories;

import com.egor.postgres.TestDataUtil;
import com.egor.postgres.domain.Author;
import com.egor.postgres.domain.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {
    private BookRepository sut;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository sut) {
        this.sut = sut;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.getTestAuthorA();
        Book book = TestDataUtil.getTestBookA(author);

        Book savedBook = sut.save(book);
        Optional<Book> result = sut.findById(savedBook.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedBook);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.getTestAuthorA();

        Book bookA = TestDataUtil.getTestBookA(author);
        Book bookB = TestDataUtil.getTestBookB(author);
        Book bookC = TestDataUtil.getTestBookC(author);
        Book savedBookA = sut.save(bookA);
        Book savedBookB = sut.save(bookB);
        Book savedBookC = sut.save(bookC);

        Iterable<Book> result = sut.findAll();

        assertThat(result).hasSize(3).contains(savedBookA, savedBookB, savedBookC);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        Author author = TestDataUtil.getTestAuthorA();
        Book book = TestDataUtil.getTestBookA(author);
        Book savedBook = sut.save(book);
        book.setTitle("UPDATED");
        sut.save(savedBook);

        Optional<Book> result = sut.findById(savedBook.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedBook);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        Author author = TestDataUtil.getTestAuthorA();
        Book book = TestDataUtil.getTestBookA(author);
        Book savedBook = sut.save(book);
        sut.deleteById(savedBook.getIsbn());

        Optional<Book> result = sut.findById(savedBook.getIsbn());

        assertThat(result).isEmpty();
    }
}
