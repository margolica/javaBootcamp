package day_02.ex_02;

public class PathIsNotFileException extends RuntimeException {
    public PathIsNotFileException(String errorMessage) {
        super(errorMessage);
    }
}