package com.gabriel_f_s.bookstore.security.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Table(name = "permission")
@Entity
@Setter
@Getter
public class Permission implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Override
    public String getAuthority() {
        return this.name;
    }
}
