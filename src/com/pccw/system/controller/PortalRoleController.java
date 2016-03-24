package com.pccw.system.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.pccw.system.model.PortalRole;
import com.pccw.system.service.PortalRoleFuncService;
import com.pccw.system.service.PortalRoleService;
import com.pccw.system.service.PortalRoleUserService;
import com.pccw.system.utils.GenKeyUtils;
import com.pccw.system.utils.RequestUtils;
import com.pccw.system.utils.ResponseUtils;
/**
 * 
 *控制器
 *
 */
@Controller
@RequestMapping(value = "/system/portalRole")
public class PortalRoleController {
	
	private PortalRoleService portalRoleService;
	
	private PortalRoleFuncService portalRoleFuncService;
	
	private PortalRoleUserService portalRoleUserService;
	
	@Autowired
	public void setPortalRoleService(PortalRoleService portalRoleService) {
		this.portalRoleService = portalRoleService;
	}
	@Autowired
	public void setPortalRoleFuncService(PortalRoleFuncService portalRoleFuncService) {
		this.portalRoleFuncService = portalRoleFuncService;
	}
	
	@Autowired
	public void setPortalRoleUserService(PortalRoleUserService portalRoleUserService) {
		this.portalRoleUserService = portalRoleUserService;
	}
	@RequestMapping("/getPageResult")
	public String getPageResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		Map<String,Object> conditions=RequestUtils.getConditionByClass(request, PortalRole.class,dateFormat);
		params.putAll(conditions);
		List<PortalRole> list=portalRoleService.getPageData(params);
		Integer allCount=portalRoleService.getPageCount(conditions);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response){
		String delstr=request.getParameter("delstr");
		List<PortalRole> list=JSON.parseArray(delstr, PortalRole.class);
		boolean flag=true;
		for(PortalRole po:list){
			try {
				portalRoleService.deleteByPrimaryKey(po);
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("roleId", po.getRoleId());
				portalRoleFuncService.deleteRoleFuncs(map);
				portalRoleUserService.removeRoles(map);
			} catch (Exception e) {
				flag=false;
				e.printStackTrace();
			}
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
	
	@RequestMapping("/add")
	public String add(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		boolean flag=true;
		try {
			Map<String,Object> map=RequestUtils.getConditionByClass(request, PortalRole.class,dateFormat);
   			map.put("roleId",GenKeyUtils.genDBKey("PORTAL_ROLE", "ROLE_ID"));
   			String roleFuncs=request.getParameter("roleFuncs");
			portalRoleService.insert(map);
			String[] funcs=roleFuncs.split(",");
			for(String funcId:funcs){
				map.put("funcId", new BigDecimal(funcId));
				portalRoleFuncService.insertWithParentFunc(map);
			}
			
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
	
	@RequestMapping("/update")
	public String update(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		boolean flag=true;
		try {
			Map<String,Object> map= RequestUtils.getConditionByClass(request,PortalRole.class,dateFormat);
			portalRoleService.updateByPrimaryKeySelective(map);
			portalRoleFuncService.deleteRoleFuncs(map);
			String roleFuncs=request.getParameter("roleFuncs");
			String[] funcs=roleFuncs.split(",");
			for(String funcId:funcs){
				map.put("funcId", new BigDecimal(funcId));
				portalRoleFuncService.insertWithParentFunc(map);
			}
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
   
	@RequestMapping("/getUserRoles")
	public String getUserRoles(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		String userId=request.getParameter("userId");
		params.put("userId", userId);
		List<PortalRole> list=portalRoleService.getUserRole(params);
		Integer allCount=portalRoleService.getUserRoleCount(params);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	
	@RequestMapping("/getUserEnableSelectedRoles")
	public String getUserEnableSelectedRoles(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		String userId=request.getParameter("userId");
		params.put("userId", userId);
		List<PortalRole> list=portalRoleService.getUserEnableSelectedRole(params);
		Integer allCount=portalRoleService.getUserEnableSelectedRoleCount(params);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	
	
}