package com.gabriel_f_s.bookstore.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabriel_f_s.bookstore.dto.mapper.DataMapper;
import com.gabriel_f_s.bookstore.exception.handlers.StandardError;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.time.Instant;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper mapper;

    public CustomAccessDeniedHandler(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String error = "Access denied. You do not have sufficient permissions to access this resource.";

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        StandardError exceptionResponse = new StandardError(
                Instant.now(),
                response.getStatus(),
                error,
                accessDeniedException.getMessage(),
                request.getRequestURI()
        );

        OutputStream out = response.getOutputStream();
        mapper.writeValue(out, exceptionResponse);
        out.flush();
    }
}
