package util.android.date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class JSDateTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void parseBasicDate() throws JavaScriptDateParseException {
        String dateIn = "Wed May 23 2018 14:18:53 GMT+0200 (CEST)";
        Date date = JSDate.parseDate(dateIn);
        assertEquals(1527077933000L, date.getTime());
    }

    @Test
    public void parseBasicDate2() throws JavaScriptDateParseException {
        String dateIn = "Wed Mar 25 2015 01:00:00 GMT+0100 (CET)";
        Date date = JSDate.parseDate(dateIn);
        assertEquals(1427241600000L, date.getTime());
    }

    @Test
    public void formatBasicDate1() {
        String dateIn = "Wed May 23 2018 14:18:53 GMT+0200 (CEST)";
        assertEquals(dateIn, JSDate.formatDate(new Date(1527077933000L), TimeZoneConstants.TZ_MADRID));
    }

    @Test
    public void formatBasicDate2() {
        String dateIn = "Wed Mar 25 2015 01:00:00 GMT+0100 (CET)";
        assertEquals(dateIn, JSDate.formatDate(new Date(1427241600000L), TimeZoneConstants.TZ_CET));
    }
}