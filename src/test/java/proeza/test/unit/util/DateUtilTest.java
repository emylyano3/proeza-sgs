package proeza.test.unit.util;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import static com.proeza.core.util.date.DateUtil.*;

public class DateUtilTest {

    @Test
    public void firstDayOfWeek_1 () {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 2);
        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.YEAR, 2015);
        Date fdow = firstDayOfWeek(cal.getTime());
        cal.set(Calendar.DAY_OF_MONTH, 28);
        cal.set(Calendar.MONTH, 12);
        cal.set(Calendar.YEAR, 2014);
        fdow.equals(cal.getTime());
    }

    @Test
    public void firstDayOfWeek_2 () {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 2);
        cal.set(Calendar.MONTH, 4);
        cal.set(Calendar.YEAR, 2015);
        Date fdow = firstDayOfWeek(cal.getTime());
        cal.set(Calendar.DAY_OF_MONTH, 29);
        cal.set(Calendar.MONTH, 3);
        cal.set(Calendar.YEAR, 2015);
        fdow.equals(cal.getTime());
    }

    @Test
    public void firstDayOfWeek_3 () {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 25);
        cal.set(Calendar.MONTH, 4);
        cal.set(Calendar.YEAR, 2015);
        Date fdow = firstDayOfWeek(cal.getTime());
        cal.set(Calendar.DAY_OF_MONTH, 19);
        cal.set(Calendar.MONTH, 4);
        cal.set(Calendar.YEAR, 2015);
        fdow.equals(cal.getTime());
    }
}