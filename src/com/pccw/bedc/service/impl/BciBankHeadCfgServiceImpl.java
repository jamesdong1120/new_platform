package com.pccw.bedc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.bedc.dao.BciBankHeadCfgMapper;
import com.pccw.bedc.model.BciBankHeadCfg;
import com.pccw.bedc.service.BciBankHeadCfgService;

@Service("bciBankHeadCfgService")
public class BciBankHeadCfgServiceImpl implements BciBankHeadCfgService {
	private BciBankHeadCfgMapper bciBankHeadCfgMapper;
	@Autowired
	public void setBciBankHeadCfgMapper(BciBankHeadCfgMapper bciBankHeadCfgMapper) {
		this.bciBankHeadCfgMapper = bciBankHeadCfgMapper;
	}
	
	
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    @Override
    public int deleteByPrimaryKey(BciBankHeadCfg po){
    	return bciBankHeadCfgMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  bciBankHeadCfgMapper.insert(map);
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  bciBankHeadCfgMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public BciBankHeadCfg selectByPrimaryKey(Map<String,Object> map){
    	return  bciBankHeadCfgMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(Map<String,Object> map){
    	return  bciBankHeadCfgMapper.updateByPrimaryKeySelective(map);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return bciBankHeadCfgMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<BciBankHeadCfg> getPageData(Map<String, Object> map) {
		return bciBankHeadCfgMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return bciBankHeadCfgMapper.getPageCount(map);
	}


	@Override
	public List<BciBankHeadCfg> getBankTranHeadConfig(Map<String, Object> map) {
		return bciBankHeadCfgMapper.getBankTranHeadConfig(map);
	}
	

}
