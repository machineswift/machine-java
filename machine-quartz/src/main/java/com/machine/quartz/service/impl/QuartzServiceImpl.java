package com.machine.quartz.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.machine.quartz.service.QuartzService;

@Service
public class QuartzServiceImpl implements QuartzService {

	@Autowired
	SchedulerFactoryBean schedulerFactoryBean;

	@Override
	public boolean pause() {
		JobKey jobKey = new JobKey("jobDetail_1", JOB_GROUP_NAME);
		pauseJob(jobKey);
		return Boolean.TRUE;
	}

	@Override
	public boolean resume() {
		JobKey jobKey = new JobKey("jobDetail_1", JOB_GROUP_NAME);
		resumeJob(jobKey);
		return Boolean.TRUE;
	}

	@Override
	public boolean update() {
		updateQuartzJob("trigger_1", TRIGGER_GROUP_NAME, "0/10 * * * * ?");
		return Boolean.TRUE;
	}

	@Override
	public boolean remove() {
		removeJob("jobDetail_1", JOB_GROUP_NAME, "trigger_1", TRIGGER_GROUP_NAME);
		return false;
	}

	/**
	 * 暂停任务调度中的定时任务
	 */
	private void pauseJob(JobKey jobKey) {
		try {
			if (null == jobKey) {
				logger.info("修改调度任务参数不正常！");
				return;
			}
			logger.info("任务调度[JobGroup:" + jobKey.getGroup() + " JobName:" + jobKey.getName() + "]开始暂停!");
			schedulerFactoryBean.getScheduler().pauseJob(jobKey);
			logger.info("任务调度[JobGroup:" + jobKey.getGroup() + " JobName:" + jobKey.getName() + "]暂停成功!");
		} catch (Exception e) {
			logger.error("暂停任务调度中的定时任务异常！" + e.getMessage(), e);
		}
	}

	/**
	 * 恢复任务调度中的定时任务
	 */
	private void resumeJob(JobKey jobKey) {
		try {
			if (null == jobKey) {
				logger.info("修改调度任务参数不正常！");
				return;
			}
			logger.info("任务调度[JobGroup:" + jobKey.getGroup() + " JobName:" + jobKey.getName() + "]开始恢复!");
			schedulerFactoryBean.getScheduler().resumeJob(jobKey);
			logger.info("任务调度[JobGroup:" + jobKey.getGroup() + " JobName:" + jobKey.getName() + "]恢复成功!");
		} catch (Exception e) {
			logger.error("恢复任务调度中的定时任务异常！" + e.getMessage(), e);
		}
	}

	/**
	 * @Description: 修改一个任务的触发时间
	 * @param triggerName
	 *            触发器名
	 * @param triggerGroupName
	 *            触发器组名
	 * @param cron
	 *            时间设置，参考quartz说明文档
	 */
	public void updateQuartzJob(String triggerName, String triggerGroupName, String cron) {
		try {
			StdScheduler scheduler = (StdScheduler) schedulerFactoryBean.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
			CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
			if (trigger == null) {
				logger.info("没找到对应的触发器:triggerName" + triggerName + "  triggerGroupName:" + triggerGroupName);
				return;
			}

			String oldTime = trigger.getCronExpression();
			if (!oldTime.equalsIgnoreCase(cron)) {
				logger.debug("TriggerName:" + triggerName + "  triggerGroupName:" + triggerGroupName
						+ " cronExpression-old: " + oldTime);
				// 触发器
				TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
				// 触发器名,触发器组
				triggerBuilder.withIdentity(triggerName, triggerGroupName);
				triggerBuilder.startNow();
				// 触发器时间设定
				triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
				// 创建Trigger对象
				trigger = (CronTrigger) triggerBuilder.build();
				// 修改一个任务的触发时间
				scheduler.rescheduleJob(triggerKey, trigger);
				logger.debug("TriggerName:" + triggerName + "  triggerGroupName:" + triggerGroupName
						+ "cronExpression-new: " + cron);
			} else {
				logger.debug("cronExpression与原来相同: " + cron);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description: 移除一个任务
	 */
	public void removeJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName) {
		try {
			StdScheduler scheduler = (StdScheduler) schedulerFactoryBean.getScheduler();
			TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
			scheduler.pauseTrigger(triggerKey);// 停止触发器
			scheduler.unscheduleJob(triggerKey);// 移除触发器
			scheduler.deleteJob(JobKey.jobKey(jobName, jobGroupName));// 删除任务
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private final static String JOB_GROUP_NAME = "DEFAULT";
	private final static String TRIGGER_GROUP_NAME = "DEFAULT";
	private static final Logger logger = LogManager.getLogger(QuartzServiceImpl.class.getName());

}
