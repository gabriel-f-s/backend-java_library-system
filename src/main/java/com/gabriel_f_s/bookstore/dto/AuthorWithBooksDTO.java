package com.gabriel_f_s.bookstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AuthorWithBooksDTO {

    private Long id;
    private String name;
    private Set<BookDTO> books;
}
