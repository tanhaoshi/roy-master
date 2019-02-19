package roy.application.master.oksocket;

public class ManuallyDisconnectException extends RuntimeException{

    public ManuallyDisconnectException() {
        super();
    }

    public ManuallyDisconnectException(String message) {
        super(message);
    }

    public ManuallyDisconnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManuallyDisconnectException(Throwable cause) {
        super(cause);
    }
}
