package com.feng.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.feng.springcloud.entities.Dept;

@RestController
public class DeptController_Consumer {
	
	//private static final String REST_URL_PREFIX = "http://localhost:80";
    //通过微服务名称访问微服务
	private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT";
	
	 /**
	   * 使用restTemplate访问 restful接口, spring对rest访问进行了封装,
     * URL: REST请求地址
     * requestMap: 请求参数
     * ResponseBean.class: HTTP响应转换被转换成的对象类型
     */
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/consumer/dept/add")
	public boolean add(Dept dept) {
		return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, boolean.class);
	}
	
	@RequestMapping(value="/consumer/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id) {
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get" + id, Dept.class);
	}
	
	@RequestMapping(value="/consumer/dept/list")
	public List<Dept> list() {
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
	}
	
	
	
	
}
