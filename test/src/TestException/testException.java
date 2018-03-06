package TestException;

public class testException extends Exception {
    public testException() {
    }

    public testException(String message) {
        super(message);
    }

    public testException(String message, Throwable cause) {
        super(message, cause);
    }

    public testException(Throwable cause) {
        super(cause);
    }

    public testException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
