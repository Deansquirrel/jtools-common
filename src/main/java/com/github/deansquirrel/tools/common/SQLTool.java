package com.github.deansquirrel.tools.common;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

/***
 * SQL 帮助类
 * @author yuansong6@163.com
 *
 */

public class SQLTool {
	
	private SQLTool() {}

	/**
	 * 判断结果集中是否包含特定名称的列
	 * @param rs 结果集
	 * @param columnName 列名称
	 * @return True-包含 false-不包含
	 */
	public static boolean isExistColumn(ResultSet rs, String columnName) throws SQLException {
		return rs.findColumn(columnName) > 0;
	}

	/**
	 * 获取字符串
	 * @param rs ResultSet
	 * @param columnLabel String
	 * @return result
	 * @throws SQLException Exception
	 */
	public static String getString(ResultSet rs, String columnLabel) throws SQLException {
		return rs.getString(columnLabel);
	}
	
	/**
	 * 获取字符串 N
	 * @param rs ResultSet
	 * @param columnLabel String
	 * @return result
	 * @throws SQLException Exception
	 */
	public static String getNString(ResultSet rs, String columnLabel) throws SQLException {
		return rs.getNString(columnLabel);
	}
	
	/**
	 * 获取byte
	 * @param rs ResultSet
	 * @param columnLabel String
	 * @return result
	 * @throws SQLException Exception
	 */
	public static Byte getByte(ResultSet rs, String columnLabel) throws SQLException {
		Byte d = rs.getByte(columnLabel);
		if(rs.wasNull()) {
			d = null;
		}
		return d;
	}
	
	/**
	 * 获取Short
	 * @param rs ResultSet
	 * @param columnLabel String
	 * @return result
	 * @throws SQLException Exception
	 */
	public static Short getShort(ResultSet rs, String columnLabel) throws SQLException {
		Short d = rs.getShort(columnLabel);
		if(rs.wasNull()) {
			d = null;
		}
		return d;
	}
	
	/**
	 * 获取Integer
	 * @param rs ResultSet
	 * @param columnLabel String
	 * @return result
	 * @throws SQLException Exception
	 */
	public static Integer getInt(ResultSet rs, String columnLabel) throws SQLException {
		Integer d = rs.getInt(columnLabel);
		if(rs.wasNull()) {
			d = null;
		}
		return d;
	}
	
	/**
	 * 获取Long
	 * @param rs ResultSet
	 * @param columnLabel String
	 * @return result
	 * @throws SQLException Exception
	 */
	public static Long getLong(ResultSet rs, String columnLabel) throws SQLException {
		Long d = rs.getLong(columnLabel);
		if(rs.wasNull()) {
			d = null;
		}
		return d;
	}
	
	/**
	 * 获取Float
	 * @param rs ResultSet
	 * @param columnLabel String
	 * @return result
	 * @throws SQLException Exception
	 */
	public static Float getFloat(ResultSet rs, String columnLabel) throws SQLException {
		Float d = rs.getFloat(columnLabel);
		if(rs.wasNull()) {
			d = null;
		}
		return d;
	}
	
	/**
	 * 获取Double
	 * @param rs ResultSet
	 * @param columnLabel String
	 * @return result
	 * @throws SQLException Exception
	 */
	public static Double getDouble(ResultSet rs, String columnLabel) throws SQLException {
		Double d = rs.getDouble(columnLabel);
		if(rs.wasNull()) {
			d = null;
		}
		return d;
	}
	
	/**
	 * 获取byte[]
	 * @param rs ResultSet
	 * @param columnLabel String
	 * @return result
	 * @throws SQLException Exception
	 */
	public static byte[] getBytes(ResultSet rs, String columnLabel) throws SQLException {
		return rs.getBytes(columnLabel);
	}
	
	/**
	 * 获取日期
	 * @param rs ResultSet
	 * @param columnLabel String
	 * @return result
	 * @throws SQLException Exception
	 */
	public static java.sql.Date getDate(ResultSet rs, String columnLabel) throws SQLException {
		return rs.getDate(columnLabel);
	}
	
	/**
	 * 获取时间
	 * @param rs ResultSet
	 * @param columnLabel String
	 * @return result
	 * @throws SQLException Exception
	 */
	public static java.sql.Time getTime(ResultSet rs, String columnLabel) throws SQLException {
		return rs.getTime(columnLabel);
	}
	
	/**
	 * 获取日期时间
	 * @param rs ResultSet
	 * @param columnLabel String
	 * @return result
	 * @throws SQLException Exception
	 */
	public static java.sql.Date getDatetime(ResultSet rs, String columnLabel) throws SQLException {
		java.sql.Timestamp dt = rs.getTimestamp(columnLabel);
		if(rs.wasNull()) {
			return null;
		}
		return new java.sql.Date(dt.getTime());
	}
	
	/**
	 * 获取decimal
	 * @param rs ResultSet
	 * @param columnLabel String
	 * @return result
	 * @throws SQLException Exception
	 */
	public static java.math.BigDecimal getBigDecimal(ResultSet rs, String columnLabel) throws SQLException {
		return rs.getBigDecimal(columnLabel);
	}

	/**
	 * 转换SQL数据库日期时间字段
	 * @param rs ResultSet 数据集
	 * @param columnLabel String
	 * @return result
	 * @throws SQLException Exception
	 * @throws ParseException Exception
	 */
	public static Date getSQLiteDateTime(ResultSet rs, String columnLabel) throws SQLException, ParseException {
		String t = getString(rs, columnLabel);
		if(t == null || t.isEmpty()) {
			return null;
		}
		return DateTool.ParseStr(t.substring(0, 19));
	}

	/**
	 * 转换SQL数据库日期时间字段
	 * @param rs ResultSet
	 * @param columnLabel String
	 * @return result
	 * @throws SQLException Exception
	 * @throws ParseException Exception
	 */
	public static Date getSQLiteDate(ResultSet rs, String columnLabel) throws SQLException, ParseException {
		String t = getString(rs, columnLabel);
		if(t == null || t.isEmpty()) {
			return null;
		}
		return DateTool.ParseStr(t.substring(0, 10));
	}

	/**
	 * 根据指定格式转换SQL数据库日期时间字段
	 * @param rs ResultSet
	 * @param columnLabel String
	 * @param dateFormat 日期格式字符串
	 * @return result
	 * @throws SQLException Exception
	 * @throws ParseException Exception
	 */
	public static Date getSQLiteDate(ResultSet rs, String columnLabel, String dateFormat) throws SQLException, ParseException {
		if(dateFormat == null || dateFormat.isEmpty()) {
			return null;
		}
		String t = getString(rs, columnLabel);
		if(t == null || t.isEmpty()) {
			return null;
		}
		return DateTool.ParseStr(t.substring(0,  dateFormat.length()), dateFormat);
	}

}
