package com.imooc.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.imooc.web.filter.TimeFilter;
import com.imooc.web.interceptor.TimeInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private TimeInterceptor timeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(timeInterceptor);
	}

	@Bean
	public FilterRegistrationBean timerFiler(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		TimeFilter timefilter = new TimeFilter();
		registrationBean.setFilter(timefilter);
		
		List<String> urls = new ArrayList<>();
		urls.add("/*");//根据情况写路径
		registrationBean.setUrlPatterns(urls);
		return registrationBean;
	}
	
}
