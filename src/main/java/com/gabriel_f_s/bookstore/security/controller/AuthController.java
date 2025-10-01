package com.gabriel_f_s.bookstore.security.controller;

import com.gabriel_f_s.bookstore.security.dto.AccountCredentialsDTO;
import com.gabriel_f_s.bookstore.security.dto.TokenDTO;
import com.gabriel_f_s.bookstore.security.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService service;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AccountCredentialsDTO credentials) {
        if (credentials == null
        || StringUtils.isBlank(credentials.getUsername())
        || StringUtils.isBlank(credentials.getPassword()))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client Request.");

        ResponseEntity<TokenDTO> token = service.signIn(credentials);

        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client Request.");

        return ResponseEntity.ok().body(token);
    }

    @PutMapping("/refresh/{username}")
    public ResponseEntity<?> refreshToken(
            @PathVariable("username") String username,
            @RequestHeader("Authorization") String refreshToken
    ) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client Request.");

        ResponseEntity<TokenDTO> token = service.refreshToken(username, refreshToken);

        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client Request.");

        return ResponseEntity.ok().body(token);
    }
}
