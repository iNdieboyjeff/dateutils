package util.android.date;

import java.util.TimeZone;

/**
 * Series of constants representing some standard time zones.
 *
 * You can find out more at
 *
 * https://en.wikipedia.org/wiki/List_of_tz_database_time_zones
 * https://en.wikipedia.org/wiki/List_of_time_zone_abbreviations
 */
public class TimeZoneConstants {

    TimeZoneConstants() {
    }

    public static final TimeZone TZ_LONDON = TimeZone.getTimeZone("Europe/London");
    public static final TimeZone TZ_LISBON = TimeZone.getTimeZone("Europe/Lisbon");
    public static final TimeZone TZ_ISTANBUL = TimeZone.getTimeZone("Europe/Istanbul");
    public static final TimeZone TZ_MADRID = TimeZone.getTimeZone("Europe/Madrid");
    public static final TimeZone TZ_PARIS = TimeZone.getTimeZone("Europe/Paris");
    public static final TimeZone TZ_ROME = TimeZone.getTimeZone("Europe/Rome");
    public static final TimeZone TZ_MOSCOW = TimeZone.getTimeZone("Europe/Moscow");

    public static final TimeZone TZ_UTC = TimeZone.getTimeZone("UTC"); // Coordinated Universal Time
    public static final TimeZone TZ_GMT = TimeZone.getTimeZone("GMT"); // Greenwich Mean Time (= UTC)
    public static final TimeZone TZ_BST = TimeZone.getTimeZone("BST"); // British Summer Time
    public static final TimeZone TZ_CET = TimeZone.getTimeZone("CET"); // Central European Time
    public static final TimeZone TZ_EET = TimeZone.getTimeZone("EET"); // Eastern European Time

}
