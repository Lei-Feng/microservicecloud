package com.feng.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.feng.springcloud.entities.Dept;

import feign.hystrix.FallbackFactory;
@Component  //不要忘记添加！！！！！！
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService>{

	@Override
	public DeptClientService create(Throwable cause) {
		// TODO Auto-generated method stub
		
		return new DeptClientService() {

			@Override
			public Dept get(long id) {
				Dept dept = new Dept();
				dept.setDeptno(id);
				dept.setDnames("该ID " + id + "没有相对应的信息，Consumer客户端提供的降级信息，此刻服务器已经关闭");
				dept.setDb_soures("no database in MySQL");
				return dept;
			}

			@Override
			public boolean add(Dept dept) {
				return false;
			}

			@Override
			public List<Dept> list() {
				return null;
			}
			
		};
	}

}
