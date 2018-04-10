package com.machine.redis.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.JedisCluster;

public class ClusterRedis {
	public static void main(String[] args) {
		ApplicationContext springContext = new ClassPathXmlApplicationContext(
				new String[] { "classpath:applicationContext.xml" });

		JedisCluster jedisCluster = (JedisCluster) springContext.getBean("jedisCluster");
		System.out.println(jedisCluster.set("age", "20"));
		System.out.println(jedisCluster.set("sex", "男"));
		System.out.println(jedisCluster.set("name", "machine"));

		System.out.println(jedisCluster.get("age"));
		System.out.println(jedisCluster.get("sex"));
		System.out.println(jedisCluster.get("name"));
	}
}
