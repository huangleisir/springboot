package com.jst.demo.service.test.impl;

import org.springframework.stereotype.Service;

import com.jst.demo.service.test.TestServiceTwo;
@Service//@Service("testServiceTwo")
public class TestServiceTwoImpl implements TestServiceTwo {

	@Override
	public String serviceTwo() {
		System.out.println("do sth,serviceTwo");
		return "do sth,serviceTwo";
	}
	
	
	
	

}
