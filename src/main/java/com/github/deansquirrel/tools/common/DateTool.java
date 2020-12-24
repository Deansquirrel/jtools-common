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

	private static String _dateFormat = "yyyy-MM-dd";
	private static String _datetimeFormat = "yyyy-MM-dd HH:mm:ss";
	private static String _datetimeWithMillionsecondFormat = "yyyy-MM-dd HH:mm:ss.SSS";

	/**
	 * 获取指定日期、指定格式的日期字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String GetStr(Date date, String format) {
		if (date == null || format == null || format.trim() == "") {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 获取当前日期、指定格式的日期字符串
	 * @param format
	 * @return
	 */
	public static String GetStr(String format) {
		return GetStr(new Date(), format);
	}

	/**
	 * 获取指定日期字符串 yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String GetDateStr(Date date) {
		return GetStr(date, _dateFormat);
	}

	/**
	 * 获取当前日期字符串 yyyy-MM-dd
	 * @return
	 */
	public static String GetDateStr() {
		return GetStr(new Date(), _dateFormat);
	}

	/**
	 * 获取指定日期字符串 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String GetDateTimeStr(Date date) {
		return GetStr(date, _datetimeFormat);
	}

	/**
	 * 获取当前日期字符串 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String GetDateTimeStr() {
		return GetStr(new Date(), _datetimeFormat);
	}

	/**
	 * 获取指定日期字符串 yyyy-MM-dd HH:mm:ss.SSS
	 * @param date
	 * @return
	 */
	public static String GetDateTimeWithMillionsecond(Date date) {
		return GetStr(date, _datetimeWithMillionsecondFormat);
	}

	/**
	 * 获取当前日期字符串 yyyy-MM-dd HH:mm:ss.SSS
	 * @return
	 */
	public static String GetDateTimeWithMillionsecond() {
		return GetStr(new Date(), _datetimeWithMillionsecondFormat);
	}

	/**
	 * 根据日期字符串和指定格式转换日期
	 * @param date
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date ParseStr(String date, String format) throws ParseException {
		if (date == null || format == null || format.trim() == "") {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(date);
	}

	/**
	 * 根据日期字符串转换日期 yyyy-MM-dd
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date ParseDateStr(String date) throws ParseException {
		return ParseStr(date, _dateFormat);
	}
	
	/**
	 * 根据日期字符串转换日期 yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date ParseDateTimeStr(String date) throws ParseException {
		return ParseStr(date, _datetimeFormat);
	}
	
	/**
	 * 根据日期字符串转换日期 yyyy-MM-dd HH:mm:ss.SSS
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date ParseDateTimeWithMillionsecond(String date) throws ParseException {
		return ParseStr(date, _datetimeWithMillionsecondFormat);
	}
}
