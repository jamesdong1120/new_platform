package com.pccw.system.dao;


import java.util.Map;
import java.util.List;

import com.pccw.system.model.PortalRole;

/************
 * @Dao
 * @author JamesDong
 *
 */
public interface PortalRoleMapper {    

	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    int deleteByPrimaryKey(PortalRole po);
	
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
    PortalRole selectByPrimaryKey(Map<String,Object> map);
    
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
    List<PortalRole> getPageData(Map<String,Object> map);
    
    /**
     * 获取分页的总记录条数
     * @param map
     * @return
     */
    int getPageCount(Map<String,Object> map);
    
    List<PortalRole> getUserRole(Map<String,Object> map);
    
    int getUserRoleCount(Map<String,Object> map);
    
    List<PortalRole> getUserEnableSelectedRole(Map<String,Object> map);
    
    int getUserEnableSelectedRoleCount(Map<String,Object> map);
	

}


