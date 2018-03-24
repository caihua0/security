package com.imooc.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
//让这个类变成一个bean
@Component
public class MyUserDetailsService implements UserDetailsService{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		// 查找用户信息
		logger.info("登录用户名:{}",username);
		String encode = passwordEncoder.encode("123456");//从数据库读取密码
		logger.info("密码是:{}",encode);
		return new User(username, encode , AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
