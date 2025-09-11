package com.gabriel_f_s.bookstore.controllers;

import com.gabriel_f_s.bookstore.entities.Genre;
import com.gabriel_f_s.bookstore.mapper.DataMapper;
import com.gabriel_f_s.bookstore.mapper.dtos.GenreDTO;
import com.gabriel_f_s.bookstore.mapper.dtos.GenreWithBooksDTO;
import com.gabriel_f_s.bookstore.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    GenreService service;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> findAll() {
        List<GenreDTO> body = service.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreWithBooksDTO> findById(@PathVariable Long id) {
        GenreWithBooksDTO body = service.findById(id);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<GenreDTO> create(@RequestBody GenreDTO newGenre) {
        Genre body = service.create(newGenre);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(body.getId())
                .toUri();
        return ResponseEntity.created(uri).body(DataMapper.parseData(body, GenreDTO.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> update(@PathVariable Long id, @RequestBody GenreDTO newGenre) {
        GenreDTO body = service.update(id, newGenre);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
