package com.example.demo.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author: N469
 * @date: 2020-01-20 10:35
 */
public class DateUtil {

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 返回当前的日期
     *
     * @return
     */
    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    /**
     * 返回当前日期时间
     *
     * @return
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 返回当前日期字符串 yyyyMMdd
     *
     * @return
     */
    public static String getCurrentDateStr() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    /**
     * 返回当前日期时间字符串 yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentDateTimeStr() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }

    public static LocalDate parseLocalDate(String dateStr, String pattern) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime parseLocalDateTime(String dateTimeStr, String pattern) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期相隔天数
     *
     * @param startDateInclusive
     * @param endDateExclusive
     * @return
     */
    public static int periodDays(LocalDate startDateInclusive, LocalDate endDateExclusive) {
        return Period.between(startDateInclusive, endDateExclusive).getDays();
    }

    /**
     * 日期相隔小时
     *
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static long durationHours(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toHours();
    }

    /**
     * 日期相隔分钟
     *
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static long durationMinutes(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toMinutes();
    }

    /**
     * 日期相隔毫秒数
     *
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static long durationMillis(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toMillis();
    }




    /**
     * 是否当天
     *
     * @param date
     * @return
     */
    public static boolean isToday(LocalDate date) {
        return getCurrentLocalDate().equals(date);
    }

    /**
     * 获取本月的第一天
     *
     * @return
     */
    public static String getFirstDayOfThisMonth() {
        return getCurrentLocalDate().with(TemporalAdjusters.firstDayOfMonth()).format(DATE_FORMATTER);
    }

    /**
     * 获取本月的最后一天
     *
     * @return
     */
    public static String getLastDayOfThisMonth() {
        return getCurrentLocalDate().with(TemporalAdjusters.lastDayOfMonth()).format(DATE_FORMATTER);
    }

    /**
     * 获取某年的第一个周一
     * @param "2017-01-01"
     * @return
     */
    public static String getFirstMonday(String date) {
        return LocalDate.parse(date).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY))
                .format(DATE_FORMATTER);
    }

    /**
     * 获取当前日期的后两周
     *
     * @return
     */
    public static String getCurDateAfterTwoWeek() {
        return getCurrentLocalDate().plus(2, ChronoUnit.WEEKS).format(DATE_FORMATTER);
    }

    /**
     * 获取当前日期的6个月后的日期
     *
     * @return
     */
    public static String getCurDateAfterSixMonth() {
        return getCurrentLocalDate().plus(6, ChronoUnit.MONTHS).format(DATE_FORMATTER);
    }

    /**
     * 获取当前日期的5年后的日期
     *
     * @return
     */
    public static String getCurDateAfterFiveYear() {
        return getCurrentLocalDate().plus(5, ChronoUnit.YEARS).format(DATE_FORMATTER);
    }

    /**
     * 获取当前日期的20年后的日期
     *
     * @return
     */
    public static String getCurDateAfterTwentyYear() {
        return getCurrentLocalDate().plus(2, ChronoUnit.DECADES).format(DATE_FORMATTER);
    }

    public static void main(String[] args) {
        Date refreshDate = new Date();
        //刷新时间在创建时间的指定时间内
        refreshDate.toInstant().plusSeconds(60000);
        System.out.println(refreshDate);

        System.out.println(new Date(refreshDate.getTime() + 60000));
//        System.out.println(DateUtil.getFirstDayOfThisMonth());
//        System.out.println(DateUtil.getLastDayOfThisMonth());
//        System.out.println(DateUtil.getFirstMonday("2020-01-01"));
//        System.out.println(DateUtil.getCurDateAfterTwentyYear());
    }

}
