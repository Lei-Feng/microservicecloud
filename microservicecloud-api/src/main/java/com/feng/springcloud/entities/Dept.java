package com.feng.springcloud.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

//Dept(Entity) ORM   mysql --> Dept(table)

/**
 *   @NoArgsConstructor  �ղ�
     @AllArgsConstructor �޲�
     @Data               get set
     @Accessors(chain = true)  ��ʽ��� dept.setDeptno(1L).setDb_source("db1");
 */

@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Dept implements Serializable{  
	
	private long deptno;   //pk
	private String dnames; //
	private String db_soures;  //�����ĸ����ݿ⣻��Ϊ΢�����ܿ���һ�������Ӧһ�����ݿ⣻ͬһ����Ϣ���洢����ͬ���ݿ�
	public long getDeptno() {
		return deptno;
	}
	public void setDeptno(long deptno) {
		this.deptno = deptno;
	}
	public String getDnames() {
		return dnames;
	}
	public void setDnames(String dnames) {
		this.dnames = dnames;
	}
	public String getDb_soures() {
		return db_soures;
	}
	public void setDb_soures(String db_soures) {
		this.db_soures = db_soures;
	}
	
	
	
	
	
}
