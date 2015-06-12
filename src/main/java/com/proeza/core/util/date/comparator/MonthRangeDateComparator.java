package com.proeza.core.util.date.comparator;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;

public class MonthRangeDateComparator implements Comparator<Date> {

    @Override
    public int compare (Date d1, Date d2) {
        if (d1 == null && d2 == null) {
            return 0;
        }
        if (d1 == null) {
            return -1;
        }
        if (d2 == null) {
            return 1;
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(d1);
        int firstMonth = calendar.get(Calendar.MONTH);
        int firstYear = calendar.get(Calendar.YEAR);
        calendar.setTime(d2);
        int lastMonth = calendar.get(Calendar.MONTH);
        int lastYear = calendar.get(Calendar.YEAR);
        return firstYear * 12 + firstMonth - (lastYear * 12 + lastMonth);
    }
}