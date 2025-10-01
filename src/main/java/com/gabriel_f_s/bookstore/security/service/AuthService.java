package com.gabriel_f_s.bookstore.security.service;

import com.gabriel_f_s.bookstore.security.dto.AccountCredentialsDTO;
import com.gabriel_f_s.bookstore.security.dto.TokenDTO;
import com.gabriel_f_s.bookstore.security.entity.User;
import com.gabriel_f_s.bookstore.security.jwt.JwtTokenProvider;
import com.gabriel_f_s.bookstore.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository repository;

    public ResponseEntity<TokenDTO> signIn(AccountCredentialsDTO credentials) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credentials.getUsername(),
                        credentials.getPassword()
                )
        );

        User user = repository.findByUsername(credentials.getUsername());

        if (user == null) throw new UsernameNotFoundException("Username " + credentials.getUsername() + " not found.");

        TokenDTO token = jwtTokenProvider.createAccessToken(
                credentials.getUsername(),
                user.getRoles()
        );

        return ResponseEntity.ok(token);
    }

    public ResponseEntity<TokenDTO> refreshToken(String username, String refreshToken) {
        User user = repository.findByUsername(username);
        TokenDTO token;

        if (user != null) {
            token = jwtTokenProvider.refreshToken(refreshToken);
        }
        else throw new UsernameNotFoundException("Username " + username + " not found.");

        return ResponseEntity.ok(token);
    }
}
