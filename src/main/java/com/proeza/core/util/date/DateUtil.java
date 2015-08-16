package com.proeza.core.util.date;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.thymeleaf.util.DateUtils;

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
    public static Date firstDayOfCurrentMonth (Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return create(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
    }

    /**
     * Devuelve la fecha del primer dia del año
     */
    public static Date firstDayOfYear (Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return create(cal.get(Calendar.YEAR), 1, 1);
    }

    /**
     * Devuelve la fecha del primer dia de la semana
     */
    public static Date firstDayOfWeek (Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dom = cal.get(Calendar.DAY_OF_MONTH);
        int dow = cal.get(Calendar.DAY_OF_WEEK);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        if (dow > dom) {
            if (month == 1) {
                cal.set(Calendar.YEAR, --year);
                cal.set(Calendar.MONTH, 12);
                cal.set(Calendar.DAY_OF_MONTH, 31 - (dow - dom));
            } else {
                cal.set(Calendar.MONTH, --month);
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH) - (dow - dom));
            }
        } else {
            cal.set(Calendar.DAY_OF_MONTH, dom - dow);
        }
        return cal.getTime();
    }

    public static Timestamp createNowstamp () {
        return new Timestamp(DateUtils.createNow().getTime().getTime());
    }
}