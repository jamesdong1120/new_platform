package com.pccw.bedc.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.bedc.dao.BciBankBodyCfgMapper;
import com.pccw.bedc.dao.BciBankMapper;
import com.pccw.bedc.dao.BciBankTransMapper;
import com.pccw.bedc.model.BciBank;
import com.pccw.bedc.model.BciBankBodyCfg;
import com.pccw.bedc.model.BciBankTrans;
import com.pccw.bedc.service.BciBankBodyCfgService;

@Service("bciBankBodyCfgService")
public class BciBankBodyCfgServiceImpl implements BciBankBodyCfgService {
	private BciBankBodyCfgMapper bciBankBodyCfgMapper;
	private BciBankMapper bciBankMapper;
	private BciBankTransMapper bciBankTransMapper;
	@Autowired
	public void setBciBankTransMapper(BciBankTransMapper bciBankTransMapper) {
		this.bciBankTransMapper = bciBankTransMapper;
	}
	@Autowired
	public void setBciBankBodyCfgMapper(BciBankBodyCfgMapper bciBankBodyCfgMapper) {
		this.bciBankBodyCfgMapper = bciBankBodyCfgMapper;
	}
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
    public int deleteByPrimaryKey(BciBankBodyCfg po){
    	return bciBankBodyCfgMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  bciBankBodyCfgMapper.insert(map);
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  bciBankBodyCfgMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public BciBankBodyCfg selectByPrimaryKey(Map<String,Object> map){
    	return  bciBankBodyCfgMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(Map<String,Object> map){
    	return  bciBankBodyCfgMapper.updateByPrimaryKeySelective(map);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return bciBankBodyCfgMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<BciBankBodyCfg> getPageData(Map<String, Object> map) {
		return bciBankBodyCfgMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return bciBankBodyCfgMapper.getPageCount(map);
	}


	@Override
	public String getBankTreeJsonData() {
		List<BciBank> list=bciBankMapper.getALLBANK();
		StringBuffer buffer=new StringBuffer();
		buffer.append("[");
		int count=0;
		String state="open";
		Integer isLeaf=1;
		for(BciBank bb:list){
			count++;
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("bankCode", bb.getBankcode());
			List<BciBankTrans> bankTrans=bciBankTransMapper.getBankTrans(map);
			if(null!=bankTrans&&bankTrans.size()>=1){
				state="closed";
				isLeaf=0;
			}
			buffer.append("{\"id\":\""+bb.getBankcode()+"\",\"text\":\""+bb.getShortname()+"\",\"state\":\""+state+"\",\"corpcode\":\""+bb.getCorpcode()+"\",\"isLeaf\":\""+isLeaf+"\"}");
			if(count!=list.size()){
				buffer.append(",");
			}
		}
		buffer.append("]");
		return buffer.toString();
		
	}
	@Override
	public List<BciBankBodyCfg> getBankTranBodyConfig(Map<String, Object> map) {
		return bciBankBodyCfgMapper.getBankTranBodyConfig(map);
	}
	

}
