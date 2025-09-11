package com.gabriel_f_s.bookstore.services;

import com.gabriel_f_s.bookstore.entities.Genre;
import com.gabriel_f_s.bookstore.mapper.DataMapper;
import com.gabriel_f_s.bookstore.mapper.dtos.GenreDTO;
import com.gabriel_f_s.bookstore.mapper.dtos.GenreWithBooksDTO;
import com.gabriel_f_s.bookstore.mapper.dtos.BookDTO;
import com.gabriel_f_s.bookstore.repositories.GenreRepository;
import com.gabriel_f_s.bookstore.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GenreService {

    @Autowired
    GenreRepository repository;

    public List<GenreDTO> findAll() {
        return DataMapper.parseListData(repository.findAll(), GenreDTO.class);
    }

    public GenreWithBooksDTO findById(Long id) {
        return repository.findById(id)
                .map(genre -> {
                    GenreWithBooksDTO dto = new GenreWithBooksDTO();
                    dto.setId(genre.getId());
                    dto.setName(genre.getName());

                    Set<BookDTO> bookDTOs = genre.getBooks().stream()
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

    public Genre create(GenreDTO newGenre) {
        return repository.save(DataMapper.parseData(newGenre, Genre.class));
    }

    public GenreDTO update(Long id, GenreDTO newGenre) {
        Genre object = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        object.setName(newGenre.getName());
        return DataMapper.parseData(repository.save(object), GenreDTO.class);
    }

    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        else throw new ResourceNotFoundException(id);
    }
}
