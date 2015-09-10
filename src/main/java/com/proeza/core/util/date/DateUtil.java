package com.proeza.core.util.date;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.thymeleaf.util.DateUtils;

import static java.util.Calendar.*;

public class DateUtil {

    public static Date create (int year, int month, int day) {
        return DateUtils.create(year, month, day).getTime();
    }

    public static Date createNow () {
        return DateUtils.createNow().getTime();
    }

    /**
     * Devuelve la fecha del primer dia del mes
     */
    public static Date firstDayOfMonth (Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return create(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
    }

    /**
     * Devuelve la fecha del primer dia del año
     */
    public static Date firstDayOfYear (Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return create(cal.get(Calendar.YEAR), 1, 1);
    }

    /**
     * Devuelve la fecha del primer dia del año
     */
    public static Date substractMonths (Date date, int months) {
        Calendar cal = new GregorianCalendar();
        int years = months / 12;
        months = months % 12;
        cal.setTime(date);
        cal.set(YEAR, cal.get(YEAR) - years);
        cal.set(MONTH, cal.get(MONTH) - months);
        return cal.getTime();
    }

    /**
     * Devuelve la fecha del primer dia de la semana
     */
    public static Date firstDayOfWeek (Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        int dom = cal.get(Calendar.DAY_OF_MONTH);
        int dow = cal.get(Calendar.DAY_OF_WEEK);
        if (dow > dom) {
            cal.set(Calendar.DAY_OF_MONTH, dom - dow + 1);
        } else {
            cal.set(Calendar.DAY_OF_MONTH, dom - dow + 1);
        }
        return cal.getTime();
    }

    public static Timestamp createNowstamp () {
        return new Timestamp(DateUtils.createNow().getTime().getTime());
    }
}