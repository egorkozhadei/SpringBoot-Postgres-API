package com.egor.springbootpostgresapi.services;

import com.egor.springbootpostgresapi.domain.entities.AuthorEntity;

import java.util.List;

public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity authorEntity);

    List<AuthorEntity> findAll();
}
