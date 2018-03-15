package com.imooc.security.browser;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.imooc.security.browser.support.SimpleResponse;

public class BrowserSecutiryController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@RequestMapping("/authentication/require")
	@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
	public SimpleResponse requireAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		SavedRequest saveRequest = requestCache.getRequest(request, response);
		if(null!=saveRequest)
		{
			String target = saveRequest.getRedirectUrl();
			logger.info("引发跳转的请求是：{}",target);
			if(StringUtils.endsWithIgnoreCase(target, ".html")){
				redirectStrategy.sendRedirect(request, response, "");
				
			}
		}
		return new SimpleResponse("需要身份认证，请跳转到登录");
	}

}
