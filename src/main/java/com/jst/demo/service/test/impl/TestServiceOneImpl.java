package com.jst.demo.service.test.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jst.demo.service.test.TestServiceOne;
import com.jst.demo.service.test.TestServiceTwo;
@Service//@Service("testServiceOne")
public class TestServiceOneImpl implements TestServiceOne {
	
	@Autowired
	TestServiceTwo serviceTwo;

	@Override
	public String serviceOne() {
		serviceTwo.serviceTwo();
		System.out.println("do sth,serviceOne");
		return "do sth,serviceOne";
	}
	
	
	
	

}
