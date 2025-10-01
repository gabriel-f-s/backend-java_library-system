package com.gabriel_f_s.bookstore.security.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

@Entity
@Table(name = "tb_roles")
@Getter
@Setter
@EqualsAndHashCode
public class Role implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @Getter(value = AccessLevel.NONE)
    private String roleName;

    @Override
    public String getAuthority() {
        return this.roleName;
    }
}
