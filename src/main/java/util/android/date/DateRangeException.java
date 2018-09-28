package util.android.date;

public class DateRangeException extends Exception {
    public DateRangeException() {
        super();
    }

    public DateRangeException(String message) {
        super(message);
    }

    public DateRangeException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateRangeException(Throwable cause) {
        super(cause);
    }

    protected DateRangeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
