/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.ace.oms.trade.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Classname: TradeService
 * @Description: 交易api
 * @author jianghan
 * @date 2020-06-15 22:18:09
 */
@FeignClient(name = "rpcServer-${Env}")
public interface TradeService {
	
	String hello();
}
