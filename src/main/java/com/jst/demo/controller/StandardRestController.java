package com.jst.demo.controller;
import java.util.HashMap;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jst.demo.bean.LoginBean;
import com.jst.demo.bean.ResultBean;

/**
 * http://127.0.0.1:8866/demo/mem/login/234/23423
 * @author cmy
 *
 */
@RestController
@RequestMapping(value="/mem")
public class StandardRestController {
	private final Logger log = LoggerFactory.getLogger(StandardRestController.class);
	@Autowired
	private Environment config;
	@RequestMapping(value = "/login/{v1}/{v2}", method = RequestMethod.POST)
	private Object login(LoginBean loginBean,@PathVariable(value="v1")String value1,
			@PathVariable(value="v2")String  value2){
		HashMap dataMap = new HashMap();
		dataMap.put("v1", value1);
		dataMap.put("v2", value2);
		ResultBean result = new ResultBean("00", "success", dataMap);
		log.info(config.getProperty("self.company.name"));
		return result;
	}
}
