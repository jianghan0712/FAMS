/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.king.component.account;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.purefun.fams.framework.common.enums.ErrorCodeEnum;
import com.purefun.fams.framework.common.exception.FAMSException;
import com.purefun.fams.king.component.strategy.model.Account;

/**
 * @Classname: AccountContainer
 * @Description: 保存账户和资金类信息
 * @author 015979
 * @date 2020-03-31 21:18:27
 */

public class AccountContainer {
	private static final Logger logger = LogManager.getLogger(AccountContainer.class);

	Map<String, Account> accountMap = new HashMap<String, Account>();

	/**
	 * 增加一个账户
	 * 
	 * @MethodName: addAccount
	 * @author 015979
	 * @date 2020-03-31 21:27:29
	 * @param account
	 */
	public void addAccount(Account account) {
		if (accountMap.containsKey(account.getAcct())) {
			logger.warn("已存在该账户的资金信息，操作后将进行覆盖，acct={}", account.getAcct());
		}
		accountMap.put(account.getAcct(), account);
	}

	/**
	 * 删除一个账户信息
	 * 
	 * @MethodName: removeAccount
	 * @author 015979
	 * @date 2020-03-31 21:31:00
	 * @param acct
	 */
	public void removeAccount(String acct) {
		accountMap.remove(acct);
	}

	/**
	 * 获取账户信息
	 * 
	 * @MethodName: getAccount
	 * @author 015979
	 * @date 2020-03-31 22:09:25
	 * @param acct
	 * @return
	 */
	public Account getAccount(String acct) throws FAMSException {
		Account account = accountMap.get(acct);
		if (account == null) {
			logger.error("资金账户不存在！acct={}", acct);
			throw new FAMSException(ErrorCodeEnum.KING_ACCOUNT_NOT_FOUNT);
		}
		return account;
	}

	/**
	 * 冻结资金
	 * 
	 * @MethodName: freeze
	 * @author 015979
	 * @date 2020-03-31 21:41:39
	 * @param acct
	 * @param money
	 * @throws FAMSException
	 */
	public void freeze(String acct, BigDecimal money) throws FAMSException {
		Account account = getAccount(acct);
		BigDecimal available = account.getAvailableCapital();
		if (available.compareTo(money) < 0) {
			logger.error("资金余额不足，无法完成冻结，acct={},AvailableCapital={},freeze={}", acct, available, money);
			throw new FAMSException(ErrorCodeEnum.KING_ACCOUNT_CAPITAL_NOT_ENOUGH);
		}
		account.setAvailableCapital(available.subtract(money));
		account.setFreezeCapital(account.getFreezeCapital().add(money));
		accountMap.put(acct, account);
	}

	/**
	 * 解冻资金
	 * 
	 * @MethodName: unfreeze
	 * @author 015979
	 * @date 2020-03-31 21:45:43
	 * @param acct
	 * @param money
	 * @param isDeal 是否是交易后的冻结扣减,
	 * @throws FAMSException
	 */
	public void unfreeze(String acct, BigDecimal money, boolean isDeal) throws FAMSException {
		Account account = getAccount(acct);
		BigDecimal freeze = account.getFreezeCapital();
		if (freeze.compareTo(money) < 0) {
			logger.error("已冻结资金不足以用于解冻，acct={},freeze={},unfreeze={}", acct, freeze, money);
			throw new FAMSException(ErrorCodeEnum.KING_ACCOUNT_CAPITAL_NOT_ENOUGH);
		}
		if (isDeal) {
			account.setTotalCapital(account.getTotalCapital().subtract(money));
			account.setFreezeCapital(freeze.subtract(money));
		} else {
			account.setAvailableCapital(account.getAvailableCapital().add(money));
			account.setFreezeCapital(freeze.subtract(money));
		}

		accountMap.put(acct, account);
	}

	/**
	 * 入金
	 * 
	 * @MethodName: cashin
	 * @author 015979
	 * @date 2020-03-31 21:47:33
	 * @param acct
	 * @param money
	 * @throws FAMSException
	 */
	public void cashin(String acct, BigDecimal money) throws FAMSException {
		Account account = getAccount(acct);

		account.setAvailableCapital(account.getAvailableCapital().add(money));
		account.setTotalCapital(account.getTotalCapital().add(money));
	}

	/**
	 * 出金
	 * 
	 * @MethodName: cashout
	 * @author 015979
	 * @date 2020-03-31 21:48:32
	 * @param acct
	 * @param money
	 */
	public void cashout(String acct, BigDecimal money) {
		Account account = getAccount(acct);
		account.setAvailableCapital(account.getAvailableCapital().subtract(money));
		account.setTotalCapital(account.getTotalCapital().subtract(money));
	}

	/**
	 * 获得账户的可用资金
	 * 
	 * @MethodName: getAvaliableCapital
	 * @author 015979
	 * @date 2020-04-02 20:40:29
	 * @param acct
	 * @return
	 */
	public BigDecimal getAvaliableCapital(String acct) {
		return getAccount(acct).getAvailableCapital();
	}
}
