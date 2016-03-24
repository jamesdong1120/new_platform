package com.pccw.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.pccw.system.dao.TaskScheduleJobMapper;
import com.pccw.system.model.TaskScheduleJob;
import com.pccw.system.service.TaskScheduleJobService;
import com.pccw.system.utils.QuartzJobFactory;
import com.pccw.system.utils.QuartzJobFactoryDisallowConcurrentExecution;

@Service("taskScheduleJobService")
public class TaskScheduleJobServiceImpl implements TaskScheduleJobService {
	private TaskScheduleJobMapper taskScheduleJobMapper;
	private SchedulerFactoryBean schedulerFactoryBean;
	
	@Autowired
	public void setSchedulerFactoryBean(SchedulerFactoryBean schedulerFactoryBean) {
		this.schedulerFactoryBean = schedulerFactoryBean;
	}


	@Autowired
	public void setTaskScheduleJobMapper(TaskScheduleJobMapper taskScheduleJobMapper) {
		this.taskScheduleJobMapper = taskScheduleJobMapper;
	}
	
	
	//@PostConstruct
	public void init() throws Exception {
		// 这里获取任务信息数据
		List<TaskScheduleJob> jobList = getAllTask();
	
		for (TaskScheduleJob job : jobList) {
			addJob(job);
		}
	}
	
	
	/**
	 * 从数据库中获取所有调度任务
	 * 区别于getAllJob
	 * @return
	 */
	public List<TaskScheduleJob> getAllTask(){
		return taskScheduleJobMapper.getAllTask();
		
	}
	
	/**
	 * 更改任务状态
	 * @param map
	 * @throws SchedulerException 
	 */
	@Override
	public void changeStatus(Map<String,Object> map) throws SchedulerException{
		TaskScheduleJob taskScheduleJob=selectByPrimaryKey(map);
		if(taskScheduleJob==null){
			return;
		}
		if("stop".equals(map.get("cmd"))){
			deleteJob(taskScheduleJob);
			taskScheduleJob.setJobStatus(TaskScheduleJob.STATUS_NOT_RUNNING);
		}else if("start".equals(map.get("cmd"))){
			taskScheduleJob.setJobStatus(TaskScheduleJob.STATUS_RUNNING);
			addJob(taskScheduleJob);
		}
		taskScheduleJobMapper.updateByPrimaryKeySelective(taskScheduleJob);
	}
	
	/**
	 * 更改任务表达式
	 * @param map
	 * @param cron
	 * @throws SchedulerException 
	 */
	public void updateCron(Map<String,Object> map,String cron) throws SchedulerException{
		TaskScheduleJob job=selectByPrimaryKey(map);
		if(job==null){
			return;
		}
		job.setJobCronExpression(cron);
		if(TaskScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())){
			updateJobCron(job);
		}
		taskScheduleJobMapper.updateByPrimaryKeySelective(job);
		
	}
	
	public void addJob(TaskScheduleJob job) throws SchedulerException{
		if(job==null||!TaskScheduleJob.STATUS_RUNNING.equals(job.getJobStatus())){
			return;
		}
		Scheduler scheduler=schedulerFactoryBean.getScheduler();
		System.out.println("add job=============="+job.getJobName());
		TriggerKey triggerKey=TriggerKey.triggerKey(job.getJobName(),job.getJobGroup());
		CronTrigger trigger=(CronTrigger) scheduler.getTrigger(triggerKey);
		//如果trigger 不存在就创建一个
		if(null==trigger){
			Class clazz=TaskScheduleJob.CONCURRENT_IS.equals(job.getIsConcurrent())?QuartzJobFactory.class:QuartzJobFactoryDisallowConcurrentExecution.class;
			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();

			jobDetail.getJobDataMap().put("scheduleJob", job);

			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getJobCronExpression());

			trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();

			scheduler.scheduleJob(jobDetail, trigger);
		}else{
			// Trigger已存在，那么更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getJobCronExpression());
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
		
	}
	

	/**
	 * 获取所有计划中的任务列表
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<TaskScheduleJob> getAllJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<TaskScheduleJob> jobList = new ArrayList<TaskScheduleJob>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				TaskScheduleJob job = new TaskScheduleJob();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setJobDescription("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setJobCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return jobList;
	}
	
	
	/**
	 * 所有正在运行的job
	 * 
	 * @return
	 * @throws SchedulerException
	 */
	public List<TaskScheduleJob> getRunningJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<TaskScheduleJob> jobList = new ArrayList<TaskScheduleJob>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			TaskScheduleJob job = new TaskScheduleJob();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			job.setJobName(jobKey.getName());
			job.setJobGroup(jobKey.getGroup());
			job.setJobDescription("触发器:" + trigger.getKey());
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			job.setJobStatus(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setJobCronExpression(cronExpression);
			}
			jobList.add(job);
		}
		return jobList;
	}
	
	/**
	 * 暂停一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void pauseJob(TaskScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.pauseJob(jobKey);
	}
	
	
	/**
	 * 恢复一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void resumeJob(TaskScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.resumeJob(jobKey);
	}
	
	/**
	 * 删除一个job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void deleteJob(TaskScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.deleteJob(jobKey);

	}
	
	/**
	 * 立即执行job
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void runAJobNow(TaskScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.triggerJob(jobKey);
	}
	
	/**
	 * 更新job时间表达式
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 */
	public void updateJobCron(TaskScheduleJob scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();

		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());

		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getJobCronExpression());

		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

		scheduler.rescheduleJob(triggerKey, trigger);
	}
	
	
	
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    @Override
    public int deleteByPrimaryKey(TaskScheduleJob po){
    	return taskScheduleJobMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  taskScheduleJobMapper.insert(map);
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  taskScheduleJobMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public TaskScheduleJob selectByPrimaryKey(Map<String,Object> map){
    	return  taskScheduleJobMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(TaskScheduleJob taskScheduleJob){
    	return  taskScheduleJobMapper.updateByPrimaryKeySelective(taskScheduleJob);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return taskScheduleJobMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<TaskScheduleJob> getPageData(Map<String, Object> map) {
		return taskScheduleJobMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return taskScheduleJobMapper.getPageCount(map);
	}
	

}
