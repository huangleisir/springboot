package com.hl;
import javax.annotation.Resource;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hl.entity.City;
import com.hl.mapper.CityMapper;


@RestController
public class HomeController {
	 private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	/**
	 * http://localhost:8080/demo
	 * @param value
	 * @return
	 */
	@RequestMapping(value = "/demo2", method = RequestMethod.GET)
	//@ResponseBody
	public String demo(){
		
		logger.info("---------------~~~~~~~~~~~~23424242~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		
		return "welcome to demo ";
	}
	
	@Resource  
	    private CityMapper cityMapper;  
	    @RequestMapping("/test")  
	    String test1(){  
	        return "hello,test1()";  
	    }  
	    @RequestMapping("/findCity2")  
	    City findCity2(@RequestParam String id){  
	        return cityMapper.findCityById(id);  
	    } 

}
