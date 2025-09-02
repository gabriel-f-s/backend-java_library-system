package com.gabriel_f_s.bookstore.services;

import com.gabriel_f_s.bookstore.entities.Book;
import com.gabriel_f_s.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public List<Book> findAll() {
        return repository.findAll();
    }

    public Book findById(Long id) {
        Optional<Book> object = repository.findById(id);
        return object.orElseThrow(() -> new IllegalArgumentException("Book not found."));
    }
}
