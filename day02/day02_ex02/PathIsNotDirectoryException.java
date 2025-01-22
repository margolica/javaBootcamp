package day_02.ex_02;

public class PathIsNotDirectoryException extends RuntimeException {
    public PathIsNotDirectoryException(String errorMessage) {
        super(errorMessage);
    }
}