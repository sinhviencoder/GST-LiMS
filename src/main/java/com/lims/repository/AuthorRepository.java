package com.lims.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.lims.entity.Author;

public interface AuthorRepository extends DataTablesRepository<Author, Long> {
}
