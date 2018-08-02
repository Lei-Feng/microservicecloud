package com.feng.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.feng.springcloud.entities.Dept;
import com.feng.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DeptController {

	@Autowired
	private DeptService service;
	
	@RequestMapping(value="/dept/add", method=RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return service.add(dept);
	}
	
	@RequestMapping(value="/dept/get/{id}", method=RequestMethod.GET)
	//模拟错误，通过 @HystrixCommand 返回异常，避免雪崩
	//一旦调用方法失败并抛出错误信息后，会自动调用 @HystrixCommand 标注好的 fallbackMethod 掉用指定类中的方法
	@HystrixCommand(fallbackMethod="processHystrix_Get")
	public Dept get(@PathVariable Long id) {
		
		Dept dept = this.service.get(id);
		if(dept == null) {
			throw new RuntimeException("该ID " + id + "没有相对应的信息");
		}
		return dept;
	}
	
	public Dept processHystrix_Get(@PathVariable("id") Long id) {
		//return new Dept().setDeptno(id).setdnames("该ID " + id + "没有相对应的信息, null --> @HystrixCommand").setDb_source("no database in MySQL");
		Dept dept = new Dept();
		dept.setDeptno(id);
		dept.setDnames("该ID " + id + "没有相对应的信息, null -- @HystrixCommand");
		dept.setDb_soures("no database in MySQL");
		return dept;
	}
	
	@RequestMapping(value="/dept/list", method=RequestMethod.GET)
	public List<Dept> list() {
		//System.out.println("hello");
		return service.list();
	}
	
	
}
