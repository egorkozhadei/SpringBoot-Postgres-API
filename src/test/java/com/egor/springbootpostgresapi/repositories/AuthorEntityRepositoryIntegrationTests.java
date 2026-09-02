package com.egor.springbootpostgresapi.repositories;

import com.egor.springbootpostgresapi.TestDataUtil;
import com.egor.springbootpostgresapi.domain.entities.AuthorEntity;
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
public class AuthorEntityRepositoryIntegrationTests {
    private AuthorRepository sut;

    @Autowired
    public AuthorEntityRepositoryIntegrationTests(AuthorRepository sut) {
        this.sut = sut;
    }

    @Test
    public void testThatAuthorCanBeCreatedAndRecalled() {
        AuthorEntity authorEntity = TestDataUtil.getTestAuthorA();
        AuthorEntity savedAuthorEntity = sut.save(authorEntity);

        Optional<AuthorEntity> result = sut.findById(savedAuthorEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedAuthorEntity);
    }

    @Test
    public void testThatMultipleAuthorsCanBeCreatedAndRecalled() {
        AuthorEntity authorEntityA = TestDataUtil.getTestAuthorA();
        AuthorEntity authorEntityB = TestDataUtil.getTestAuthorB();
        AuthorEntity authorEntityC = TestDataUtil.getTestAuthorC();
        sut.save(authorEntityA);
        sut.save(authorEntityB);
        sut.save(authorEntityC);

        Iterable<AuthorEntity> result = sut.findAll();
        assertThat(result).hasSize(3).contains(authorEntityA, authorEntityB, authorEntityC);
    }

    @Test
    public void testThatAuthorCanBeUpdated() {
        AuthorEntity authorEntityA = TestDataUtil.getTestAuthorA();
        AuthorEntity savedAuthorEntity = sut.save(authorEntityA);
        savedAuthorEntity.setName("UPDATED");
        sut.save(savedAuthorEntity);
        Optional<AuthorEntity> result = sut.findById(savedAuthorEntity.getId());

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(savedAuthorEntity);
    }

    @Test
    public void testThatAuthorCanBeDeleted() {
        AuthorEntity authorEntity = TestDataUtil.getTestAuthorA();
        AuthorEntity savedAuthorEntity = sut.save(authorEntity);
        sut.deleteById(savedAuthorEntity.getId());

        Optional<AuthorEntity> result = sut.findById(savedAuthorEntity.getId());

        assertThat(result).isEmpty();
    }

    @Test
    public void testThatGetAuthorsWithAgeLessThan() {
        AuthorEntity testAuthorEntityA = TestDataUtil.getTestAuthorA();
        AuthorEntity testAuthorEntityB = TestDataUtil.getTestAuthorB();
        AuthorEntity testAuthorEntityC = TestDataUtil.getTestAuthorC();
        AuthorEntity savedAuthorEntityA = sut.save(testAuthorEntityA);
        AuthorEntity savedAuthorEntityB = sut.save(testAuthorEntityB);
        AuthorEntity savedAuthorEntityC = sut.save(testAuthorEntityC);

        Iterable<AuthorEntity> result = sut.ageLessThan(50);
        assertThat(result).contains(savedAuthorEntityB, savedAuthorEntityC);
    }

    @Test
    public void testThatGetAuthorsWithAgeGreaterThan() {
        AuthorEntity testAuthorEntityA = TestDataUtil.getTestAuthorA();
        AuthorEntity testAuthorEntityB = TestDataUtil.getTestAuthorB();
        AuthorEntity testAuthorEntityC = TestDataUtil.getTestAuthorC();
        AuthorEntity savedAuthorEntityA = sut.save(testAuthorEntityA);
        AuthorEntity savedAuthorEntityB = sut.save(testAuthorEntityB);
        AuthorEntity savedAuthorEntityC = sut.save(testAuthorEntityC);

        Iterable<AuthorEntity> result = sut.findAuthorsWithAgeGreaterThan(50);
        assertThat(result).contains(savedAuthorEntityA);
    }
}
