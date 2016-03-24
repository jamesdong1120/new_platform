package com.pccw.system.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.pccw.system.model.PortalFunc;

/**
 * 业务逻辑层
 * 
 *
 */
public interface PortalFuncService {
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    int deleteByPrimaryKey(PortalFunc po);
	
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
    PortalFunc selectByPrimaryKey(Map<String,Object> map);
    
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
    List<PortalFunc> getPageData(Map<String,Object> map);
    
    /**
     * 获取分页的总记录条数
     * @param map
     * @return
     */
    int getPageCount(Map<String,Object> map);
    
    
    String getChildFuncTreeJsonData(Map<String,Object> map);
    
    String getEffectChildFuncTreeJsonData(Map<String,Object> map);
    
    String getAllEffectFuncTreeJsonData(Map<String,Object> map);
    
    String getAllEffectRoleMenuTreeJsonData(Map<String,Object> map);
    
    String getInitMenuTreeJsonData(Map<String,Object> map);
    
    Integer getMaxFuncSort(Map<String,Object> map);
    
    
    void updateFuncSortMinusByRecursion(Map<String,Object> map);
    
    void updateFuncSortPlusByRecursion(Map<String,Object> map);
    
    List<PortalFunc> getFuncParent(Map<String,Object> map);
    
    
	
	
	
}