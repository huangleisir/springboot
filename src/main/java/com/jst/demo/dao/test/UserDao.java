/*
* Copyright (c) 2015-2018 SHENZHEN GUIYI SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 
*/
package com.jst.demo.dao.test;

import org.springframework.stereotype.Repository;

import com.jst.demo.bean.User;
import com.jst.demo.dao.base.AbstractDao;

@Repository
public class UserDao extends AbstractDao{
	
	 private final String MAPPER_NAMESPACE = "User." ;

	/**
	 *  
	 * @return
	 */
	public User selectAll() {
		return baseDao.getOne(MAPPER_NAMESPACE.concat("selectAll"), "") ;
	}
}
