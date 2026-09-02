package com.egor.springbootpostgresapi.repositories;

import com.egor.springbootpostgresapi.TestDataUtil;
import com.egor.springbootpostgresapi.domain.entities.AuthorEntity;
import com.egor.springbootpostgresapi.domain.entities.BookEntity;
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
public class BookEntityRepositoryIntegrationTests {
    private BookRepository sut;

    @Autowired
    public BookEntityRepositoryIntegrationTests(BookRepository sut) {
        this.sut = sut;
    }

    @Test
    public void testThatBookCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity = TestDataUtil.getTestAuthorA();
        BookEntity bookEntity = TestDataUtil.getTestBookA(authorEntity);

        BookEntity savedBookEntity = sut.save(bookEntity);
        Optional<BookEntity> result = sut.findById(savedBookEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedBookEntity);
    }

    @Test
    public void testThatMultipleBooksCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity = TestDataUtil.getTestAuthorA();

        BookEntity bookEntityA = TestDataUtil.getTestBookA(authorEntity);
        BookEntity bookEntityB = TestDataUtil.getTestBookB(authorEntity);
        BookEntity bookEntityC = TestDataUtil.getTestBookC(authorEntity);
        BookEntity savedBookEntityA = sut.save(bookEntityA);
        BookEntity savedBookEntityB = sut.save(bookEntityB);
        BookEntity savedBookEntityC = sut.save(bookEntityC);

        Iterable<BookEntity> result = sut.findAll();

        assertThat(result).hasSize(3).contains(savedBookEntityA, savedBookEntityB, savedBookEntityC);
    }

    @Test
    public void testThatBookCanBeUpdated() {
        AuthorEntity authorEntity = TestDataUtil.getTestAuthorA();
        BookEntity bookEntity = TestDataUtil.getTestBookA(authorEntity);
        BookEntity savedBookEntity = sut.save(bookEntity);
        bookEntity.setTitle("UPDATED");
        sut.save(savedBookEntity);

        Optional<BookEntity> result = sut.findById(savedBookEntity.getIsbn());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedBookEntity);
    }

    @Test
    public void testThatBookCanBeDeleted() {
        AuthorEntity authorEntity = TestDataUtil.getTestAuthorA();
        BookEntity bookEntity = TestDataUtil.getTestBookA(authorEntity);
        BookEntity savedBookEntity = sut.save(bookEntity);
        sut.deleteById(savedBookEntity.getIsbn());

        Optional<BookEntity> result = sut.findById(savedBookEntity.getIsbn());

        assertThat(result).isEmpty();
    }
}
