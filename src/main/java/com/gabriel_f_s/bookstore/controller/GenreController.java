package com.gabriel_f_s.bookstore.controller;

import com.gabriel_f_s.bookstore.entity.Genre;
import com.gabriel_f_s.bookstore.dto.mapper.DataMapper;
import com.gabriel_f_s.bookstore.dto.GenreDTO;
import com.gabriel_f_s.bookstore.dto.GenreWithBooksDTO;
import com.gabriel_f_s.bookstore.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    GenreService service;

    @PreAuthorize("hasAuthority('GENRES_READ')")
    @GetMapping
    public ResponseEntity<List<GenreDTO>> findAll() {
        List<GenreDTO> body = service.findAll();
        return ResponseEntity.ok(body);
    }

    @PreAuthorize("hasAuthority('GENRES_READ')")
    @GetMapping("/{id}")
    public ResponseEntity<GenreWithBooksDTO> findById(@PathVariable Long id) {
        GenreWithBooksDTO body = service.findById(id);
        return ResponseEntity.ok(body);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER', 'ROLE_LIBRARIAN')")
    @PostMapping
    public ResponseEntity<GenreDTO> create(@RequestBody GenreDTO newGenre) {
        Genre body = service.create(newGenre);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(body.getId())
                .toUri();
        return ResponseEntity.created(uri).body(DataMapper.parseData(body, GenreDTO.class));
    }

    @PreAuthorize("hasRole('ROLE_MANAGER', 'ROLE_LIBRARIAN')")
    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> update(@PathVariable Long id, @RequestBody GenreDTO newGenre) {
        GenreDTO body = service.update(id, newGenre);
        return ResponseEntity.ok(body);
    }

    @PreAuthorize("hasRole('ROLE_MANAGER', 'ROLE_LIBRARIAN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
