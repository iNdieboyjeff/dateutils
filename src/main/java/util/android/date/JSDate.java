package util.android.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class JSDate {

    JSDate() {
    }

    public static final String DATE_FORMAT = "EE MMM d y HH:mm:ss 'GMT'Z (zz)";

    public static Date parseDate(String dateString) throws JavaScriptDateParseException {
        if (dateString == null || dateString.isEmpty()) {
            throw new JavaScriptDateParseException("Cannot parse null or empty date string");
        }
        try {
            DateFormat jsDateFormat = new SimpleDateFormat(DATE_FORMAT);
            return jsDateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new JavaScriptDateParseException("Cannot parse: " + dateString, e);
        }
    }

    public static String formatDate(Date inDate) {
        return formatDate(inDate, TimeZoneConstants.TZ_UTC);
    }

    public static String formatDate(Date inDate, TimeZone timeZone) {
        if (inDate == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        DateFormat atomDateFormat = new SimpleDateFormat(DATE_FORMAT);
        atomDateFormat.setTimeZone(timeZone);
        return atomDateFormat.format(inDate);
    }
}
