package com.github.deansquirrel.tools.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class MathTool {
	
	private MathTool() {}
		
	/***
	 * 根据指定范围，返回随机整数
	 * @param min 最小值
	 * @param max 最大值（不含）
	 * @return 整数
	 */
	public static Integer RandInt(Integer min,Integer max) {
		if(min == null) {
			min = 0;
		}
		if(max == null) {
			max = 0;
		}
		if(min.equals(max)) {
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
	 * @return 整数
	 */
	public static Integer RandInt(Integer max) {
		return RandInt(0, max);
	}

	public static Long addLong(Long a, Long b) {
		if(a == null && b == null) {
			return null;
		}
		if(a == null) {
			return b;
		}
		if(b == null) {
			return a;
		}
		return a + b;
	}

	public static BigDecimal addBigDecimal(BigDecimal a, BigDecimal b) {
		if(a == null && b == null) {
			return null;
		}
		if(a == null) {
			return b;
		}
		if(b == null) {
			return a;
		}
		return a.add(b);
	}

	public static BigDecimal addBigDecimal(BigDecimal... l) {
		if(ValidateUtil.isEmpty(l)) {
			return null;
		}
		BigDecimal r = l[0];
		for(int i = 1; i < l.length; i++) {
			r = addBigDecimal(r, l[i]);
		}
		return r;
	}

	public static BigDecimal subtractBigDecimal(BigDecimal a, BigDecimal b) {
		if(a == null && b == null) {
			return null;
		}
		if(a == null) {
			return b.negate();
		}
		if(b == null) {
			return a;
		}
		return a.subtract(b);
	}

	public static BigDecimal multiplyBigDecimal(BigDecimal a, BigDecimal b) {
		if(a == null || b == null) {
			return null;
		}
		return a.multiply(b);
	}

	public static BigDecimal divideBigDecimal(BigDecimal a, BigDecimal b) {
		if(a == null || b == null) {
			return null;
		}
		return a.divide(b, RoundingMode.HALF_UP);
	}

}
