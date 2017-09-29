/*
* Copyright (c) 2015-2018 SHENZHEN JST SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 
*/
package com.jst.demo.service.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jst.framework.common.enums.ReturnCodeEnum;
import com.jst.demo.dao.test.UserDao;
import com.jst.prodution.base.bean.BaseBean;
import com.jst.prodution.base.service.AbstractBaseService;

@Service
public class TestService extends AbstractBaseService {
	
	@Autowired
	UserDao userDao ;
	

	@Override
	public void checkParams(BaseBean input) {
		
	}

	@Override
	@Transactional
	public BaseBean process(BaseBean input) {
		log.info("================user:",userDao.selectAll());
		return input;
	}

	@Override
	protected String getSysResCode() {
		return ReturnCodeEnum.FAIL.getCode();
	}

	
	
}
