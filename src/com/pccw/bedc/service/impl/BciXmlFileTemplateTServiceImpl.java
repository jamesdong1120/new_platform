package com.pccw.bedc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.bedc.dao.BciXmlFileTemplateTMapper;
import com.pccw.bedc.model.BciXmlFileTemplateT;
import com.pccw.bedc.service.BciXmlFileTemplateTService;

@Service("bciXmlFileTemplateTService")
public class BciXmlFileTemplateTServiceImpl implements BciXmlFileTemplateTService {
	private BciXmlFileTemplateTMapper bciXmlFileTemplateTMapper;
	@Autowired
	public void setBciXmlFileTemplateTMapper(BciXmlFileTemplateTMapper bciXmlFileTemplateTMapper) {
		this.bciXmlFileTemplateTMapper = bciXmlFileTemplateTMapper;
	}
	
	
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    @Override
    public int deleteByPrimaryKey(BciXmlFileTemplateT po){
    	return bciXmlFileTemplateTMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  bciXmlFileTemplateTMapper.insert(map);
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  bciXmlFileTemplateTMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public BciXmlFileTemplateT selectByPrimaryKey(Map<String,Object> map){
    	return  bciXmlFileTemplateTMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(Map<String,Object> map){
    	return  bciXmlFileTemplateTMapper.updateByPrimaryKeySelective(map);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return bciXmlFileTemplateTMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<BciXmlFileTemplateT> getPageData(Map<String, Object> map) {
		return bciXmlFileTemplateTMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return bciXmlFileTemplateTMapper.getPageCount(map);
	}


	@Override
	public BciXmlFileTemplateT getBankTranTemple(Map<String, String> map) {
		return bciXmlFileTemplateTMapper.getBankTranTemple(map);
	}
	

}
