package com.pccw.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.system.dao.PortalRoleMapper;
import com.pccw.system.model.PortalRole;
import com.pccw.system.service.PortalRoleService;

@Service("portalRoleService")
public class PortalRoleServiceImpl implements PortalRoleService {
	private PortalRoleMapper portalRoleMapper;
	@Autowired
	public void setPortalRoleMapper(PortalRoleMapper portalRoleMapper) {
		this.portalRoleMapper = portalRoleMapper;
	}
	
	
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    @Override
    public int deleteByPrimaryKey(PortalRole po){
    	return portalRoleMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  portalRoleMapper.insert(map);
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  portalRoleMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public PortalRole selectByPrimaryKey(Map<String,Object> map){
    	return  portalRoleMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(Map<String,Object> map){
    	return  portalRoleMapper.updateByPrimaryKeySelective(map);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return portalRoleMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<PortalRole> getPageData(Map<String, Object> map) {
		return portalRoleMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return portalRoleMapper.getPageCount(map);
	}


	@Override
	public List<PortalRole> getUserRole(Map<String, Object> map) {
		return portalRoleMapper.getUserRole(map);
	}


	@Override
	public int getUserRoleCount(Map<String, Object> map) {
		
		return portalRoleMapper.getUserRoleCount(map);
	}


	@Override
	public List<PortalRole> getUserEnableSelectedRole(Map<String, Object> map) {
		
		return portalRoleMapper.getUserEnableSelectedRole(map);
	}


	@Override
	public int getUserEnableSelectedRoleCount(Map<String, Object> map) {
		
		return portalRoleMapper.getUserEnableSelectedRoleCount(map);
	}
	

}
