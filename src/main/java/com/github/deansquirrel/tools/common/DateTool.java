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
}
