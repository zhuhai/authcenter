package com.zhuhai.common.util;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: zhuhai
 * Date: 2017/6/14
 * Time: 15:33
 */
public class DateUtil {

    private static final String DEFAULT = "yyyy-MM-dd HH:mm:ss";
    private static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    private static DateTime dateTime = new DateTime();

    public static String Date2String(Date date, String pattern) {
        return dateTime.toString(pattern);
    }

    public static String Date2String(Date date) {
        return Date2String(date, DEFAULT);
    }


}
