package httpClient.expcetion;

public class HttpToolException extends RuntimeException {

    public HttpToolException() {
    }

    public HttpToolException(String message) {
        super(message);
    }

    public HttpToolException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpToolException(Throwable cause) {
        super(cause);
    }

    public HttpToolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
