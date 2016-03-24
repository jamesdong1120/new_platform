package com.pccw.bedc.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.bedc.dao.BciMultiLevelDicMapper;
import com.pccw.bedc.model.BciMultiLevelDic;
import com.pccw.bedc.service.BciMultiLevelDicService;
import com.pccw.system.model.PortalOrg;

@Service("bciMultiLevelDicService")
public class BciMultiLevelDicServiceImpl implements BciMultiLevelDicService {
	private BciMultiLevelDicMapper bciMultiLevelDicMapper;
	@Autowired
	public void setBciMultiLevelDicMapper(BciMultiLevelDicMapper bciMultiLevelDicMapper) {
		this.bciMultiLevelDicMapper = bciMultiLevelDicMapper;
	}
	
	
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    @Override
    public int deleteByPrimaryKey(BciMultiLevelDic po){
    	return bciMultiLevelDicMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  bciMultiLevelDicMapper.insert(map);
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  bciMultiLevelDicMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public BciMultiLevelDic selectByPrimaryKey(Map<String,Object> map){
    	return  bciMultiLevelDicMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(Map<String,Object> map){
    	return  bciMultiLevelDicMapper.updateByPrimaryKeySelective(map);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return bciMultiLevelDicMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<BciMultiLevelDic> getPageData(Map<String, Object> map) {
		return bciMultiLevelDicMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return bciMultiLevelDicMapper.getPageCount(map);
	}


	@Override
	public List<BciMultiLevelDic> getAllData(Map<String, Object> map) {
		return bciMultiLevelDicMapper.getAllData(map);
	}


	@Override
	public String getEffectTreeJsonData(Map<String, Object> map) {
		StringBuffer buffer=new StringBuffer();
		String parentDicId=(String) map.get("parentDicId");
		doRecursionEffectDicTree(buffer, parentDicId);
		return buffer.toString();
	}
	
	
	private void doRecursionEffectDicTree(StringBuffer buffer,String parentDicId){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("parentDicId", parentDicId);
		List<BciMultiLevelDic> list=bciMultiLevelDicMapper.getEffectChildDic(map);
		buffer.append("[");
		int count=0;
		for(BciMultiLevelDic dic:list){
			count++;
			boolean hasChilds=containChildsDic(dic.getIsLeaf());
			String state=hasChilds==false?"open":"closed";
			if(hasChilds){
				buffer.append("{\"id\":\""+dic.getDicCode()+"\",\"text\":\""+dic.getDicValue()+"\",\"state\":\""+state+"\",\"parentDicId\":\""+dic.getParentDicId()+"\",\"children\":");
				doRecursionEffectDicTree(buffer,dic.getDicCode());
				buffer.append("}");	
			}else{
				buffer.append("{\"id\":\""+dic.getDicCode()+"\",\"text\":\""+dic.getDicValue()+"\",\"state\":\""+state+"\",\"parentDicId\":\""+dic.getParentDicId()+"\",\"checked\":false}");
				
			}
			if(count!=list.size()){
				buffer.append(",");
			}
		}
		buffer.append("]");
	}
	
	private boolean containChildsDic(BigDecimal value){
		if(value.intValue()>0){
			return true;
		}else{
			return false;
		}
	}
	

}
