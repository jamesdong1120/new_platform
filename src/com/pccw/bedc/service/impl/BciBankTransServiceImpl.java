package com.pccw.bedc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.bedc.dao.BciBankTransMapper;
import com.pccw.bedc.model.BciBank;
import com.pccw.bedc.model.BciBankTrans;
import com.pccw.bedc.service.BciBankTransService;

@Service("bciBankTransService")
public class BciBankTransServiceImpl implements BciBankTransService {
	private BciBankTransMapper bciBankTransMapper;
	@Autowired
	public void setBciBankTransMapper(BciBankTransMapper bciBankTransMapper) {
		this.bciBankTransMapper = bciBankTransMapper;
	}
	
	
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    @Override
    public int deleteByPrimaryKey(BciBankTrans po){
    	return bciBankTransMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  bciBankTransMapper.insert(map);
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  bciBankTransMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public BciBankTrans selectByPrimaryKey(Map<String,Object> map){
    	return  bciBankTransMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(Map<String,Object> map){
    	return  bciBankTransMapper.updateByPrimaryKeySelective(map);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return bciBankTransMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<BciBankTrans> getPageData(Map<String, Object> map) {
		return bciBankTransMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return bciBankTransMapper.getPageCount(map);
	}


	@Override
	public String getBciBankTransTreeData(Map<String, Object> map) {
		List<BciBankTrans> list=bciBankTransMapper.getBankTrans(map);
		StringBuffer buffer=new StringBuffer();
		buffer.append("[");
		int count=0;
		for(BciBankTrans bb:list){
			count++;
			String state="open";
			buffer.append("{\"id\":\""+bb.getTranCode()+"\",\"text\":\""+bb.getTranDesc()+"\",\"state\":\""+state+"\",\"isLeaf\":\""+1+"\",\"bankCode\":\""+bb.getBankCode()+"\"}");
			if(count!=list.size()){
				buffer.append(",");
			}
		}
		buffer.append("]");
		return buffer.toString();
	}
	

}
