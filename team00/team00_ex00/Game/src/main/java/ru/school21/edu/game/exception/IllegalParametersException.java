package ru.school21.edu.game.exception;

public class IllegalParametersException extends RuntimeException {

    public IllegalParametersException(String message) {
        super(message);
    }

    public IllegalParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalParametersException(Throwable cause) {
        super(cause);
    }

    protected IllegalParametersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
