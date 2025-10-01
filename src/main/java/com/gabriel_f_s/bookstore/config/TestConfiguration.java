package com.gabriel_f_s.bookstore.config;

import com.gabriel_f_s.bookstore.entity.Author;
import com.gabriel_f_s.bookstore.entity.Book;
import com.gabriel_f_s.bookstore.entity.Genre;
import com.gabriel_f_s.bookstore.repository.AuthorRepository;
import com.gabriel_f_s.bookstore.repository.BookRepository;
import com.gabriel_f_s.bookstore.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Configuration
@Profile("test")
public class TestConfiguration implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Author> authors = Arrays.asList(
                new Author(null, "J.K. Rowling"),
                new Author(null, "Robert C. Martin"),
                new Author(null, "Eric Evans")
        );
        authorRepository.saveAll(authors);

        List<Genre> genres = Arrays.asList(
                new Genre(null, "Fantasia"),
                new Genre(null, "Aventura"),
                new Genre(null, "Científico")
        );
        genreRepository.saveAll(genres);

//        List<Book> books = Arrays.asList(
//                new Book(null, "Harry Potter e a Pedra Filosofal", "978-8532530783", Set.of(authors.get(0)), Set.of(genres.get(0), genres.get(1))),
//                new Book(null, "Código Limpo: Habilidades Práticas do Agile Software", "978-8576082675", Set.of(authors.get(1)), Set.of(genres.get(2))),
//                new Book(null, "Arquitetura Limpa: o Guia do Artesão Para Estrutura e Design de Software", "978-8550804606", Set.of(authors.get(1)), Set.of(genres.get(2))),
//                new Book(null, "Domain-Driven Design: Atacando as Complexidades no Coração do Software", "978-8550800653", Set.of(authors.get(2)), Set.of(genres.get(2)))
//        );
//        bookRepository.saveAll(books);
    }
}
