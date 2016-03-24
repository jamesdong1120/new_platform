package com.pccw.bedc.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.pccw.bedc.dao.BciBankMapper;
import com.pccw.bedc.model.BciBank;
import com.pccw.bedc.service.BciBankService;
import com.pccw.system.model.PortalFunc;

@Service("bciBankService")
public class BciBankServiceImpl implements BciBankService {
	private BciBankMapper bciBankMapper;
	@Autowired
	public void setBciBankMapper(BciBankMapper bciBankMapper) {
		this.bciBankMapper = bciBankMapper;
	}
	
	
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    @Override
    public int deleteByPrimaryKey(BciBank po){
    	return bciBankMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  bciBankMapper.insert(map);
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  bciBankMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public BciBank selectByPrimaryKey(Map<String,Object> map){
    	return  bciBankMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(Map<String,Object> map){
    	return  bciBankMapper.updateByPrimaryKeySelective(map);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return bciBankMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<BciBank> getPageData(Map<String, Object> map) {
		return bciBankMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return bciBankMapper.getPageCount(map);
	}


	@Override
	public String getALLBankJsonTreeData() {
		List<BciBank> list=bciBankMapper.getALLBANK();
		StringBuffer buffer=new StringBuffer();
		buffer.append("[");
		int count=0;
		for(BciBank bb:list){
			count++;
			String state="open";
			buffer.append("{\"id\":\""+bb.getBankcode()+"\",\"text\":\""+bb.getShortname()+"\",\"state\":\""+state+"\",\"corpcode\":\""+bb.getCorpcode()+"\",\"isLeaf\":\""+1+"\"}");
			if(count!=list.size()){
				buffer.append(",");
			}
		}
		buffer.append("]");
		return buffer.toString();
	}


	@Override
	public String getALLBankData() {
		List<BciBank> list=bciBankMapper.getALLBANK();
		String data=null;
		if(null!=list&&list.size()>0){
			data=JSON.toJSONString(list);
		}
		return data;
	}
	

}
