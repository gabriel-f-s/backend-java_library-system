package com.gabriel_f_s.bookstore.controllers.exceptions;

import com.gabriel_f_s.bookstore.services.exceptions.DatabaseException;
import com.gabriel_f_s.bookstore.services.exceptions.RelatedEntityNotFoundException;
import com.gabriel_f_s.bookstore.services.exceptions.ResourceNotFoundException;
import com.gabriel_f_s.bookstore.services.exceptions.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(
            ResourceNotFoundException exception,
            HttpServletRequest request
    ) {
        String error = "Resource not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError body = new StandardError(Instant.now(), status.value(), error, exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(RelatedEntityNotFoundException.class)
    public ResponseEntity<StandardError> relatedEntityNotFound(
            RelatedEntityNotFoundException exception,
            HttpServletRequest request
    ) {
        String error = "Authors or Genres not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError body = new StandardError(Instant.now(), status.value(), error ,exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<StandardError> validation(
            ValidationException exception,
            HttpServletRequest request
    ) {
        String error = "The request could not be processed due to invalid data.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError body = new StandardError(Instant.now(), status.value(), error ,exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<StandardError> noResourceFound(
            NoResourceFoundException exception,
            HttpServletRequest request
    ) {
        String error = "Page not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError body = new StandardError(Instant.now(), status.value(), error ,exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(
            DatabaseException exception,
            HttpServletRequest request
    ) {
        String error = "Database error.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError body = new StandardError(Instant.now(), status.value(), error ,exception.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }
}
