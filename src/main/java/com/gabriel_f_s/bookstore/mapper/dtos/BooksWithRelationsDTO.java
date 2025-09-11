package com.gabriel_f_s.bookstore.mapper.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class BooksWithRelationsDTO {

    private Long id;
    private String title;
    private String isbn;
    private Set<AuthorDTO> authors;
    private Set<GenreDTO> genres;

}
