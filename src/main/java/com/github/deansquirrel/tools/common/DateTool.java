package com.github.deansquirrel.tools.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * 日期工具类
 * 
 * @author yuansong6@163.com
 *
 */
public class DateTool {

	private DateTool() {}

	private static final String _dateFormat = "yyyy-MM-dd";
	private static final String _datetimeFormat = "yyyy-MM-dd HH:mm:ss";
	private static final String _datetimeWithMillionSecondFormat = "yyyy-MM-dd HH:mm:ss.SSS";

	private static final String _ymFormat = "yyyy-mm";

	private static final String _ymStrFormat = "yyyymm";

	/**
	 * 获取指定日期、指定格式的日期字符串
	 * @param date 日期
	 * @param format 格式
	 * @return 字符串
	 */
	public static String GetStr(Date date, String format) {
		if (date == null || format == null || format.trim().equals("")) {
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
		if (date == null || format == null || format.trim().equals("")) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(date);
	}

	/**
	 * 根据日期字符串转换日期 yyyy-MM-dd
	 * @param date 日期
	 * @return 字符串
	 * @throws ParseException 转换异常
	 */
	public static Date ParseDateStr(String date) throws ParseException {
		return ParseStr(date, _dateFormat);
	}
	
	/**
	 * 根据日期字符串转换日期 yyyy-MM-dd HH:mm:ss
	 * @param date 日期
	 * @return 字符串
	 * @throws ParseException 转换异常
	 */
	public static Date ParseDateTimeStr(String date) throws ParseException {
		return ParseStr(date, _datetimeFormat);
	}
	
	/**
	 * 根据日期字符串转换日期 yyyy-MM-dd HH:mm:ss.SSS
	 * @param date 日期
	 * @return 字符串
	 * @throws ParseException 转换异常
	 */
	public static Date ParseDateTimeWithMillionSecond(String date) throws ParseException {
		return ParseStr(date, _datetimeWithMillionSecondFormat);
	}

	//年首日
	//年末日
	//年首月末日
	//年末月首日

	//季度首日
	//季度末日
	//季度首月首日
	//季度首月末日
	//季度次月首日
	//季度次月末日
	//季度季月首日
	//季度季月末日

	//月首日
	//月末日




	//下年首日 DATE_YEAR_NEXT_BEGIN
	//下年末日 DATE_YEAR_NEXT_END
	//当年首日 DATE_YEAR_CURR_BEGIN
	//当年末日 DATE_YEAR_CURR_END
	//当年首月首日 DATE_MONTH_FIRST_BEGIN
	//当年首月末日 DATE_MONTH_FIRST_END
	//当月首日 DATE_MONTH_CURR_BEGIN
	//当月末日 DATE_MONTH_CURR_END
	//	DATE_MONTH_PREVIOUS_BEGIN,
	//	DATE_MONTH_PREVIOUS_END,
	//	DATE_MONTH_NEXT_BEGIN,
	//	DATE_MONTH_NEXT_END,
	//	DATE_QUARTER_FIRST_BEGIN,
	//	DATE_QUARTER_FIRST_END,
	//	DATE_QUARTER_CURR_BEGIN,
	//	DATE_QUARTER_CURR_END,
	//	DATE_QUARTER_NEXT_BEGIN,
	//	DATE_QUARTER_NEXT_END,
	//	DATE_QUARTER_PREVIOUS_BEGIN,
	//	DATE_QUARTER_PREVIOUS_END,
	//	DATETIME_YEAR_NEXT_BEGIN,
	//	DATETIME_YEAR_CURR_BEGIN,
	//	DATETIME_YEAR_CURR_END,
	//	DATETIME_MONTH_FIRST_BEGIN,
	//	DATETIME_MONTH_FIRST_END,
	//	DATETIME_MONTH_CURR_BEGIN,
	//	DATETIME_MONTH_CURR_END,
	//	DATETIME_MONTH_PREVIOUS_BEGIN,
	//	DATETIME_MONTH_PREVIOUS_END,
	//	DATETIME_MONTH_NEXT_BEGIN,
	//	DATETIME_MONTH_NEXT_END,
	//	DATETIME_QUARTER_FIRST_BEGIN,
	//	DATETIME_QUARTER_FIRST_END,
	//	DATETIME_QUARTER_CURR_BEGIN,
	//	DATETIME_QUARTER_CURR_END,
	//	DATETIME_QUARTER_NEXT_BEGIN,
	//	DATETIME_QUARTER_NEXT_END,
	//	DATETIME_QUARTER_PREVIOUS_BEGIN,
	//	DATETIME_QUARTER_PREVIOUS_END,
	//	YM_YEAR_NEXT_BEGIN,
	//	YM_YEAR_CURR_BEGIN,
	//	YM_YEAR_CURR_END,
	//	YM_MONTH_FIRST,
	//	YM_MONTH_CURR,
	//	YM_MONTH_PREVIOUS,
	//	YM_MONTH_NEXT,
	//	YM_QUARTER_FIRST_BEGIN,
	//	YM_QUARTER_FIRST_END,
	//	YM_QUARTER_CURR_BEGIN,
	//	YM_QUARTER_CURR_END,
	//	YM_QUARTER_NEXT_BEGIN,
	//	YM_QUARTER_NEXT_END,
	//	YM_QUARTER_PREVIOUS_BEGIN,
	//	YM_QUARTER_PREVIOUS_END,
}
