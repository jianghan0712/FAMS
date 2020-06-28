/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.oms.apc.service.impl;

import javax.annotation.PostConstruct;

import org.apache.ignite.IgniteCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purefun.fams.ace.constant.AceCacheConstant;
import com.purefun.fams.ace.key.AceApcPositionKeyEntity;
import com.purefun.fams.ace.oms.AceApcPositionBO;
import com.purefun.fams.ace.oms.apc.service.PositionService;
import com.purefun.fams.common.enums.RespondEnums;
import com.purefun.fams.framework.common.ace.oms.apc.request.PositionRequest;
import com.purefun.fams.framework.common.ace.oms.apc.respond.PositionRespond;
import com.purefun.fams.framework.common.exception.FAMSException;
import com.purefun.fams.framework.common.util.AssertUtil;

/**
 * @Classname: PositionServiceImpl
 * @Description:
 * @author JiangHan
 * @date 2020-05-27 16:51:51
 */
@Service
public class PositionServiceImpl implements PositionService {
	private static final Logger logger = LogManager.getLogger(PositionServiceImpl.class);

	@Autowired
	private com.purefun.fams.framework.ignite.expose.IgniteCache<AceApcPositionKeyEntity, AceApcPositionBO> igniteCache;
	/** 持仓类缓存 */
	private IgniteCache<AceApcPositionKeyEntity, AceApcPositionBO> positionCache;

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void initService() {
		positionCache = igniteCache.getCache(AceCacheConstant.APCCacheType.PositionCache);
	}

	@SuppressWarnings("finally")
	@Override
	public PositionRespond freezePosition(PositionRequest request) {
		// TODO Auto-generated method stub
		checkPositionRequest(request);

		StringBuilder errorMessage = new StringBuilder("冻结持仓失败 ！");

		String account = request.getAccount();
		String securityId = request.getSecurityId();
		String exch = request.getExch();
		long volume = request.getVolume();

		PositionRespond respond = new PositionRespond();
		try {
			AceApcPositionKeyEntity key = new AceApcPositionKeyEntity(account, securityId, exch);
			AceApcPositionBO bo = positionCache.get(key);

			if (bo == null) {
				errorMessage.append("账户持仓不存在！").append("account=").append(account).append(", securityId=")
						.append(securityId).append(", exch=").append(exch);
				throw new FAMSException(errorMessage.toString());
			}
			/** 如果可用持仓小于要冻结的volume */
			if (bo.getAvailableQty().compareTo(volume) < 0) {
				errorMessage.append("可用持仓不足: account=").append(account).append(", securityId=").append(securityId)
						.append(", AvailableQty=").append(bo.getAvailableQty()).append(", volume=").append(volume);
				throw new FAMSException(errorMessage.toString());
			}
			long avaliableQty = bo.getAvailableQty() - volume;
			long freezeQty = bo.getFreezeQty() + volume;

			bo.setAvailableQty(avaliableQty);
			bo.setFreezeQty(freezeQty);

			positionCache.replace(key, bo);
		} catch (Exception e) {
			// TODO: handle exception
			respond.setResultCode(RespondEnums.FAIL.getCode());
			respond.setResultMsg(errorMessage.toString());
		} finally {
			return respond;
		}
	}

	@Override
	public PositionRespond unfreezePosition(PositionRequest request) {
		return null;
	}

	/**
	 * position请求检查
	 * 
	 * @MethodName: checkPositionRequest
	 * @author JiangHan
	 * @date 2020-05-27 16:20:21
	 * @param request
	 */
	private void checkPositionRequest(PositionRequest request) {
		AssertUtil.assertNotBlank(request.getAccount(), 100002);
		AssertUtil.assertNotBlank(request.getExch(), 100002);
		AssertUtil.assertNotBlank(request.getSecurityId(), 100002);
		AssertUtil.assertTrue(request.getVolume() <= 0, 100002);
	}
}
