package upwork.sample.core.services.exceptions;

public class AuthorAlreadyExistsException extends RuntimeException {

    public AuthorAlreadyExistsException() {
        super();
    }

    public AuthorAlreadyExistsException(String message) {
        super(message);
    }

    public AuthorAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
