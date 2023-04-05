package dom.dec.carapp.exception;

public class InternalServerException extends RuntimeException {
    private String message;

    public InternalServerException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
