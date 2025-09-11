package com.gabriel_f_s.bookstore.repositories;

import com.gabriel_f_s.bookstore.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
