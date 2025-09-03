package com.gabriel_f_s.bookstore.services;

import com.gabriel_f_s.bookstore.entities.Book;
import com.gabriel_f_s.bookstore.exceptions.ResourceNotFoundException;
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
        return object.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Book create(Book newBook) {
        return repository.save(newBook);
    }

    public Book update(Long id, Book newBook) {
        Book object = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        object.setName(newBook.getName());
        object.setPrice(newBook.getPrice());
        object.setNumberOfPages(newBook.getNumberOfPages());
        return repository.save(object);
    }

    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }
}
