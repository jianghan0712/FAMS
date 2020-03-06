/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.core.thread;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.purefun.fams.framework.core.service.impl.CommondServiceImpl;

/**
 * @Classname: CommondThread
 * @Description:
 * @author jianghan
 * @date 2020-03-05 16:58:40
 */
public class CommondThread implements Runnable {
	private static final Logger logger = LogManager.getLogger(CommondThread.class);

	@Autowired
	private CommondServiceImpl commondService;

	@Override
	public void run() {
		logger.info("{} core命令线程就位", Thread.currentThread().getName());
		Scanner sc = new Scanner(System.in);

		while (true) {
			String commond = sc.nextLine();
			logger.info("[{}] - receive commond: {}", Thread.currentThread().getName(), commond);
			try {
				commondService.executeCommond(commond);
			} catch (Exception e) {
				// TODO: handle exception
				logger.error("执行命令出错： {}", e);
			}
		}

	}
}
