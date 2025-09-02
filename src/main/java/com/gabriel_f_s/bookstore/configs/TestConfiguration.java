package com.gabriel_f_s.bookstore.configs;

import com.gabriel_f_s.bookstore.entities.Book;
import com.gabriel_f_s.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class TestConfiguration implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Book> books = Arrays.asList(
                new Book(null, "Código Limpo: Habilidades Práticas do Agile Software", 75.99, 425),
                new Book(null, "Arquitetura Limpa: o Guia do Artesão Para Estrutura e Design de Software", 78.99, 432),
                new Book(null, "Domain-Driven Design: Atacando as Complexidades no Coração do Software", 102.75, 528),
                new Book(null, "Entendendo Algoritmos", 54.15, 264)
        );

        bookRepository.saveAll(books);
    }
}
