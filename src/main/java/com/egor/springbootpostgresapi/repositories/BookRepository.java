package com.egor.springbootpostgresapi.repositories;

import com.egor.springbootpostgresapi.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, String> {
}
