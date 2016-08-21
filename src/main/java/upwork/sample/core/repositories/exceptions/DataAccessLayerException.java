package upwork.sample.core.repositories.exceptions;

public class DataAccessLayerException extends RuntimeException {

    public DataAccessLayerException() {
        super();
    }

    public DataAccessLayerException(String message) {
        super(message);
    }

    public DataAccessLayerException(String message, Throwable cause) {
        super(message, cause);
    }
}
