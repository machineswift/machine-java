package com.machine.quartz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machine.quartz.dao.QuartzDao;
import com.machine.quartz.service.QuartzBusinessService;

@Service
@SuppressWarnings("serial")
public class QuartzBusinessServiceImpl implements QuartzBusinessService {

	
	@Autowired
	QuartzDao quartzDao;
	

	@Override
	public void testTriger1AndJobDetail1() {
		logger.info("业务逻辑-----Triger--1--AndJobDetail--1");
	}

	@Override
	public void testTriger1AndJobDetail2() {
		logger.info("业务逻辑-----Triger--2--AndJobDetail--2");
		
	}
	
	private static final Logger logger = LoggerFactory.getLogger(QuartzBusinessServiceImpl.class);

}
