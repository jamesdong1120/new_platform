package com.pccw.system.utils;

import org.apache.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.pccw.system.model.TaskScheduleJob;


/*******************************************
 * <p>Title: </p>
 * <p>Description: 若一个方法一次执行不完下次轮转时则等待改方法执行完后才执行下一次操作</p>
 * <p>Company:PCCW </p> 
 * @author Jamesdong
 * @date 2016年2月16日 下午4:44:00
 ******************************************/
@DisallowConcurrentExecution
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {
	public final Logger log = Logger.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		TaskScheduleJob scheduleJob = (TaskScheduleJob) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);

	}
}