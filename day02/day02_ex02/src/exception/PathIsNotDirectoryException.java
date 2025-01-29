package exception;

public class PathIsNotDirectoryException extends RuntimeException {
    public PathIsNotDirectoryException(String errorMessage) {
        super(errorMessage);
    }
}