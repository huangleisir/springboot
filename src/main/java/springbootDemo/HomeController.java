package springbootDemo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import entity.Entity;

@RestController
public class HomeController {
	/**
	 * http://localhost:8080/中文    不错，是可以支持中文的
	 * http://localhost:8080/中文?name=黑妹&age=19&desc=美女一位
	 * http://localhost:8080/中文?name=黑妹&age=19&desc=美女一位&hobbies=sdfs,werwr,rtwret,ertwert,ertwert
	 * @param value
	 * @return
	 */
	@RequestMapping(value = "/{value}", method = RequestMethod.GET)
	@ResponseBody
	public String demo(@PathVariable String value,Entity entity,@RequestParam("bodyDesc") String bodyDesc){
		
		
		
		
		return "welcome to us , "+entity.toString();
	}
}
