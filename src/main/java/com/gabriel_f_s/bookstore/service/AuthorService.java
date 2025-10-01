package com.gabriel_f_s.bookstore.service;

import com.gabriel_f_s.bookstore.entity.Author;
import com.gabriel_f_s.bookstore.dto.mapper.DataMapper;
import com.gabriel_f_s.bookstore.dto.AuthorDTO;
import com.gabriel_f_s.bookstore.dto.AuthorWithBooksDTO;
import com.gabriel_f_s.bookstore.dto.BookDTO;
import com.gabriel_f_s.bookstore.repository.AuthorRepository;
import com.gabriel_f_s.bookstore.exception.DatabaseException;
import com.gabriel_f_s.bookstore.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository repository;

    public List<AuthorDTO> findAll() {
        return DataMapper.parseListData(repository.findAll(), AuthorDTO.class);
    }

    public AuthorWithBooksDTO findById(Long id) {
        return repository.findById(id)
                .map(author -> {
                    AuthorWithBooksDTO dto = new AuthorWithBooksDTO();
                    dto.setId(author.getId());
                    dto.setName(author.getName());

                    Set<BookDTO> bookDTOs = author.getBooks().stream()
                            .map(book -> {
                                BookDTO bookDTO = new BookDTO();
                                bookDTO.setId(book.getId());
                                bookDTO.setTitle(book.getTitle());
                                bookDTO.setIsbn(book.getIsbn());
                                return bookDTO;
                            })
                            .collect(Collectors.toSet());
                    dto.setBooks(bookDTOs);
                    return dto;
                }).orElseThrow(() -> new ResourceNotFoundException("Author not found.", id));
    }

    public Author create(AuthorDTO newAuthor) {
        return repository.save(DataMapper.parseData(newAuthor, Author.class));
    }

    public AuthorDTO update(Long id, AuthorDTO newAuthor) {
        Author object = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author not found.", id));
        object.setName(newAuthor.getName());
        return DataMapper.parseData(repository.save(object), AuthorDTO.class);
    }

    public void delete(Long id) {
        try {
            if (repository.existsById(id)) {
                repository.deleteById(id);
            }
            else throw new ResourceNotFoundException("Author not found.", id);
        }
        catch (DataIntegrityViolationException exception) {
            throw new DatabaseException(exception.getMessage());
        }
    }
}
