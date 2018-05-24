package util.android.date;

public class AtomDateParseException extends DateParseException {
    public AtomDateParseException() {
        super();
    }

    public AtomDateParseException(String message) {
        super(message);
    }

    public AtomDateParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public AtomDateParseException(Throwable cause) {
        super(cause);
    }

    protected AtomDateParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
