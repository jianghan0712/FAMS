/**
 * FAMS
 * Copyright (c) 1990-2020 All Rights Reserved.
 */
package com.purefun.fams.upstream.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.purefun.fams.framework.common.enums.ErrorCodeEnum;
import com.purefun.fams.framework.common.util.AssertUtil;
import com.purefun.fams.upstream.dao.UserinfoMapper;
import com.purefun.fams.upstream.domain.Userinfo;
import com.purefun.fams.upstream.enums.ResponseEnum;
import com.purefun.fams.upstream.request.LoginVO;
import com.purefun.fams.upstream.response.ResponseResult;

/**
 * @Classname: LoginController
 * @Description:
 * @author jianghan
 * @date 2020-05-03 18:34:09
 */
@RestController
@RequestMapping("/vue-element-admin/user")
public class LoginController {
	private static final Logger logger = LogManager.getLogger(LoginController.class);
	@Autowired
	private UserinfoMapper userMapper;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseResult<String> getLogin(@RequestBody LoginVO user) {
		AssertUtil.assertNotBlank(user.getUsername(), ErrorCodeEnum.PARAM_EXCEPTION);
		AssertUtil.assertNotBlank(user.getPassword(), ErrorCodeEnum.PARAM_EXCEPTION);

		Userinfo userinfo = userMapper.selectByEntity(user.getUsername(), null);
		ResponseResult<String> ret = new ResponseResult<String>();
		if (userinfo.getPassword().equals(user.getPassword())) {
			ret.setResult(userinfo.getToken());
			logger.info("用户{}已验证登录", user.getUsername());
		} else {
			ret.setCode(ResponseEnum.FAIL.getCode());
			ret.setMsg(ResponseEnum.FAIL.getDesc());
			logger.info("用户{}密码错误，输入：{}，存储的{}", user.getUsername(), user.getPassword(), userinfo.getPassword());
		}

		return ret;

	}

	@RequestMapping(value = "/user/info")
	public ResponseResult<Userinfo> getUserInfo(@RequestParam String token) {

		Userinfo userinfo = userMapper.selectByEntity(null, token);
		ResponseResult<Userinfo> ret = new ResponseResult<Userinfo>();
		if (userinfo != null) {
			ret.setResult(userinfo);
			logger.info("获取用户信息：{}", userinfo.toString());
		} else {
			ret.setCode(ResponseEnum.FAIL.getCode());
			ret.setMsg(ResponseEnum.FAIL.getDesc());
			logger.info("token未找到对应用户：{}，存储的{}", token);
		}

		return ret;
	}
}
