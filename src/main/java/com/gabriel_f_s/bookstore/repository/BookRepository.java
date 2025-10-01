package com.gabriel_f_s.bookstore.repository;

import com.gabriel_f_s.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
