package pers.hosea.exception;

public class SimpleMinioException extends RuntimeException {
    public SimpleMinioException() {
        super();
    }

    public SimpleMinioException(String message) {
        super(message);
    }

    public SimpleMinioException(String message, Throwable cause) {
        super(message, cause);
    }

    public SimpleMinioException(Throwable cause) {
        super(cause);
    }

    protected SimpleMinioException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
