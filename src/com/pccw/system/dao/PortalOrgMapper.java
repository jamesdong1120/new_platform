package com.pccw.system.dao;


import java.util.List;
import java.util.Map;

import com.pccw.system.model.PortalOrg;

/************
 * @Dao
 * @author JamesDong
 *
 */
public interface PortalOrgMapper {    

	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    int deleteByPrimaryKey(PortalOrg po);
	
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
    PortalOrg selectByPrimaryKey(Map<String,Object> map);
    
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
    List<PortalOrg> getPageData(Map<String,Object> map);
    
    /**
     * 获取分页的总记录条数
     * @param map
     * @return
     */
    int getPageCount(Map<String,Object> map);
	
    
    List<PortalOrg> getChildOrgs(Map<String,Object> map);
    
    List<PortalOrg> getEffectChildOrgs(Map<String,Object> map);
    
    
    Integer getMaxOrgSort(Map<String,Object> map);
    
    
    void updateOrgSortMinusByRecursion(Map<String,Object> map);
    
    void updateOrgSortPlusByRecursion(Map<String,Object> map);

}


