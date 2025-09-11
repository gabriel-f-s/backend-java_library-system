package com.gabriel_f_s.bookstore.configs;

import com.gabriel_f_s.bookstore.entities.Author;
import com.gabriel_f_s.bookstore.entities.Book;
import com.gabriel_f_s.bookstore.repositories.AuthorRepository;
import com.gabriel_f_s.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Configuration
public class TestConfiguration implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Author> authors = Arrays.asList(
                new Author(null, "J.K. Rowling"),
                new Author(null, "Robert C. Martin"),
                new Author(null, "Eric Evans")
        );
        authorRepository.saveAll(authors);


        List<Book> books = Arrays.asList(
                new Book(null, "Harry Potter e a Pedra Filosofal", "978-8532530783", Set.of(authors.get(0))),
                new Book(null, "Código Limpo: Habilidades Práticas do Agile Software", "978-8576082675", Set.of(authors.get(1))),
                new Book(null, "Arquitetura Limpa: o Guia do Artesão Para Estrutura e Design de Software", "978-8550804606", Set.of(authors.get(1))),
                new Book(null, "Domain-Driven Design: Atacando as Complexidades no Coração do Software", "978-8550800653", Set.of(authors.get(2)))
        );
        bookRepository.saveAll(books);
    }
}
