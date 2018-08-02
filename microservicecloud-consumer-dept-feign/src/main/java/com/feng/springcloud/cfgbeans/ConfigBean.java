package com.feng.springcloud.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * @Configuration     =====applicationContext.xml
 * @author fenglei
 *
 */
import org.springframework.web.client.RestTemplate;
@Configuration
public class ConfigBean {
	
	@Bean
	@LoadBalanced    //LB，完成真正的为服务通过eureka找到服务
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
