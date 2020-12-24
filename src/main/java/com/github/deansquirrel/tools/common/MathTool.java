package com.github.deansquirrel.tools.common;

import java.util.Random;

/***
 * 计算工具类
 * @author yuansong6@163.com
 *
 */

public class MathTool {
	
	private MathTool() {}
		
	/***
	 * 根据指定范围，返回随机整数
	 * @param min 最小值
	 * @param max 最大值（不含）
	 * @return
	 */
	public static Integer RandInt(Integer min,Integer max) {
		if(min == null) {
			min = 0;
		}
		if(max == null) {
			max = 0;
		}
		if(min == max) {
			return min;
		}
		if(min>max) {
			Integer t = min;
			min = max;
			max = t;
		}
		Random r = new Random();
		return min + r.nextInt(max-min);
	}
	
	/**
	 * 返回随机整数（最小值=0）
	 * @param max 最大值（不含）
	 * @return
	 */
	public static Integer RandInt(Integer max) {
		return RandInt(0, max);
	}
}
