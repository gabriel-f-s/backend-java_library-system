package com.gabriel_f_s.bookstore.repositories;

import com.gabriel_f_s.bookstore.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
