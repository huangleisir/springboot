package springbootDemo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	/**
	 * http://localhost:8080/中文    不错，是可以支持中文的
	 * @param value
	 * @return
	 */
	@RequestMapping(value = "/{value}", method = RequestMethod.GET)
	@ResponseBody
	public String demo(@PathVariable String value){
		
		return "welcome to us  "+value;
	}
}
