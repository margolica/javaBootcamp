package edu.school21.numbers.exception;

public class AlwaysAuthenticatedException extends RuntimeException {
    public AlwaysAuthenticatedException(String message) {
        super(message);
    }
}
