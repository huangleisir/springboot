package com.hl;

import com.hl.common.CustomException;
import com.hl.entity.Student;
import com.hl.entity.User;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DELL
 */
@Data
@RestController
@RequestMapping(value = "/home", method = RequestMethod.GET)
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
		Student s = new Student();
		return "welcome to us , demo   "  +  params1;
	}

	/**
	 * http://localhost/home/hl/888
	 * @param request
	 * @param params1
	 * @return
	 */
	@RequestMapping(value = "hl/{v}", method = RequestMethod.GET)
	@ResponseBody
	public Object hl(HttpServletRequest request, @PathVariable("v") String params1) {
		// 使用全局异常
		throw new CustomException(67,"试试全局异常" + params1);
		// logger.info("---------------~~~~~~~~~~~~23424242~~~~~~~~~~~~~~~~~~~~~~~~~~");
		//Student s = new Student();
		// return "welcome to us , demo   "  +  params1;
	}

	@RequestMapping(value = "postDemo", method = RequestMethod.POST)
	@ResponseBody
	public Object postDemo(@RequestBody User user) {
		logger.info("---------------~~~~~~~~~~~~23424242~~~~~~~~~~~~~~~~~~~~~~~~~~");
		return "welcome to us , demo   "  +  user.toString();
	}
}
