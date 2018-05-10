package com.machine.utils.date;

import com.machine.utils.envm.DateEnum;
import com.machine.utils.tuple.TwoTuple;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期工具类
 *
 * Created by yanshan.chen on 2018/05/10.
 */
public class DateUtils {

    /*得到对应描述的日期范围-------start*/

    public static TwoTuple<Date, Date> getScope(DateEnum dateEnum) {
        switch (dateEnum) {
            case YESTER_DAY:
                return getScope4Yesterday();
            case CURRENT_DAY:
                return getScope4CurrentDay();
            case CURRENT_WEEK:
                return getScope4CurrentWeek();
            case CURRENT_MONTH:
                return getScope4CurrentMonth();
            case CURRENT_QUARTER:
                return getScope4CurrentQuarter();
            case CURRENT_YEAR:
                return getScope4CurrentYear();
            default:
                return null;
        }
    }

    public static TwoTuple<Date, Date> getScope4Yesterday() {
        return new TwoTuple(getYesterdayStart(), getCurrentDayStart());
    }

    public static TwoTuple<Date, Date> getScope4CurrentDay() {
        return new TwoTuple(getCurrentDayStart(), getTomorrowStart());
    }

    public static TwoTuple<Date, Date> getScope4CurrentWeek() {
        return new TwoTuple(getCurrentWeekStart(), getNextWeekStart());
    }

    public static TwoTuple<Date, Date> getScope4CurrentMonth() {
        return new TwoTuple(getCurrentMonthStart(), getNextMonthStart());
    }

    public static TwoTuple<Date, Date> getScope4CurrentQuarter() {
        return new TwoTuple(getCurrentQuarterStart(), getNextQuarterStart());
    }

    public static TwoTuple<Date, Date> getScope4CurrentYear() {
        return new TwoTuple(getCurrentYearStart(), getNextYearStart());
    }

    /**
     * 获得当天开始时间
     */
    public static Date getCurrentDayStart() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    /**
     * 获得昨天开始时间
     */
    public static Date getYesterdayStart() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getCurrentDayStart().getTime() - 3600 * 24 * 1000);
        return cal.getTime();
    }

    /**
     * 获得明天开始时间
     */
    public static Date getTomorrowStart() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(getCurrentDayStart().getTime() + 3600 * 24 * 1000);
        return cal.getTime();
    }

    /**
     * 获得本周开始时间
     */
    public static Date getCurrentWeekStart() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得下周开始时间
     */
    public static Date getNextWeekStart() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentWeekStart());
        cal.add(Calendar.DAY_OF_WEEK, 7);
        return cal.getTime();
    }

    /**
     * 获得本月开始时间
     */
    public static Date getCurrentMonthStart() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获得下个月开始时间
     */
    public static Date getNextMonthStart() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentMonthStart());
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
    }

    /**
     * 当前季度的开始时间
     */
    public static Date getCurrentQuarterStart() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 下一个季度的开始时间
     */
    public static Date getNextQuarterStart() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentQuarterStart());
        cal.add(Calendar.MONTH, 3);
        return cal.getTime();
    }

    /**
     * 本年开始时间
     */
    public static Date getCurrentYearStart() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.YEAR));
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 明年开始时间
     */
    public static Date getNextYearStart() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getCurrentYearStart());
        cal.add(Calendar.YEAR, +1);
        return cal.getTime();
    }
    /*得到对应描述的日期范围-------end*/
}
