package util.android.date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    public void parseDate() throws DateParseException {
        testStandardUTCAtom();
        parseBasicJSDate();
        parseInvalidDate();
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
}