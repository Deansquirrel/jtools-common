package com.github.deansquirrel.tools.common;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/***
 * 普通工具类
 * @author yuansong6@163.com
 *
 */

public class CommonTool {
	
	private CommonTool() {}
	
	/***
	 * MD5计算
	 * @param data 源数据
	 * @return 32位MD5
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
	 
}
