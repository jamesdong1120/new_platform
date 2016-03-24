package com.pccw.bedc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.bedc.dao.BciXmlLabelsTMapper;
import com.pccw.bedc.model.BciXmlLabelsT;
import com.pccw.bedc.service.BciXmlLabelsTService;

@Service("bciXmlLabelsTService")
public class BciXmlLabelsTServiceImpl implements BciXmlLabelsTService {
	private BciXmlLabelsTMapper bciXmlLabelsTMapper;
	@Autowired
	public void setBciXmlLabelsTMapper(BciXmlLabelsTMapper bciXmlLabelsTMapper) {
		this.bciXmlLabelsTMapper = bciXmlLabelsTMapper;
	}
	
	
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    @Override
    public int deleteByPrimaryKey(BciXmlLabelsT po){
    	return bciXmlLabelsTMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  bciXmlLabelsTMapper.insert(map);
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  bciXmlLabelsTMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public BciXmlLabelsT selectByPrimaryKey(Map<String,Object> map){
    	return  bciXmlLabelsTMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(Map<String,Object> map){
    	return  bciXmlLabelsTMapper.updateByPrimaryKeySelective(map);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return bciXmlLabelsTMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<BciXmlLabelsT> getPageData(Map<String, Object> map) {
		return bciXmlLabelsTMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return bciXmlLabelsTMapper.getPageCount(map);
	}
	

}
