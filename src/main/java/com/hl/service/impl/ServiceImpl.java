package com.hl.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hl.service.Service;

public class ServiceImpl implements Service {

	@Override
	public int plus(int a,int b) {
		// TODO Auto-generated method stub
		System.out.println("这是dubbo服务端，你已经调到dubbo服务了，祝贺你成功了");
		int f = 9;
				int g = 10;
				int c =11;
				List list = new ArrayList();
				
		return a+b;
	}

}
