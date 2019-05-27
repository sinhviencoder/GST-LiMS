package com.lims.repository;

import org.springframework.data.repository.CrudRepository;

import com.lims.entity.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
