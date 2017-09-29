package com.jst.demo.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jst.demo.bo.BaseBo;
import com.jst.demo.dubbo.TestDuServiceImpl;
import com.jst.framework.common.bean.Result;
import com.jst.prodution.base.bean.BaseBean;
import com.jst.prodution.demo.service.TestDuService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("")
public class TestDubboController {
	/**
	 * 注意这个方法的请求，：     http://localhost:8866/demo/test/testdemo 
	 * postman 用raw，请求体用json，{"appVersion":"34234"} contentType ： Json(application/json)
	 * @param baseBo
	 * @return
	 */
	/*@Resource
	private TestDuServiceImpl testDuService;*/
	
	
 	@RequestMapping(value = "/dubbo", method = RequestMethod.POST)
    @ResponseBody
    public Result test(@ApiParam(name="BaseBo",value="测试baseBo",required=true)@RequestBody BaseBo baseBo) {
        Result result = new Result();
        BaseBean input = new BaseBean();
        input.setAppVersion("8.8.8.8");
//        testDuService.action(input);
        return result;
    }
}
