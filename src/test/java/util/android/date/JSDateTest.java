package util.android.date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class JSDateTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
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
}