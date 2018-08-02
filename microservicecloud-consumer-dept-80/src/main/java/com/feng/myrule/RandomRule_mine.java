package com.feng.myrule;

import java.util.List;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.servo.jsr166e.ThreadLocalRandom;

public class RandomRule_mine extends AbstractLoadBalancerRule {

    /**
     * Randomly choose from all living servers
     */
    //   需求   依旧轮询，每个机器访问五次
	//  total = 0  
	// index = 0
	// 分析：我们5次， 但是微服务只有8001， 8002， 8003 三台机器
	// 
	
	
	private int total = 0;     //总共被调用的次数，目前要求每台被调用5此
	private int currentIndex = 0;  //  当前提供服务的机器号
	
	
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }
            //  Random 算法， 采用轮询策略
            //int index = chooseRandomInt(serverCount);
            //server = upList.get(index);
            
            
            
            // 自定义算法
            // private int total = 0;     //总共被调用的次数，目前要求每台被调用5此
        	// private int currentIndex = 0;  //  当前提供服务的机器号
            if(total < 5) {
            	server = upList.get(currentIndex);
            	total++;
            } else {
            	total = 0;
            	currentIndex ++;
            	if(currentIndex >= upList.size()) {
            		currentIndex = 0;
            	}
            }

            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

	@Override
	public Server choose(Object key) {
		return choose(getLoadBalancer(), key);
	}

	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {
		// TODO Auto-generated method stub
		
	}
}
