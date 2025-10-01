package com.gabriel_f_s.bookstore.repository;

import com.gabriel_f_s.bookstore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
