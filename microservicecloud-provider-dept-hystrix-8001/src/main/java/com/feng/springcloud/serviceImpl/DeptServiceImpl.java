package com.feng.springcloud.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feng.springcloud.dao.DeptDao;
import com.feng.springcloud.entities.Dept;
import com.feng.springcloud.service.DeptService;
@Service
public class DeptServiceImpl implements DeptService{
	@Autowired
	private DeptDao dao;

	@Override
	public boolean add(Dept dept) {
		// TODO Auto-generated method stub
		return dao.addDept(dept);
	}

	@Override
	public Dept get(Long id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	public List<Dept> list() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	
	
}
