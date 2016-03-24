package com.pccw.system.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.system.dao.PortalFuncMapper;
import com.pccw.system.model.PortalFunc;
import com.pccw.system.model.PortalOrg;
import com.pccw.system.service.PortalFuncService;

@Service("portalFuncService")
public class PortalFuncServiceImpl implements PortalFuncService {
	private PortalFuncMapper portalFuncMapper;
	@Autowired
	public void setPortalFuncMapper(PortalFuncMapper portalFuncMapper) {
		this.portalFuncMapper = portalFuncMapper;
	}
	
	
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    @Override
    public int deleteByPrimaryKey(PortalFunc po){
    	return portalFuncMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  portalFuncMapper.insert(map);
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  portalFuncMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public PortalFunc selectByPrimaryKey(Map<String,Object> map){
    	return  portalFuncMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(Map<String,Object> map){
    	return  portalFuncMapper.updateByPrimaryKeySelective(map);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return portalFuncMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<PortalFunc> getPageData(Map<String, Object> map) {
		return portalFuncMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return portalFuncMapper.getPageCount(map);
	}


	@Override
	public String getChildFuncTreeJsonData(Map<String, Object> map) {
		List<PortalFunc> list=portalFuncMapper.getChildFuncs(map);
		StringBuffer buffer=new StringBuffer();
		buffer.append("[");
		int count=0;
		for(PortalFunc func:list){
			count++;
			boolean hasChilds=containChildsFunction(func.getIsLeaf());
			String state=hasChilds==false?"open":"closed";
			buffer.append("{\"id\":\""+func.getFuncId()+"\",\"text\":\""+func.getFuncName()+"\",\"state\":\""+state+"\",\"url\":\""+func.getFuncUrl()+"\",\"funcSort\":\""+func.getFuncSort()+"\",\"parentId\":\""+func.getParentId()+"\"}");
			if(count!=list.size()){
				buffer.append(",");
			}
		}
		buffer.append("]");
		return buffer.toString();
	}
	
	@Override
	public String getEffectChildFuncTreeJsonData(Map<String, Object> map) {
		List<PortalFunc> list=portalFuncMapper.getEffectChildFuncs(map);
		StringBuffer buffer=new StringBuffer();
		buffer.append("[");
		int count=0;
		for(PortalFunc func:list){
			count++;
			boolean hasChilds=containChildsFunction(func.getIsLeaf());
			String state=hasChilds==false?"open":"closed";
			buffer.append("{\"id\":\""+func.getFuncId()+"\",\"text\":\""+func.getFuncName()+"\",\"state\":\""+state+"\",\"url\":\""+func.getFuncUrl()+"\",\"funcSort\":\""+func.getFuncSort()+"\",\"parentId\":\""+func.getParentId()+"\"}");
			if(count!=list.size()){
				buffer.append(",");
			}
		}
		buffer.append("]");
		return buffer.toString();
	}
	
	@Override
	public String getAllEffectFuncTreeJsonData(Map<String, Object> map) {
		StringBuffer buffer=new StringBuffer();
		doRecursionEffectFuncTree(buffer, new BigDecimal("-1"));
		return buffer.toString();
	}
	

	@Override
	public String getInitMenuTreeJsonData(Map<String, Object> map) {
		PortalFunc portalFunc=portalFuncMapper.selectByPrimaryKey(map);
		StringBuffer buffer=new StringBuffer();
		buffer.append("[");
		if(null!=portalFunc){
			buffer.append("{\"id\":\""+portalFunc.getFuncId()+"\",\"text\":\""+portalFunc.getFuncName()+"\",\"state\":\"closed\",\"url\":\""+portalFunc.getFuncUrl()+"\",\"funcSort\":\""+portalFunc.getFuncSort()+"\",\"parentId\":\""+portalFunc.getParentId()+"\"}");
		}
		buffer.append("]");
		return buffer.toString();
	}


	@Override
	public Integer getMaxFuncSort(Map<String, Object> map) {
		return portalFuncMapper.getMaxFuncSort(map);
	}



	@Override
	public void updateFuncSortMinusByRecursion(Map<String, Object> map) {
		portalFuncMapper.updateFuncSortMinusByRecursion(map);
		
	}


	@Override
	public void updateFuncSortPlusByRecursion(Map<String, Object> map) {
		portalFuncMapper.updateFuncSortPlusByRecursion(map);
		
	}
	
	private boolean containChildsFunction(BigDecimal isLeaf ){
		if(isLeaf.intValue()>0){
			return true;
		}else{
			return false;
		}
	}
	
	
	private void doRecursionEffectFuncTree(StringBuffer buffer,BigDecimal parentId){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("parentId", parentId);
		List<PortalFunc> list=portalFuncMapper.getEffectChildFuncs(map);
		buffer.append("[");
		int count=0;
		for(PortalFunc func:list){
			count++;
			boolean hasChilds=containChildsFunction(func.getIsLeaf());
			String state=hasChilds==false?"open":"closed";
			if(hasChilds){
				buffer.append("{\"id\":\""+func.getFuncId()+"\",\"text\":\""+func.getFuncName()+"\",\"state\":\""+state+"\",\"funcSort\":\""+func.getFuncSort()+"\",\"parentId\":\""+func.getParentId()+"\",\"children\":");
				doRecursionEffectFuncTree(buffer,func.getFuncId());
				buffer.append("}");	
			}else{
				buffer.append("{\"id\":\""+func.getFuncId()+"\",\"text\":\""+func.getFuncName()+"\",\"state\":\""+state+"\",\"funcSort\":\""+func.getFuncSort()+"\",\"parentId\":\""+func.getParentId()+"\",\"checked\":false}");
				
			}
			if(count!=list.size()){
				buffer.append(",");
			}
		}
		buffer.append("]");
	}


	@Override
	public List<PortalFunc> getFuncParent(Map<String, Object> map) {
		return portalFuncMapper.getFuncParent(map);
	}


	@Override
	public String getAllEffectRoleMenuTreeJsonData(Map<String, Object> map) {
		StringBuffer buffer=new StringBuffer();
		BigDecimal roleId=(BigDecimal) map.get("roleId");
		doRecursionEffectRoleFuncTree(buffer, new BigDecimal("-1"),roleId);
		return buffer.toString();
	}
	
	private void doRecursionEffectRoleFuncTree(StringBuffer buffer,BigDecimal parentId,BigDecimal roleId){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("parentId", parentId);
		map.put("roleId", roleId);
		List<PortalFunc> list=portalFuncMapper.getEffectRoleChildFuncs(map);
		buffer.append("[");
		int count=0;
		for(PortalFunc func:list){
			count++;
			boolean hasChilds=containChildsFunction(func.getIsLeaf());
			String state=hasChilds==false?"open":"closed";
			if(hasChilds){
				buffer.append("{\"id\":\""+func.getFuncId()+"\",\"text\":\""+func.getFuncName()+"\",\"state\":\""+state+"\",\"funcSort\":\""+func.getFuncSort()+"\",\"parentId\":\""+func.getParentId()+"\",\"children\":");
				doRecursionEffectRoleFuncTree(buffer,func.getFuncId(),roleId);
				buffer.append("}");	
			}else{
				buffer.append("{\"id\":\""+func.getFuncId()+"\",\"text\":\""+func.getFuncName()+"\",\"state\":\""+state+"\",\"funcSort\":\""+func.getFuncSort()+"\",\"parentId\":\""+func.getParentId()+"\",\"checked\":false}");
				
			}
			if(count!=list.size()){
				buffer.append(",");
			}
		}
		buffer.append("]");
	}


	


}
