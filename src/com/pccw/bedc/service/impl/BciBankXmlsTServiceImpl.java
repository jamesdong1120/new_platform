package com.pccw.bedc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.bedc.dao.BciBankXmlsTMapper;
import com.pccw.bedc.model.BciBankXmlsT;
import com.pccw.bedc.service.BciBankXmlsTService;

@Service("bciBankXmlsTService")
public class BciBankXmlsTServiceImpl implements BciBankXmlsTService {
	private BciBankXmlsTMapper bciBankXmlsTMapper;
	@Autowired
	public void setBciBankXmlsTMapper(BciBankXmlsTMapper bciBankXmlsTMapper) {
		this.bciBankXmlsTMapper = bciBankXmlsTMapper;
	}
	
	
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    @Override
    public int deleteByPrimaryKey(BciBankXmlsT po){
    	return bciBankXmlsTMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  bciBankXmlsTMapper.insert(map);
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  bciBankXmlsTMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public BciBankXmlsT selectByPrimaryKey(Map<String,Object> map){
    	return  bciBankXmlsTMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(Map<String,Object> map){
    	return  bciBankXmlsTMapper.updateByPrimaryKeySelective(map);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return bciBankXmlsTMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<BciBankXmlsT> getPageData(Map<String, Object> map) {
		return bciBankXmlsTMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return bciBankXmlsTMapper.getPageCount(map);
	}
	

}
