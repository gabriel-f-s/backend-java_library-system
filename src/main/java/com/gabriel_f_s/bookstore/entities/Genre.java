package com.gabriel_f_s.bookstore.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_genre")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Genre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Setter(AccessLevel.NONE)
    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    @Autowired
    private Set<Book> books = new HashSet<>();

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
