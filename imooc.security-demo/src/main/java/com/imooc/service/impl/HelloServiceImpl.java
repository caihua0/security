package com.imooc.service.impl;

import org.springframework.stereotype.Service;

import com.imooc.service.HelloService;
@Service
public class HelloServiceImpl implements HelloService{

	@Override
	public String greeting(String name) {
		// TODO Auto-generated method stub
		System.out.println("greeting");
		return "hello"+name;
	}

}
