package com.pccw.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.system.dao.PortalFuncMapper;
import com.pccw.system.dao.PortalRoleFuncMapper;
import com.pccw.system.model.PortalFunc;
import com.pccw.system.model.PortalRoleFunc;
import com.pccw.system.service.PortalRoleFuncService;
import com.pccw.system.utils.GenKeyUtils;

@Service("portalRoleFuncService")
public class PortalRoleFuncServiceImpl implements PortalRoleFuncService {
	private PortalRoleFuncMapper portalRoleFuncMapper;
	
	private PortalFuncMapper portalFuncMapper;
	@Autowired
	public void setPortalRoleFuncMapper(PortalRoleFuncMapper portalRoleFuncMapper) {
		this.portalRoleFuncMapper = portalRoleFuncMapper;
	}
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
    public int deleteByPrimaryKey(PortalRoleFunc po){
    	return portalRoleFuncMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  portalRoleFuncMapper.insert(map);
    }
    
    @Override
    public int insertWithParentFunc(Map<String,Object> map){
    	insertParentFuncId(map);
    	map.put("seqNo",GenKeyUtils.genDBKey("PORTAL_ROLE_FUNC", "SEQ_NO"));
    	Integer result=portalRoleFuncMapper.insert(map);
    	return result ;
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  portalRoleFuncMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public PortalRoleFunc selectByPrimaryKey(Map<String,Object> map){
    	return  portalRoleFuncMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(Map<String,Object> map){
    	return  portalRoleFuncMapper.updateByPrimaryKeySelective(map);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return portalRoleFuncMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<PortalRoleFunc> getPageData(Map<String, Object> map) {
		return portalRoleFuncMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return portalRoleFuncMapper.getPageCount(map);
	}


	@Override
	public boolean existRoleFunc(Map<String, Object> map) {
		Integer i=portalRoleFuncMapper.existRoleFunc(map);
		if(i>0){
			return true;
		}else{
			return false;
		}
	}
	
	private void insertParentFuncId(Map<String,Object> map){
		List<PortalFunc> funcs=portalFuncMapper.getFuncParent(map);
		for(PortalFunc func:funcs){
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("funcId", func.getFuncId());
			params.put("roleId", map.get("roleId"));
			if(!existRoleFunc(params)){
				params.put("seqNo",GenKeyUtils.genDBKey("PORTAL_ROLE_FUNC", "SEQ_NO"));
				portalRoleFuncMapper.insert(params);
			}
			
		}
		
		
		
	}
	@Override
	public List<PortalRoleFunc> getRoleFuncs(Map<String, Object> map) {
		return portalRoleFuncMapper.getRoleFuncs(map);
	}
	@Override
	public int deleteRoleFuncs(Map<String, Object> map) {
		return portalRoleFuncMapper.deleteRoleFuncs(map);
	}
	@Override
	public void removeRoles(Map<String, Object> map) {
		portalRoleFuncMapper.removeRoles(map);
		
	}
	@Override
	public void removeFuncs(Map<String, Object> map) {
		portalRoleFuncMapper.removeFuncs(map);
		
	}

}
