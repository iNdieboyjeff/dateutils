package util.android.date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DateConstantsTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        assertNotNull(new DateConstants());
    }

    @Test
    @SuppressWarnings("squid:S3415")
    public void testMillisPerSecond() {
        assertEquals(1000, DateConstants.MILLIS_PER_SECOND);
    }

    @Test
    @SuppressWarnings("squid:S3415")
    public void testMillisPerminute() {
        assertEquals(60000, DateConstants.MILLIS_PER_MINUTE);
    }
}