package com.pccw.bedc.service;

import java.util.List;
import java.util.Map;

import com.pccw.bedc.model.BciBank;

/**
 * 业务逻辑层
 * 
 *
 */
public interface BciBankService {
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    int deleteByPrimaryKey(BciBank po);
	
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
    BciBank selectByPrimaryKey(Map<String,Object> map);
    
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
    List<BciBank> getPageData(Map<String,Object> map);
    
    /**
     * 获取分页的总记录条数
     * @param map
     * @return
     */
    int getPageCount(Map<String,Object> map);
    
    /**
     * 获取银行树
     * @return
     */
    String getALLBankJsonTreeData();
    
    /**
     * 获取所有银行
     * @return
     */
    String getALLBankData();
	
	
	
}