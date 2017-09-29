package com.jst.demo.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jst.demo.bo.BaseBo;
import com.jst.framework.common.bean.Result;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/test")
public class TestController {
		/**
		 * 注意这个方法的请求，：     http://localhost:8866/demo/test/testdemo 
		 * postman 用raw，请求体用json，{"appVersion":"34234"} contentType ： Json(application/json)
		 * @param baseBo
		 * @return
		 */
	 	@RequestMapping(value = "/testdemo", method = RequestMethod.POST)
	    @ResponseBody
	    public Result test(@ApiParam(name="BaseBo",value="测试baseBo",required=true)@RequestBody BaseBo baseBo) {
	        Result result = new Result();
	        return result;
	    }
}
