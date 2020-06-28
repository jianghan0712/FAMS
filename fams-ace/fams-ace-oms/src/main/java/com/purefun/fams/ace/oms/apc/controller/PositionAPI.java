package com.purefun.fams.ace.oms.apc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.purefun.fams.ace.oms.apc.service.PositionService;
import com.purefun.fams.framework.common.ace.oms.apc.request.PositionRequest;
import com.purefun.fams.framework.common.ace.oms.apc.respond.PositionRespond;

/**
 * position类对外暴露接口
 * 
 * @Classname: PositionAPI
 * @Description:
 * @author jianghan
 * @date 2020-06-26 15:22:53
 */
@RestController
@RequestMapping("/ace/oms/position")
public class PositionAPI {
	@Autowired
	private PositionService positionService;

	@RequestMapping(value = "/freeze", method = RequestMethod.POST)
	public PositionRespond freeze(@RequestBody PositionRequest request) {
		PositionRespond response = positionService.freezePosition(request);
		return response;
	}

	@RequestMapping(value = "/unfreeze", method = RequestMethod.POST)
	public PositionRespond unfreeze(@RequestBody PositionRequest request) {
		PositionRespond response = positionService.unfreezePosition(request);
		return response;
	}

}
