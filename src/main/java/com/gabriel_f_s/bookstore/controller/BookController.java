package com.gabriel_f_s.bookstore.controller;

import com.gabriel_f_s.bookstore.dto.BookDTO;
import com.gabriel_f_s.bookstore.dto.BooksWithRelationsDTO;
import com.gabriel_f_s.bookstore.dto.CreateBookDTO;
import com.gabriel_f_s.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService service;

    @PreAuthorize("hasAuthority('BOOKS_READ')")
    @GetMapping
    public ResponseEntity<List<BookDTO>> findAll() {
        List<BookDTO> body = service.findAll();
        return ResponseEntity.ok(body);
    }

    @PreAuthorize("hasAuthority('BOOKS_READ')")
    @GetMapping("/{id}")
    public ResponseEntity<BooksWithRelationsDTO> findById(@PathVariable Long id) {
        BooksWithRelationsDTO body = service.findById(id);
        return ResponseEntity.ok(body);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER', 'ROLE_LIBRARIAN')")
    @PostMapping
    public ResponseEntity<BooksWithRelationsDTO> create(@RequestBody CreateBookDTO book) {
        BooksWithRelationsDTO body = service.create(book);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(body.getId())
                .toUri();
        return ResponseEntity.created(uri).body(body);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER', 'ROLE_LIBRARIAN')")
    @PutMapping("/{id}")
    public ResponseEntity<BooksWithRelationsDTO> update(@PathVariable Long id, @RequestBody CreateBookDTO book) {
        BooksWithRelationsDTO body = service.update(id, book);
        return ResponseEntity.ok(body);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER', 'ROLE_LIBRARIAN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
