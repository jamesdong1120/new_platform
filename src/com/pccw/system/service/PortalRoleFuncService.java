package com.pccw.system.service;

import java.util.List;
import java.util.Map;

import com.pccw.system.model.PortalRoleFunc;

/**
 * 业务逻辑层
 * 
 *
 */
public interface PortalRoleFuncService {
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    int deleteByPrimaryKey(PortalRoleFunc po);
	
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
    PortalRoleFunc selectByPrimaryKey(Map<String,Object> map);
    
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
    List<PortalRoleFunc> getPageData(Map<String,Object> map);
    
    /**
     * 获取分页的总记录条数
     * @param map
     * @return
     */
    int getPageCount(Map<String,Object> map);
    
    boolean existRoleFunc(Map<String,Object> map);
    
    public int insertWithParentFunc(Map<String,Object> map);
    
    
    List<PortalRoleFunc> getRoleFuncs(Map<String,Object> map);
    
    int deleteRoleFuncs(Map<String,Object> map);
    
    void removeRoles(Map<String, Object> map);
    
    void removeFuncs(Map<String, Object> map);
	
	
	
}