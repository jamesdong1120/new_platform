package com.pccw.system.utils;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.pccw.system.model.TaskScheduleJob;


/*******************************************
 * <p>Title: </p>
 * <p>Description: 计划任务执行处 无状态</p>
 * <p>Company:PCCW </p> 
 * @author Jamesdong
 * @date 2016年2月16日 下午4:41:46
 ******************************************/
public class QuartzJobFactory implements Job {
	public final Logger log = Logger.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		TaskScheduleJob scheduleJob = (TaskScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);
	}
}