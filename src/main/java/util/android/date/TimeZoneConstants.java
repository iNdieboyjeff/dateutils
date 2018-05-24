package util.android.date;

import java.util.TimeZone;

/**
 * Series of constants representing some standard time zones.
 */
public class TimeZoneConstants {

    private TimeZoneConstants() {
    }

    public static final TimeZone TZ_LONDON = TimeZone.getTimeZone("Europe/London");
    public static final TimeZone TZ_LISBON = TimeZone.getTimeZone("Europe/Lisbon");
    public static final TimeZone TZ_MADRID = TimeZone.getTimeZone("Europe/Madrid");
    public static final TimeZone TZ_PARIS = TimeZone.getTimeZone("Europe/Paris");
    public static final TimeZone TZ_ROME = TimeZone.getTimeZone("Europe/Rome");
    public static final TimeZone TZ_MOSCOW = TimeZone.getTimeZone("Europe/Moscow");
    public static final TimeZone TZ_UTC = TimeZone.getTimeZone("UTC");
    public static final TimeZone TZ_GMT = TimeZone.getTimeZone("GMT");
    public static final TimeZone TZ_BST = TimeZone.getTimeZone("BST");
    public static final TimeZone TZ_CET = TimeZone.getTimeZone("CET");

}
