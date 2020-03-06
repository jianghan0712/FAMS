/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.core.thread;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @Classname: FAMSCoreThreadPool
 * @Description: FAMS-core的线程池
 * @author jianghan
 * @date 2020-03-04 17:25:33
 */
public class FAMSCoreThreadPool {
	/** 核心线程数 */
	private int corePoolSize;
	/** 最大线程数 */
	private int maxPoolSize;
	/** 排队最大容量 */
	private int queueCapacity;
	/** 线程名前缀 */
	private String ThreadNamePrefix;
	/** 保活时间 */
	private int keepAliveSeconds;

	/** core的线程池 */
	private ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

	/**
	 * 初始化线程
	 * 
	 * @MethodName: initPool
	 * @author jianghan
	 * @date 2020-03-04 17:43:54
	 */
	public void initPool() {
		taskExecutor.setCorePoolSize(corePoolSize);
		taskExecutor.setMaxPoolSize(maxPoolSize);
		taskExecutor.setQueueCapacity(queueCapacity);
		taskExecutor.setKeepAliveSeconds(keepAliveSeconds);
		taskExecutor.setThreadNamePrefix(ThreadNamePrefix);
		taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
		taskExecutor.setAwaitTerminationSeconds(60);
		taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		taskExecutor.initialize();
	}

	/**
	 * 取出一个线程去执行
	 * 
	 * @MethodName: execute
	 * @author jianghan
	 * @date 2020-03-05 17:49:13
	 * @param thread
	 */
	public void execute(Runnable thread) {
		taskExecutor.execute(thread);
	}

	public int getCorePoolSize() {
		return corePoolSize;
	}

	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
	}

	public int getQueueCapacity() {
		return queueCapacity;
	}

	public void setQueueCapacity(int queueCapacity) {
		this.queueCapacity = queueCapacity;
	}

	public String getThreadNamePrefix() {
		return ThreadNamePrefix;
	}

	public void setThreadNamePrefix(String threadNamePrefix) {
		ThreadNamePrefix = threadNamePrefix;
	}

	public ThreadPoolTaskExecutor getTaskExecutor() {
		return taskExecutor;
	}

	public void setTaskExecutor(ThreadPoolTaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}

	public int getKeepAliveSeconds() {
		return keepAliveSeconds;
	}

	public void setKeepAliveSeconds(int keepAliveSeconds) {
		this.keepAliveSeconds = keepAliveSeconds;
	}

}
