package com.hl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hl.config.RedisUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DELL
 */
@RestController
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	RedisUtil redisUtil;

	/**
	 * http://localhost:8080/中文 不错，是可以支持中文的
	 * 
	 * @param params1
	 * @return
	 */
	@RequestMapping(value = "/home/{v}", method = RequestMethod.GET)
	@ResponseBody
	public Object demo(HttpServletRequest request, @PathVariable("v") String params1) {
		logger.info("---------------~~~~~~~~~~~~23424242~~~~~~~~~~~~~~~~~~~~~~~~~~");
		redisUtil.set("a", params1);
		logger.info((String)redisUtil.get("a"));
		return "welcome to us , demo   "  +  (String)redisUtil.get("a") +"     000";
	}
}
