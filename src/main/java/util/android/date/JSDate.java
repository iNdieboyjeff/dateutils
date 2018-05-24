package util.android.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JSDate {

    private JSDate() {
    }

    public static Date parseDate(String dateString) throws JavaScriptDateParseException {
        if (dateString == null || dateString.isEmpty()) {
            throw new JavaScriptDateParseException("Cannot parse null or empty date string");
        }
        try {
            DateFormat jsDateFormat = new SimpleDateFormat("EE MMM d y H:m:s 'GMT'Z (zz)");
            return jsDateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new JavaScriptDateParseException("Cannot parse: " + dateString, e);
        }
    }
}
