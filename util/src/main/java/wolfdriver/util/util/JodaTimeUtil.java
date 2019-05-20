package wolfdriver.util.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.util.*;


/**
 * JodaTime 日期处理类
 *
 * @author wolf_z
 * @date 2018/6/5 9:29
 */
public class JodaTimeUtil {
    private static final int WEEK_DAYS = 7;
    public static final String format_default = "yyyy-MM-dd HH:mm:ss";
    public static final String format_yMd = "yyyy-MM-dd";

    public static final int year = 1;
    public static final int month = 2;
    public static final int day = 3;
    public static final int hour = 4;
    public static final int minute = 5;
    public static final int second = 6;
    public static final int millis = 7;


    /**
     * 获取当前时间字符串
     */
    public static String getNowTime(String... format) {
        String fm = format_default;
        if (format != null && format.length > 0) {
            fm = format[0];
        }
        return new DateTime().toString(fm);
    }

    /**
     * date 转jodaTime
     */
    public static DateTime dateToDateTime(Date date) {
        return new DateTime(date);
    }

    /**
     * 日期转字符串
     */
    public static String dateToString(Date date, String... format) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        String pattern = format_default;
        if (isNotEmpty(format)) {
            pattern = format[0];
        }
        return dateToDateTime(date).toString(pattern);
    }


    /**
     * 字符串转日期
     */
    public static DateTime stringToDateTime(String time) {
        return DateTime.parse(time, DateTimeFormat.forPattern(format_default));
    }


    /**
     * 字符串转年月日
     */
    public static Calendar stringToCalendarYMD(String time) {
        return DateTime.parse(time, DateTimeFormat.forPattern(format_yMd))
                .toCalendar(Locale.getDefault());
    }


    public static DateTime plusNumToDateTime(int num, int key, DateTime... dt) {
        DateTime dateTime = DateTime.now();
        if (isNotEmpty(dt)) {
            dateTime = dt[0];
        }
        switch (key) {
            case year:
                dateTime = dateTime.plusYears(num);
                break;
            case month:
                dateTime = dateTime.plusMonths(num);
                break;
            case day:
                dateTime = dateTime.plusDays(num);
                break;
            case hour:
                dateTime = dateTime.plusHours(num);
                break;
            case minute:
                dateTime = dateTime.plusMinutes(num);
                break;
            case second:
                dateTime = dateTime.plusSeconds(num);
                break;
            default:
                dateTime = dateTime.plusHours(num);
                break;
        }
        return dateTime;
    }

    /**
     * 返回00:00:00
     */
    public static Calendar getCalendarMorning(Calendar c) {
        return getDateTimeMorning(c).toCalendar(Locale.getDefault());
    }


    /**
     * 返回00:00:00
     */
    public static DateTime getDateTimeMorning(Calendar c) {
        DateTime dateTime = calendarToDateTime(c);
        dateTime = dateTime.withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0);
        return dateTime;
    }

    /**
     * 返回23:59:59
     *
     * @param c
     * @return
     */
    public static Calendar getCalendarMidnight(Calendar c) {
        return getDateTimeMidnight(c).toCalendar(Locale.getDefault());
    }

    /**
     * 返回23:59:59
     *
     * @param c
     * @return
     */
    public static DateTime getDateTimeMidnight(Calendar c) {
        DateTime dateTime = calendarToDateTime(c);
        dateTime = dateTime.withHourOfDay(23).withMinuteOfHour(59)
                .withSecondOfMinute(59);
        return dateTime;
    }

    /**
     * 一周中的第几天。周一：１　周二：２ ...
     *
     * @param c
     * @return
     */
    public static int getDayInWeek(Calendar c) {
        if (c == null) {
            c = Calendar.getInstance();
        }
        return new DateTime(c).getDayOfWeek();
    }


    /**
     * 日期在年中第几周
     *
     * @param c
     * @return
     */
    public static int getWeekInYear(Calendar c) {
        if (c == null) {
            c = Calendar.getInstance();
        }
        return new DateTime(c).getWeekOfWeekyear();
    }

    public static int getWeekOfYMD(int year, int monthOfYear, int dayOfMonth) {
        LocalDate date = new LocalDate(year, monthOfYear, dayOfMonth);
        return date.weekOfWeekyear().get();
    }

    /**
     * 某年某周第一天
     *
     * @param year
     * @param weekOfWeekyear
     * @return
     */
    public static DateTime getFirstDayOfYearWeek(int year, int weekOfWeekyear) {
        DateTime dateTime = DateTime.now();
        dateTime = dateTime.withYear(year).withWeekOfWeekyear(weekOfWeekyear).dayOfWeek().withMinimumValue();
        return dateTime;
    }

    public static DateTime getLastDayOfYearWeek(int year, int weekOfWeekyear) {
        return DateTime.now().withYear(year).withWeekOfWeekyear(weekOfWeekyear)
                .dayOfWeek().withMaximumValue();
    }

    public static DateTime getFirstDayInNowWeek() {
        return DateTime.now().dayOfWeek().withMinimumValue();
    }

    public static DateTime getLastDayInNowWeek() {
        return DateTime.now().dayOfWeek().withMaximumValue();
    }

    public static DateTime getLastDayOfYearMonth(int year, int monthOfYear) {
        return DateTime.now().withYear(year).withMonthOfYear(monthOfYear)
                .dayOfMonth().withMaximumValue();
    }

    public static DateTime getLastDayOfNowMonth() {
        return DateTime.now().dayOfMonth().withMaximumValue();
    }


    public static Map<String, String> getDateOfWeekMap(int year, int week,
                                                       String... format) {
        String pattern = format_yMd;
        if (isNotEmpty(format)) {
            pattern = format[0];
        }
        Map<String, String> map = new LinkedHashMap<String, String>();
        DateTime dateTime = DateTime.now().withYear(year)
                .withWeekOfWeekyear(week).dayOfWeek().withMinimumValue();
        for (int i = 0; i < WEEK_DAYS; i++) {
            map.put((i + 1) + "", dateTime.plusDays(i).toString(pattern));
        }
        return map;
    }

    public static Map<String, String> getWeekAssertDateMap(String... format) {
        return getWeekAssertDateMap(2, true, format);
    }

    public static Map<String, String> getWeekAssertDateMap(int type,
                                                           String... format) {
        return getWeekAssertDateMap(type, true, format);
    }

    /**
     * 今日起一周内对应日期
     *
     * @param type               　１：数字(1-7) 2:周几　default:星期几
     * @param isChangeWeekToDate 默认true{key:type，value:日期}; false:{key:日期，value:type}
     * @param format
     * @return
     */
    public static Map<String, String> getWeekAssertDateMap(int type,
                                                           boolean isChangeWeekToDate, String... format) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        String pattern = "yyMMdd";
        if (isNotEmpty(format)) {
            pattern = format[0];
        }
        String[] weekArr = new String[]{};
        switch (type) {
            case 1:
                weekArr = new String[]{"1", "2", "3", "4", "5", "6", "7"};
                break;
            case 2:
                weekArr = new String[]{"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
                break;
            default:
                weekArr = new String[]{"星期一", "星期二", "星期三", "星期四", "星期五", "星期六",
                        "星期日"};
                break;
        }
        DateTime dateTime = DateTime.now();
        for (int i = 0; i < weekArr.length; i++) {
            int day = dateTime.getDayOfWeek();
            if (isChangeWeekToDate) {
                map.put(weekArr[day - 1], dateTime.toString(pattern));
            } else {
                map.put(dateTime.toString(pattern), weekArr[day - 1]);
            }
            dateTime = dateTime.plusDays(1);
        }
        return map;
    }

    public static String getDayOfWeek(Calendar c) {
        return new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"}[c.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public static <T> boolean isNotEmpty(@SuppressWarnings("unchecked") T... obj) {
        if (obj != null && obj.length > 0 && obj[0] != null) {
            if (obj[0] instanceof String) {
                if (StringUtils.isBlank(obj[0].toString())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    public static DateTime calendarToDateTime(Calendar c) {
        return new DateTime(c);
    }

}
