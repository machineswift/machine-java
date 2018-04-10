package com.machine.quartz;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuartzMainTest {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext(new String[] { "classpath:applicationContext.xml" });
	}
}
