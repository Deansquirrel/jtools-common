package com.github.deansquirrel.tools.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/***
 * 日期工具类
 * @author yuansong6@163.com
 */
public class DateTool {

	private DateTool() {}

	private static final String _dateFormat = "yyyy-MM-dd";
	private static final String _datetimeFormat = "yyyy-MM-dd HH:mm:ss";
	private static final String _datetimeWithMillionSecondFormat = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * 获取指定日期、指定格式的日期字符串
	 * @param date 日期
	 * @param format 格式
	 * @return 字符串
	 */
	public static String GetStr(Date date, String format) {
		if (date == null || format == null || format.trim().isEmpty()) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 获取当前日期、指定格式的日期字符串
	 * @param format 格式
	 * @return 字符串
	 */
	public static String GetStr(String format) {
		return GetStr(new Date(), format);
	}

	/**
	 * 获取指定日期字符串 yyyy-MM-dd
	 * @param date 日期
	 * @return 字符串
	 */
	public static String GetDateStr(Date date) {
		return GetStr(date, _dateFormat);
	}

	/**
	 * 获取当前日期字符串 yyyy-MM-dd
	 * @return 字符串
	 */
	public static String GetDateStr() {
		return GetStr(new Date(), _dateFormat);
	}

	/**
	 * 获取指定日期字符串 yyyy-MM-dd HH:mm:ss
	 * @param date 日期
	 * @return 字符串
	 */
	public static String GetDateTimeStr(Date date) {
		return GetStr(date, _datetimeFormat);
	}

	/**
	 * 获取当前日期字符串 yyyy-MM-dd HH:mm:ss
	 * @return 字符串
	 */
	public static String GetDateTimeStr() {
		return GetStr(new Date(), _datetimeFormat);
	}

	/**
	 * 获取指定日期字符串 yyyy-MM-dd HH:mm:ss.SSS
	 * @param date 日期
	 * @return 字符串
	 */
	public static String GetDateTimeWithMillionSecond(Date date) {
		return GetStr(date, _datetimeWithMillionSecondFormat);
	}

	/**
	 * 获取当前日期字符串 yyyy-MM-dd HH:mm:ss.SSS
	 * @return 字符串
	 */
	public static String GetDateTimeWithMillionSecond() {
		return GetStr(new Date(), _datetimeWithMillionSecondFormat);
	}

	/**
	 * 根据日期字符串和指定格式转换日期
	 * @param date 日期
	 * @param format 格式
	 * @return 字符串
	 * @throws ParseException 转换异常
	 */
	public static Date ParseStr(String date, String format) throws ParseException {
		if (date == null || format == null || date.trim().isEmpty() || format.trim().isEmpty()) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(date.length() < format.length() ? format.substring(0, date.length() - 1) : format);
		return sdf.parse(date);
	}

	/**
	 * 按默认格式转换
	 * @param date 日期字符串
	 * @return 日期
	 * @throws ParseException 转换异常
	 */
	public static Date ParseStr(String date) throws ParseException {
		return ParseStr(date, _datetimeWithMillionSecondFormat);
	}

	/**
	 * 获取指定日期的整点时间 00:00:00
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetZeroDate(Date date) {
		Calendar cal = Calendar.getInstance();
		long t = (date == null ? new Date() : date).getTime();
		cal.setTimeInMillis(t - t % 1000L);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH),
				0,0,0);
		return cal.getTime();
	}

	public static Date GetZeroDate(Date date, int dayAmount) {
		if(dayAmount == 0) {
			return GetZeroDate(date);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(GetZeroDate(date).getTime());
		cal.add(Calendar.DAY_OF_MONTH, dayAmount);
		return GetZeroDate(cal.getTime());
	}

	/**
	 * 获取当月首日
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetMonthFirstDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(GetZeroDate(date).getTime());
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 获取偏移月份的首日
	 * @param date 指定日期
	 * @param monthAmount 偏移约束
	 * @return 维护后的日期
	 */
	public static Date GetMonthFirstDay(Date date, int monthAmount) {
		if(monthAmount == 0) {
			return GetMonthFirstDay(date);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(GetZeroDate(date).getTime());
		cal.add(Calendar.MONTH, monthAmount);
		return GetMonthFirstDay(cal.getTime());
	}

	/**
	 * 获取下月首日
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetNextMonthFirstDay(Date date) {
        return GetMonthFirstDay(date, 1);
	}

	/**
	 * 获取上月首日
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetPreviousMonthFirstDay(Date date) {
        return GetMonthFirstDay(date, -1);
	}

	/**
	 * 获取当月最后一天
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetMonthLastDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(GetNextMonthFirstDay(date).getTime());
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 获取当年第一天的日期
	 * @param date 指定日志
	 * @return 维护后的日期
	 */
	public static Date GetYearFirstDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(GetZeroDate(date).getTime());
		cal.set(Calendar.MONTH, Calendar.JANUARY);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}

	/**
	 * 获取明年第一天的日期
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetNextYearFirstDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(GetZeroDate(date).getTime());
		cal.add(Calendar.YEAR, 1);
		return GetYearFirstDay(cal.getTime());
	}

	/**
	 * 获取去年第一天的日期
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetPreviousYearFirstDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(GetZeroDate(date).getTime());
		cal.add(Calendar.YEAR, -1);
		return GetYearFirstDay(cal.getTime());
	}

	/**
	 * 获取当年最后一天的日期
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetYearLastDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(GetYearFirstDay(date).getTime());
		cal.add(Calendar.YEAR, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	/**
	 * 获取当年首月首日（同当年首日）
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetFirstMonthFirstDay(Date date) {
		return GetYearFirstDay(date);
	}

	/**
	 * 获取当年首月末日
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetFirstMonthLastDay(Date date) {
		return GetMonthLastDay(GetYearFirstDay(date));
	}

	/**
	 * 获取当年末月首日
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetLastMonthFirstDay(Date date) {
		return GetMonthFirstDay(GetYearLastDay(date));
	}

	/**
	 * 获取当年末月末日（同当年末日）
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetLastMonthLastDay(Date date) {
		return GetYearLastDay(date);
	}

	/**
	 * 获取季度首日
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetQuarterFirstDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(GetZeroDate(date));
		int ret = cal.get(Calendar.MONTH) % 3;
		if(ret != 0) {
			cal.add(Calendar.MONTH, -1 * ret);
		}
		return GetMonthFirstDay(cal.getTime());
	}

	/**
	 * 获取下季度首日
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetNextQuarterFirstDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(GetZeroDate(date));
		cal.add(Calendar.MONTH,3);
		return GetQuarterFirstDay(cal.getTime());
	}

	/**
	 * 获取上季度首日
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetPreviousQuarterFirstDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(GetZeroDate(date));
		cal.add(Calendar.MONTH,-3);
		return GetQuarterFirstDay(cal.getTime());
	}

	/**
	 * 获取季度末日
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetQuarterLastDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(GetZeroDate(date));
		int ret = 2 - cal.get(Calendar.MONTH) % 3;
		if(ret != 0) {
			cal.add(Calendar.MONTH, ret);
		}
		return GetMonthLastDay(cal.getTime());
	}

	/**
	 * 获取季度首月首日（同季度首日）
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetQuarterFirstMonthFirstDay(Date date) {
		return GetQuarterFirstDay(date);
	}

	/**
	 * 获取季度首月末日
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetQuarterFirstMonthLastDay(Date date) {
		return GetMonthLastDay(GetQuarterFirstMonthFirstDay(date));
	}

	/**
	 * 获取季度次月首日
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetQuarterSecondMonthFirstDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(GetZeroDate(date));
		int ret = 1 - cal.get(Calendar.MONTH) % 3;
		if(ret != 0) {
			cal.add(Calendar.MONTH, ret);
		}
		return GetMonthFirstDay(cal.getTime());
	}

	/**
	 * 获取季度次月末日
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetQuarterSecondMonthLastDay(Date date) {
		return GetMonthLastDay(GetQuarterSecondMonthFirstDay(date));
	}

	/**
	 * 获取季度末月首日
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetQuarterThirdMonthFirstDay(Date date) {
		return GetMonthFirstDay(GetQuarterLastDay(date));
	}

	/**
	 * 获取季度末月末日（同季度末日）
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetQuarterThirdMonthLastDay(Date date) {
		return GetQuarterLastDay(date);
	}

	/**
	 * 获取前一天的日期
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetPreviousDay(Date date) {
		return GetZeroDate(date, -1);
	}

	/**
	 * 获取后一天的日期
	 * @param date 指定日期
	 * @return 维护后的日期
	 */
	public static Date GetNextDay(Date date) {
		return GetZeroDate(date, 1);
	}

	/**
	 * 根据指定的开始日期、截止日期、维度、步长拆分成多个日期段
	 * @param field 日期拆分维度，同 Calendar
	 * @param amount 步长
	 * @param begDate 开始日期
	 * @param endDate 截止日期（含）
	 * @return 拆分后的日期组
	 */
	public static Map<Date, Date> splitDate(int field, int amount, Date begDate, Date endDate) {
		if(ValidateTool.isEmpty(begDate) || ValidateTool.isEmpty(endDate)) {
			throw new RuntimeException("开始日期和截止日期不允许为空");
		}
		Map<Date, Date> r = new HashMap<>();
		if(ValidateTool.areEqualIgnoreCase(
				DateTool.GetDateTimeWithMillionSecond(begDate),
				DateTool.GetDateTimeWithMillionSecond(endDate))) {
			r.put(begDate, endDate);
			return r;
		}
		Calendar begCal = Calendar.getInstance();
		begCal.setTimeInMillis(begDate.getTime());
		Calendar endCal = Calendar.getInstance();
		endCal.setTimeInMillis(endDate.getTime());
		if((amount >= 0 && endCal.before(begCal)) || (amount <= 0 && endCal.after(begCal))) {
			throw new RuntimeException("日期分割异常【无止境拆分】");
		}
		while((amount > 0 && endCal.after(begCal)) || (amount < 0 && endCal.before(begCal))) {
			Date b = begCal.getTime();
			begCal.add(field, amount);
			if((amount > 0 && endCal.after(begCal)) || (amount < 0 && endCal.before(begCal))) {
				if(amount > 0) {
					r.put(b, begCal.getTime());
				} else {
					r.put(begCal.getTime(), b);
				}
			} else {
				if(amount > 0) {
					r.put(b, endDate);
				} else {
					r.put(endDate, b);
				}
			}
		}
		return r;
	}

	/**
	 * 根据指定的开始日期、维度、步长、拆分数量获取多个日期段
	 * @param field 日期拆分维度，同 Calendar
	 * @param amount 步长
	 * @param begDate 开始日期
	 * @param num 拆分数量
	 * @return 拆分后的日期组
	 */
	public static Map<Date, Date> splitDate(int field, int amount, Date begDate, int num) {
		if(amount == 0 || num < 0) {
			throw new RuntimeException("日期分割异常【无止境拆分】");
		}
		Date d = ValidateTool.isEmpty(begDate) ? GetZeroDate(new Date()) : begDate;
		Date e = DateTool.GetZeroDate(d, amount * num);
		return splitDate(field, amount, d, e);
	}

	/**
	 * 计算两个日期间相差的天数（根据传入日期的零点计算）
	 * @param begDate 开始日期（含）
	 * @param endDate 截止日期（不含）
	 * @return 天数差
	 */
	public static int getDateDiff(Date begDate, Date endDate) {
		if(ValidateTool.isEmpty(begDate) || ValidateTool.isEmpty(endDate)) {
			return -1;
		}
		long beg = GetZeroDate(begDate).getTime();
		long end = GetZeroDate(endDate).getTime();
		return (int) Math.ceil((double) (end - beg) / (24 * 60 * 60 * 1000));
	}
}
