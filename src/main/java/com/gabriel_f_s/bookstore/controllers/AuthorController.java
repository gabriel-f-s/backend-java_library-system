package com.gabriel_f_s.bookstore.controllers;

import com.gabriel_f_s.bookstore.entities.Author;
import com.gabriel_f_s.bookstore.mapper.DataMapper;
import com.gabriel_f_s.bookstore.mapper.dtos.AuthorDTO;
import com.gabriel_f_s.bookstore.mapper.dtos.AuthorWithBooksDTO;
import com.gabriel_f_s.bookstore.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService service;

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> findAll() {
        List<AuthorDTO> body = service.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorWithBooksDTO> findById(@PathVariable Long id) {
        AuthorWithBooksDTO body = service.findById(id);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> create(@RequestBody AuthorDTO newAuthor) {
        Author body = service.create(newAuthor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(body.getId())
                .toUri();
        return ResponseEntity.created(uri).body(DataMapper.parseData(body, AuthorDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable Long id, @RequestBody AuthorDTO newAuthor) {
        AuthorDTO body = service.update(id, newAuthor);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
