package com.imooc.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Component
public class TimeInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("timer interceptor afterCompletion");
		long start = (long) request.getAttribute("starttime");
		System.out.println("timer interceptor postHandle:耗时"+(new Date().getTime()-start));
		System.out.println("ex is "+ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView ex)
			throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("timer interceptor postHandle");
		long start = (long) request.getAttribute("starttime");
		System.out.println("timer interceptor postHandle:耗时"+(new Date().getTime()-start));
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("timer interceptor preHandle");
		
		System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
		System.out.println(((HandlerMethod)handler).getMethod().getName());
		
		request.setAttribute("starttime", new Date().getTime());
		return true;
	}

}
