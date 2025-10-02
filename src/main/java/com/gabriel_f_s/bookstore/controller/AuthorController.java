package com.gabriel_f_s.bookstore.controller;

import com.gabriel_f_s.bookstore.entity.Author;
import com.gabriel_f_s.bookstore.dto.mapper.DataMapper;
import com.gabriel_f_s.bookstore.dto.AuthorDTO;
import com.gabriel_f_s.bookstore.dto.AuthorWithBooksDTO;
import com.gabriel_f_s.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    AuthorService service;

    @PreAuthorize("hasAuthority('AUTHORS_READ')")
    @GetMapping
    public ResponseEntity<List<AuthorDTO>> findAll() {
        List<AuthorDTO> body = service.findAll();
        return ResponseEntity.ok(body);
    }

    @PreAuthorize("hasAuthority('AUTHORS_READ')")
    @GetMapping("/{id}")
    public ResponseEntity<AuthorWithBooksDTO> findById(@PathVariable Long id) {
        AuthorWithBooksDTO body = service.findById(id);
        return ResponseEntity.ok(body);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER', 'ROLE_LIBRARIAN')")
    @PostMapping
    public ResponseEntity<AuthorDTO> create(@RequestBody AuthorDTO newAuthor) {
        Author body = service.create(newAuthor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(body.getId())
                .toUri();
        return ResponseEntity.created(uri).body(DataMapper.parseData(body, AuthorDTO.class));
    }

    @PreAuthorize("hasRole('ROLE_MANAGER', 'ROLE_LIBRARIAN')")
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable Long id, @RequestBody AuthorDTO newAuthor) {
        AuthorDTO body = service.update(id, newAuthor);
        return ResponseEntity.ok(body);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER', 'ROLE_LIBRARIAN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
