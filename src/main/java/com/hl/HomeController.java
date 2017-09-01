package com.hl;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hl.service.Service;
@RestController
public class HomeController {
	 private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	 @Resource
	 private Service service;
	/**
	 * http://localhost:8080/中文    不错，是可以支持中文的
	 * @param value
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public String demo(){
		logger.info("---------------~~~~~~~~~~~这是dubbo调用端：马上就要调用dubbo服务咯~~");
		 int result = service.plus(3, 5);
		return result+"";
	}
}
