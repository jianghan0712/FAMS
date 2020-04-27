/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.mdc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname: XTPMDConfig
 * @Description:
 * @author jianghan
 * @date 2020-04-25 22:58:17
 */
@Configuration
public class XTPConfig {
	@Value("${fams.ace.xtp.trade.ip}")
	private String tradeServerIp = "120.27.164.69";// xtp交易server的ip
	@Value("${fams.ace.xtp.trade.port}")
	private int tradeServerPort = 6001;// xtp交易server的端口
	@Value("${fams.ace.xtp.trade.key}")
	private String tradeKey = "b8aa7173bba3470e390d787219b2112e";// xtp交易serverkey
	@Value("${fams.ace.xtp.clientid}")
	private short clientId = 18;// xtp允许的clientid
	@Value("${fams.ace.xtp.quote.ip}")
	private String quoteServerIp = "120.27.164.138";// xtp行情server的ip
	@Value("${fams.ace.xtp.quote.port}")
	private int quoteServerPort = 6002;// xtp行情server的端口
	@Value("${fams.ace.xtp.account}")
	private String account = "53191000937";// xtp资金账号
	@Value("${fams.ace.xtp.password}")
	private String password = "OUr8sGgh";// xtp密码
	@Value("${fams.ace.xtp.log}")
	private String dataFolder = "/var/log/zts/xtp";// java api输出日志的本地目录

	/**
	 * Getter method for property <tt>tradeServerIp</tt>.
	 * 
	 * @return property value of tradeServerIp
	 */

	public String getTradeServerIp() {
		return tradeServerIp;
	}

	/**
	 * Setter method for property <tt>tradeServerIp</tt>.
	 * 
	 * @param tradeServerIp value to be assigned to property tradeServerIp
	 */
	public void setTradeServerIp(String tradeServerIp) {
		this.tradeServerIp = tradeServerIp;
	}

	/**
	 * Getter method for property <tt>tradeServerPort</tt>.
	 * 
	 * @return property value of tradeServerPort
	 */

	public int getTradeServerPort() {
		return tradeServerPort;
	}

	/**
	 * Setter method for property <tt>tradeServerPort</tt>.
	 * 
	 * @param tradeServerPort value to be assigned to property tradeServerPort
	 */
	public void setTradeServerPort(int tradeServerPort) {
		this.tradeServerPort = tradeServerPort;
	}

	/**
	 * Getter method for property <tt>tradeKey</tt>.
	 * 
	 * @return property value of tradeKey
	 */

	public String getTradeKey() {
		return tradeKey;
	}

	/**
	 * Setter method for property <tt>tradeKey</tt>.
	 * 
	 * @param tradeKey value to be assigned to property tradeKey
	 */
	public void setTradeKey(String tradeKey) {
		this.tradeKey = tradeKey;
	}

	/**
	 * Getter method for property <tt>clientId</tt>.
	 * 
	 * @return property value of clientId
	 */

	public short getClientId() {
		return clientId;
	}

	/**
	 * Setter method for property <tt>clientId</tt>.
	 * 
	 * @param clientId value to be assigned to property clientId
	 */
	public void setClientId(short clientId) {
		this.clientId = clientId;
	}

	/**
	 * Getter method for property <tt>quoteServerIp</tt>.
	 * 
	 * @return property value of quoteServerIp
	 */

	public String getQuoteServerIp() {
		return quoteServerIp;
	}

	/**
	 * Setter method for property <tt>quoteServerIp</tt>.
	 * 
	 * @param quoteServerIp value to be assigned to property quoteServerIp
	 */
	public void setQuoteServerIp(String quoteServerIp) {
		this.quoteServerIp = quoteServerIp;
	}

	/**
	 * Getter method for property <tt>quoteServerPort</tt>.
	 * 
	 * @return property value of quoteServerPort
	 */

	public int getQuoteServerPort() {
		return quoteServerPort;
	}

	/**
	 * Setter method for property <tt>quoteServerPort</tt>.
	 * 
	 * @param quoteServerPort value to be assigned to property quoteServerPort
	 */
	public void setQuoteServerPort(int quoteServerPort) {
		this.quoteServerPort = quoteServerPort;
	}

	/**
	 * Getter method for property <tt>account</tt>.
	 * 
	 * @return property value of account
	 */

	public String getAccount() {
		return account;
	}

	/**
	 * Setter method for property <tt>account</tt>.
	 * 
	 * @param account value to be assigned to property account
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * Getter method for property <tt>password</tt>.
	 * 
	 * @return property value of password
	 */

	public String getPassword() {
		return password;
	}

	/**
	 * Setter method for property <tt>password</tt>.
	 * 
	 * @param password value to be assigned to property password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter method for property <tt>dataFolder</tt>.
	 * 
	 * @return property value of dataFolder
	 */

	public String getDataFolder() {
		return dataFolder;
	}

	/**
	 * Setter method for property <tt>dataFolder</tt>.
	 * 
	 * @param dataFolder value to be assigned to property dataFolder
	 */
	public void setDataFolder(String dataFolder) {
		this.dataFolder = dataFolder;
	}

}
