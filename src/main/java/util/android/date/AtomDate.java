package util.android.date;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class AtomDate {

    AtomDate() {
    }

    /**
     * The masks used to validate and parse the input to an Atom date.
     *
     * These are a lot more forgiving than the Atom
     * spec allows.
     */
    private static final String[] atomMasks = {
            "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
            "yyyy-MM-dd't'HH:mm:ss.SSSZ",
            "yyyy-MM-dd't'HH:mm:ss.SSSz",
            "yyyy-MM-dd't'HH:mm:ss.SSS z",
            "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd't'HH:mm:ss.SSS'z'",
            "yyyy-MM-dd'T'HH:mm:ssZ",
            "yyyy-MM-dd'T'HH:mm:ssXXX",
            "yyyy-MM-dd'T'HH:mm:ss Z",
            "yyyy-MM-dd'T'HH:mm:ssz",
            "yyyy-MM-dd't'HH:mm:ssz",
            "yyyy-MM-dd'T'HH:mm:ss z",
            "yyyy-MM-dd't'HH:mm:ss z",
            "yyyy-MM-dd'T'HH:mm:ss'Z'",
            "yyyy-MM-dd't'HH:mm:ss'z'",
            "yyyy-MM-dd'T'HH:mmz",
            "yyyy-MM-dd't'HH:mmz",
            "yyyy-MM-dd'T'HH:mm'Z'",
            "yyyy-MM-dd't'HH:mm'z'",
            "yyyy-MM-dd'T'HH:mm:ss",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd",
            "yyyy MM dd",
            "yyyy-MM",
            "yyyy"
    };

    private static final SimpleDateFormat[] atomFormats = {
            new SimpleDateFormat(atomMasks[0]),
            new SimpleDateFormat(atomMasks[1]),
            new SimpleDateFormat(atomMasks[2]),
            new SimpleDateFormat(atomMasks[3]),
            new SimpleDateFormat(atomMasks[4]),
            new SimpleDateFormat(atomMasks[5]),
            new SimpleDateFormat(atomMasks[6]),
            new SimpleDateFormat(atomMasks[7]),
            new SimpleDateFormat(atomMasks[8]),
            new SimpleDateFormat(atomMasks[9]),
            new SimpleDateFormat(atomMasks[10]),
            new SimpleDateFormat(atomMasks[11]),
            new SimpleDateFormat(atomMasks[12]),
            new SimpleDateFormat(atomMasks[13]),
            new SimpleDateFormat(atomMasks[14]),
            new SimpleDateFormat(atomMasks[15]),
            new SimpleDateFormat(atomMasks[16]),
            new SimpleDateFormat(atomMasks[17]),
            new SimpleDateFormat(atomMasks[18]),
            new SimpleDateFormat(atomMasks[19]),
            new SimpleDateFormat(atomMasks[20]),
            new SimpleDateFormat(atomMasks[21]),
            new SimpleDateFormat(atomMasks[22]),
            new SimpleDateFormat(atomMasks[23]),
            new SimpleDateFormat(atomMasks[24])
    };

    /**
     * Parse an Atom date String into Date object. This is a fairly lenient parse and does not require the date String
     * to conform exactly.
     *
     * If the date String contains the 'Z' character then UTC will be assumed, overriding any following timezone offset
     * specified int he string (which is incorrect anyway)
     *
     * @param dateString A date string in the ATOM format
     * @return java.util.Date
     * @throws AtomDateParseException Unable to parse date string, or null or empty date string
     */
    public static Date parseDate(String dateString) throws AtomDateParseException {
        if (dateString == null || dateString.isEmpty()) {
            throw new AtomDateParseException("Cannot parse null or empty date string");
        }
        if (dateString.indexOf('Z') == -1 && dateString.indexOf('+') == -1) {
            dateString += 'Z';
        }
        TimeZone tz = TimeZoneConstants.TZ_UTC;
        String zoneInfo;
        if (dateString.indexOf('Z') != -1 && dateString.indexOf('+') != -1) {
            zoneInfo = dateString.substring(dateString.indexOf('+')).trim();
            tz = TimeZone.getTimeZone("GMT" + zoneInfo);
        } else if (dateString.indexOf('Z') != -1 && dateString.length() > dateString.indexOf('Z'))  {
            zoneInfo = dateString.substring(dateString.indexOf('Z') + 1).trim();
            tz = TimeZone.getTimeZone("GMT" + zoneInfo);
        }
        Date d = null;
        for (int n = 0; n < atomMasks.length; n++) {
            try {
                atomFormats[n].setTimeZone(tz);
                atomFormats[n].setLenient(true);
                d = atomFormats[n].parse(dateString, new ParsePosition(0));
                if (d != null)
                    break;
            } catch (Exception ignored) {
                // Do nothing
            }
        }
        if (d == null) {
            throw new AtomDateParseException("Cannot parse: " + dateString);
        }
        return d;
    }

    /**
     * Format a Date object as an Atom Date/Time String.
     *
     * @param inDate
     * @return String
     */
    public static String formatAtomDate(Date inDate) {
       return formatAtomDate(inDate, TimeZoneConstants.TZ_UTC);
    }

    /**
     * Format a timestamp as an Atom Date/Time String.
     *
     * @param inTime long - Timestamp in ms
     * @return String
     */
    public static String formatAtomDate(long inTime) {
        return formatAtomDate(new Date(inTime), TimeZoneConstants.TZ_UTC);
    }

    public static String formatAtomDate(Date inDate, TimeZone timeZone) {
        if (inDate == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        DateFormat atomDateFormat =  new SimpleDateFormat(atomMasks[7]);
        atomDateFormat.setTimeZone(timeZone);
        return atomDateFormat.format(inDate);
    }

    /**
     * Format a Date object as an Atom Date/Time String.
     *
     * @param inTime
     * @return String
     */
    public static String formatAtomDate(long inTime, TimeZone timeZone) {
        return formatAtomDate(new Date(inTime), timeZone);
    }
}
