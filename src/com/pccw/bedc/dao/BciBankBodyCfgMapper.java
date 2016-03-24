package com.pccw.bedc.dao;


import java.util.Map;
import java.util.List;

import com.pccw.bedc.model.BciBankBodyCfg;

/************
 * @Dao
 * @author JamesDong
 *
 */
public interface BciBankBodyCfgMapper {    

	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    int deleteByPrimaryKey(BciBankBodyCfg po);
	
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
    BciBankBodyCfg selectByPrimaryKey(Map<String,Object> map);
    
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
    List<BciBankBodyCfg> getPageData(Map<String,Object> map);
    
    /**
     * 获取分页的总记录条数
     * @param map
     * @return
     */
    int getPageCount(Map<String,Object> map);
    
    /**
     * 获取银行交易包体配置信息
     * @param map
     * @return
     */
    List<BciBankBodyCfg> getBankTranBodyConfig(Map<String,Object> map);
    
	

}


