package upwork.sample.core.services.exceptions;

public class BugAlreadyExistsException extends RuntimeException {

    public BugAlreadyExistsException() {
        super();
    }

    public BugAlreadyExistsException(String message) {
        super(message);
    }

    public BugAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
