package com.machine.quartz.jobbean;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.machine.quartz.service.QuartzBusinessService;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class QuartzJobBean_1 extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("QuartzJobBean_1-- start");
		getApplicationContext(context).getBean("quartzBusinessServiceImpl", QuartzBusinessService.class)
				.testTriger1AndJobDetail1();
		logger.info("QuartzJobBean_1-- end");
	}

	private ApplicationContext getApplicationContext(final JobExecutionContext jobexecutioncontext) {
		try {
			return (ApplicationContext) jobexecutioncontext.getScheduler().getContext().get("applicationContextKey");
		} catch (SchedulerException e) {
			logger.error("jobexecutioncontext.getScheduler().getContext() error!", e);
			throw new RuntimeException(e);
		}
	}

	private static final Logger logger = LoggerFactory.getLogger(QuartzJobBean_1.class);

}
