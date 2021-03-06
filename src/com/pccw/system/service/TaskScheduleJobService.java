package com.pccw.system.service;

import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;

import com.pccw.system.model.TaskScheduleJob;

/**
 * 业务逻辑层
 * 
 *
 */
public interface TaskScheduleJobService {
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    int deleteByPrimaryKey(TaskScheduleJob po);
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    int insert(Map<String,Object> map);
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    int insertSelective(Map<String,Object> map);
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    TaskScheduleJob selectByPrimaryKey(Map<String,Object> map);
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    int updateByPrimaryKeySelective(TaskScheduleJob taskScheduleJob);

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    int updateByPrimaryKey(Map<String,Object> map);
    
    
     /**
     * 获取分页数据
     * @param map
     * @return
     */
    List<TaskScheduleJob> getPageData(Map<String,Object> map);
    
    /**
     * 获取分页的总记录条数
     * @param map
     * @return
     */
    int getPageCount(Map<String,Object> map);
    
    
    /**
     * 更改job状态
     * @param map
     * @throws SchedulerException 
     */
    void changeStatus(Map<String,Object> map) throws SchedulerException;
    
    
    /**
     * 修改job的执行表达式
     * @param map
     * @param cron
     * @throws SchedulerException
     */
    void updateCron(Map<String,Object> map,String cron) throws SchedulerException;
	
	
	
}