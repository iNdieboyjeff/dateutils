package util.android.date;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Utility class for Date related functions.
 * <p>
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
     * <p>
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
            return SQLDate.parseDate(dateString);
        } catch (DateParseException ignored) {
            // Do nothing
        }

        try {
            return JSDate.parseDate(dateString);
        } catch (DateParseException ignored) {
            // Do nothing
        }

        try {
            return TwitterDate.parseDate(dateString);
        } catch (DateParseException ignored) {
            // Do nothing
        }

        throw new DateParseException("Parse error: " + dateString);
    }

    /**
     * Format a given Date in Atom format (RFC 3339) using UTC offset.
     * <p>
     * As the date will be using UTC the TimeZone will be indicated using Z rather than an offset of +00:00
     * <p>
     * Examples of output String are:
     * <p>
     * 2009-11-04T20:55:41Z
     *
     * @param inDate Date to format
     * @return java.lang.String representation of Date in Atom format
     */
    public static String asAtomUTC(Date inDate) {
        return AtomDate.formatAtomDate(inDate);
    }

    /**
     * Format a given Date in Atom format (RFC 3339) using UTC offset.
     * <p>
     * As the date will be using UTC the TimeZone will be indicated using Z rather than an offset of +00:00
     * <p>
     * Examples of output String are:
     * <p>
     * 2009-11-04T20:55:41Z
     *
     * @param inDate timestamp to format
     * @return java.lang.String representation of Date in Atom format
     */
    public static String asAtomUTC(long inDate) {
        return AtomDate.formatAtomDate(inDate);
    }

    /**
     * Format a given Date in Atom format (RFC 3339) using UTC offset.
     * <p>
     * As the date will be using UTC the TimeZone will be indicated using Z rather than an offset of +00:00
     * <p>
     * Examples of output String are:
     * <p>
     * 2009-11-04T20:55:41Z
     *
     * @param inDate Calendar to format
     * @return java.lang.String representation of Date in Atom format
     */
    public static String asAtomUTC(Calendar inDate) {
        return AtomDate.formatAtomDate(inDate.getTime());
    }

    /**
     * Format a given Date in Atom format (RFC 3339) using UTC offset.
     * <p>
     * As the date will be using UTC the TimeZone will be indicated using Z rather than an offset of +00:00
     * <p>
     * Examples of output String are:
     * <p>
     * 2009-11-04T20:55:41Z
     *
     * @param inDate String date to format
     * @return java.lang.String representation of Date in Atom format
     * @throws DateParseException
     */
    public static String asAtomUTC(String inDate) throws DateParseException {
        return AtomDate.formatAtomDate(Dates.parseDate(inDate));
    }

    /**
     * Format a given Date in Atom format (RFC 3339) using specified TimeZone offset
     * <p>
     * Where the TimeZone is the same at UTC the UTC offset will be indicated using Z, otherwise it will be in the
     * +/-HH:MM format (e.g. +01:00, +05:00, -02:00)
     * <p>
     * Examples of output String are:
     * <p>
     * 2009-11-04T20:55:41Z
     * 2009-11-04T20:55:41+01:00
     *
     * @param inDate   Date to format
     * @param timeZone TimeZone to use when calculating the offset from UTC to display
     * @return String representation of Date in Atom format
     */
    public static String asAtom(Date inDate, TimeZone timeZone) {
        return AtomDate.formatAtomDate(inDate, timeZone);
    }

    /**
     * Add a number of days to a Date.
     * <p>
     * Returns the time at midnight on the new date.
     *
     * @param d
     * @param days
     * @return
     */
    public static Date getDatePlus(Date d, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_YEAR, days); // <--
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * Add a number of days to today's date.
     * <p>
     * Returns the time at midnight on the new date.
     *
     * @param days
     * @return
     */
    public static Date getTodayPlus(int days) {
        return getDatePlus(new Date(), days);
    }

    /**
     * Get the age on a specified date based on a date of birth
     *
     * @param dateOfBirth
     * @param onDate
     * @return
     */
    public static int getAgeOnDate(Date dateOfBirth, Date onDate) {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(onDate);

        int y;
        int m;
        int d;
        int a;

        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(dateOfBirth);

        a = y - cal.get(Calendar.YEAR);
        if ((m < cal.get(Calendar.MONTH))
                || ((m == cal.get(Calendar.MONTH)) && (d < cal
                .get(Calendar.DAY_OF_MONTH)))) {
            --a;
        }
        if (a < 0)
            throw new IllegalArgumentException("Age < 0");
        return a;
    }

    /**
     * Get the current age based on a date of birth
     *
     * @param dateOfBirth
     * @return
     */
    public static int getAge(Date dateOfBirth) {
        return getAgeOnDate(dateOfBirth, new Date());
    }

    /**
     * Do two dates represent the same actual day?
     *
     * @param date1
     * @param date2
     * @return boolean
     */
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

    /**
     * Do two dates represent the same actual day?
     *
     * @param cal1
     * @param cal2
     * @return boolean
     */
    public static boolean isSameDay(final Calendar cal1, final Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isYesterday(Date date) {
        Calendar c1 = Calendar.getInstance(); // today
        c1.add(Calendar.DAY_OF_YEAR, -1); // yesterday

        Calendar c2 = Calendar.getInstance();
        c2.setTime(date); // your date

        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }


    public static boolean isYesterday(long time) {
        return isYesterday(new Date(time));
    }

    public static boolean isToday(Date date) {
        Calendar c1 = Calendar.getInstance(); // today
        c1.add(Calendar.DAY_OF_YEAR, 0); // yesterday

        Calendar c2 = Calendar.getInstance();
        c2.setTime(date); // your date

        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isToday(long time) {
        return isToday(new Date(time));
    }

    public static boolean isTomorrow(Date date) {
        Calendar c1 = Calendar.getInstance(); // today
        c1.add(Calendar.DAY_OF_YEAR, 1); // yesterday

        Calendar c2 = Calendar.getInstance();
        c2.setTime(date); // your date

        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isTomorrow(long time) {
        return isTomorrow(new Date(time));
    }

    /**
     * Check if a date falls between two specified dates
     *
     * @param from Starting date of date range to check
     * @param to   End date of date range to check
     * @param test Date you want to test
     * @return
     * @throws DateRangeException if End date is before Start date
     */
    public static boolean isBetween(Date from, Date to, Date test) throws DateRangeException {
        if (to.before(from) || from.after(to)) {
            throw new DateRangeException("From date is after to date");
        }
        return !test.before(from) && !test.after(to);
    }

    /**
     * Get the number of days between now and a specified date.
     *
     * @param date
     * @return long - number of days until specified data. Returns a negative value if date has already passed.
     */
    public static long getDaysUntil(Date date) {

        Calendar c1 = Calendar.getInstance();
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(date); // your date
        c1.set(Calendar.HOUR_OF_DAY, 0);
        c1.set(Calendar.MINUTE, 0);
        c1.set(Calendar.SECOND, 0);
        c1.set(Calendar.MILLISECOND, 0);

        return TimeUnit.MILLISECONDS.toDays(c2.getTimeInMillis() - c1.getTimeInMillis());
    }
}
