package com.egor.springbootpostgresapi.services;

import com.egor.springbootpostgresapi.domain.entities.AuthorEntity;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.Optional;

public interface AuthorService {
    AuthorEntity save(AuthorEntity authorEntity);

    Page<AuthorEntity> findAll(Pageable pageable);

    Optional<AuthorEntity> findOne(Long id);

    boolean isExists(Long id);

    AuthorEntity partialUpdate(Long id, AuthorEntity authorEntity);

    void delete(Long id);
}
