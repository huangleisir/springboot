package com.hl.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hl.entity.Entity;

@RestController
@RequestMapping("/weixin")
public class WeixinController {
	 private static final Logger log = LoggerFactory.getLogger(WeixinController.class);
	
	    
	    @RequestMapping(value = "/textMsg", method = RequestMethod.GET)
		@ResponseBody
		public String textMsg(){
	    	log.info("----receive textMsg msg---------");
			return "hello world!";
		}
}
