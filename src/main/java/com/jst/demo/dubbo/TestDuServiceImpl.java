/*
* Copyright (c) 2015-2018 SHENZHEN JST SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 
*/
package com.jst.demo.dubbo;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;
import com.jst.demo.service.test.TestService;
import com.jst.prodution.base.bean.BaseBean;
import com.jst.prodution.demo.service.TestDuService;
/** 使用@Service定义TestDuService的提供者*/
@Service
public class TestDuServiceImpl implements TestDuService {

	@Autowired
	TestService testService ;
	
	@Override
	public BaseBean action(BaseBean input) {
		return testService.action(input);
	}

	@Override
	public int plus(int a, int b) {
		// TODO Auto-generated method stub
		return a+b;
	}



}
