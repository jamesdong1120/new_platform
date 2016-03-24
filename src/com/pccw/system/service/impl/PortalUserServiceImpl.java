package com.pccw.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.system.dao.PortalUserMapper;
import com.pccw.system.model.PortalUser;
import com.pccw.system.service.PortalUserService;

@Service("portalUserService")
public class PortalUserServiceImpl implements PortalUserService {
	private PortalUserMapper portalUserMapper;
	@Autowired
	public void setPortalUserMapper(PortalUserMapper portalUserMapper) {
		this.portalUserMapper = portalUserMapper;
	}
	
	
	/***********************
	 * 根据主键删除记录
     * @param map
     * @return
     ****************************/
    @Override
    public int deleteByPrimaryKey(PortalUser po){
    	return portalUserMapper.deleteByPrimaryKey(po);
    }
	
	/***********************
	 * 插入记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insert(Map<String,Object> map){
    	return  portalUserMapper.insert(map);
    }
	
	/***********************
	 * 动态插入可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int insertSelective(Map<String,Object> map){
    	return  portalUserMapper.insertSelective(map);
    }
    
	/***********************
	 * 根据主键查询单条记录
     * @param map
     * @return
     ****************************/
    @Override
    public PortalUser selectByPrimaryKey(Map<String,Object> map){
    	return  portalUserMapper.selectByPrimaryKey(map);
    }
    
    /***********************
	 * 根据主键动态更新可选记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKeySelective(Map<String,Object> map){
    	return  portalUserMapper.updateByPrimaryKeySelective(map);
    }

     /***********************
	 * 根据主键更新记录
     * @param map
     * @return
     ****************************/
    @Override
    public int updateByPrimaryKey(Map<String,Object> map){
    	return portalUserMapper.updateByPrimaryKey(map);
    }
	
	/***********************
	 * 获取分页数据
     * @param map
     * @return
     ****************************/
	@Override
	public List<PortalUser> getPageData(Map<String, Object> map) {
		return portalUserMapper.getPageData(map);
	}

	/***********************
	 * 获取分页总记录数
     * @param map
     * @return
     ****************************/
	@Override
	public int getPageCount(Map<String, Object> map) {
		return portalUserMapper.getPageCount(map);
	}


	@Override
	public boolean validateUser(Map<String, Object> map) {
		int result=portalUserMapper.validateUser(map);
		if(result==1){
			return true;
		}else{
			return false;
		}
	}
	

}
