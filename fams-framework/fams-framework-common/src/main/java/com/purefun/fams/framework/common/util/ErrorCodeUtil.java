/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.framework.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Classname: ErrorCodeUtil
 * @Description:
 * @author jianghan
 * @date 2020-06-06 13:12:09
 */
public class ErrorCodeUtil {
	private static final Logger logger = LogManager.getLogger(ErrorCodeUtil.class);

	private static Map<Integer, ErrorCode> errorCodeMap = new HashMap<Integer, ErrorCode>();

	private static ErrorCode defultError = new ErrorCode(100001, "UNKNOWN_EXCEPTION", "未知错误");

	/**
	 * fams框架初始化时调用
	 * 
	 * @MethodName: createMap
	 * @author jianghan
	 * @date 2020-06-06 13:31:08
	 * @param fileInput
	 */
	public static void createMap(InputStream fileInput) {
		InputStreamReader inputReader = new InputStreamReader(fileInput);
		BufferedReader bf = new BufferedReader(inputReader);

		String str;
		String[] line = null;
		int i = 0;// 当前读到第几行
		try {
			while ((str = bf.readLine()) != null) {
				i++;
				str = str.trim();

				if (str.length() == 0 || str.charAt(0) == '#') {
					/** 跳过注释行 */
					continue;
				}
				line = str.split(",");
				if (line.length != 3) {
					logger.error("读取errorCode文件第{}行失败！{}", i, str);
					continue;
				}
				int id = Integer.valueOf(line[0].trim());
				errorCodeMap.put(id, new ErrorCode(id, line[1].trim(), line[2].trim()));
			}
			logger.info("加载errorMap成功，总计{}个错误码", errorCodeMap.size());
			fileInput.close();
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

	/**
	 * 根据id获取具体的错误原因
	 * 
	 * @MethodName: error
	 * @author jianghan
	 * @date 2020-06-06 13:32:11
	 * @param errorId
	 * @return
	 */
	public static ErrorCode error(int errorId) {
		ErrorCode ret = errorCodeMap.get(errorId);
		return ret == null ? defultError : ret;
	}

	/**
	 * 返回默认的错误
	 * 
	 * @MethodName: error
	 * @author jianghan
	 * @date 2020-06-06 13:54:30
	 * @return
	 */
	public static ErrorCode error() {
		return defultError;
	}

}
