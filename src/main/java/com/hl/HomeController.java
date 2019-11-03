package com.hl;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import com.hl.service.Service;
@RestController
public class HomeController {
	 private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	 @Resource
	 private Service service;
	/**
	 * http://localhost:8080/中文    不错，是可以支持中文的
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/home/{v1}/{v2}", method = RequestMethod.GET)
	@ResponseBody
	public String demo(@PathVariable("v1") Integer v1,@PathVariable("v2") Integer v2){
		logger.info("---------------~~~~~~~~~~~这是dubbo调用端：马上就要调用dubbo服务咯~~");
		 int result ;
		 try{
			 result = service.plus(v1, v2);
		 }catch (Exception e){
			 return "error";
		 }
		return result+"";
	}
}
