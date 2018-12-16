
/**
 * Project Name:sell.<br/> 
 * File Name:DateUtil.java.<br/> 
 * Package Name:com.imooc.sell.utils.<br/> 
 * Date:2017年12月12日下午12:20:21.<br/> 
 * Copyright (c) 2017, 版权所有 (C) 2016-2036  土豆互联科技(深圳)有限公司 www.potato369.com All Rights Reserved.<br/> 
 */

package com.imooc.sell.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ClassName: DateUtil. <br/>
 * Function: ADD FUNCTION.<br/>
 * Reason: ADD REASON(可选).<br/>
 * Date: 2017年12月12日 下午12:20:21. <br/>
 * Desc: ADD DESC(可选). <br/>
 * 
 * @author 王艳军
 * @version
 * @since JDK 1.6
 */

public class DateUtil {

  private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");

  private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");

  private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");

  private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  private final static SimpleDateFormat sdfTimeMinu = new SimpleDateFormat("yyyyMMddHHmmss");

  private final static SimpleDateFormat sdfTimeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS");

  /**
   * 获取YYYY格式
   * 
   * @return
   */
  public static String getYear() {
    return sdfYear.format(new Date());
  }

  /**
   * 获取YYYY-MM-DD格式
   * 
   * @return
   */
  public static String getDay() {
    return sdfDay.format(new Date());
  }

  /**
   * 获取YYYYMMDD格式
   * 
   * @return
   */
  public static String getDays() {
    return sdfDays.format(new Date());
  }

  /**
   * 获取YYYY-MM-DD HH:mm:ss格式
   * 
   * @return
   */
  public static String getTime() {
    return sdfTime.format(new Date());
  }

  /**
   * 获取YYYYMMDDHHmmss格式
   * 
   * @return
   */
  public static String getTimeMinu() {
    return sdfTimeMinu.format(new Date());
  }

  /**
   * 获取YYYYMMDDHHmmssSSS格式
   * 
   * @return
   */
  public static String getTimestamp() {
    return sdfTimeStamp.format(new Date());
  }

  /**
   * @Title: compareDate
   * @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
   * @param s
   * @param e
   * @return boolean
   * @throws @author
   *           luguosui
   */
  public static boolean compareDate(String s, String e) {
    if (fomatDate(s) == null || fomatDate(e) == null) {
      return false;
    }
    return fomatDate(s).getTime() >= fomatDate(e).getTime();
  }

  /**
   * 格式化日期
   * 
   * @return
   */
  public static Date fomatDate(String date) {
    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    try {
      return fmt.parse(date);
    } catch (ParseException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * 校验日期是否合法
   * 
   * @return
   */
  public static boolean isValidDate(String s) {
    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    try {
      fmt.parse(s);
      return true;
    } catch (Exception e) {
      // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
      return false;
    }
  }

  public static int getDiffYear(String startTime, String endTime) {
    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    try {
      int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24))
          / 365);
      return years;
    } catch (Exception e) {
      // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
      return 0;
    }
  }

  /**
   * <li>功能描述：时间相减得到天数
   * 
   * @param beginDateStr
   * @param endDateStr
   * @return long
   * @author Administrator
   */
  public static long getDaySub(String beginDateStr, String endDateStr) {
    long day = 0;
    java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
    java.util.Date beginDate = null;
    java.util.Date endDate = null;

    try {
      beginDate = format.parse(beginDateStr);
      endDate = format.parse(endDateStr);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
    // System.out.println("相隔的天数="+day);

    return day;
  }

  /**
   * 得到n天之后的日期
   * 
   * @param days
   * @return
   */
  public static String getAfterDayDate(String days) {
    int daysInt = Integer.parseInt(days);

    Calendar canlendar = Calendar.getInstance(); // java.util包
    canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
    Date date = canlendar.getTime();

    SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateStr = sdfd.format(date);

    return dateStr;
  }

  /**
   * 得到n天之后是周几
   * 
   * @param days
   * @return
   */
  public static String getAfterDayWeek(String days) {
    int daysInt = Integer.parseInt(days);

    Calendar canlendar = Calendar.getInstance(); // java.util包
    canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
    Date date = canlendar.getTime();

    SimpleDateFormat sdf = new SimpleDateFormat("E");
    String dateStr = sdf.format(date);

    return dateStr;
  }

  /**
   * 时间转换成字符串
   * 
   * @param formate
   * @param str_time
   * @return
   */
  public static String strFormat(Date date, String format) {
    try {
      return new SimpleDateFormat(format).format(date);
    } catch (Exception e) {
      return null;
    }

  }

  /**
   * 字符串转时间
   * 
   * @param formate
   * @param str_time
   * @return
   */
  public static Date dateFormat(String formate, String str_time) {

    try {
      return new SimpleDateFormat(formate).parse(str_time);
    } catch (ParseException e) {
      return null;
    }
  }

  public static String formatDateStr(String format, String time) {
    return DateUtil.strFormat(DateUtil.dateFormat(format, time), format);
  }

  /**
   * 
   * 获取当前时间之前或之后几小时 hour
   * 
   */

  public static String getTimeByHour(int hour) {

    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);

    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

  }

  /**
   * 
   * 获取当前时间之前或之后几分钟 minute
   * 
   */

  public static String getTimeByMinute(int minute) {

    Calendar calendar = Calendar.getInstance();

    calendar.add(Calendar.MINUTE, minute);

    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());

  }

  public static void main(String[] args) {
    System.out.println(getDays());
    System.out.println(getAfterDayWeek("3"));
    System.out.println(getTimestamp());
    String orderId = DateUtil.getTimestamp().concat(UUIDUtil.genTimstampUUID().substring(17, 32));
    System.out.println(orderId);
    Date date = DateUtil.dateFormat("yyyyMMddHHmmss", "20170415012956");
    String str = DateUtil.strFormat(date, "yyyy-MM-dd HH:mm:ss");
    System.out.println("yyyy-MM-dd HH:mm:ss" + str);

    // 获取当前时间5分钟前的时间 格式yyyy-MM-dd HH:mm:ss

    System.out.println("获取当前时间5分钟前的时间 格式yyyy-MM-dd HH:mm:ss==>" + getTimeByMinute(-5));

    // 获取当前时间1小时后的时间 格式yyyy-MM-dd HH:mm:ss

    System.out.println("获取当前时间1小时后的时间 格式yyyy-MM-dd HH:mm:ss==>" + getTimeByHour(1));
  }
}
