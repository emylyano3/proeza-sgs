package com.proeza.core.util.date;

import java.util.Date;

import org.thymeleaf.util.DateUtils;

public class DateUtil {

    public static Date create (int year, int month, int day) {
        return DateUtils.create(year, month, day).getTime();
    }

    public static Date createNow () {
        return DateUtils.createNow().getTime();
    }
}