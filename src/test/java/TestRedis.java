import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.hl.Hao123Application;
import com.hl.config.RedisUtil;
import com.hl.service.IService;

import entity.Entity;


@SpringBootTest(classes={Hao123Application.class})
@RunWith(SpringRunner.class)  
@ContextConfiguration(classes=RedisUtil.class)  
public class TestRedis {
	static Logger log = LoggerFactory.getLogger(TestRedis.class);
	@Autowired
	RedisUtil redisUtil;
	@Autowired
	IService service;
	
	@org.junit.Test
	public void testRedis() {
		redisUtil.set("a", 23L);
		log.info((Integer)redisUtil.get("a")   +"");
		Entity entity = new Entity();
		entity.setAge(12);
		entity.setDesc("this is a boy");
		entity.setName("Hellen");
		redisUtil.set("b", entity);
		entity = (Entity)redisUtil.get("b");
		service.action();
	}
	
	
	

}
