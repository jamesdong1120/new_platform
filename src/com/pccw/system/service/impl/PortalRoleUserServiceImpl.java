package com.pccw.system.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.system.dao.PortalRoleUserMapper;
import com.pccw.system.model.PortalRoleUser;
import com.pccw.system.service.PortalRoleUserService;

@Service("portalRoleUserService")
public class PortalRoleUserServiceImpl implements PortalRoleUserService {
	private PortalRoleUserMapper portalRoleUserMapper;
	@Autowired
	public void setPortalRoleUserMapper(PortalRoleUserMapper portalRoleUserMapper) {
		this.portalRoleUserMapper = portalRoleUserMapper;
	}
	
	
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    @Override
    public int deleteByPrimaryKey(PortalRoleUser po){
    	return portalRoleUserMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  portalRoleUserMapper.insert(map);
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  portalRoleUserMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public PortalRoleUser selectByPrimaryKey(Map<String,Object> map){
    	return  portalRoleUserMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(Map<String,Object> map){
    	return  portalRoleUserMapper.updateByPrimaryKeySelective(map);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return portalRoleUserMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<PortalRoleUser> getPageData(Map<String, Object> map) {
		return portalRoleUserMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return portalRoleUserMapper.getPageCount(map);
	}


	@Override
	public void removeUserRole(PortalRoleUser po) {
		portalRoleUserMapper.removeUserRole(po);
	}


	@Override
	public void removeUsers(Map<String, Object> map) {
		portalRoleUserMapper.removeUsers(map);
	}


	@Override
	public void removeRoles(Map<String, Object> map) {
		
		portalRoleUserMapper.removeRoles(map);
	}
	

}
