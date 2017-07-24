package springbootDemo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
	 private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	/**
	 * http://localhost:8080/中文    不错，是可以支持中文的
	 * http://localhost:8080/中文?name=黑妹&age=19&desc=美女一位
	 * http://localhost:8080/中文?name=黑妹&age=19&desc=美女一位&hobbies=sdfs,werwr,rtwret,ertwert,ertwert
	 * http://localhost:8080/中文?name=黑妹&age=19&desc=美女一位&hobbies=sdfs,werwr,rtwret,ertwert,ertwert&bodyDesc=身材不错
	 * @param value
	 * @return
	 */
	@RequestMapping(value = "/demo", method = RequestMethod.GET)
	@ResponseBody
	public String demo(){
		
		logger.info("---------------~~~~~~~~~~~~23424242~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		
		return "welcome to demo ";
	}
}
