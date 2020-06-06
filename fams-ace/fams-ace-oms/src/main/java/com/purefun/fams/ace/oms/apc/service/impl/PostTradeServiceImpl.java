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
import com.purefun.fams.ace.enums.CashAccountType;
import com.purefun.fams.ace.key.AceApcCashKeyEntity;
import com.purefun.fams.ace.key.AceApcPositionKeyEntity;
import com.purefun.fams.ace.oms.AceApcCashBO;
import com.purefun.fams.ace.oms.AceApcPositionBO;
import com.purefun.fams.ace.oms.apc.request.CashOpRequest;
import com.purefun.fams.ace.oms.apc.request.DealRequest;
import com.purefun.fams.ace.oms.apc.response.DealRespond;
import com.purefun.fams.ace.oms.apc.service.CashService;
import com.purefun.fams.ace.oms.apc.service.PostTradeService;
import com.purefun.fams.common.util.MathUtil;
import com.purefun.fams.framework.common.exception.FAMSException;
import com.purefun.fams.framework.common.util.ResponseUtil;

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

	@Autowired
	private CashService cashService;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void initService() {
		cashCache = igniteCache.getCache(AceCacheConstant.APCCacheType.CashCache);
		positionCache = igniteCache.getCache(AceCacheConstant.APCCacheType.PositionCache);
	}

	@Override
	public DealRespond cutCashAndAddPosition(DealRequest request) {
		logger.info("处理增加cash减少position的请求：{}", request);
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
					FAMSException e = new FAMSException(200002);
					ResponseUtil.buildErrorResponse(res, e);
					logger.info("处理减少cash，增加position的失败，原因：{}", res);
					throw e;
				}
				/** 冻结资金不足以扣减，抛错 */
				if (MathUtil.compareThanZero(cashBo.getFreezeAmount().subtract(cash)) < 0) {
					FAMSException e = new FAMSException(200003);
					ResponseUtil.buildErrorResponse(res, e);
					logger.info("处理减少cash，增加position的失败，原因：{}", res);
					throw e;
				}
				/** 扣减冻结资金，扣减总现金 */
				cashBo.setFreezeAmount(cashBo.getFreezeAmount().subtract(cash));
				cashBo.setTotalAmount(cashBo.getTotalAmount().subtract(cash));
				/** 更新持仓 */
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
		DealRespond res = new DealRespond();
		try {
			String account = request.getAccount();
			String securityId = request.getSecurityId();
			String exch = request.getExch();
			String currency = request.getCurrency();
			long volume = request.getVolume(); // 卖单成交，如果之前有持仓，要更新持仓；如果持仓清空，删除该条记录
			BigDecimal price = request.getPrice();// 如果之前有持仓，需要重新计算平均价格
			BigDecimal cash = request.getCash(); // 卖单成交，增加可用金额

			try (Transaction tx = Ignition.ignite().transactions().txStart(TransactionConcurrency.OPTIMISTIC,
					TransactionIsolation.SERIALIZABLE)) {
				AceApcPositionKeyEntity positionKey = new AceApcPositionKeyEntity(account, securityId, exch);
				AceApcCashKeyEntity cashKey = new AceApcCashKeyEntity(account, currency);

				AceApcCashBO cashBo = cashCache.get(cashKey);
				AceApcPositionBO positionBo = positionCache.get(positionKey);
				/** 持仓记录不存在 抛错 */
				if (positionBo == null) {
					FAMSException e = new FAMSException(200005);
					ResponseUtil.buildErrorResponse(res, e);
					logger.info("处理减少position,增加cash的失败，原因：{}", res);
					throw e;
				}
				/** 冻结持仓不足以扣减，抛错 */
				if (positionBo.getFreezeQty() - volume < 0) {
					FAMSException e = new FAMSException(200004);
					ResponseUtil.buildErrorResponse(res, e);
					logger.info("处理减少position,增加cash的失败，原因：{}", res);
					throw e;
				}
				/** 扣减冻结持仓，扣减总持仓 */
				positionBo.setFreezeQty(positionBo.getFreezeQty() - volume);
				positionBo.setTotalQty(positionBo.getTotalQty() - volume);
				/** 清仓，删除该条记录 */
				if (positionBo.getTotalQty() == 0) {
					positionCache.remove(positionKey);
				} else {
					/** 重新计算成本均价 **/
					BigDecimal marketValue = positionBo.getMarketValue()
							.subtract(price.multiply(new BigDecimal(volume)));
					positionBo.setMarketValue(marketValue);
					positionBo.setAveCostPrice(
							marketValue.divide(new BigDecimal(positionBo.getTotalQty()), RoundingMode.CEILING));
				}

				/** 该账户的该币种资金账户为0 */
				if (cashBo == null) {
					cashBo = new AceApcCashBO();
					cashBo.setAccount(account);
					cashBo.setAvailableAmount(BigDecimal.ZERO);
					cashBo.setCurrency(currency);
					cashBo.setFreezeAmount(BigDecimal.ZERO);
					cashBo.setOnwayAmount(BigDecimal.ZERO);
					cashBo.setTotalAmount(BigDecimal.ZERO);
					cashCache.put(cashKey, cashBo);
				}

				/** 增加资金 */
				CashOpRequest cashRequest = new CashOpRequest();
				cashRequest.setAccount(account);
				cashRequest.setCurrency(currency);
				cashRequest.setAmount(cash);
				cashRequest.setInAccountType(CashAccountType.AVAILABLE);

				cashService.cashIn(cashRequest);
				positionCache.replace(positionKey, positionBo);

				tx.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return res;
		}
	}

}
