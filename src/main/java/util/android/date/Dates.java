package util.android.date;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Utility class for Date related functions.
 *
 * This provides a simple way to invoke functionality contained in other classes in this library, along with other
 * stand-alone date/time related functions.
 *
 * @author Jeff Sutton
 */
public class Dates {

    private Dates() {
    }

    /**
     * Attempt to parse a date string into a Date object.
     *
     * This function is a wrapper that tries to parse a Date using the parseDate() method of the other classes in
     * this package in order until it finds a match (or the Date fails to parse at all).
     *
     * @param dateString String representation of a date
     * @return java.util.Date
     * @throws DateParseException Date string fails to parse, or is null/empty
     */
    public static Date parseDate(String dateString) throws DateParseException {
        if (dateString == null || dateString.isEmpty()) {
            throw new DateParseException("Cannot parse null or empty date string");
        }

        try {
            return AtomDate.parseDate(dateString);
        } catch (DateParseException ignored) {
            // Do nothing
        }

        try {
            return JSDate.parseDate(dateString);
        } catch (DateParseException ignored) {
            // Do nothing
        }

        throw new DateParseException("Parse error: " + dateString);
    }

    /**
     * Format a given Date in Atom format (RFC 3339) using UTC offset.
     *
     * As the date will be using UTC the TimeZone will be indicated using Z rather than an offset of +00:00
     *
     * Examples of output String are:
     *
     *      2009-11-04T20:55:41Z
     *
     * @param inDate Date to format
     * @return java.lang.String representation of Date in Atom format
     */
    public static String asAtomUTC(Date inDate) {
        return AtomDate.formatAtomDate(inDate);
    }

    /**
     * Format a given Date in Atom format (RFC 3339) using specified TimeZone offset
     *
     * Where the TimeZone is the same at UTC the UTC offset will be indicated using Z, otherwise it will be in the
     * +/-HH:MM format (e.g. +01:00, +05:00, -02:00)
     *
     * Examples of output String are:
     *
     *       2009-11-04T20:55:41Z
     *       2009-11-04T20:55:41+01:00
     *
     * @param inDate Date to format
     * @param timeZone TimeZone to use when calculating the offset from UTC to display
     * @return String representation of Date in Atom format
     */
    public static String asAtom(Date inDate, TimeZone timeZone) {
        return AtomDate.formatAtomDate(inDate, timeZone);
    }

    public static boolean isSameDay(final Date date1, final Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        final Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        final Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    public static boolean isSameDay(final Calendar cal1, final Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
}
