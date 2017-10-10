package com.jst.demo.controller;
import java.util.HashMap;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jst.demo.bean.LoginBean;
import com.jst.demo.bean.ResultBean;
import com.jst.demo.rabbit.DemoQueue;
import com.jst.demo.rabbit.PayDelay5QueueConfig;

/**
 * http://localhost:8866/demo/mq/send?msg=10Smsg&pri=10&exp=10000
 * @author cmy
 *
 */
@RestController
@RequestMapping(value="/mq")
public class MQSendController {
	private final Logger log = LoggerFactory.getLogger(MQSendController.class);
	@Autowired
	private Environment config;
	
	/**
	 * 
	 * 
2017-10-10 13:49:35.787[INFO ][com.jst.demo.rabbit.PayDelay5QueueConfig] ---mqsend---3000--------30Smsg
2017-10-10 13:49:35.788[INFO ][com.jst.demo.controller.MQSendController] --------------------
2017-10-10 13:49:38.562[INFO ][com.jst.demo.rabbit.PayDelay5QueueConfig] ---mqsend---3000--------10Smsg
2017-10-10 13:49:38.563[INFO ][com.jst.demo.controller.MQSendController] --------------------
2017-10-10 13:50:05.799[INFO ][com.jst.demo.rabbit.PayDelay5QueueConfig] =======================收到mq信息：30Smsg
2017-10-10 13:50:05.800[INFO ][com.jst.demo.rabbit.PayDelay5QueueConfig] =======================收到mq信息：10Smsg
	 * 
	 * http://localhost:8866/demo/mq/send?msg=30Smsg&pri=10&exp=30000
http://localhost:8866/demo/mq/send?msg=10Smsg&pri=10&exp=10000
如果是这样，先请求上面的，2秒后请求下面的，发送端也是先发送上面的，2秒后发送下面的消息到该队列，
这里没有什么疑问，但是会出现先处理上面的消息，再处理下面的消息，14:50:799处理上面的消息 14:50:800处理下面的消息，
不用再等10秒，如果你要将下面的消息优先处理，那么必须将下面的优先级设置高于上面的优先级（1-255），超过255的话
rabbitMQ会崩掉，宇恒实验过，只能重装MQ。我试验过，调优先级没卵用。
	 * 
	 * 
	 * @param msg
	 * @param pri
	 * @param exp
	 * @return
	 */
	
	
	
	
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	private Object login(String msg,int pri,String exp){
		HashMap dataMap = new HashMap<>();
		ResultBean result = new ResultBean("00", "success", dataMap);
		DemoQueue.send("hello");
		PayDelay5QueueConfig.send(msg,exp,pri);
		log.info("--------------------");
		return result;
	}
}
