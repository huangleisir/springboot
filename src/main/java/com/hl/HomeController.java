package com.hl;
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
public class HomeController {
	 private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	 
	 @Value("${com.bluecoffee.space.author}")
	    private String blogAuthor;

	    @Value("${com.bluecoffee.space.title}")
	    private String blogTitle;

	    @Value("${com.bluecoffee.random.str}")
	    private String randomStr;
	    
	    @Value("${com.bluecoffee.random.number}")
	    private String randomNumber;
	    @Value("${com.bluecoffee.random.bigNumber}")
	    private String bigNumber;
	    
	    @Value("${com.bluecoffee.random.test1}")
	    private String randomTest1;
	    @Value("${com.bluecoffee.random.test2}")
	    private String randomTest2;
	 
	 @Autowired
	    private Environment env;
	 /**
	  *http://localhost:9080/springboot/profile
	  * @return
	  */
	        
	    @RequestMapping("/profile")
	    public String testProfile(){
	    	logger.info("~~~read from application(-*).properties,aaa:"+env.getProperty("aaa"));
	        return env.getProperty("aaa")+" "+blogAuthor+" "+blogTitle;
	    }
	/**
	 * http://localhost:9080/springboot/中文    不错，是可以支持中文的
	 * http://localhost:9080/springboot/中文?name=黑妹&age=19&desc=美女一位
	 * http://localhost:9080/springboot/中文?name=黑妹&age=19&desc=美女一位&hobbies=sdfs,werwr,rtwret,ertwert,ertwert
	 * http://localhost:9080/springboot/中文?name=黑妹&age=19&desc=美女一位&hobbies=sdfs,werwr,rtwret,ertwert,ertwert&bodyDesc=身材不错
	 * @param value
	 * @return
	 */
	    @GetMapping(value = "/{value}")
	@ResponseBody
	public String demo(@PathVariable String value,Entity entity,@RequestParam("bodyDesc") String bodyDesc){
		
		logger.info("---------------~~~~~~~~~~~~23424242~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		logger.info("随机字符串"+randomStr+ " 随机数："+bigNumber+" 随机number:"+randomNumber +"  randomTest1"+randomTest1);
		
		
		return "welcome to us , "+entity.toString() +"    "+"随机字符串"+randomStr+ " 随机数："+bigNumber+" 随机number:"+randomNumber +"  randomTest1"+randomTest1;
	}
	    
	    @RequestMapping(value = "/demo", method = RequestMethod.GET)
		@ResponseBody
		public String demo(){
			return "hello world!";
		}
}
