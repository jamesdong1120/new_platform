package com.pccw.bedc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.bedc.dao.BciXmlFilesTMapper;
import com.pccw.bedc.model.BciXmlFilesT;
import com.pccw.bedc.service.BciXmlFilesTService;

@Service("bciXmlFilesTService")
public class BciXmlFilesTServiceImpl implements BciXmlFilesTService {
	private BciXmlFilesTMapper bciXmlFilesTMapper;
	@Autowired
	public void setBciXmlFilesTMapper(BciXmlFilesTMapper bciXmlFilesTMapper) {
		this.bciXmlFilesTMapper = bciXmlFilesTMapper;
	}
	
	
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    @Override
    public int deleteByPrimaryKey(BciXmlFilesT po){
    	return bciXmlFilesTMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  bciXmlFilesTMapper.insert(map);
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  bciXmlFilesTMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public BciXmlFilesT selectByPrimaryKey(Map<String,Object> map){
    	return  bciXmlFilesTMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(Map<String,Object> map){
    	return  bciXmlFilesTMapper.updateByPrimaryKeySelective(map);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return bciXmlFilesTMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<BciXmlFilesT> getPageData(Map<String, Object> map) {
		return bciXmlFilesTMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return bciXmlFilesTMapper.getPageCount(map);
	}
	

}
