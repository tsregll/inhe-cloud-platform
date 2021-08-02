package com.inhe.mdm.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * @description 日期帮助类
 * @author admin
 */
public class MdmDateUtil {
	/**
     * @description 获取指定月份最后一天日期
     * @param month 格式 2020-08
     * @return
     */
    public static String getLastDayOfMonth(String month) {
        LocalDate localDate = LocalDate.parse(month+"-01", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate lastDayOfMonth = localDate.with(TemporalAdjusters.lastDayOfMonth()); 
        String lastDay=lastDayOfMonth.format(DateTimeFormatter.ISO_LOCAL_DATE);
        return lastDay;
    }
    
    /**
     * @description 获取指定月份下个月的第一天日期
     * @param month 格式 2020-08
     * @return
     */
    public static String getFirstDayOfNextMonth(String month) {
        LocalDate localDate = LocalDate.parse(month+"-01", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate firstDayOfNextMonth = localDate.with(TemporalAdjusters.firstDayOfNextMonth()); 
        String firstDay=firstDayOfNextMonth.format(DateTimeFormatter.ISO_LOCAL_DATE);
        return firstDay;
    }
    
    /**
     * @description 获取指定月份下个月的最后一天日期
     * @param month 格式 2020-08
     * @return
     */
    public static String getLastDayOfNextMonth(String month) {
        LocalDate localDate = LocalDate.parse(month+"-01", DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate firstDayOfNextMonth = localDate.with(TemporalAdjusters.firstDayOfNextMonth());
        LocalDate lastDayOfNextMonth = firstDayOfNextMonth.with(TemporalAdjusters.lastDayOfMonth());
        String lastDay=lastDayOfNextMonth.format(DateTimeFormatter.ISO_LOCAL_DATE);
        return lastDay;
    }

}
