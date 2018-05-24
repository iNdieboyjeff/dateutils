package util.android.date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class AtomDateTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown()  {
    }

    @Test
    public void testStandardUTCAtom() throws AtomDateParseException {
        String atomDate = "2009-11-04T19:55:41Z";
        Date dateVal = AtomDate.parseDate(atomDate);
        assertEquals(1257364541000L, dateVal.getTime());
    }

    @Test
    public void testAtomWithoutTimeZone() throws AtomDateParseException {
        String atomDate = "2009-11-04T19:55:41";
        Date dateVal = AtomDate.parseDate(atomDate);
        assertEquals(1257364541000L, dateVal.getTime());
    }

    @Test
    public void testStandardAtomWithPositiveOffset() throws AtomDateParseException {
        String atomDate = "2009-11-04T21:55:41+0200";
        Date dateVal = AtomDate.parseDate(atomDate);
        assertEquals(1257364541000L, dateVal.getTime());
    }

    @Test
    public void testStandardAtomWithNegativeOffset() throws AtomDateParseException  {
        String atomDate = "2009-11-04T17:55:41-0200";
        Date dateVal = AtomDate.parseDate(atomDate);
        assertEquals(1257364541000L, dateVal.getTime());
    }

    @Test
    public void testAtomWithSpaceBeforePositiveOffset() throws AtomDateParseException {
        String atomDate = "2009-11-04T21:55:41 +0200";
        Date dateVal = AtomDate.parseDate(atomDate);
        assertEquals(1257364541000L, dateVal.getTime());
    }

    @Test
    public void testAtomWithSpaceBeforeNegativeOffset() throws AtomDateParseException {
        String atomDate = "2009-11-04T17:55:41 -0200";
        Date dateVal = AtomDate.parseDate(atomDate);
        assertEquals(1257364541000L, dateVal.getTime());
    }

    @Test(expected = AtomDateParseException.class)
    public void testParseException() throws AtomDateParseException {
        String atomDate = "bob";
        AtomDate.parseDate(atomDate);
    }

    @Test
    public void testATOMNoSeconds() throws AtomDateParseException {
        String atomDate1 = "2009-11-04T19:55:00Z";
        String atomDate2 = "2009-11-04T19:55";
        Date dateVal1 = AtomDate.parseDate(atomDate1);
        Date dateVal2 = AtomDate.parseDate(atomDate2);
        assertEquals(dateVal2.getTime(), dateVal1.getTime());
        assertEquals(1257364500000L, dateVal1.getTime());
        assertEquals(1257364500000L, dateVal2.getTime());
    }

    @Test
    public void testYearOnly() throws AtomDateParseException {
        String atomDate = "1970";
        Date dateVal = AtomDate.parseDate(atomDate);
        assertEquals(0L, dateVal.getTime());
    }

    @Test(expected = AtomDateParseException.class)
    public void testNoDate() throws AtomDateParseException {
        AtomDate.parseDate(null);
    }

    @Test
    public void testFormatAtomDateWithoutTimeZone() {
        String atomDate = "2009-11-04T19:55:41Z";
        Date dateVal = new Date(1257364541000L);
        assertEquals(atomDate, AtomDate.formatAtomDate(dateVal));
    }

    @Test
    public void testFormatAtomTimeWithoutTimeZone() {
        String atomDate = "2009-11-04T19:55:41Z";
        assertEquals(atomDate, AtomDate.formatAtomDate(1257364541000L));
    }

    @Test
    public void testFormatAtomDateWithTimeZone() {
        String atomDate = "2009-11-04T20:55:41+01:00";
        Date dateVal = new Date(1257364541000L);
        assertEquals(atomDate, AtomDate.formatAtomDate(dateVal, TimeZoneConstants.TZ_CET));
    }

    @Test
    public void testParseAndFormatAtomDateWithDifferentTimeZone() throws AtomDateParseException {
        String atomDate = "2009-11-04T19:55:41Z";
        String atomDateOut = "2009-11-04T20:55:41+01:00";
        Date parsedDate = AtomDate.parseDate(atomDate);
        Date dateVal = new Date(1257364541000L);
        assertEquals(atomDate, AtomDate.formatAtomDate(dateVal));
        assertEquals(atomDateOut, AtomDate.formatAtomDate(dateVal, TimeZoneConstants.TZ_CET));
        assertNotEquals(AtomDate.formatAtomDate(dateVal), AtomDate.formatAtomDate(dateVal, TimeZoneConstants.TZ_CET));
        assertEquals(parsedDate, dateVal);
        assertEquals(parsedDate.getTime(), dateVal.getTime());
    }

}