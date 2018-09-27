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
        String atomDate = "2009-11-04T17:55:41-02:00";
        Date dateVal = AtomDate.parseDate(atomDate);
        assertEquals(1257364541000L, dateVal.getTime());
    }

    @Test
    public void testStandardAtomWithPositiveOffset2() throws AtomDateParseException {
        String atomDate = "2009-11-04T21:55:41+02:00";
        Date dateVal = AtomDate.parseDate(atomDate);
        assertEquals(1257364541000L, dateVal.getTime());
    }

    @Test
    public void testStandardAtomWithMS1() throws AtomDateParseException {
        String atomDate = "2018-05-25T07:36:28.418Z";
        Date dateVal = AtomDate.parseDate(atomDate);
        assertEquals(1527233788418L, dateVal.getTime());
    }

    @Test
    public void testStandardAtomWithNegativeOffset2() throws AtomDateParseException {
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

    @Test
    public void testStandardAtomWithZPositiveOffset() throws AtomDateParseException {
        String atomDate = "2009-11-04T21:55:41Z+0200";
        Date dateVal = AtomDate.parseDate(atomDate);
        assertNotEquals(1257364541000L, dateVal.getTime());
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
    public void testFormatAtomDateMSWithoutTimeZone1() {
        String atomDate = "2009-11-04T19:55:41.000Z";
        Date dateVal = new Date(1257364541000L);
        assertEquals(atomDate, AtomDate.formatAtomDateWithMS(dateVal));
    }

    @Test
    public void testFormatAtomDateMSWithoutTimeZone2() {
        String atomDate = "2018-05-25T07:36:28.418Z";
        Date dateVal = new Date(1527233788418L);
        assertEquals(atomDate, AtomDate.formatAtomDateWithMS(dateVal));
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

    @Test(expected = IllegalArgumentException.class)
    public void testFormatAtomDateWithNoDate1() {
        AtomDate.formatAtomDate(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormatAtomDateWithNoDate2() {
        AtomDate.formatAtomDate(null, TimeZoneConstants.TZ_CET);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormatAtomDateWithNoDate3() {
        AtomDate.formatAtomDateWithMS(null, TimeZoneConstants.TZ_CET);
    }


    @Test
    public void testFormatAtomTimeWithTimeZone() {
        String atomDate = "2009-11-04T20:55:41+01:00";
        assertEquals(atomDate, AtomDate.formatAtomDate(1257364541000L, TimeZoneConstants.TZ_CET));
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


    @Test
    public void testConstructor() {
        assertNotNull(new AtomDate());
    }
}