package util.android.date;

public class JavaScriptDateParseException extends DateParseException {
    public JavaScriptDateParseException() {
        super();
    }

    public JavaScriptDateParseException(String message) {
        super(message);
    }

    public JavaScriptDateParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public JavaScriptDateParseException(Throwable cause) {
        super(cause);
    }

    protected JavaScriptDateParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
