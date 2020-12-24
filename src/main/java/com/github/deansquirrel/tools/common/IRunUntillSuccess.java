package com.github.deansquirrel.tools.common;

import java.text.MessageFormat;

import org.slf4j.LoggerFactory;

public interface IRunUntillSuccess  {
	
	/**
	 * 指定名称
	 * @return
	 */
	public String getName();
	
	/**
	 * 是否开始执行
	 * @return
	 */
	public boolean getPremiss();
	
	/**
	 * 执行内容
	 * 如果发生异常，等待特定时间后再次运行
	 * @throws Exception
	 */
	public void job() throws Exception;
	
	/**
	 * 执行任务前内容
	 */
	public void prefixJob();
	
	/**
	 * 执行任务后执行
	 */
	public void suffixJob();
	
	/**
	 * 当前线程中启动
	 */
	default public void startMain() {
		this.startMain(5000L);
	}
	
	/**
	 * 以线程方式启动
	 * warn: 非线程池
	 */
	default public void startThread() {
		this.startThread(5000L);
	}
	
	/**
	 * 当前线程中启动
	 * @param interval
	 */
	default public void startMain(long interval) {
		runJob(interval);
	}
	
	/**
	 * 以线程方式启动
	 * warn: 非线程池
	 * @param interval
	 */
	default public void startThread(long interval) {
		Thread d = new Thread(new Runnable() {

			@Override
			public void run() {
				runJob(interval);
			}
			
		});
		d.start();		
	}
	
	/**
	 * 执行代码内容
	 * @param interval
	 */
	default void runJob(long interval) {
		org.slf4j.Logger logger = LoggerFactory.getLogger(IRunUntillSuccess.class);
		String jobId = CommonTool.UUID();
		logger.info(MessageFormat.format("task [{0}] start ID: {1}", getName(), jobId));
		try {
			this.prefixJob();
		} catch(Exception e) {}
		while(true) {
			try {
				if(getPremiss()) {
					job();
					break;						
				} else {
					logger.warn(MessageFormat.format("task [{0}] skip ID: {1}", getName(), jobId));					
				}
			}catch(Exception e) {
				logger.error(ExceptionTool.getStackTrace(e));
			}
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {}
		}
		try {
			this.suffixJob();
		} catch(Exception e) {}
		logger.info(MessageFormat.format("task [{0}] finished ID: {1}", getName(), jobId));
	}
}
