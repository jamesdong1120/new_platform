package com.pccw.system.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.system.dao.PortalOrgMapper;
import com.pccw.system.model.PortalFunc;
import com.pccw.system.model.PortalOrg;
import com.pccw.system.service.PortalOrgService;

@Service("portalOrgService")
public class PortalOrgServiceImpl implements PortalOrgService {
	private PortalOrgMapper portalOrgMapper;
	@Autowired
	public void setPortalOrgMapper(PortalOrgMapper portalOrgMapper) {
		this.portalOrgMapper = portalOrgMapper;
	}
	
	
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    @Override
    public int deleteByPrimaryKey(PortalOrg po){
    	return portalOrgMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  portalOrgMapper.insert(map);
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  portalOrgMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public PortalOrg selectByPrimaryKey(Map<String,Object> map){
    	return  portalOrgMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(Map<String,Object> map){
    	return  portalOrgMapper.updateByPrimaryKeySelective(map);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return portalOrgMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<PortalOrg> getPageData(Map<String, Object> map) {
		return portalOrgMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return portalOrgMapper.getPageCount(map);
	}


	@Override
	public String getChildOrgTreeJsonData(Map<String, Object> map) {
		StringBuffer buffer=new StringBuffer();
		doRecursionOrgTree(buffer,new BigDecimal(-1));
		return buffer.toString();
	}


	@Override
	public String getEffectChildOrgTreeJsonData(Map<String, Object> map) {
		StringBuffer buffer=new StringBuffer();
		doRecursionEffectOrgTree(buffer,new BigDecimal(-1));
		return buffer.toString();
	}



	@Override
	public void updateOrgSortPlusByRecursion(Map<String, Object> map) {
		portalOrgMapper.updateOrgSortPlusByRecursion(map);
		
	}


	@Override
	public void updateOrgSortMinusByRecursion(Map<String, Object> map) {
		portalOrgMapper.updateOrgSortMinusByRecursion(map);
		
	}


	@Override
	public int getMaxOrgSort(Map<String, Object> map) {
		
		return portalOrgMapper.getMaxOrgSort(map);
	}
	
	private boolean containChildsOrg(BigDecimal isLeaf){
		if(isLeaf.intValue()>0){
			return true;
		}else{
			return false;
		}
		
	}
	private void doRecursionOrgTree(StringBuffer buffer,BigDecimal orgParent){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("orgParent", orgParent);
		List<PortalOrg> list=portalOrgMapper.getChildOrgs(map);
		buffer.append("[");
		int count=0;
		for(PortalOrg org:list){
			count++;
			boolean hasChilds=containChildsOrg(org.getIsLeaf());
			String state=hasChilds==false?"open":"closed";
			if(hasChilds){
				buffer.append("{\"id\":\""+org.getOrgId()+"\",\"text\":\""+org.getOrgShortName()+"\",\"state\":\""+state+"\",\"orgSort\":\""+org.getOrgSort()+"\",\"orgParent\":\""+org.getOrgParent()+"\",\"children\":");
				doRecursionOrgTree(buffer,org.getOrgId());
				buffer.append("}");	
			}else{
				buffer.append("{\"id\":\""+org.getOrgId()+"\",\"text\":\""+org.getOrgShortName()+"\",\"state\":\""+state+"\",\"orgSort\":\""+org.getOrgSort()+"\",\"orgParent\":\""+org.getOrgParent()+"\"}");
				
			}
			if(count!=list.size()){
				buffer.append(",");
			}
		}
		buffer.append("]");
	}
	
	
	private void doRecursionEffectOrgTree(StringBuffer buffer,BigDecimal orgParent){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("orgParent", orgParent);
		List<PortalOrg> list=portalOrgMapper.getEffectChildOrgs(map);
		buffer.append("[");
		int count=0;
		for(PortalOrg org:list){
			count++;
			boolean hasChilds=containChildsOrg(org.getIsLeaf());
			String state=hasChilds==false?"open":"closed";
			if(hasChilds){
				buffer.append("{\"id\":\""+org.getOrgId()+"\",\"text\":\""+org.getOrgShortName()+"\",\"state\":\""+state+"\",\"orgSort\":\""+org.getOrgSort()+"\",\"orgParent\":\""+org.getOrgParent()+"\",\"children\":");
				doRecursionEffectOrgTree(buffer,org.getOrgId());
				buffer.append("}");	
			}else{
				buffer.append("{\"id\":\""+org.getOrgId()+"\",\"text\":\""+org.getOrgShortName()+"\",\"state\":\""+state+"\",\"orgSort\":\""+org.getOrgSort()+"\",\"orgParent\":\""+org.getOrgParent()+"\"}");
				
			}
			if(count!=list.size()){
				buffer.append(",");
			}
		}
		buffer.append("]");
	}
	
	

}
