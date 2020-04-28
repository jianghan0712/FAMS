/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.mdc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname: SinaConfig
 * @Description:
 * @author jianghan
 * @date 2020-04-26 16:46:57
 */
@Configuration
public class SinaConfig {
	@Value("${fams.ace.sina.url}")
	private String url;
	@Value("${fams.ace.sina.interval}")
	private int interval;
	@Value("${fams.ace.sina.maxnum}")
	private int maxnum;

	/**
	 * Getter method for property <tt>url</tt>.
	 * 
	 * @return property value of url
	 */

	public String getUrl() {
		return url;
	}

	/**
	 * Setter method for property <tt>url</tt>.
	 * 
	 * @param url value to be assigned to property url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Getter method for property <tt>interval</tt>.
	 * 
	 * @return property value of interval
	 */

	public int getInterval() {
		return interval;
	}

	/**
	 * Setter method for property <tt>interval</tt>.
	 * 
	 * @param interval value to be assigned to property interval
	 */
	public void setInterval(int interval) {
		this.interval = interval;
	}

	/**
	 * Getter method for property <tt>maxnum</tt>.
	 * 
	 * @return property value of maxnum
	 */

	public int getMaxnum() {
		return maxnum;
	}

	/**
	 * Setter method for property <tt>maxnum</tt>.
	 * 
	 * @param maxnum value to be assigned to property maxnum
	 */
	public void setMaxnum(int maxnum) {
		this.maxnum = maxnum;
	}

}
