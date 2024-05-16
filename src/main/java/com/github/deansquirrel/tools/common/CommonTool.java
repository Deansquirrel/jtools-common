package com.github.deansquirrel.tools.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/***
 * 普通工具类
 * @author yuansong6@163.com
 *
 */

public class CommonTool {

	private static final Logger logger = LoggerFactory.getLogger(CommonTool.class);
	
	private CommonTool() {}
	
	/**
	 * MD5计算
	 * @param data 源数据
	 * @return 32位MD5
	 * @throws NoSuchAlgorithmException Exception
	 */
	public static String Md5Encode(byte[] data) throws NoSuchAlgorithmException {
	    MessageDigest md5;
        md5 = MessageDigest.getInstance("MD5");

		byte[] md5Bytes = md5.digest(data);
		StringBuilder hexValue = new StringBuilder();
		for (byte md5Byte : md5Bytes) {
			int val = ((int) md5Byte) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString().toUpperCase();
	}

	public static String Md5Encode(String data) throws NoSuchAlgorithmException {
		return CommonTool.Md5Encode(data.getBytes(StandardCharsets.UTF_8));
	}
	
	/***
	 * GUID
	 * @return GUID
	 */
	public static String UUID() {
		return  java.util.UUID.randomUUID().toString().toUpperCase();
	}
	
	/**
	 * 按固定长度拆分数据列表
	 * @param <T> 类型
	 * @param list 待拆分列表
	 * @param length 拆分涨肚
	 * @return 拆分后列表
	 */
	public static <T> List<List<T>> splitDataList(List<T> list, int length) {
		List<List<T>> result = new ArrayList<>();
		List<T> sList = new ArrayList<>();
		for(int i = 0; i < list.size(); i++) {
			if(i != 0 && i%length == 0) {
				result.add(sList);
				sList = new ArrayList<>();
			}
			sList.add(list.get(i));
		}
		if(sList.size() > 0) {
			result.add(sList);
		}
		return result;
	}


	/**
	 * 等待特定秒数
	 * @param s 秒数
	 */
	public static void sleepSeconds(int s) {
		logger.debug(MessageFormat.format("等待 {0} 秒", s));
        try {
            Thread.sleep(s * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

	/**
	 * 等待随机秒数
	 * @param min 最小等待秒数
	 * @param max 最大等待秒数
	 */
	public static void sleepSeconds(int min, int max) {
		if(Math.min(min, max) < 1) {
			return;
		}
		int s = MathTool.RandInt(min, max);
		sleepSeconds(s);
	}

	/**
	 * 等待特定毫秒数
	 * @param m 等待毫秒数
	 */
	public static void sleepMillionSeconds(long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
			Thread.currentThread().interrupt();
        }
    }

	private static String lastTimeStr = "";

	public synchronized static String getNextTimeStr() {
		String s = getTimeStr();
		while(s.equals(lastTimeStr)) {
			s = getTimeStr();
		}
		lastTimeStr = s;
		return lastTimeStr;
	}

	private static String getTimeStr() {
		return DateTool.GetDateTimeStr()
				.replace(" ","")
				.replace(":","")
				.replace("-","");
	}

	 
}
