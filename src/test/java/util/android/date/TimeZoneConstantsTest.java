package util.android.date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.TimeZone;

import static org.junit.Assert.*;

public class TimeZoneConstantsTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        assertNotNull(new TimeZoneConstants());
    }

    @Test
    public void testCreateLondonTimeZone() {
        TimeZone timeZone = TimeZoneConstants.TZ_LONDON;
        assertNotNull(timeZone);
    }

    @Test
    public void testCreateLisbonTimeZone() {
        TimeZone timeZone = TimeZoneConstants.TZ_LISBON;
        assertNotNull(timeZone);
        testNotGMT(timeZone);
    }

    @Test
    public void testCreateMadridTimeZone() {
        TimeZone timeZone = TimeZoneConstants.TZ_MADRID;
        assertNotNull(timeZone);
        testNotGMT(timeZone);
    }

    @Test
    public void testCreateParisTimeZone() {
        TimeZone timeZone = TimeZoneConstants.TZ_PARIS;
        assertNotNull(timeZone);
        testNotGMT(timeZone);
    }

    @Test
    public void testCreateRomeTimeZone() {
        TimeZone timeZone = TimeZoneConstants.TZ_ROME;
        assertNotNull(timeZone);
        testNotGMT(timeZone);
    }

    @Test
    public void testCreateMoscowTimeZone() {
        TimeZone timeZone = TimeZoneConstants.TZ_MOSCOW;
        assertNotNull(timeZone);
        testNotGMT(timeZone);
    }

    @Test
    public void testCreateGMTTimeZone() {
        TimeZone timeZone = TimeZoneConstants.TZ_GMT;
        assertNotNull(timeZone);
        assertEquals(timeZone, TimeZone.getTimeZone("GMT"));
    }

    @Test
    public void testCreateBSTTimeZone() {
        TimeZone timeZone = TimeZoneConstants.TZ_BST;
        assertNotNull(timeZone);
        testNotGMT(timeZone);
    }

    @Test
    public void testCreateCETTimeZone() {
        TimeZone timeZone = TimeZoneConstants.TZ_CET;
        assertNotNull(timeZone);
        testNotGMT(timeZone);
    }

    void testNotGMT(TimeZone timeZone) {
        assertNotEquals(timeZone, TimeZone.getTimeZone("GMT"));
    }
}