package com.pccw.system.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.pccw.system.model.PortalOrg;
import com.pccw.system.model.TaskScheduleJob;
import com.pccw.system.service.TaskScheduleJobService;
import com.pccw.system.utils.GenKeyUtils;
import com.pccw.system.utils.RequestUtils;
import com.pccw.system.utils.ResponseUtils;

@Controller
@RequestMapping("/system/taskScheduleJob")
public class TaskScheduleJobController {
	// 日志记录器
	public final Logger log = Logger.getLogger(this.getClass());
	private TaskScheduleJobService taskScheduleJobService;
	
	@Autowired
	public void setTaskScheduleJobService(
			TaskScheduleJobService taskScheduleJobService) {
		this.taskScheduleJobService = taskScheduleJobService;
	}
	
	@RequestMapping("/getPageResult")
	public String listTasks(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		Map<String,Object> conditions=RequestUtils.getConditionByClass(request, TaskScheduleJob.class,dateFormat);
		params.putAll(conditions);
		List<TaskScheduleJob> list=taskScheduleJobService.getPageData(params);
		Integer allCount=taskScheduleJobService.getPageCount(conditions);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response){
		String delstr=request.getParameter("delstr");
		List<TaskScheduleJob> list=JSON.parseArray(delstr, TaskScheduleJob.class);
		boolean flag=true;
		for(TaskScheduleJob po:list){
			try {
				taskScheduleJobService.deleteByPrimaryKey(po);
			} catch (Exception e) {
				flag=false;
				e.printStackTrace();
			}
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		boolean flag=true;
		String msg="操作成功";
		try {
			Map<String,Object> map=RequestUtils.getConditionByClass(request, TaskScheduleJob.class,dateFormat);
			flag= CronExpression.isValidExpression(map.get("jobCronExpression").toString());
			if(flag){
				map.put("jobId",GenKeyUtils.genDBKey("TASK_SCHEDULE_JOB", "JOB_ID"));
				taskScheduleJobService.insert(map);
			}else{
				msg="操作失败,请检查表达式是否正确or表单是否正确!!!";
			}
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respDefineSubmitStatusData(response, flag, msg);
		return null;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		boolean flag=true;
		String msg="操作成功";
		try {
			TaskScheduleJob job=(TaskScheduleJob) RequestUtils.getObjectConditionByClass(request, TaskScheduleJob.class,dateFormat);
			flag= CronExpression.isValidExpression(job.getJobCronExpression());
			if(flag){
				taskScheduleJobService.updateByPrimaryKeySelective(job);
			}else{
				msg="操作失败,请检查表达式是否正确or表单是否正确!!!";
			}
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respDefineSubmitStatusData(response, flag, msg);
		return null;
	}
	
	
	
	@RequestMapping("/changeJobStatus")
	public String changeJobStatus(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		boolean flag=true;
		String dateFormat="yyyy-MM-dd";
		String cmd=request.getParameter("cmd");
		Map<String,Object> condition=RequestUtils.getConditionByClass(request, TaskScheduleJob.class, dateFormat); 
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			map.put("cmd", cmd);
			condition.putAll(map);
			taskScheduleJobService.changeStatus(condition);;
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
	
	@RequestMapping("/updateCron")
	public String updateCron(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		boolean flag=true;
		String dateFormat="yyyy-MM-dd";
		Map<String,Object> map=RequestUtils.getConditionByClass(request, TaskScheduleJob.class, dateFormat); 
		String cron=map.get("jobCronExpression").toString();
		String msg="操作成功";
		try {
			//判断表达式是否正确
			flag= CronExpression.isValidExpression(cron);
			if(flag){
				taskScheduleJobService.updateCron(map, cron);
			}else{
				msg="操作失败,请检查表达式是否正确!!!";
			}
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respDefineSubmitStatusData(response, flag, msg);
		return null;
	}
	
	
	
	
	
	
	
		
}
