package com.purefun.fams.ace.oms.apc.service.impl;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;

import org.apache.ignite.IgniteCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purefun.fams.ace.constant.AceCacheConstant;
import com.purefun.fams.ace.key.AceApcCashKeyEntity;
import com.purefun.fams.ace.oms.AceApcCashBO;
import com.purefun.fams.ace.oms.apc.request.CashOpRequest;
import com.purefun.fams.ace.oms.apc.request.CashRequest;
import com.purefun.fams.ace.oms.apc.response.CashOpRespond;
import com.purefun.fams.ace.oms.apc.response.CashRespond;
import com.purefun.fams.ace.oms.apc.service.CashService;
import com.purefun.fams.common.enums.RespondEnums;
import com.purefun.fams.framework.common.enums.ErrorCodeEnum;
import com.purefun.fams.framework.common.exception.FAMSException;
import com.purefun.fams.framework.common.util.AssertUtil;

/**
 * 
 * @Classname: CashServiceImpl
 * @Description:
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
			respond.setResultCode(RespondEnums.FAIL.getCode());
			respond.setResultMsg(errorMessage.toString());
		} finally {
			return respond;
		}
	}

	@Override
	public CashRespond unfreezeCash(CashRequest request) {
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
			respond.setResultCode(RespondEnums.FAIL.getCode());
			respond.setResultMsg(errorMessage.toString());
		} finally {
			return respond;
		}
	}

	@Override
	public CashOpRespond cashIn(CashOpRequest request) {
		return null;
	}

	@Override
	public CashOpRespond cashOut(CashOpRequest request) {
		return null;
	}

	@Override
	public CashOpRespond cashInAndCashOut(CashOpRequest request) {
		return null;
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
		AssertUtil.assertNotBlank(request.getAccount(), ErrorCodeEnum.PARAM_EXCEPTION);
		AssertUtil.assertNotBlank(request.getCurrency(), ErrorCodeEnum.PARAM_EXCEPTION);
		AssertUtil.assertTrue(BigDecimal.ZERO.compareTo(request.getAmount()) <= 0, ErrorCodeEnum.PARAM_EXCEPTION);
	}
}
