package com.proeza.core.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class DataTypeConverter {

    public static String tostring (Integer i) {
        if (i == null) {
            return null;
        }
        return i.toString();
    }

    public static String tostring (Object o) {
        if (o == null) {
            return null;
        }
        return o.toString();
    }

    public static String tostring (Double d) {
        if (d == null) {
            return null;
        }
        return d.toString();
    }

    public static String tostring (BigDecimal bd) {
        if (bd == null) {
            return null;
        }
        return bd.toString();
    }

    public static Timestamp toTimestamp (Date date) {
        return new Timestamp(date.getTime());
    }
}