package com.gabriel_f_s.bookstore.service;

import com.gabriel_f_s.bookstore.dto.*;
import com.gabriel_f_s.bookstore.entity.Author;
import com.gabriel_f_s.bookstore.entity.Book;
import com.gabriel_f_s.bookstore.entity.Genre;
import com.gabriel_f_s.bookstore.dto.mapper.DataMapper;
import com.gabriel_f_s.bookstore.repository.AuthorRepository;
import com.gabriel_f_s.bookstore.repository.GenreRepository;
import com.gabriel_f_s.bookstore.exception.RelatedEntityNotFoundException;
import com.gabriel_f_s.bookstore.exception.ResourceNotFoundException;
import com.gabriel_f_s.bookstore.repository.BookRepository;
import com.gabriel_f_s.bookstore.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    BookRepository repository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    GenreRepository genreRepository;

    public List<BookDTO> findAll() {
        return DataMapper.parseListData(repository.findAll(), BookDTO.class);
    }

    @Transactional
    public BooksWithRelationsDTO findById(Long id) {
        return repository.findById(id)
                .map(book -> {
                    BooksWithRelationsDTO dto = new BooksWithRelationsDTO();
                    dto.setId(book.getId());
                    dto.setTitle(book.getTitle());
                    dto.setIsbn(book.getIsbn());

                    Set<AuthorDTO> authorDTOs = book.getAuthors().stream()
                            .map(author -> new AuthorDTO(author.getId(), author.getName()))
                            .collect(Collectors.toSet());
                    dto.setAuthors(authorDTOs);

                    Set<GenreDTO> genreDTOs = book.getGenres().stream()
                            .map(genre -> new GenreDTO(genre.getId(), genre.getName()))
                            .collect(Collectors.toSet());
                    dto.setGenres(genreDTOs);
                    return dto;
                }).orElseThrow(() -> new ResourceNotFoundException("Book not found.", id));
    }

    @Transactional
    public BooksWithRelationsDTO create(CreateBookDTO newBook) {
        Set<Author> authors = new HashSet<>(authorRepository.findAllById(newBook.getAuthorsIds()));
        Set<Genre> genres = new HashSet<>(genreRepository.findAllById(newBook.getGenresIds()));

        if (authors.isEmpty() || genres.isEmpty()) throw new RelatedEntityNotFoundException("Authors or Genres not found.");

        Book book = repository.save(new Book(newBook.getId(), newBook.getTitle(), newBook.getIsbn(), newBook.getPublicationYear(), authors, genres));
        return DataMapper.parseData(book, BooksWithRelationsDTO.class);
    }

    @Transactional
    public BooksWithRelationsDTO update(Long id, CreateBookDTO newBook) {
        Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found.", id));

        Set<Author> authors = new HashSet<>(authorRepository.findAllById(newBook.getAuthorsIds()));
        Set<Genre> genres = new HashSet<>(genreRepository.findAllById(newBook.getGenresIds()));

        if (authors.isEmpty() || genres.isEmpty()) throw new ValidationException("The book must have at least one author and one genre.");

        book.setTitle(newBook.getTitle());
        book.setIsbn(newBook.getIsbn());
        book.setAuthors(authors);
        book.setGenres(genres);
        return DataMapper.parseData(repository.save(book), BooksWithRelationsDTO.class);
    }

    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Book not found.", id);
        }
    }
}
