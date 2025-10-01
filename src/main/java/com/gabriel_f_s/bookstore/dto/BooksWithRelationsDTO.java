package com.gabriel_f_s.bookstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BooksWithRelationsDTO {

    private Long id;
    private String title;
    private String isbn;
    private Set<AuthorDTO> authors = new HashSet<>();
    private Set<GenreDTO> genres = new HashSet<>();

}
