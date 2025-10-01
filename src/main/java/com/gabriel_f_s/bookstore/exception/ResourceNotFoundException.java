package com.gabriel_f_s.bookstore.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message, Object id) {
        super(message + " Id: " + id);
    }
}
