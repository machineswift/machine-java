package com.machine.activemq.consumer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerTest {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext(
				new String[] { "applicationContext.xml" });
	}
}