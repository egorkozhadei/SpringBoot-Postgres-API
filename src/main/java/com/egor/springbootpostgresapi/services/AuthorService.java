package com.egor.springbootpostgresapi.services;

import com.egor.springbootpostgresapi.domain.entities.AuthorEntity;

public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity authorEntity);
}
