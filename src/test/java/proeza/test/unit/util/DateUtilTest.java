package proeza.test.unit.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import static com.proeza.core.util.date.DateUtil.*;
import static java.util.Calendar.*;
import static org.junit.Assert.*;

public class DateUtilTest {

    @Test
    public void firstDayOfWeek_1 () {
        Calendar cal = new GregorianCalendar();
        cal.set(DAY_OF_MONTH, 2);
        cal.set(MONTH, JANUARY);
        cal.set(YEAR, 2015);
        Date fdow = firstDayOfWeek(cal.getTime());
        cal.set(DAY_OF_MONTH, 28);
        cal.set(MONTH, DECEMBER);
        cal.set(YEAR, 2014);
        assertEquals(cal.getTime(), fdow);
    }

    @Test
    public void firstDayOfWeek_2 () {
        Calendar cal = new GregorianCalendar();
        cal.set(DAY_OF_MONTH, 2);
        cal.set(MONTH, APRIL);
        cal.set(YEAR, 2015);
        Date fdow = firstDayOfWeek(cal.getTime());
        cal.set(DAY_OF_MONTH, 29);
        cal.set(MONTH, MARCH);
        cal.set(YEAR, 2015);
        assertEquals(cal.getTime(), fdow);
    }

    @Test
    public void firstDayOfWeek_3 () {
        Calendar cal = new GregorianCalendar();
        cal.set(DAY_OF_MONTH, 25);
        cal.set(MONTH, APRIL);
        cal.set(YEAR, 2015);
        Date fdow = firstDayOfWeek(cal.getTime());
        cal.set(DAY_OF_MONTH, 19);
        cal.set(MONTH, APRIL);
        cal.set(YEAR, 2015);
        assertEquals(cal.getTime(), fdow);
    }

    @Test
    public void substractMonths_1 () {
        Calendar cal = new GregorianCalendar();
        cal.set(DAY_OF_MONTH, 25);
        cal.set(MONTH, APRIL);
        cal.set(YEAR, 2015);
        Date result = substractMonths(cal.getTime(), 3);
        cal.set(DAY_OF_MONTH, 25);
        cal.set(MONTH, JANUARY);
        cal.set(YEAR, 2015);
        assertEquals(result, cal.getTime());
    }

    @Test
    public void substractMonths_2 () {
        Calendar cal = new GregorianCalendar();
        cal.set(DAY_OF_MONTH, 25);
        cal.set(MONTH, APRIL);
        cal.set(YEAR, 2015);
        Date result = substractMonths(cal.getTime(), 7);
        cal.set(DAY_OF_MONTH, 25);
        cal.set(MONTH, SEPTEMBER);
        cal.set(YEAR, 2014);
        assertEquals(result, cal.getTime());
    }

    @Test
    public void substractMonths_3 () {
        Calendar cal = new GregorianCalendar();
        cal.set(DAY_OF_MONTH, 25);
        cal.set(MONTH, APRIL);
        cal.set(YEAR, 2015);
        Date result = substractMonths(cal.getTime(), 19);
        cal.set(DAY_OF_MONTH, 25);
        cal.set(MONTH, SEPTEMBER);
        cal.set(YEAR, 2013);
        assertEquals(result, cal.getTime());
    }
}