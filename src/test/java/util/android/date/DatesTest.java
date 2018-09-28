package util.android.date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DatesTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testStandardUTCAtom() throws DateParseException {
        String atomDate = "2009-11-04T19:55:41Z";
        Date dateVal = Dates.parseDate(atomDate);
        assertEquals(1257364541000L, dateVal.getTime());
    }

    @Test
    public void parseBasicJSDate() throws DateParseException {
        String dateIn = "Wed May 23 2018 14:18:53 GMT+0200 (CEST)";
        Date date = Dates.parseDate(dateIn);
        assertEquals(1527077933000L, date.getTime());
    }

    @Test(expected = DateParseException.class)
    public void parseInvalidDate() throws DateParseException {
        String dateIn = "I am not a date";
        Dates.parseDate(dateIn);
    }

    @Test(expected = DateParseException.class)
    public void parseNullDate() throws DateParseException {
        Dates.parseDate(null);
    }

    @Test(expected = DateParseException.class)
    public void parseDate() throws DateParseException {
        testStandardUTCAtom();
        parseBasicJSDate();
        parseInvalidDate();
    }

    @Test
    public void testFormatStandardUTCAtom1() throws DateParseException {
        String atomDate = "2009-11-04T19:55:41Z";
        Date dateVal = Dates.parseDate(atomDate);
        assertEquals(atomDate, Dates.asAtomUTC(dateVal));
    }

    @Test
    public void testFormatStandardUTCAtom2() throws DateParseException {
        String atomDate1 = "2009-11-04T19:55:41Z";
        String atomDate2 = "2009-11-04T21:55:41+02:00";
        Date dateVal = Dates.parseDate(atomDate2);
        assertEquals(atomDate1, Dates.asAtomUTC(dateVal));
    }

    @Test
    public void testFormatStandardUTCAtom3() throws DateParseException {
        String atomDate1 = "2009-11-04T19:55:41Z";
        String atomDate2 = "2009-11-04T22:55:41+03:00";
        assertEquals(atomDate1, Dates.asAtomUTC(atomDate2));
    }

    @Test
    public void testFormatStandardUTCAtom4() throws DateParseException {
        String atomDate1 = "2009-11-04T19:55:41Z";
        String atomDate2 = "2009-11-04T22:55:41+03:00";
        Date dateVal = Dates.parseDate(atomDate2);
        assertEquals(atomDate1, Dates.asAtomUTC(dateVal.getTime()));
    }

    @Test
    public void testFormatStandardUTCAtom5() throws DateParseException {
        String atomDate1 = "2009-11-04T19:55:41Z";
        String atomDate2 = "2009-11-04T22:55:41+03:00";
        Date dateVal = Dates.parseDate(atomDate2);
        Calendar calValue = Calendar.getInstance();
        calValue.setTime(dateVal);
        assertEquals(atomDate1, Dates.asAtomUTC(calValue));
    }

    @Test
    public void testFormatStandardAtom1() throws DateParseException {
        String atomDate1 = "2009-11-04T19:55:41Z";
        String atomDate2 = "2009-11-04T21:55:41+02:00";
        Date dateVal = Dates.parseDate(atomDate2);
        assertEquals(atomDate1, Dates.asAtom(dateVal, TimeZoneConstants.TZ_GMT));
    }

    @Test
    public void testFormatStandardAtom2() throws DateParseException {
        String atomDate1 = "2009-11-04T19:55:41Z";
        String atomDate2 = "2009-11-04T21:55:41+02:00";
        Date dateVal = Dates.parseDate(atomDate1);
        assertEquals(atomDate2, Dates.asAtom(dateVal, TimeZoneConstants.TZ_EET));
    }

    @Test
    public void testIsSameDayDateWithSameDayDates() {
        Date d1 = new Date(1257364541000L);
        Date d2 = new Date(1257364939620L);
        assertTrue(Dates.isSameDay(d1, d2));
    }

    @Test
    public void testIsSameDayDateWithDifferentDayDates() {
        Date d1 = new Date(1257364541000L);
        Date d2 = new Date(1957364939620L);
        assertFalse(Dates.isSameDay(d1, d2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsSameDayDateWithNullFirst1() {
        Date d1 = null;
        Date d2 = new Date(1957364939620L);
        Dates.isSameDay(d1, d2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsSameDayDateWithNullFirst2() {
        Calendar d1 = null;
        Calendar d2 = Calendar.getInstance();
        d2.setTime(new Date(1957364939620L));
        Dates.isSameDay(d1, d2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsSameDayDateWithNullSecond() {
        Date d1 = new Date(1257364541000L);
        Date d2 = null;
        Dates.isSameDay(d1, d2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsSameDayDateWithNullBoth() {
        Date d1 = null;
        Date d2 = null;
        Dates.isSameDay(d1, d2);
    }

    @Test
    public void testIsSameDayOverTimezone() throws AtomDateParseException {
        String atomDate1 = "2009-11-04T19:55:41Z";
        String atomDate2 = "2009-11-04T19:55:41+12:00";
        assertTrue(Dates.isSameDay(AtomDate.parseDate(atomDate1), AtomDate.parseDate(atomDate2)));
    }

    @Test
    public void testGetAgeOnDate1() throws AtomDateParseException {
        String atomDate1 = "1979-06-19T19:55:41Z";
        String atomDate2 = "2018-06-19T19:55:41Z";
        assertEquals(39, Dates.getAgeOnDate(AtomDate.parseDate(atomDate1), AtomDate.parseDate(atomDate2)));
    }

    @Test
    public void testGetAgeOnDate2() throws AtomDateParseException {
        String atomDate1 = "1979-06-19T19:55:41Z";
        String atomDate2 = "2018-06-18T19:55:41Z";
        assertEquals(38, Dates.getAgeOnDate(AtomDate.parseDate(atomDate1), AtomDate.parseDate(atomDate2)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAgeOnDate3() throws AtomDateParseException {
        String atomDate1 = "1979-06-19T19:55:41Z";
        String atomDate2 = "1978-06-18T19:55:41Z";
        assertEquals(-1, Dates.getAgeOnDate(AtomDate.parseDate(atomDate1), AtomDate.parseDate(atomDate2)));
    }

    @Test
    public void testGetDaysUntil1() {
        Date tomorrow = Dates.getTodayPlus(394);
        assertEquals(394, Dates.getDaysUntil(tomorrow));
    }

    @Test
    public void testGetDaysUntil2() {
        Date tomorrow = Dates.getTodayPlus(1);
        assertEquals(1, Dates.getDaysUntil(tomorrow));
    }

    @Test
    public void testIsToday1() {
        Date now = new Date();
        assertTrue(Dates.isToday(now));
    }

    @Test
    public void testIsToday2() {
        Date now = new Date();
        assertTrue(Dates.isToday(now.getTime()));
    }

    @Test
    public void testIsTomorrow1() {
        Date tomorrow = Dates.getTodayPlus(1);
        assertTrue(Dates.isTomorrow(tomorrow));
    }

    @Test
    public void testIsTomorrow2() {
        Date tomorrow = Dates.getTodayPlus(1);
        assertTrue(Dates.isTomorrow(tomorrow.getTime()));
    }

    @Test
    public void testIsYesterday1() {
        Date tomorrow = Dates.getTodayPlus(-1);
        assertTrue(Dates.isYesterday(tomorrow));
    }

    @Test
    public void testIsYesterday2() {
        Date tomorrow = Dates.getTodayPlus(-1);
        assertTrue(Dates.isYesterday(tomorrow.getTime()));
    }

    @Test
    public void testIsBetween1() throws DateParseException, DateRangeException {
        Date from = Dates.parseDate("2000-01-01");
        Date to = Dates.parseDate("2000-12-31");
        Date testDate = Dates.parseDate(("2000-06-01"));
        assertTrue(Dates.isBetween(from, to, testDate));
    }

    @Test
    public void testIsBetween2() throws DateParseException, DateRangeException {
        Date from = Dates.parseDate("2000-01-01");
        Date testDate = Dates.parseDate("2000-12-31");
        Date to = Dates.parseDate(("2000-06-01"));
        assertFalse(Dates.isBetween(from, to, testDate));
    }

    @Test(expected = DateRangeException.class)
    public void testIsBetween3() throws DateParseException, DateRangeException {
        Date to = Dates.parseDate("2000-01-01");
        Date testDate = Dates.parseDate("2000-12-31");
        Date from = Dates.parseDate(("2000-06-01"));
        assertFalse(Dates.isBetween(from, to, testDate));
    }
}