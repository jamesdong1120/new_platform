package com.pccw.bedc.dao;


import java.util.Map;
import java.util.List;

import com.pccw.bedc.model.BciBankTrans;

/************
 * @Dao
 * @author JamesDong
 *
 */
public interface BciBankTransMapper {    

	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    int deleteByPrimaryKey(BciBankTrans po);
	
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
    BciBankTrans selectByPrimaryKey(Map<String,Object> map);
    
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
    List<BciBankTrans> getPageData(Map<String,Object> map);
    
    /**
     * 获取分页的总记录条数
     * @param map
     * @return
     */
    int getPageCount(Map<String,Object> map);
    
    
    /**
     * 获取银行的交易代码
     * @param map
     * @return
     */
    List<BciBankTrans> getBankTrans(Map<String,Object> map);
	

}


