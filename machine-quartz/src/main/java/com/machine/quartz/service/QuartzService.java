package com.machine.quartz.service;

public interface QuartzService {
	
	boolean pause();

	boolean resume();

	boolean update();

	boolean remove();

}
