/*
* Copyright (c) 2015-2018 SHENZHEN JST SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 

package com.jst.demo.dubbo;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.jst.demo.dao.test.UserDao;
import com.jst.demo.service.test.TestService;
import com.jst.prodution.base.bean.BaseBean;
import com.jst.prodution.demo.service.TestDuService;
*//** 使用@Service定义TestDuService的提供者*//*
@Service
public class TestDuServiceImplConsumer {
@Resource
	private UserDao userDao;
 用@Reference定义dubbo消费方* 
@Reference
	private TestDuService testDuService; 

	public BaseBean action(BaseBean input) {
		// TODO Auto-generated method stub
		return testDuService.action(input);
	}
	



}
*/