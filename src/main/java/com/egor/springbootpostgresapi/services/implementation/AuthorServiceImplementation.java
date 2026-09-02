package com.egor.springbootpostgresapi.services.implementation;

import com.egor.springbootpostgresapi.domain.entities.AuthorEntity;
import com.egor.springbootpostgresapi.repositories.AuthorRepository;
import com.egor.springbootpostgresapi.services.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImplementation implements AuthorService {
    private AuthorRepository authorRepository;

    public AuthorServiceImplementation(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {
        return authorRepository.save(authorEntity);
    }
}
