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
 * http://localhost:8866/demo/mq/send
 * @author cmy
 *
 */
@RestController
@RequestMapping(value="/mq")
public class MQSendController {
	private final Logger log = LoggerFactory.getLogger(MQSendController.class);
	@Autowired
	private Environment config;
	@RequestMapping(value = "/send", method = RequestMethod.GET)
	private Object login(){
		HashMap dataMap = new HashMap<>();
		ResultBean result = new ResultBean("00", "success", dataMap);
		DemoQueue.send("hello");
		PayDelay5QueueConfig.send("30秒后的消息");
		log.info("--------------------");
		return result;
	}
}
