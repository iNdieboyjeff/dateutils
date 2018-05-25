package util.android.date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertNotNull;

public class SQLDateTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        assertNotNull(new SQLDate());
    }

    @Test
    public void parseBasicDate() throws DateParseException {
        String dateIn = "2018-06-25";
        Date date = SQLDate.parseDate(dateIn);
        assertNotNull(date);
    }

    @Test
    public void parseBasicDate2() throws DateParseException {
        String dateIn = "2018-6-25";
        Date date = SQLDate.parseDate(dateIn);
        assertNotNull(date);
    }

    @Test
    public void parseBasicDate3() throws DateParseException {
        String dateIn = "2018-6-9";
        Date date = SQLDate.parseDate(dateIn);
        assertNotNull(date);
    }

    @Test(expected = DateParseException.class)
    public void parseNullDate() throws DateParseException {
        SQLDate.parseDate(null);
    }

    @Test(expected = DateParseException.class)
    public void parseInvalidDat1e() throws DateParseException {
        String dateIn = "bob";
        Date date = SQLDate.parseDate(dateIn);
    }

    @Test(expected = DateParseException.class)
    public void parseInvalidDate2() throws DateParseException {
        String dateIn = "2018-6-90";
        SQLDate.parseDate(dateIn);
    }
}