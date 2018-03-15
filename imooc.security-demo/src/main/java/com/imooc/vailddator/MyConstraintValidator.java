package com.imooc.vailddator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.imooc.service.HelloService;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object>{
	//注入服务
	@Autowired
//	@Qualifier("abc")
	public HelloService helloService;
	
	@Override
	public void initialize(MyConstraint constraint) {
		// TODO Auto-generated method stub
		System.out.println("this is my validator init ");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
		// TODO Auto-generated method stub
		helloService.greeting("tom");
		System.out.println(value);
		return false;
	}
}
