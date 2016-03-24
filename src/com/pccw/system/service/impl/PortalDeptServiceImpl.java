package com.pccw.system.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.system.dao.PortalDeptMapper;
import com.pccw.system.model.PortalDept;
import com.pccw.system.model.PortalOrg;
import com.pccw.system.service.PortalDeptService;

@Service("portalDeptService")
public class PortalDeptServiceImpl implements PortalDeptService {
	private PortalDeptMapper portalDeptMapper;
	@Autowired
	public void setPortalDeptMapper(PortalDeptMapper portalDeptMapper) {
		this.portalDeptMapper = portalDeptMapper;
	}
	
	
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    @Override
    public int deleteByPrimaryKey(PortalDept po){
    	return portalDeptMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  portalDeptMapper.insert(map);
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  portalDeptMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public PortalDept selectByPrimaryKey(Map<String,Object> map){
    	return  portalDeptMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(Map<String,Object> map){
    	return  portalDeptMapper.updateByPrimaryKeySelective(map);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return portalDeptMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<PortalDept> getPageData(Map<String, Object> map) {
		return portalDeptMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return portalDeptMapper.getPageCount(map);
	}


	@Override
	public String getChildDeptTreeJsonData(Map<String, Object> map) {
		StringBuffer buffer=new StringBuffer();
		BigDecimal orgId=(BigDecimal) map.get("orgId");
		doRecursionDeptTree(buffer,new BigDecimal(-1),new BigDecimal(0),orgId);
		return buffer.toString();
	}


	@Override
	public String getEffectChildDeptTreeJsonData(Map<String, Object> map) {
		StringBuffer buffer=new StringBuffer();
		BigDecimal orgId=(BigDecimal) map.get("orgId");
		doRecursionEffectDeptTree(buffer,new BigDecimal(-1),new BigDecimal(0),orgId);
		return buffer.toString();
	}


	@Override
	public void updateDeptSortPlusByRecursion(Map<String, Object> map) {
		portalDeptMapper.updateDeptSortPlusByRecursion(map);
		
	}


	@Override
	public void updateDeptSortMinusByRecursion(Map<String, Object> map) {
		portalDeptMapper.updateDeptSortMinusByRecursion(map);
		
	}


	@Override
	public int getMaxDeptSort(Map<String, Object> map) {
		return portalDeptMapper.getMaxDeptSort(map);
	}

	private boolean containChildsDept(BigDecimal isLeaf){
		if(isLeaf.intValue()>0){
			return true;
		}else{
			return false;
		}
		
	}

	private void doRecursionDeptTree(StringBuffer buffer,BigDecimal deptParent,BigDecimal rootOrgId,BigDecimal orgId){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("deptParent", deptParent);
		if(null!=rootOrgId){
			map.put("orgId", rootOrgId);
		}else{
			map.put("orgId", orgId);
		}
		map.put("orgId2", orgId);
		List<PortalDept> list=portalDeptMapper.getChildDepts(map);
		buffer.append("[");
		int count=0;
		for(PortalDept dept:list){
			count++;
			boolean hasChilds=containChildsDept(dept.getIsLeaf());
			String state=hasChilds==false?"open":"closed";
			if(hasChilds){
				buffer.append("{\"id\":\""+dept.getDeptId()+"\",\"text\":\""+dept.getDeptName()+"\",\"state\":\""+state+"\",\"deptSort\":\""+dept.getDeptSort()+"\",\"deptParent\":\""+dept.getDeptParent()+"\",\"children\":");
				doRecursionDeptTree(buffer,dept.getDeptId(),null,orgId);
				buffer.append("}");	
			}else{
				buffer.append("{\"id\":\""+dept.getDeptId()+"\",\"text\":\""+dept.getDeptName()+"\",\"state\":\""+state+"\",\"deptSort\":\""+dept.getDeptSort()+"\",\"deptParent\":\""+dept.getDeptParent()+"\"}");
				
			}
			if(count!=list.size()){
				buffer.append(",");
			}
		}
		buffer.append("]");
	}	
	
	
	private void doRecursionEffectDeptTree(StringBuffer buffer,BigDecimal deptParent,BigDecimal rootOrgId,BigDecimal orgId){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("deptParent", deptParent);
		if(null!=rootOrgId){
			map.put("orgId", rootOrgId);
		}else{
			map.put("orgId", orgId);
		}
		map.put("orgId2", orgId);
		List<PortalDept> list=portalDeptMapper.getEffectChildDepts(map);
		buffer.append("[");
		int count=0;
		for(PortalDept dept:list){
			count++;
			boolean hasChilds=containChildsDept(dept.getIsLeaf());
			String state=hasChilds==false?"open":"closed";
			if(hasChilds){
				buffer.append("{\"id\":\""+dept.getDeptId()+"\",\"text\":\""+dept.getDeptName()+"\",\"state\":\""+state+"\",\"deptSort\":\""+dept.getDeptSort()+"\",\"deptParent\":\""+dept.getDeptParent()+"\",\"children\":");
				doRecursionEffectDeptTree(buffer,dept.getDeptId(),null,orgId);
				buffer.append("}");	
			}else{
				buffer.append("{\"id\":\""+dept.getDeptId()+"\",\"text\":\""+dept.getDeptName()+"\",\"state\":\""+state+"\",\"deptSort\":\""+dept.getDeptSort()+"\",\"deptParent\":\""+dept.getDeptParent()+"\"}");
				
			}
			if(count!=list.size()){
				buffer.append(",");
			}
		}
		buffer.append("]");
	}	



	
	

}
