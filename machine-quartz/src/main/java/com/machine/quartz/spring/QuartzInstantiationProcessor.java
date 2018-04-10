package com.machine.quartz.spring;

import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 *Spring容器启动之后需要进行一些初始化操做(只执行一次)
 *
 */
@Component
public class QuartzInstantiationProcessor {
	@PostConstruct
	public void init() {
		System.out.println("Spring容器启动之后需要进行一些初始化操做(只执行一次)");
	}
}
