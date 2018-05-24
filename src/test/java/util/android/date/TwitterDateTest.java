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

    @Test(expected = DateParseException.class)
    public void parseNullDate() throws DateParseException {
        TwitterDate.parseDate(null);
    }
}