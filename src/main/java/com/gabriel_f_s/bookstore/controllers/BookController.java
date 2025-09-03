package com.gabriel_f_s.bookstore.controllers;

import com.gabriel_f_s.bookstore.entities.Book;
import com.gabriel_f_s.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        List<Book> body = service.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        Book body = service.findById(id);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book) {
        Book body = service.create(book);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(body.getId())
                .toUri();

        return ResponseEntity.created(uri).body(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody Book book) {
        Book body = service.update(id, book);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
