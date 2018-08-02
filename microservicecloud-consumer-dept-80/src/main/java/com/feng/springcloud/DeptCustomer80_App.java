package com.feng.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import com.feng.myrule.MySelfRule;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name="microservicecloud-dept", configuration=MySelfRule.class)
public class DeptCustomer80_App {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(DeptCustomer80_App.class, args);
	}

}
