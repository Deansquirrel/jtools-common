package com.github.deansquirrel.tools.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

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

	/**
	 * MD5计算
	 * @param data 数据源
	 * @return 32位MD5
	 * @throws NoSuchAlgorithmException Exception
	 */
	public static String Md5Encode(String data) throws NoSuchAlgorithmException {
		return CommonTool.Md5Encode(data.getBytes(StandardCharsets.UTF_8));
	}
	
	/***
	 * UUID
	 * @return UUID
	 */
	public static String UUID() {
		return  java.util.UUID.randomUUID().toString().toUpperCase();
	}

	/**
	 * UUID
	 * @return UUID（不含分隔符）
	 */
	public static String getSimpleUUID() {
		return UUID().replace("-","");
	}

	/**
	 * 按固定长度拆分数据列表
	 * @param <T> 类型
	 * @param list 待拆分列表
	 * @param length 拆分长度
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
		if(!sList.isEmpty()) {
			result.add(sList);
		}
		return result;
	}


	/**
	 * 等待特定秒数
	 * @param s 秒数
	 */
	public static void sleepSeconds(int s) {
		logger.info(MessageFormat.format("等待 {0} 秒", s));
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

	/**
	 * 获取时间字符串，格式yyyyMMddHHmmss
	 * 如果已生成过，则直到下一秒再返回
	 * @return 事件字符串
	 */
	public synchronized static String getNextTimeStr() {
		String s = getTimeStr();
		while(s.equals(lastTimeStr)) {
			sleepMillionSeconds(50L);
			s = getTimeStr();
		}
		lastTimeStr = s;
		return lastTimeStr;
	}

	private static String getTimeStr() {
		return DateTool.GetStr("yyyyMMddHHmmss");
	}

	/**
	 * 获取简化字符串，去掉首尾空格、换行、回车，多空格合并为单空格
	 * @param s 待简化的字符串
	 * @return 简化后的字符串
	 */
	public static String getSimpleStr(String s) {
		if(ValidateTool.isEmpty(s)) {
			return s;
		}
		String str = s.trim();
		str = str.replaceAll("\\n", " ");
		str = str.replaceAll("\\r", " ");
		str = str.replaceAll("\\t", " ");
		while(str.contains("  ")) {
			str = str.replace("  ", " ");
		}
		return str.trim();
	}

	public interface ErrorCallback {
		void error(Exception e);
	}

	public static Thread startSubThread(Runnable runnable) {
		return startSubThread(runnable, null, null);
	}

	public static Thread startSubThread(Runnable runnable, ErrorCallback callback) {
		return startSubThread(runnable, null, callback);
	}

	public static Thread startSubThread(Runnable runnable, ThreadPoolTaskExecutor executor) {
		return startSubThread(runnable, executor, null);
	}

	/**
	 * 启动线程执行特定功能
	 * @param runnable 特定功能
	 * @param executor 线程池
	 * @param callback 异常回调
	 * @return 线程对象
	 */
	public static Thread startSubThread(Runnable runnable, ThreadPoolTaskExecutor executor, ErrorCallback callback) {
		if(runnable == null) {
			logger.warn("runnable can not be null");
			throw new NullPointerException();
		}
		Runnable nRunnable = () -> {
            try{
                runnable.run();
            } catch (Exception e) {
                if(callback == null) {
					logger.error(ExceptionTool.getStackTrace(e));
                } else {
                    callback.error(e);
                }
            }
        };
		Thread thread;
		if(executor == null) {
			thread = new Thread(nRunnable);
		} else {
			thread = executor.createThread(nRunnable);
		}
		thread.start();
		return thread;
	}

	/**
	 * 启动线程运行特定功能
	 * 遇到异常后重复执行，直至达到做大错误次数
	 * @param runnable 特定功能
	 * @param executor 线程池
	 * @param max 最大错误次数，0 或 负数 按 10000 执行
	 */
	public static void startSubThread(Runnable runnable, ThreadPoolTaskExecutor executor, int max) {
		int tMax = max <= 0 ? 10000 : max;
		startSubThread(runnable, executor, e -> {
			int count = tMax - 1;
			logger.error(ExceptionTool.getStackTrace(e));
			if(count <= 0) {
				logger.error("error match max times exists");
				return;
			}
            logger.error("error time left {}", count);
            sleepSeconds(30, 60);
            startSubThread(runnable, executor, count);
        });
	}

	/**
	 * 启动线程运行特定功能（非线程池）
	 * 遇到异常后重复执行，直至达到做大错误次数
	 * @param runnable 特定功能
	 * @param max 最大错误次数，0 或 负数 按 10000 执行
	 */
	public static void startSubThread(Runnable runnable, int max) {
		startSubThread(runnable, null, max);
	}

	/**
	 * 获取线程池对象
	 * @param namePrefix 线程前缀
	 * @return 线程池
	 */
	public static ThreadPoolTaskExecutor getThreadPool(String namePrefix) {
		int maxThreads = Runtime.getRuntime().availableProcessors() * 2 + 1;
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(maxThreads);
		pool.setMaxPoolSize(maxThreads);
		pool.setQueueCapacity(maxThreads * 30);
		if(namePrefix != null && !namePrefix.isEmpty()) {
			pool.setThreadNamePrefix(namePrefix.trim());
		}
		pool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
		pool.initialize();
		pool.setAllowCoreThreadTimeOut(true);
		pool.setKeepAliveSeconds(10);
		return pool;
	}

	/**
	 * 获取线程池对象
	 * @return 线程池
	 */
	public static ThreadPoolTaskExecutor getThreadPool() {
		return getThreadPool(null);
	}

	public static <T> void addObjToMap(String k, T v, Map<String, T> map) {
		if(ValidateTool.isEmpty(k) || ValidateTool.isEmpty(v) || map == null) {
			return;
		}
		if(map.containsKey(k)) {
			logger.warn("map val will be replaced, key is 【{}】", k);
		}
		map.put(k, v);
	}

	public static void addValToMap(String k, String v, Map<String, Set<String>> map) {
		if(ValidateTool.isEmpty(k) || ValidateTool.isEmpty(v) || map == null) {
			return;
		}
		Set<String> set = map.getOrDefault(k, new HashSet<>());
		set.add(v);
		map.put(k, set);
	}

	public static <T extends ComparableDO> void addObjToMap(List<T> val, Map<String, T> map) {
		for(T obj : val) {
			addObjToMap(obj, map);
		}
	}

	public static <T extends ComparableDO> void addObjToMap(T val, Map<String, T> map) {
		if(ValidateTool.isEmpty(val) || map == null) {
			return;
		}
		String k = val.getCompareKey();
		addObjToMap(k, val, map);
	}

}
