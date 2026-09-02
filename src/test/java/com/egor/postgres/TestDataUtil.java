package com.egor.postgres;

import com.egor.postgres.domain.Author;
import com.egor.postgres.domain.Book;

public final class TestDataUtil {
    private TestDataUtil() {}


    public static Author getTestAuthorA() {
        return Author.builder()
                .name("Abigail Blake")
                .age(80)
                .build();
    }

    public static Author getTestAuthorB() {
        return Author.builder()
                .name("Thomas Green")
                .age(44)
                .build();
    }

    public static Author getTestAuthorC() {
        return Author.builder()
                .name("Jessee Casey")
                .age(24)
                .build();
    }

    public static Book getTestBookA(final Author author) {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(author)
                .build();
    }

    public static Book getTestBookB(final Author author) {
        return Book.builder()
                .isbn("978-1-2345-6789-1")
                .title("The Sun in the Basement")
                .author(author)
                .build();
    }

    public static Book getTestBookC(final Author author) {
        return Book.builder()
                .isbn("978-1-2345-6789-2")
                .title("The Dark in the Attic")
                .author(author)
                .build();
    }
}
