/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.oms.apc.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.annotation.PostConstruct;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.transactions.Transaction;
import org.apache.ignite.transactions.TransactionConcurrency;
import org.apache.ignite.transactions.TransactionIsolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purefun.fams.ace.constant.AceCacheConstant;
import com.purefun.fams.ace.key.AceApcCashKeyEntity;
import com.purefun.fams.ace.key.AceApcPositionKeyEntity;
import com.purefun.fams.ace.oms.AceApcCashBO;
import com.purefun.fams.ace.oms.AceApcPositionBO;
import com.purefun.fams.ace.oms.apc.request.DealRequest;
import com.purefun.fams.ace.oms.apc.response.DealRespond;
import com.purefun.fams.ace.oms.apc.service.PostTradeService;
import com.purefun.fams.common.util.MathUtil;

/**
 * @Classname: PostTradeServiceImpl
 * @Description:
 * @author JiangHan
 * @date 2020-05-27 17:08:44
 */
@Service
public class PostTradeServiceImpl implements PostTradeService {
	private static final Logger logger = LogManager.getLogger(PostTradeServiceImpl.class);

	@Autowired
	private com.purefun.fams.framework.ignite.expose.IgniteCache igniteCache;
	/** 资金类缓存 */
	private IgniteCache<AceApcCashKeyEntity, AceApcCashBO> cashCache;
	/** 持仓类缓存 */
	private IgniteCache<AceApcPositionKeyEntity, AceApcPositionBO> positionCache;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void initService() {
		cashCache = igniteCache.getCache(AceCacheConstant.APCCacheType.CashCache);
		positionCache = igniteCache.getCache(AceCacheConstant.APCCacheType.PositionCache);
	}

	@Override
	public DealRespond cutCashAndAddPosition(DealRequest request) {
		DealRespond res = new DealRespond();
		try {
			String account = request.getAccount();
			String securityId = request.getSecurityId();
			String exch = request.getExch();
			String currency = request.getCurrency();
			String securityName = request.getSecurityName();
			long volume = request.getVolume(); // 买单成交，如果之前有持仓，要更新持仓；如果没有持仓，则新加持仓
			BigDecimal price = request.getPrice();// 如果之前有持仓，需要重新计算平均价格
			BigDecimal cash = request.getCash(); // 买单成交，扣减冻结金额

			try (Transaction tx = Ignition.ignite().transactions().txStart(TransactionConcurrency.OPTIMISTIC,
					TransactionIsolation.SERIALIZABLE)) {
				AceApcCashBO cashBo = cashCache.get(new AceApcCashKeyEntity(account, currency));
				AceApcPositionBO positionBo = positionCache.get(new AceApcPositionKeyEntity(account, securityId, exch));
				/** 资金记录不存在 抛错 */
				if (cashBo == null) {

				}
				/** 冻结资金不足以扣减，抛错 */
				if (MathUtil.compareThanZero(cashBo.getFreezeAmount().subtract(cash)) < 0) {

				}
				/** 扣减冻结资金，扣减总现金 */
				cashBo.setFreezeAmount(cashBo.getFreezeAmount().subtract(cash));
				cashBo.setTotalAmount(cashBo.getTotalAmount().subtract(cash));
				if (positionBo == null) {
					positionBo = new AceApcPositionBO();
					positionBo.setAccount(account);
					positionBo.setExch(exch);
					positionBo.setSecurityId(securityId);
					positionBo.setSecurityName(securityName);
					positionBo.setAvailableQty(0l);
					positionBo.setFreezeQty(0l);
					positionBo.setOnwayQty(0l);
					positionBo.setTotalQty(0l);
					positionBo.setAveCostPrice(BigDecimal.ZERO);
					positionBo.setAveCostPrice(price);
				}
				BigDecimal marketValue = positionBo.getMarketValue().add(price.multiply(new BigDecimal(volume)));
				long totalQty = positionBo.getTotalQty() + volume;

				positionBo.setOnwayQty(positionBo.getOnwayQty() + volume);
				positionBo.setTotalQty(totalQty);
				positionBo.setMarketValue(marketValue);
				positionBo.setAveCostPrice(marketValue.divide(new BigDecimal(totalQty), RoundingMode.CEILING));

				positionCache.put(new AceApcPositionKeyEntity(account, securityId, exch), positionBo);
				cashCache.replace(new AceApcCashKeyEntity(account, currency), cashBo);
				tx.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return res;
		}
	}

	@Override
	public DealRespond cutPositionAndAddCash(DealRequest request) {
		return null;
	}

}
