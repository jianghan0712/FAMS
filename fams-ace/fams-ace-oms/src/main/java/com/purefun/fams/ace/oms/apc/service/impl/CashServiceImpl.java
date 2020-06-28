package com.purefun.fams.ace.oms.apc.service.impl;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.apache.ignite.IgniteCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purefun.fams.ace.constant.AceCacheConstant;
import com.purefun.fams.ace.enums.CashAccountType;
import com.purefun.fams.ace.key.AceApcCashKeyEntity;
import com.purefun.fams.ace.oms.AceApcCashBO;
import com.purefun.fams.ace.oms.apc.service.CashService;
import com.purefun.fams.common.enums.RespondEnums;
import com.purefun.fams.framework.common.ace.oms.apc.request.CashOpRequest;
import com.purefun.fams.framework.common.ace.oms.apc.request.CashRequest;
import com.purefun.fams.framework.common.ace.oms.apc.respond.CashOpRespond;
import com.purefun.fams.framework.common.ace.oms.apc.respond.CashRespond;
import com.purefun.fams.framework.common.exception.FAMSException;
import com.purefun.fams.framework.common.util.AssertUtil;

/**
 * 
 * @Classname: CashServiceImpl
 * @Description: 资金类操作
 * @author JiangHan
 * @date 2020-05-27 16:49:16
 */
@Service
public class CashServiceImpl implements CashService {
	private static final Logger logger = LogManager.getLogger(CashServiceImpl.class);

	@Autowired
	private com.purefun.fams.framework.ignite.expose.IgniteCache<AceApcCashKeyEntity, AceApcCashBO> igniteCache;
	/** 资金类缓存 */
	private IgniteCache<AceApcCashKeyEntity, AceApcCashBO> cashCache;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void initService() {
		cashCache = igniteCache.getCache(AceCacheConstant.APCCacheType.CashCache);
	}

	@Override
	public CashRespond freezeCash(CashRequest request) {
		AssertUtil.assertNotNull(request);

		logger.info("收到冻结资金请求：{}", request);

		CashRespond respond = new CashRespond();
		StringBuilder errorMessage = new StringBuilder("冻结资金失败 ！");

		try {
			checkCashRequest(request);
			String account = request.getAccount();
			String currency = request.getCurrency();
			BigDecimal amount = request.getAmount();

			AceApcCashKeyEntity key = new AceApcCashKeyEntity(account, currency);
			AceApcCashBO bo = cashCache.get(key);

			if (bo == null) {
				errorMessage.append("账户资金类型不存在！").append("account=").append(account).append(", currency=")
						.append(currency);
				throw new FAMSException(errorMessage.toString());
			}
			if (amount.compareTo(bo.getAvailableAmount()) > 0) {
				errorMessage.append("可用资金不足:account=").append(account).append(", currency=").append(currency)
						.append(", AvailableAmount=").append(bo.getAvailableAmount()).append(", freezeAmont=")
						.append(amount);
				throw new FAMSException(errorMessage.toString());
			}
			BigDecimal avaliable = bo.getAvailableAmount().subtract(amount);
			BigDecimal freeze = bo.getFreezeAmount().add(amount);
			bo.setAvailableAmount(avaliable);
			bo.setFreezeAmount(freeze);

			cashCache.replace(key, bo);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(errorMessage.toString());
			respond.setResultCode(RespondEnums.FAIL.getCode());
			respond.setResultMsg(errorMessage.toString());
		} finally {
			return respond;
		}
	}

	@Override
	public CashRespond unfreezeCash(CashRequest request) {
		AssertUtil.assertNotNull(request);

		logger.info("收到解冻资金请求：{}", request);

		CashRespond respond = new CashRespond();
		StringBuilder errorMessage = new StringBuilder("解冻资金失败 ！");
		try {
			checkCashRequest(request);

			String account = request.getAccount();
			String currency = request.getCurrency();
			BigDecimal amount = request.getAmount();

			AceApcCashKeyEntity key = new AceApcCashKeyEntity(account, currency);
			AceApcCashBO bo = cashCache.get(key);

			if (bo == null) {
				errorMessage.append("账户资金类型不存在！").append("account=").append(account).append(", currency=")
						.append(currency);
				throw new FAMSException(errorMessage.toString());
			}
			if (amount.compareTo(bo.getFreezeAmount()) > 0) {
				errorMessage.append("待解冻资金不足: account=").append(account).append(", currency=").append(currency)
						.append(", freezeAmont=").append(bo.getFreezeAmount()).append(", amount=").append(amount);
				throw new FAMSException(errorMessage.toString());
			}
			BigDecimal avaliable = bo.getAvailableAmount().add(amount);
			BigDecimal freeze = bo.getFreezeAmount().subtract(amount);
			bo.setAvailableAmount(avaliable);
			bo.setFreezeAmount(freeze);

			cashCache.replace(key, bo);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(errorMessage.toString());
			respond.setResultCode(RespondEnums.FAIL.getCode());
			respond.setResultMsg(errorMessage.toString());
		} finally {
			return respond;
		}
	}

	@Override
	public CashOpRespond cashIn(CashOpRequest request) {
		AssertUtil.assertNotNull(request);

		logger.info("收到入金请求：{}", request);
		CashOpRespond respond = new CashOpRespond();
		StringBuilder errorMessage = new StringBuilder("cashIn资金失败 ！");

		try {
			checkCashOpRequest(request);
			String account = request.getAccount();
			String currency = request.getCurrency();
			BigDecimal amount = request.getAmount();
			CashAccountType cashIn = CashAccountType.getByCode(request.getInAccountType());

			AceApcCashKeyEntity key = new AceApcCashKeyEntity(account, currency);
			AceApcCashBO bo = cashCache.get(key);

			if (bo == null) {
				errorMessage.append("账户资金类型不存在！").append("account=").append(account).append(", currency=")
						.append(currency);
				throw new FAMSException(errorMessage.toString());
			}

			addOpAmount(bo, cashIn, amount);

			cashCache.replace(key, bo);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(errorMessage.toString());
			respond.setResultCode(RespondEnums.FAIL.getCode());
			respond.setResultMsg(errorMessage.toString());
		} finally {
			return respond;
		}
	}

	@Override
	public CashOpRespond cashOut(CashOpRequest request) {
		AssertUtil.assertNotNull(request);

		logger.info("收到出金请求：{}", request);
		CashOpRespond respond = new CashOpRespond();
		StringBuilder errorMessage = new StringBuilder("cashOut资金失败 ！");

		try {
			checkCashOpRequest(request);
			String account = request.getAccount();
			String currency = request.getCurrency();
			BigDecimal amount = request.getAmount();
			CashAccountType cashOut = CashAccountType.getByCode(request.getOutAccountType());

			AceApcCashKeyEntity key = new AceApcCashKeyEntity(account, currency);
			AceApcCashBO bo = cashCache.get(key);

			if (bo == null) {
				errorMessage.append("账户资金类型不存在！").append("account=").append(account).append(", currency=")
						.append(currency);
				throw new FAMSException(errorMessage.toString());
			}

			BigDecimal outAvailabAmount = getOpAmount(bo, cashOut);
			if (amount.compareTo(outAvailabAmount) > 0) {
				errorMessage.append("可用资金不足:account=").append(account).append(", currency=").append(currency)
						.append(", 库中Amount=").append(outAvailabAmount).append(", 需要操作的金额=").append(amount);
				throw new FAMSException(errorMessage.toString());
			}
			cutOpAmount(bo, cashOut, amount);

			cashCache.replace(key, bo);
		} catch (Exception e) {
			logger.error(errorMessage.toString());
			respond.setResultCode(RespondEnums.FAIL.getCode());
			respond.setResultMsg(errorMessage.toString());
		} finally {
			return respond;
		}
	}

	@Override
	public CashOpRespond cashInAndCashOut(CashOpRequest request) {
		AssertUtil.assertNotNull(request);

		logger.info("收到同时出金入金请求：{}", request);
		CashOpRespond respond = new CashOpRespond();
		StringBuilder errorMessage = new StringBuilder("cashIn/cashOut资金失败 ！");

		try {
			checkCashOpRequest(request);
			String account = request.getAccount();
			String currency = request.getCurrency();
			BigDecimal amount = request.getAmount();
			CashAccountType cashIn = CashAccountType.getByCode(request.getInAccountType());
			CashAccountType cashOut = CashAccountType.getByCode(request.getOutAccountType());

			if (cashIn == cashOut) {
				return respond;
			}
			AceApcCashKeyEntity key = new AceApcCashKeyEntity(account, currency);
			AceApcCashBO bo = cashCache.get(key);

			if (bo == null) {
				errorMessage.append("账户资金类型不存在！").append("account=").append(account).append(", currency=")
						.append(currency);
				throw new FAMSException(errorMessage.toString());
			}

			BigDecimal outAvailabAmount = getOpAmount(bo, cashOut);
			if (amount.compareTo(outAvailabAmount) > 0) {
				errorMessage.append("可用资金不足:account=").append(account).append(", currency=").append(currency)
						.append(", 库中Amount=").append(outAvailabAmount).append(", 需要操作的金额=").append(amount);
				throw new FAMSException(errorMessage.toString());
			}
			cutOpAmount(bo, cashOut, amount);
			addOpAmount(bo, cashIn, amount);

			cashCache.replace(key, bo);
		} catch (Exception e) {
			logger.error(errorMessage.toString());
			respond.setResultCode(RespondEnums.FAIL.getCode());
			respond.setResultMsg(errorMessage.toString());
		} finally {
			return respond;
		}

	}

	/**
	 * 增加cashIn类型账户的资金
	 * 
	 * @MethodName: addOpAmount
	 * @author jianghan
	 * @date 2020-05-29 23:49:43
	 * @param bo     表中数据
	 * @param cashIn 类型
	 * @param amount 增加的金额
	 */
	private void addOpAmount(AceApcCashBO bo, CashAccountType cashIn, BigDecimal amount) {
		if (cashIn == CashAccountType.AVAILABLE) {
			bo.setAvailableAmount(bo.getAvailableAmount().add(amount));
		} else if (cashIn == CashAccountType.FREEZE) {
			bo.setFreezeAmount(bo.getFreezeAmount().add(amount));
		} else if (cashIn == CashAccountType.ONWAY) {
			bo.setOnwayAmount(bo.getOnwayAmount().add(amount));
		}
	}

	/**
	 * 减少cashOut类型账户的资金
	 * 
	 * @MethodName: cutOpAmount
	 * @author jianghan
	 * @date 2020-05-29 23:49:41
	 * @param bo      表中数据
	 * @param cashOut 类型
	 * @param amount  减少的金额
	 */
	private void cutOpAmount(AceApcCashBO bo, CashAccountType cashOut, BigDecimal amount) {
		if (cashOut == CashAccountType.AVAILABLE) {
			bo.setAvailableAmount(bo.getAvailableAmount().subtract(amount));
		} else if (cashOut == CashAccountType.FREEZE) {
			bo.setFreezeAmount(bo.getFreezeAmount().subtract(amount));
		} else if (cashOut == CashAccountType.ONWAY) {
			bo.setOnwayAmount(bo.getOnwayAmount().subtract(amount));
		}
	}

	/**
	 * 获取cashOut类型账户的资金
	 * 
	 * @MethodName: getOpAmount
	 * @author jianghan
	 * @date 2020-05-29 22:23:24
	 * @param bo
	 * @param cashOut
	 * @return
	 */
	private BigDecimal getOpAmount(AceApcCashBO bo, CashAccountType cashOut) {
		BigDecimal ret = null;
		if (cashOut == CashAccountType.AVAILABLE) {
			ret = bo.getAvailableAmount();
		} else if (cashOut == CashAccountType.FREEZE) {
			ret = bo.getFreezeAmount();
		} else if (cashOut == CashAccountType.ONWAY) {
			ret = bo.getOnwayAmount();
		}

		return ret;
	}

	/**
	 * cash请求检查
	 * 
	 * @MethodName: checkCashRequest
	 * @author JiangHan
	 * @date 2020-05-27 11:08:44
	 * @param request
	 */
	private void checkCashRequest(CashRequest request) {
		AssertUtil.assertNotBlank(request.getAccount(), 100002);
		AssertUtil.assertNotBlank(request.getCurrency(), 100002);
		AssertUtil.assertTrue(BigDecimal.ZERO.compareTo(request.getAmount()) <= 0, 100002);
	}

	/**
	 * @MethodName: checkCashOpRequest
	 * @author jianghan
	 * @date 2020-05-29 22:14:01
	 * @param request
	 */
	private void checkCashOpRequest(CashOpRequest request) {
		AssertUtil.assertNotBlank(request.getAccount(), 100002);
		AssertUtil.assertNotBlank(request.getCurrency(), 100002);
		AssertUtil.assertTrue(BigDecimal.ZERO.compareTo(request.getAmount()) <= 0, 100002);
		AssertUtil.assertNotNull(CashAccountType.getByCode(request.getInAccountType()));
		AssertUtil.assertNotNull(CashAccountType.getByCode(request.getOutAccountType()));
	}
}
