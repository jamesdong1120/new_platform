package com.pccw.system.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 定时任务表
 * TASK_SCHEDULE_JOB
 * @author JamesDong
 *
 */

public class TaskScheduleJob implements Serializable {
	
	public static final String STATUS_RUNNING = "1";
	public static final String STATUS_NOT_RUNNING = "0";
	public static final String CONCURRENT_IS = "1";
	public static final String CONCURRENT_NOT = "0";
	private BigDecimal jobId;//任务编号
	private String jobName;//任务名称
	private String jobGroup;//任务组
	private String jobStatus;//任务状态
	private String jobCronExpression;//任务运行表达式
	private String jobDescription;//任务描述
	private String beanClass;//任务类
	private String isConcurrent;//是否同时触发
	private String springId;//Spring编号
	private String methodName;//调用的方法名称
	private String createdBy;//创建者
	private Date createdDate;//创建时间
	private String lastUpdatedBy;//修改者
	private Date lastUpdatedDate;//修改时间
	
    
	public BigDecimal getJobId() {
		return jobId;
	}
    
	public void setJobId(BigDecimal jobId) {
		this.jobId = jobId;
	}
    
	public String getJobName() {
		return jobName;
	}
    
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
    
	public String getJobGroup() {
		return jobGroup;
	}
    
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
    
	public String getJobStatus() {
		return jobStatus;
	}
    
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
    
	public String getJobCronExpression() {
		return jobCronExpression;
	}
    
	public void setJobCronExpression(String jobCronExpression) {
		this.jobCronExpression = jobCronExpression;
	}
    
	public String getJobDescription() {
		return jobDescription;
	}
    
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
    
	public String getBeanClass() {
		return beanClass;
	}
    
	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}
    
	public String getIsConcurrent() {
		return isConcurrent;
	}
    
	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}
    
	public String getSpringId() {
		return springId;
	}
    
	public void setSpringId(String springId) {
		this.springId = springId;
	}
    
	public String getMethodName() {
		return methodName;
	}
    
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
    
	public String getCreatedBy() {
		return createdBy;
	}
    
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
    
	public Date getCreatedDate() {
		return createdDate;
	}
    
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
    
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
    
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
    
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
    
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	

}