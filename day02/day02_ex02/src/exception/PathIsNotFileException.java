package exception;

public class PathIsNotFileException extends RuntimeException {
    public PathIsNotFileException(String errorMessage) {
        super(errorMessage);
    }
}