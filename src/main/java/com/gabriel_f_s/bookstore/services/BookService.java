package com.gabriel_f_s.bookstore.services;

import com.gabriel_f_s.bookstore.entities.Book;
import com.gabriel_f_s.bookstore.mapper.DataMapper;
import com.gabriel_f_s.bookstore.mapper.dtos.AuthorDTO;
import com.gabriel_f_s.bookstore.mapper.dtos.BookDTO;
import com.gabriel_f_s.bookstore.mapper.dtos.BooksWithRelationsDTO;
import com.gabriel_f_s.bookstore.services.exceptions.ResourceNotFoundException;
import com.gabriel_f_s.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public List<BookDTO> findAll() {
        return DataMapper.parseListData(repository.findAll(), BookDTO.class);
    }

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
                    return dto;
                }).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public BookDTO create(BookDTO newBook) {
        return DataMapper.parseData(repository.save(DataMapper.parseData(newBook, Book.class)), BookDTO.class);
    }

    public BookDTO update(Long id, BookDTO newBook) {
        Book object = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        object.setTitle(newBook.getTitle());
        object.setIsbn(newBook.getIsbn());
        return DataMapper.parseData(repository.save(object), BookDTO.class);
    }

    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }
}
