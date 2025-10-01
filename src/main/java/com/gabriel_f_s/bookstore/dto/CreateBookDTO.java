package com.gabriel_f_s.bookstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CreateBookDTO {

    private Long id;
    private String title;
    private String isbn;
    private Integer publicationYear;
    private Set<Long> authorsIds = new HashSet<>();
    private Set<Long> genresIds = new HashSet<>();
}
