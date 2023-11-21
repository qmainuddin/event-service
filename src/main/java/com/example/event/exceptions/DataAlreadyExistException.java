package com.example.event.exceptions;

public class DataAlreadyExistException extends RuntimeException {
    public DataAlreadyExistException(String message) {
        super(message);
    }
    public DataAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
    public DataAlreadyExistException() {
        super();
    }
}
