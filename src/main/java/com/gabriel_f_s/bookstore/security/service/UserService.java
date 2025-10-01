package com.gabriel_f_s.bookstore.security.service;

import com.gabriel_f_s.bookstore.security.entity.User;
import com.gabriel_f_s.bookstore.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user != null) return user;
        else throw new UsernameNotFoundException("Username" + username + "not found.");
    }
}
