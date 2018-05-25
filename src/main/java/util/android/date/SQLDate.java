package util.android.date;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLDate {

    private static final String[] DATE_FORMATS = {"yyyy-M-d", "yyyy-MM-dd"};
    private static final SimpleDateFormat[] sqlFormats = {
            new SimpleDateFormat(DATE_FORMATS[0]),
            new SimpleDateFormat(DATE_FORMATS[1])
    };

    SQLDate() {
    }

    public static Date parseDate(String dateString) throws DateParseException {
        if (dateString == null || dateString.isEmpty()) {
            throw new DateParseException("Cannot parse null or empty date string");
        }
        Date d = null;
        for (int n = 0; n < sqlFormats.length; n++) {
            sqlFormats[n].setLenient(false);
            d = sqlFormats[n].parse(dateString, new ParsePosition(0));
            if (d != null)
                break;
        }
        if (d == null) {
            throw new DateParseException("Cannot parse: " + dateString);
        }
        return d;
    }

}
