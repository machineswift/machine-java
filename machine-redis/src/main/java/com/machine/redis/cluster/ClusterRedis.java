package com.machine.redis.cluster;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class ClusterRedis {

	public static void main(String[] args) {
		// Redis集群的节点集合
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		jedisClusterNodes.add(new HostAndPort("192.168.100.120", 6380));
		/*jedisClusterNodes.add(new HostAndPort("192.168.100.120", 6381));
		jedisClusterNodes.add(new HostAndPort("192.168.100.120", 6382));
		jedisClusterNodes.add(new HostAndPort("192.168.100.121", 6380));
		jedisClusterNodes.add(new HostAndPort("192.168.100.121", 6381));
		jedisClusterNodes.add(new HostAndPort("192.168.100.121", 6382));*/
		// 节点，超时时间，最多重定向次数，链接池
		JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
		
		System.out.println(jedisCluster.set("age", "20"));
		System.out.println(jedisCluster.set("sex", "男"));
		System.out.println(jedisCluster.set("name", "machine"));
		
		System.out.println(jedisCluster.get("age"));
		System.out.println(jedisCluster.get("sex"));
		System.out.println(jedisCluster.get("name"));
		try {
			jedisCluster.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
