package com.feng.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class MySelfRule {
	@Bean
	public IRule myRule() {
		//return new RandomRule();   //Ribbon默认为轮询，修改为自定义的随机
		return new RandomRule_mine();   //自定义的加载策略，每台机器访问5次然后轮询
	}
}
