package com.github.deansquirrel.tools.common;

/***
 * 转换工具类
 * @author yuansong6@163.com
 */

public class ConversionTool {
	
	private ConversionTool() {}

	/***
	 * java.sql.Date 转 java.util.Date 
	 * @param date 日期
	 * @return 转换后的日期
	 */
	public static java.util.Date ConvertSqlDateToUtilDate(java.sql.Date date){
		if(date == null) {
			return null;
		}
		return new java.util.Date(date.getTime());
	}
	
	/***
	 * java.util.Date 转 java.sql.Date 
	 * @param date 日期
	 * @return 转换后的日期
	 */
	public static java.sql.Date ConvertUtilDateToSqlDate(java.util.Date date){
		if(date == null) {
			return null;
		}
		return new java.sql.Date(date.getTime());
	}
}
