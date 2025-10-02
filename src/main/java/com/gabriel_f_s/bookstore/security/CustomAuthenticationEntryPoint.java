package com.gabriel_f_s.bookstore.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel_f_s.bookstore.exception.handlers.StandardError;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper;

    public CustomAuthenticationEntryPoint(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String error = "Not authenticated. You need to authenticate to access this resource.";

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        StandardError exceptionResponse = new StandardError(
                Instant.now(),
                response.getStatus(),
                error,
                authException.getMessage(),
                request.getRequestURI()
        );

        OutputStream out = response.getOutputStream();
        mapper.writeValue(out, exceptionResponse);
        out.flush();
    }
}
