package com.gabriel_f_s.bookstore.repository;

import com.gabriel_f_s.bookstore.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
