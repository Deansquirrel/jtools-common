package com.github.deansquirrel.tools.common;

import java.sql.ResultSet;
import java.sql.SQLException;

/***
 * SQL 帮助类
 * @author yuansong6@163.com
 *
 */

public class SQLTool {
	
	private SQLTool() {}
	
	/**
	 * 获取字符串
	 * @param rs
	 * @param columnLabel
	 * @return
	 * @throws SQLException
	 */
	public static String getString(ResultSet rs, String columnLabel) throws SQLException {
		return rs.getString(columnLabel);
	}
	
	/**
	 * 获取字符串 N
	 * @param rs
	 * @param columnLabel
	 * @return
	 * @throws SQLException
	 */
	public static String getNString(ResultSet rs, String columnLabel) throws SQLException {
		return rs.getNString(columnLabel);
	}
	
	/**
	 * 获取byte
	 * @param rs
	 * @param columnLabel
	 * @return
	 * @throws SQLException
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
	 * @param rs
	 * @param columnLabel
	 * @return
	 * @throws SQLException
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
	 * @param rs
	 * @param columnLabel
	 * @return
	 * @throws SQLException
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
	 * @param rs
	 * @param columnLabel
	 * @return
	 * @throws SQLException
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
	 * @param rs
	 * @param columnLabel
	 * @return
	 * @throws SQLException
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
	 * @param rs
	 * @param columnLabel
	 * @return
	 * @throws SQLException
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
	 * @param rs
	 * @param columnLabel
	 * @return
	 * @throws SQLException
	 */
	public static byte[] getBytes(ResultSet rs, String columnLabel) throws SQLException {
		return rs.getBytes(columnLabel);
	}
	
	/**
	 * 获取日期
	 * @param rs
	 * @param columnLabel
	 * @return
	 * @throws SQLException
	 */
	public static java.sql.Date getDate(ResultSet rs, String columnLabel) throws SQLException {
		return rs.getDate(columnLabel);
	}
	
	/**
	 * 获取时间
	 * @param rs
	 * @param columnLabel
	 * @return
	 * @throws SQLException
	 */
	public static java.sql.Time getTime(ResultSet rs, String columnLabel) throws SQLException {
		return rs.getTime(columnLabel);
	}
	
	/**
	 * 获取日期时间
	 * @param rs
	 * @param columnLabel
	 * @return
	 * @throws SQLException
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
	 * @param rs
	 * @param columnLabel
	 * @return
	 * @throws SQLException
	 */
	public static java.math.BigDecimal getBigDecimal(ResultSet rs, String columnLabel) throws SQLException {
		return rs.getBigDecimal(columnLabel);
	}

}
