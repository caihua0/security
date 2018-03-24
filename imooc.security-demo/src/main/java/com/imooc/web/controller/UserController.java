package com.imooc.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class UserController {
	
	@GetMapping("/user/me")
	public Object getCurrentUser(Authentication authentication) {
//		return SecurityContextHolder.getContext().getAuthentication();
		return authentication;
		//也可以Authentication 里面的一部分 可查
	}
	
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
	@JsonView(User.UserSimpleView.class)
	@ApiOperation(value="这个方法的描述")
	public List<User> query(UserQueryCondition condition,Pageable pageable){
		System.out.println(ReflectionToStringBuilder.toString(condition,ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(ReflectionToStringBuilder.toString(pageable,ToStringStyle.MULTI_LINE_STYLE));
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}
	
	@JsonView(User.UserDetailView.class)
	@RequestMapping(value="/user/{id:\\d+}", method=RequestMethod.GET)
	public User getInfo(@ApiParam("参数描述") @PathVariable String id){
//		抛出异常的时候用
//		throw new RuntimeException("user is not exist");
		System.out.println("进入getInfo服务");
		User user = new User();
		user.setUsername("tom");
		return user;
	}
	
	
	@PostMapping(value="/user")
	public User create(@Valid @RequestBody User user,BindingResult errors){
		
		if(errors.hasErrors()){
			errors.getAllErrors().stream().forEach(error-> {
				FieldError fieldError=(FieldError) error;
				String message =fieldError.getField() + " " + error.getDefaultMessage();
				System.out.println(message);
			}
			);
		}
		System.out.println(ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
		user.setId(1);
		return user;
	}
//	@PostMapping(value="/user")
//	public User create(@Valid @RequestBody User user){
//		System.out.println(ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
//		user.setId(1);
//		return user;
//	}
	
	@PutMapping("/user/{id:\\d+}")
	public User update(@Valid @RequestBody User user,BindingResult errors){
		
		if(errors.hasErrors()){
			errors.getAllErrors().stream().forEach(error-> {
//				FieldError fieldError=(FieldError) error;
//				String message =fieldError.getField() + " " + error.getDefaultMessage();
//				System.out.println(message);
				System.out.println(error.getDefaultMessage());
			}
			);
		}
		System.out.println(ReflectionToStringBuilder.toString(user,ToStringStyle.MULTI_LINE_STYLE));
//		User user =new User();
		user.setId(1);
		return user;
	}
	
	@DeleteMapping("/user/{id:\\d+}")
	public void delete(@PathVariable String id){
		System.out.println(id);
	}
}
