/*
* Copyright (c) 2015-2018 SHENZHEN JST SCIENCE AND TECHNOLOGY DEVELOP CO., LTD. All rights reserved.
*
* 注意：本内容仅限于深圳市捷顺金科研发有限公司内部传阅，禁止外泄以及用于其他的商业目的 
*/
package com.jst.demo.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
//import com.alibaba.fastjson.JSON;
import com.jst.demo.bo.UserBo;
import com.jst.demo.dao.test.UserDao;
import com.jst.framework.common.bean.Result;
import com.jst.framework.common.enums.ReturnCodeEnum;
//import com.jst.prodution.demo.service.TestDuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "首页信息接口")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

	  private final static Logger log = LoggerFactory.getLogger(UserController.class);
	  
	  
	  
	  
	  
	  
	  @Resource
	  private  UserDao userDao ;
	  @Resource
	  private  com.jst.demo.dao.test1.UserDao userDao1 ;
	  
	  
	    @ApiOperation(value="用户登录", notes="http://127.0.0.1:8866/demo/swagger-ui.html")
	    @ApiImplicitParams({
	    @ApiImplicitParam(name = "SESSIONNO", value = "登录的SESSIONNO", required = true, paramType = "header" , dataType = "String")
	    })
	    @RequestMapping(value = "/login", method = RequestMethod.POST)
	    @ResponseBody
	    public Result info(@ApiParam(name="UserBo",value="登录bean",required=true)@RequestBody UserBo userBo) {
	        Result result = new Result();
	        return result;
	    }
	    

	    
	    @RequestMapping(value = "/test", method = RequestMethod.GET)
	    @ResponseBody
	    public Result test(HttpServletRequest request, HttpServletResponse response) {
	        Result result = new Result();
	        log.info("=================操作接口开始，【入参={}】");
	        result.setData(userDao.selectAll());
	        return result;
	    }

	    
	    @RequestMapping(value = "/test1", method = RequestMethod.GET)
	    @ResponseBody
	    public Result test1(HttpServletRequest request, HttpServletResponse response) {
	        Result result = new Result();
	        log.info("=================操作接口开始，【入参={}】");
	        result.setData(userDao1.selectAll());
	        return result;
	    }
	    
	    
}
