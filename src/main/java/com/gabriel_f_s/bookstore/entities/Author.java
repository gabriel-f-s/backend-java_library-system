package com.gabriel_f_s.bookstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "tb_authors")
@NoArgsConstructor
@ToString
@Setter
@Getter
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Setter(AccessLevel.NONE)
    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;

    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addBook(Collection<Book> book) {
        books.addAll(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void removeBook(Collection<Book> book) {
        books.removeAll(book);
    }
}

// TODO: Criar a entidade Genre, e realizar a relação com Book.
// TODO: Criar DTOs para Book, Author e Genre pois acredito que desta maneira, seja possível listar os livros em /authors e /genres