package com.github.deansquirrel.tools.common;

import java.io.UnsupportedEncodingException;
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
	 * @param data
	 * @return 32位MD5
	 * @throws NoSuchAlgorithmException 
	 * @throws UnsupportedEncodingException 
	 */
	public static String Md5Encode(byte[] data) throws NoSuchAlgorithmException, UnsupportedEncodingException{
	    MessageDigest md5 = null;	    
        md5 = MessageDigest.getInstance("MD5");
		
//		byte[] byteArray = null;
//		byteArray = data.getBytes("UTF-8");		
//		byte[] md5Bytes = md5.digest(byteArray);
        
		byte[] md5Bytes = md5.digest(data);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
		    int val = ((int) md5Bytes[i]) & 0xff;
		    if (val < 16) {
		        hexValue.append("0");
		    }
		    hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString().toUpperCase();
	}
	
	/***
	 * GUID
	 * @return
	 */
	public static String UUID() {
		return  java.util.UUID.randomUUID().toString().toUpperCase();
	}
	
	/**
	 * 按固定长度拆分数据列表
	 * @param <T>
	 * @param list
	 * @param length
	 * @return
	 */
	public static <T> List<List<T>> splitDataList(List<T> list, int length) {
		List<List<T>> result = new ArrayList<List<T>>();
		int i = 0;
		List<T> sList = new ArrayList<T>();;
		for(T d : list) {
			if(i >= length) {
				result.add(sList);
				i = 0;
				sList = new ArrayList<T>();
			}
			sList.add(d);
			i++;
		}
		if(sList != null && sList.size() > 0) {
			result.add(sList);
			sList = null;
		}
		return result;
	}
	 
}
