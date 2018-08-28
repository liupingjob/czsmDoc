package com.czsm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 设置跨域请求
 * 
 * @author Mac(刘平) 2018年8月24日
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:8080").allowCredentials(true)
				.allowedMethods("GET", "POST", "DELETE", "PUT").maxAge(3600);
//		registry.addMapping("/**").allowedOrigins("*").allowCredentials(true)
//		.allowedMethods("GET", "POST", "DELETE", "PUT").maxAge(3600);
	}
}
