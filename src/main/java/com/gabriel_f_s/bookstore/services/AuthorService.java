package com.gabriel_f_s.bookstore.services;

import com.gabriel_f_s.bookstore.entities.Author;
import com.gabriel_f_s.bookstore.mapper.DataMapper;
import com.gabriel_f_s.bookstore.mapper.dtos.AuthorDTO;
import com.gabriel_f_s.bookstore.mapper.dtos.AuthorWithBooksDTO;
import com.gabriel_f_s.bookstore.mapper.dtos.BookDTO;
import com.gabriel_f_s.bookstore.repositories.AuthorRepository;
import com.gabriel_f_s.bookstore.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
                }).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Author create(AuthorDTO newAuthor) {
        return repository.save(DataMapper.parseData(newAuthor, Author.class));
    }

    public AuthorDTO update(Long id, AuthorDTO newAuthor) {
        Author object = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        object.setName(newAuthor.getName());
        return DataMapper.parseData(repository.save(object), AuthorDTO.class);
    }

    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        else throw new ResourceNotFoundException(id);
    }
}
