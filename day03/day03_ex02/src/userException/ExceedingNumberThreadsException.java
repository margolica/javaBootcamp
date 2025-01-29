package userException;

public class ExceedingNumberThreadsException extends RuntimeException {
    public ExceedingNumberThreadsException(String message) {
        super(message);
    }
}
