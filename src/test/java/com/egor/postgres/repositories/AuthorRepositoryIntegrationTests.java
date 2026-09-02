package com.egor.postgres.repositories;

import com.egor.postgres.TestDataUtil;
import com.egor.postgres.domain.Author;
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
public class AuthorRepositoryIntegrationTests {
    private AuthorRepository sut;

    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository sut) {
        this.sut = sut;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        Author author = TestDataUtil.getTestAuthorA();
        Author savedAuthor = sut.save(author);

        Optional<Author> result = sut.findById(savedAuthor.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedAuthor);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        Author authorA = TestDataUtil.getTestAuthorA();
        Author authorB = TestDataUtil.getTestAuthorB();
        Author authorC = TestDataUtil.getTestAuthorC();
        sut.save(authorA);
        sut.save(authorB);
        sut.save(authorC);

        Iterable<Author> result = sut.findAll();
        assertThat(result).hasSize(3).contains(authorA, authorB, authorC);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        Author authorA = TestDataUtil.getTestAuthorA();
        Author savedAuthor = sut.save(authorA);
        savedAuthor.setName("UPDATED");
        sut.save(savedAuthor);
        Optional<Author> result = sut.findById(savedAuthor.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedAuthor);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        Author author = TestDataUtil.getTestAuthorA();
        Author savedAuthor = sut.save(author);
        sut.deleteById(savedAuthor.getId());

        Optional<Author> result = sut.findById(savedAuthor.getId());

        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        Author testAuthorA = TestDataUtil.getTestAuthorA();
        Author testAuthorB = TestDataUtil.getTestAuthorB();
        Author testAuthorC = TestDataUtil.getTestAuthorC();
        Author savedAuthorA = sut.save(testAuthorA);
        Author savedAuthorB = sut.save(testAuthorB);
        Author savedAuthorC = sut.save(testAuthorC);

        Iterable<Author> result = sut.ageLessThan(50);
        assertThat(result).contains(savedAuthorB, savedAuthorC);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {
        Author testAuthorA = TestDataUtil.getTestAuthorA();
        Author testAuthorB = TestDataUtil.getTestAuthorB();
        Author testAuthorC = TestDataUtil.getTestAuthorC();
        Author savedAuthorA = sut.save(testAuthorA);
        Author savedAuthorB = sut.save(testAuthorB);
        Author savedAuthorC = sut.save(testAuthorC);

        Iterable<Author> result = sut.findAuthorsWithAgeGreaterThan(50);
        assertThat(result).contains(savedAuthorA);
    }
}
