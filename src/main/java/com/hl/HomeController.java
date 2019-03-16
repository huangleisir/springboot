package com.hl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

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
		return "welcome to us , demo   "  +  params1;
	}
}
