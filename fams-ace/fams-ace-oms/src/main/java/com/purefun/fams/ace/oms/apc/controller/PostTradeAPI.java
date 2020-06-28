package com.purefun.fams.ace.oms.apc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.purefun.fams.ace.oms.apc.service.PostTradeService;
import com.purefun.fams.framework.common.ace.oms.apc.request.DealRequest;
import com.purefun.fams.framework.common.ace.oms.apc.respond.DealRespond;

/**
 * post trade类对外暴露接口
 * 
 * @Classname: PostTradeAPI
 * @Description:
 * @author jianghan
 * @date 2020-06-26 15:22:53
 */
@RestController
@RequestMapping("/ace/oms/posttrade")
public class PostTradeAPI {
	@Autowired
	private PostTradeService postService;

	@RequestMapping(value = "/buyDeal", method = RequestMethod.POST)
	public DealRespond freeze(@RequestBody DealRequest request) {
		DealRespond response = postService.cutCashAndAddPosition(request);
		return response;
	}

	@RequestMapping(value = "/sellDeal", method = RequestMethod.POST)
	public DealRespond unfreeze(@RequestBody DealRequest request) {
		DealRespond response = postService.cutPositionAndAddCash(request);
		return response;
	}

}
