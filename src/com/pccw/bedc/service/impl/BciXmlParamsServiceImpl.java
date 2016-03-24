package com.pccw.bedc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.bedc.dao.BciXmlParamsMapper;
import com.pccw.bedc.model.BciXmlParams;
import com.pccw.bedc.service.BciXmlParamsService;

@Service("bciXmlParamsService")
public class BciXmlParamsServiceImpl implements BciXmlParamsService {
	private BciXmlParamsMapper bciXmlParamsMapper;
	@Autowired
	public void setBciXmlParamsMapper(BciXmlParamsMapper bciXmlParamsMapper) {
		this.bciXmlParamsMapper = bciXmlParamsMapper;
	}
	
	
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    @Override
    public int deleteByPrimaryKey(BciXmlParams po){
    	return bciXmlParamsMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  bciXmlParamsMapper.insert(map);
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  bciXmlParamsMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public BciXmlParams selectByPrimaryKey(Map<String,Object> map){
    	return  bciXmlParamsMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(Map<String,Object> map){
    	return  bciXmlParamsMapper.updateByPrimaryKeySelective(map);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return bciXmlParamsMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<BciXmlParams> getPageData(Map<String, Object> map) {
		return bciXmlParamsMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return bciXmlParamsMapper.getPageCount(map);
	}


	@Override
	public List<BciXmlParams> getBankTransParams(Map<String, Object> map) {
		return bciXmlParamsMapper.getBankTransParams(map);
	}


	@Override
	public List<Map<String, String>> testmap() {
		return bciXmlParamsMapper.testmap();
	}


	@Override
	public List<BciXmlParams> getTemplateParams(String templateId) {
		return null;
	}
	

}
