package com.github.deansquirrel.tools.common;

import java.math.BigDecimal;
import java.math.BigInteger;
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
	public static int RandInt(int min, int max) {
		if(min == max) {
			return min;
		}
		Random r = new Random();
		return Math.min(min, max) + r.nextInt(Math.max(min, max) - Math.min(min, max));
	}
	
	/**
	 * 返回随机整数（最小值=0）
	 * @param max 最大值（不含）
	 * @return 整数
	 */
	public static int RandInt(int max) {
		return RandInt(0, max);
	}

	private static <T extends Number> T addT(T a, T b, Class<T> clazz) throws ClassCastException {
		if(a == null && b == null) return null;
		if(a == null) {
			return b;
		}
		if(b == null) {
			return a;
		}
		if(a instanceof Byte && b instanceof Byte) {
			Byte r = (byte)(a.byteValue() + b.byteValue());
			return clazz.cast(r);
		}
		if(a instanceof Integer && b instanceof Integer) {
			Integer r = a.intValue() + b.intValue();
			return clazz.cast(r);
		}
		if(a instanceof BigInteger && b instanceof BigInteger) {
			BigInteger r = ((BigInteger) a).add((BigInteger) b);
			return clazz.cast(r);
		}
		if(a instanceof Short && b instanceof Short) {
			Short r = (short)(a.shortValue() + b.shortValue());
			return clazz.cast(r);
		}
		if(a instanceof Float && b instanceof Float) {
			Float r = a.floatValue() + b.floatValue();
			return clazz.cast(r);
		}
		if(a instanceof Long && b instanceof Long) {
			Long r = a.longValue() + b.longValue();
			return clazz.cast(r);
		}
		if(a instanceof Double && b instanceof Double) {
			Double r = a.doubleValue() + b.doubleValue();
			return clazz.cast(r);
		}
		if(a instanceof BigDecimal && b instanceof BigDecimal) {
			BigDecimal r = ((BigDecimal) a).add((BigDecimal) b);
			return clazz.cast(r);
		}
		// 未预知的类型一律返回运行时异常
		throw new ClassCastException("unknown handle type " + clazz.toString());
	}

	@SafeVarargs
	private static <T extends Number> T addT(Class<T> clazz, T... l) {
		if(ValidateTool.isEmpty(l)) {
			return null;
		}
		T r = l[0];
		for(int i = 1; i < l.length; i++) {
			r = addT(r, l[i], clazz);
		}
		return r;
	}

	public static Byte addData(Byte a, Byte b) {
		return addT(a, b, Byte.class);
	}

	public static Byte addData(Byte... l) {
		return addT(Byte.class, l);
	}

	public static Integer addData(Integer a, Integer b) {
		return addT(a, b, Integer.class);
	}

	public static Integer addData(Integer... l) {
		return addT(Integer.class, l);
	}

	public static BigInteger addData(BigInteger a, BigInteger b) {
		return addT(a, b, BigInteger.class);
	}

	public static BigInteger addData(BigInteger... l) {
		return addT(BigInteger.class, l);
	}

	public static Short addData(Short a, Short b) {
		return addT(a, b, Short.class);
	}

	public static Short addData(Short... l) {
		return addT(Short.class, l);
	}

	public static Float addData(Float a, Float b) {
		return addT(a, b, Float.class);
	}

	public static Float addData(Float... l) {
		return addT(Float.class, l);
	}

	public static Long addData(Long a, Long b) {
		return addT(a, b, Long.class);
	}

	public static Long addData(Long... l) {
		return addT(Long.class, l);
	}

	public static Double addData(Double a, Double b) {
		return addT(a, b, Double.class);
	}

	public static Double addData(Double... l) {
		return addT(Double.class, l);
	}

	public static BigDecimal addBigDecimal(BigDecimal a, BigDecimal b) {
		return addT(a, b, BigDecimal.class);
	}

	public static BigDecimal addBigDecimal(BigDecimal... l) {
		return addT(BigDecimal.class, l);
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

	/**
	 * 将入参数值放大缩小10的指定倍数
	 * @param num 待处理数值
	 * @param ret 缩小位数
	 * @return 处理后的数值
	 */
	public static BigDecimal transDecimal(BigDecimal num, int ret) {
		if(num == null || ret == 0) {
			return num;
		}
		int scale = num.scale();
		BigDecimal r = new BigDecimal(ret > 0 ? "10.0" : "0.1").setScale(scale, RoundingMode.HALF_UP);
		return num.multiply(r.pow(Math.abs(ret))).setScale(scale, RoundingMode.HALF_UP);
	}

	/**
	 * 将入参数值缩小10000倍
	 * @param num 入参金额
	 * @return 处理后的金额
	 */
	public static BigDecimal transMoneyWan(BigDecimal num) {
		return transMoneyWan(num, num.scale());
	}

	/**
	 * 将入参数值缩小10000倍，并保留特定的小数位数
	 * @param num 入参金额
	 * @param scale 保留位数
	 * @return 处理后的金额
	 */
	public static BigDecimal transMoneyWan(BigDecimal num, int scale) {
		return transDecimal(num, -4).setScale(scale, RoundingMode.HALF_UP);
	}

	/**
	 * 获取百分比字符串
	 * @param t 分母
	 * @param c 分子
	 * @param scale 保留小数位数
	 * @return 百分比字符串
	 */
	public static String getPercentString(BigDecimal t, BigDecimal c, int scale) {
		if(ValidateTool.isEmpty(t) || ValidateTool.isEmpty(c) || t.compareTo(BigDecimal.ZERO) == 0) {
			return null;
		}
		BigDecimal r = MathTool.divideBigDecimal(MathTool.multiplyBigDecimal(c, new BigDecimal("100.000000")), t);
		return r.setScale(Math.max(0, scale), RoundingMode.HALF_UP).toPlainString() + "%";
	}

	/**
	 * 获取百分比字符串
	 * @param t 分母
	 * @param c 分子
	 * @return 百分比字符串
	 */
	public static String getPercentString(BigDecimal t, BigDecimal c) {
		return getPercentString(t, c, 2);
	}

	/**
	 * 获取百分比字符串
	 * @param t 分母
	 * @param c 分子
	 * @param scale 保留小数位数
	 * @return 百分比字符串
	 */
	public static String getPercentString(Number t, Number c, int scale) {
		if(ValidateTool.isEmpty(t) || t.intValue() == 0 || ValidateTool.isEmpty(c)) {
			return null;
		}
		BigDecimal tb = new BigDecimal(String.valueOf(t));
		BigDecimal cb = new BigDecimal(String.valueOf(c));
		return getPercentString(tb, cb, scale);
	}

	/**
	 * 获取百分比字符串
	 * @param t 分母
	 * @param c 分子
	 * @return 百分比字符串
	 */
	public static String getPercentString(Number t, Number c) {
		return getPercentString(t, c, 2);
	}

}
