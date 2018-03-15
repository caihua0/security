package com.imooc.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix ="imooc.security")
public class SecurityProperties {

	private BrowserProperties browserProperties =new BrowserProperties();

	public BrowserProperties getBrowserProperties() {
		return browserProperties;
	}

	public void setBrowserProperties(BrowserProperties browserProperties) {
		this.browserProperties = browserProperties;
	}
}
