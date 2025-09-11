package com.gabriel_f_s.bookstore.repositories;

import com.gabriel_f_s.bookstore.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
