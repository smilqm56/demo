package com.fxw.mylibrary.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xianwen.fu
 * @version v1.0
 * @Title   时间格式的转换工具
 * @Description:
 * @Date 2016/03/04下午 2:40
 */
public class DateUtil {

    /**
     * date类型转换为String类型
     * @param data date类型时间
     * @param formatType 格式
     * @return String类型时间
     */
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }
    /**
     * long类型转换Strig类型时间
     * @param currentTime 要转换的long类型的时间
     * @param formatType 格式
     * @return String类型时间
     * @throws ParseException
     * @throws java.text.ParseException
     */
    public static String longToString(long currentTime, String formatType)
            throws ParseException, java.text.ParseException {
        Date date = longToDate(currentTime, formatType); // long类型转成Date类型
        String strTime = dateToString(date, formatType); // date类型转成String
        return strTime;
    }

    /**
     * string类型转换为date类型 strTime的时间格式必须要与formatType的时间格式相同
     * @param strTime 要转换的string类型的时间
     * @param formatType 要转换的格式
     * @return  date类型时间
     * @throws ParseException
     * @throws java.text.ParseException
     */
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException, java.text.ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }
    /**
     * long转换为Date类型
     * @param currentTime 要转换的long类型的时间
     * @param formatType 要转换的时间格式
     * @return date类型时间
     * @throws ParseException
     * @throws java.text.ParseException
     */
    public static Date longToDate(long currentTime, String formatType)
            throws ParseException, java.text.ParseException {
        Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
        String sDateTime = dateToString(dateOld, formatType); // 把date类型的时间转换为string
        Date date = stringToDate(sDateTime, formatType); // 把String类型转换为Date类型
        return date;
    }

    /**
     * string类型转换为long类型
     * strTime的时间格式和formatType的时间格式必须相同
     * @param strTime 要转换的String类型的时间
     * @param formatType 时间格式
     * @return long类型时间
     * @throws ParseException
     * @throws java.text.ParseException
     */
    public static long stringToLong(String strTime, String formatType)
            throws ParseException, java.text.ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }
    /**
     * date类型转换为long类型
     * @param date 要转换的date类型的时间
     * @return long类型时间
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }

    /**
     * int类型时间
     * @param number  要转换的int类型的时间
     * @param formatType 转格式
     * @return String类型时间
     */
    public static String numToDate(int number,String formatType){
        Date date = new Date(number);
        SimpleDateFormat sdf = new SimpleDateFormat(formatType);
        return sdf.format(date);
    }

    /**
     *  获取当前时间
     */
    @SuppressLint("SimpleDateFormat") public static String getDate(String format) {
        Calendar c = Calendar.getInstance();// 获得系统当前日期
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String newTime = sdf.format(c.getTime());
        return newTime;
    }
    /**
     * String 类型 时间格式  转换
     * @param date 时间
     * @param format 格式
     * @return
     */
    @SuppressLint("SimpleDateFormat") public static String getFormatDate(String date,String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date newTime = null;
        try {
            newTime = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdf.format(newTime);
    }
    /**
     *
     * 比较2个时间大小
     * @param mStartDate  大  返回false
     * @param mEndDate    大 返回true
     * @param mFormat   格式
     * @return
     */
    @SuppressLint("SimpleDateFormat") public static Boolean compareDataTime(String mStartDate,String mEndDate,String mFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(mFormat);
        try {
            long mStartDateTime = sdf.parse(mStartDate).getTime();
            long mEndDateTime = sdf.parse(mEndDate).getTime();
            if (mStartDateTime > mEndDateTime) {
                return false;
            }else {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定日期是星期几
     * 参数为null时表示获取当前日期是星期几
     * @param date
     * @return
     */
    public static String getWeekOfDate(Date date) {
        String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        if(date != null){
            calendar.setTime(date);
        }
        int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekOfDays[w];
    }
}
