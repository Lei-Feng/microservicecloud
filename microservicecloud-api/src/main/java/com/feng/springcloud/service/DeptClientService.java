package com.feng.springcloud.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.feng.springcloud.entities.Dept;

//@FeignClient(value="MICROSERVIECLOUD-DEPT")    //feign
@FeignClient(value="MICROSERVIECLOUD-DEPT", fallbackFactory=DeptClientServiceFallbackFactory.class)    // feign熔断机制
public interface DeptClientService {
	@RequestMapping(value="/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") long id);
	
	 @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
	 public boolean add(Dept dept);
	 
	  @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	  public List<Dept> list();
}
