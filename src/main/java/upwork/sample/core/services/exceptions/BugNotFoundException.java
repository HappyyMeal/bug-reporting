package upwork.sample.core.services.exceptions;

public class BugNotFoundException extends RuntimeException {

    public BugNotFoundException() {
        super();
    }

    public BugNotFoundException(String message) {
        super(message);
    }

    public BugNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
