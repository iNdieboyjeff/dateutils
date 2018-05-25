package util.android.date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TwitterDateTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        assertNotNull(new TwitterDate());
    }

    @Test
    public void parseBasicDate() throws DateParseException {
        String dateIn = "Fri Apr 09 12:53:54 +0000 2010";
        String atomDateIn = "2010-04-09T12:53:54Z";
        Date date = TwitterDate.parseDate(dateIn);
        assertEquals(AtomDate.parseDate(atomDateIn).getTime(), date.getTime());
    }

    @Test
    public void parseBasicDate2() throws DateParseException {
        String dateIn = "Thu Apr 06 15:24:15 +0000 2017";
        String atomDateIn = "2017-04-06T15:24:15Z";
        Date date = TwitterDate.parseDate(dateIn);
        assertEquals(AtomDate.parseDate(atomDateIn).getTime(), date.getTime());
    }

    @Test(expected = DateParseException.class)
    public void parseNullDate() throws DateParseException {
        TwitterDate.parseDate(null);
    }

    @Test
    public void formatBasicDate() throws DateParseException {
        String dateIn = "Fri Apr 09 12:53:54 +0000 2010";
        String atomDateIn = "2010-04-09T12:53:54Z";
        Date date = AtomDate.parseDate(atomDateIn);
        assertEquals(dateIn, TwitterDate.formatDate(date));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFormatJSDateWithNoDate1() {
        TwitterDate.formatDate(null);
    }

}