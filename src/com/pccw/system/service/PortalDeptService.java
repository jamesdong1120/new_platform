package com.pccw.system.service;

import java.util.List;
import java.util.Map;

import com.pccw.system.model.PortalDept;

/**
 * 业务逻辑层
 * 
 *
 */
public interface PortalDeptService {
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    int deleteByPrimaryKey(PortalDept po);
	
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
    PortalDept selectByPrimaryKey(Map<String,Object> map);
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    int updateByPrimaryKeySelective(Map<String,Object> map);

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
    List<PortalDept> getPageData(Map<String,Object> map);
    
    /**
     * 获取分页的总记录条数
     * @param map
     * @return
     */
    int getPageCount(Map<String,Object> map);

	String getChildDeptTreeJsonData(Map<String, Object> map);

	String getEffectChildDeptTreeJsonData(Map<String, Object> map);

	void updateDeptSortPlusByRecursion(Map<String, Object> map);

	void updateDeptSortMinusByRecursion(Map<String, Object> map);

	int getMaxDeptSort(Map<String, Object> map);
	
	
	
}