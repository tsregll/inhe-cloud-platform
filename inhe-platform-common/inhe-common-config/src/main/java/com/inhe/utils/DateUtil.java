/**
 * 
 */
package com.inhe.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.inhe.build.exception.InheExceptionBase;

/**
 * @author admin
 * @MSG 日期帮助类
 * @time 2018年12月5日上午11:02:06
 */
public class DateUtil {
	/**
	 * yyyyMM
	 */
	public static final String DATE_WITHOUT_DAY = "yyyyMM";
	/**
	 * yyyy-MM
	 */
	public static final String DATE_MONTH_LINE = "yyyy-MM";
	/**
	 * yyyyMMdd
	 */
	public static final String DATE_WITH_NOTHING = "yyyyMMdd";
	/**
	 * yyyy/MM/dd
	 */
	public static final String DATE_WITH_SLASH = "yyyy/MM/dd";
	/**
	 * yyyy-MM-dd
	 */
	public static final String DATE_WITH_LINE = "yyyy-MM-dd";
	/**
	 * yyyyMMdd HH:mm:ss
	 */
	public static final String DATE_TIME_NORMAL = "yyyyMMdd HH:mm:ss";
	/**
	 * yyyy/MM/dd HH:mm:ss
	 */
	public static final String DATE_TIME_WITH_SLASH = "yyyy/MM/dd HH:mm:ss";
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_TIME_WITH_LINE = "yyyy-MM-dd HH:mm:ss";
	/**
	 * yyyyMMddHHmmss
	 */
	public static final String DATE_TIME_WITH_NOTHING = "yyyyMMddHHmmss";
	/**
	 * yyyyMMddHHmmssSSS
	 */
	public static final String DATE_TIME_WITH_NOTHINGSSS = "yyyyMMddHHmmssSSS";
	/**
	 * yyyy年MM月dd日
	 */
	public static final String DATE_WITH_CH = "yyyy年MM月dd日";
	/**
	 * yyyy年MM月dd日HH时mm分ss秒
	 */
	public static final String DATE_TIME_WITH_CH = "yyyy年MM月dd日HH时mm分ss秒";
	/**
	 * HH小时mm分钟ss秒
	 */
	public static final String TIME_WITH_CH = "HH时mm分ss秒";
	/**
	 * HH:mm:ss
	 */
	public static final String TIME_NORMAL = "HH:mm:ss";

	/**
	 * 根据用户输入的格式将字符串转化成日期格式
	 * 
	 * @param date
	 * @param format日期格式
	 * @return
	 * @throws ParseException 
	 * @throws Exception
	 * @throws DateException
	 */
	public static Date stringToDate(String date, String format) throws Exception {
		Date newDate = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		newDate = simpleDateFormat.parse(date);
		return newDate;
	}

	/**
	 * 根据用户输入的格式和日期，得到字符串类型日期
	 * 
	 * @param date
	 * @param format日期格式
	 * @return
	 * @throws DateConvertException
	 */
	public static String dateToString(Date date, String format) throws Exception {
		String newDate = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		newDate = simpleDateFormat.format(date);
		return newDate;
	}

	/**
	 * 毫秒转时间
	 * 
	 * @param millis
	 * @param format
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public static String millisToString(long millis, String format) throws Exception {
		long secondTime = millis / 1000;
		int second = (int) (secondTime % 60);
		int minute = (int) (secondTime / 60 % 60);
		int hour = (int) (secondTime / 60 / 60 % 24);
		int day = (int) (secondTime / 60 / 60 / 24 % 30);
		int month = (int) (secondTime / 60 / 60 / 24 / 30 % 12);
		int year = (int) (secondTime / 60 / 60 / 24 / 30 / 12);
		if (TIME_WITH_CH.equals(format)) {
			StringBuilder t = new StringBuilder();
			t.append(year == 0 ? "" : year + "年");
			t.append(month == 0 ? "" : month + "个月");
			t.append(day == 0 ? "" : day + "天");
			t.append(hour == 0 ? "" : hour + "小时");
			t.append(minute == 0 ? "" : minute + "分钟");
			t.append(second == 0 ? "" : second + "秒");
			return t.toString();
		} else {
			Time time = new Time(hour, minute, second);
			return time.toString();
		}
	}

	/**
	 * 获得当前时间若干天前或后的日期
	 * 
	 * @param date
	 * @param days正数表示之后，负数表示之前
	 * @return
	 * @throws Exception
	 */
	public static Date rollByDay(Date date, int days) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}

	/**
	 * 获得当前时间若干月前或后的日期
	 * 
	 * @param date
	 * @param days正数表示之后，负数表示之前
	 * @return
	 * @throws Exception
	 */
	public static Date rollByMonth(Date date, int months) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, months);
		return calendar.getTime();
	}

	/**
	 * 获得当前时间若干年前或后的日期
	 * 
	 * @param date
	 * @param days正数表示之后，负数表示之前
	 * @return
	 * @throws Exception
	 */
	public static Date rollYear(Date date, int years) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
	}

	/**
	 * 获得当前时间若干小时前或后的日期
	 * 
	 * @param date
	 * @param days正数表示之后，负数表示之前
	 * @return
	 * @throws Exception
	 */
	public static Date rollHour(Date date, int hours) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hours);
		return calendar.getTime();
	}

	/**
	 * 获得当前时间若干分前或后的日期
	 * 
	 * @param date
	 * @param days正数表示之后，负数表示之前
	 * @return
	 * @throws Exception
	 */
	public static Date rollMinute(Date date, int minutes) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minutes);
		return calendar.getTime();
	}

	/**
	 * 获得当前时间若干秒前或后的日期
	 * 
	 * @param date
	 * @param days正数表示之后，负数表示之前
	 * @return
	 * @throws Exception
	 */
	public static Date rollSecond(Date date, int seconds) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, seconds);
		return calendar.getTime();
	}

	/**
	 * 根据格式从字符串中查找相应格式的日期字符串
	 * 
	 * @param src
	 * @param format
	 * @return 字符串日期
	 * @throws Exception
	 */
	public static String stringFindDateString(String src, String format) throws Exception {
		Matcher matcher = null;
		if (DATE_WITH_NOTHING.equalsIgnoreCase(format)) {
			matcher = Pattern.compile("(19|20)\\d{2}[01][0-9][0123][0-9]").matcher(src);
			matcher.find();
		} else if (DATE_WITHOUT_DAY.equalsIgnoreCase(format)) {
			matcher = Pattern.compile("(19|20)\\d{2}[01][0-9]").matcher(src);
			matcher.find();
		} else if (DATE_WITH_SLASH.equalsIgnoreCase(format)) {
			matcher = Pattern.compile("(19|20)\\d{2}[/][01][0-9][/][0123][0-9]").matcher(src);
			matcher.find();
		} else if (DATE_WITH_LINE.equalsIgnoreCase(format)) {
			matcher = Pattern.compile("(19|20)\\d{2}[-][01][0-9][-][0123][0-9]").matcher(src);
			matcher.find();
		} else if (DATE_TIME_NORMAL.equalsIgnoreCase(format)) {
			matcher = Pattern.compile("(19|20)\\d{2}[01][0-9][0123][0-9][ ][012][0-9][:][0-6][0-9][:][0-6][0-9]")
					.matcher(src);
			matcher.find();
		} else if (DATE_TIME_WITH_SLASH.equalsIgnoreCase(format)) {
			matcher = Pattern
					.compile("(19|20)\\d{2}[/][01][0-9][/][0123][0-9][ ][012][0-9][:][0-6][0-9][:][0-6][0-9]")
					.matcher(src);
			matcher.find();
		} else if (DATE_TIME_WITH_LINE.equalsIgnoreCase(format)) {
			matcher = Pattern
					.compile("(19|20)\\d{2}[-][01][0-9][-][0123][0-9][ ][012][0-9][:][0-6][0-9][:][0-6][0-9]")
					.matcher(src);
			matcher.find();
		} else if (DATE_TIME_WITH_NOTHING.equalsIgnoreCase(format)) {
			matcher = Pattern.compile("(19|20)\\d{2}[01][0-9][0123][0-9][012][0-9][0-6][0-9][0-6][0-9]")
					.matcher(src);
			matcher.find();
		} else if (DATE_WITH_CH.equalsIgnoreCase(format)) {
			matcher = Pattern.compile("(19|20)\\d{2}年[01][0-9]月[0123][0-9]日").matcher(src);
			matcher.find();
		} else if (DATE_TIME_WITH_CH.equalsIgnoreCase(format)) {
			matcher = Pattern.compile("(19|20)\\d{2}年[01][0-9]月[0123][0-9]日[ ]?[012][0-9]时[0-6][0-9]分[0-6][0-9]秒")
					.matcher(src);
			matcher.find();
		}
		return matcher.group();
	}

	/**
	 * 字符串中查找日期格式为yyyyMMdd
	 * 
	 * @param src
	 * @throws Exception
	 * @return返回日期格式
	 */
	public static Date stringFindDate(String src, String format) throws Exception {
		String dateStr = stringFindDateString(src, format);
		Date date = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		date = simpleDateFormat.parse(dateStr);
		return date;
	}

	/**
	 * 计算两个时间相差秒
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws InheExceptionBase 
	 */
	public static long twoDateDiffSecond(Date startDate, Date endDate) throws Exception {
		if (startDate != null && endDate != null) {
			long dissSS = startDate.getTime() - endDate.getTime();
			long hour = dissSS / 1000;
			return hour;
		} else {
			throw new InheExceptionBase(-10009,"日期不能为空:");
		}
	}

	/**
	 * 获取N+-天，日期 返回string
	 * 
	 * @param beginDate
	 * @param dayNum
	 * @return
	 * @throws DateConvertException
	 */
	public static String filstDateToStr(String beginDate, int dayNum) throws Exception {
		Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历。
		cal.setTime(DateUtil.stringToDate(beginDate, DateUtil.DATE_WITH_NOTHING));
		cal.add(Calendar.DAY_OF_MONTH, dayNum);// 取当前日期的前一天.
		// 通过格式化输出日期
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(DateUtil.DATE_WITH_NOTHING);
		return format.format(cal.getTime());
	}

	/**
	 * 获取N+-天，日期 返回date
	 * 
	 * @param beginDate
	 * @param dayNum
	 * @return
	 * @throws DateConvertException
	 */
	public static Date filstDateToDate(String beginDate, int dayNum) throws Exception {
		Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历。
		cal.setTime(DateUtil.stringToDate(beginDate, DateUtil.DATE_WITH_NOTHING));
		cal.add(Calendar.DAY_OF_MONTH, dayNum);// 取当前日期的前一天.
		// 通过格式化输出日期
		return cal.getTime();
	}

	/**
	 * 是否为同一天的交易
	 * @param beginDate 时间开始区间
	 * @param day		实际交易时间
	 * @param time		时间格式，时分秒
	 * @return
	 * @throws InheExceptionBase 
	 */
	public static boolean isWithDay(Date beginDate, Date day, String time) throws InheExceptionBase {
		try {
			String dateNothing = DateUtil.dateToString(beginDate, DateUtil.DATE_WITH_NOTHING);
			Date startDate = DateUtil.stringToDate(dateNothing + time, DateUtil.DATE_TIME_WITH_NOTHING);
			Calendar cal = Calendar.getInstance();// 使用默认时区和语言环境获得一个日历。
			cal.setTime(startDate);
			cal.add(Calendar.DAY_OF_MONTH, 1);// 取当前日期的前一天.
			Date endDate = cal.getTime();
			if (day.getTime() >= startDate.getTime() && day.getTime() <= endDate.getTime()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new InheExceptionBase(-10011,"date isWithDay error", e);
		}
	}
	
	public static String DateToCron(Date date,Integer cycle,Integer cycleType){
		
		String result = "";
		
		Calendar cld = Calendar.getInstance();
		cld.setTime(date);
		
		Integer s = cld.get(Calendar.SECOND);
		Integer m = cld.get(Calendar.MINUTE);
		Integer h = cld.get(Calendar.HOUR_OF_DAY);
		Integer d = cld.get(Calendar.DAY_OF_MONTH);
		Integer M = cld.get(Calendar.MONTH);
		Integer y = cld.get(Calendar.YEAR);
		
		switch (cycleType) {
			case 0:
				result += s+"/"+cycle+" * * * * ?";
				break;
			case 1:
				result += s+" "+m+"/"+cycle+" * * * ?";
				break;
			case 2:
				result += s+" "+m+" "+h+"/"+cycle+" * * ?";
				break;
			case 3:
				result += s+" "+m+" "+h+" "+d+"/"+cycle+" * ?";
				break;
			case 4:
				result += s+" "+m+" "+h+" "+d+" "+M+"/"+cycle+" ?";
				break;
			case 5:
				result += s+" "+m+" "+h+" "+d+" "+M+" "+"?"+" "+y+"/"+cycle;
				break;
			default:
				break;
		}
		return result;
		
	}
	
	/**
	 * 获取当前毫秒时间戳
	 * @return
	 * @throws InheExceptionBase 
	 */
	public static Long getMilliSecond(){
		Long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
		return milliSecond;
	}
	
	/**
	 * 获取下次执行时间
	 * @param BaseTime
	 * @param cycle
	 * @param cycleType
	 * @return NextDate
	 */
	public static Date getNextTime(Date BaseTime,Integer cycle,Integer cycleType){
		
		Calendar cld = Calendar.getInstance();
		while(BaseTime.getTime()<new Date().getTime()){
			cld.setTime(BaseTime);
			switch(cycleType){
				case 0:
					cld.add(Calendar.SECOND,cycle);	
					break;
				case 1:
					cld.add(Calendar.MINUTE,cycle);
					break;
				case 2:
					cld.add(Calendar.HOUR,cycle);
					break;
				case 3:
					cld.add(Calendar.DAY_OF_MONTH,cycle);
					break;
				case 4:
					cld.add(Calendar.MONTH,cycle);
					break;
				case 5:
					cld.add(Calendar.YEAR,cycle);
					break;
				default:
					break;
			}
			BaseTime = cld.getTime();
		}
		return BaseTime;
	}
	
	/**
     * 获取2个日期相差的月数
     * @param before
     * @param after
     * @return
     */
    public static int getDistanceMonth(Date before, Date after) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        int monthsDiff = 0;
        c1.setTime(before);
        c2.setTime(after);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        if (year1 == year2) {
        	monthsDiff = Math.abs(month2 - month1);
        } else if (year1 < year2) {
        	int yearInterval = Math.abs(year2 - year1); 
        	monthsDiff = month2 - month1 + yearInterval * 12;// monthsDiff = month2 + (12 - month1) + (yearInterval-1) * 12;
        } else if (year1 > year2) {
        	int yearInterval = Math.abs(year2 - year1);
        	monthsDiff = month1 - month2 + yearInterval*12;//monthsDiff = (12 - month2) + (yearInterval - 1) * 12 + month1;
        }
        
        return monthsDiff;
    }
    
    /**
     *  获取2个日期相隔天数
     * @param startDate
     * @param endDate
     * @return
     * @throws DateConvertException
     */
	public static int getDistanceDay(Date startDate, Date endDate) throws InheExceptionBase {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_WITH_LINE);
        long startDateTime;
        long endDateTime;
		try {
			startDateTime = dateFormat.parse(dateFormat.format(startDate)).getTime();
			endDateTime = dateFormat.parse(dateFormat.format(endDate)).getTime();
		} catch (ParseException e) {
			throw new InheExceptionBase(-10000, "日期格式有误，不能转换:" + e.getMessage());
		}
        return (int) ((endDateTime - startDateTime) / (1000 * 3600 * 24));
    }
	
	/**
	 * 获取一个月天数
	 * @param date
	 * @return
	 */
	public static int getDaysOfMonth(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
}
