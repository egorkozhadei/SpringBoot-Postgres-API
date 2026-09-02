package com.egor.springbootpostgresapi;

import com.egor.springbootpostgresapi.domain.entities.AuthorEntity;
import com.egor.springbootpostgresapi.domain.entities.BookEntity;

public final class TestDataUtil {
    private TestDataUtil() {}


    public static AuthorEntity getTestAuthorA() {
        return AuthorEntity.builder()
                .name("Abigail Blake")
                .age(80)
                .build();
    }

    public static AuthorEntity getTestAuthorB() {
        return AuthorEntity.builder()
                .name("Thomas Green")
                .age(44)
                .build();
    }

    public static AuthorEntity getTestAuthorC() {
        return AuthorEntity.builder()
                .name("Jessee Casey")
                .age(24)
                .build();
    }

    public static BookEntity getTestBookA(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity getTestBookB(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-1")
                .title("The Sun in the Basement")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity getTestBookC(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-2")
                .title("The Dark in the Attic")
                .authorEntity(authorEntity)
                .build();
    }
}
