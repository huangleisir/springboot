package springbootDemo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String demo(){
		return "welcome to us";
	}
}
