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
import com.pccw.system.model.PortalRoleUser;
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
@RequestMapping(value = "/system/portalRoleUser")
public class PortalRoleUserController {
	
	private PortalRoleUserService portalRoleUserService;
	
	@Autowired
	public void setPortalRoleUserService(PortalRoleUserService portalRoleUserService) {
		this.portalRoleUserService = portalRoleUserService;
	}
	
	@RequestMapping("/getPageResult")
	public String getPageResult(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String dateFormat="yyyy-MM-dd";
		Map<String,Object>  params=RequestUtils.getPageParamForEasyUI(request);
		Map<String,Object> conditions=RequestUtils.getConditionByClass(request, PortalRoleUser.class,dateFormat);
		params.putAll(conditions);
		List<PortalRoleUser> list=portalRoleUserService.getPageData(params);
		Integer allCount=portalRoleUserService.getPageCount(conditions);
		ResponseUtils.respPageData(response, list, allCount,dateFormat);
		return null;
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,
			HttpServletResponse response){
		String delstr=request.getParameter("delstr");
		String userId=request.getParameter("userId");
		List<PortalRoleUser> list=JSON.parseArray(delstr, PortalRoleUser.class);
		boolean flag=true;
		for(PortalRoleUser po:list){
			po.setUserId(new BigDecimal(userId));
			try {
				portalRoleUserService.removeUserRole(po);
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
			Map<String,Object> map=RequestUtils.getConditionByClass(request, PortalRoleUser.class,dateFormat);
   			
			String roleIds=request.getParameter("roleIds");
			String[] roles=roleIds.split(",");
			for(String role:roles){
				map.put("seqNo",GenKeyUtils.genDBKey("PORTAL_ROLE_USER", "SEQ_NO"));
				map.put("roleId", new BigDecimal(role));
				portalRoleUserService.insert(map);
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
			Map<String,Object> map= RequestUtils.getConditionByClass(request,PortalRoleUser.class,dateFormat);
			portalRoleUserService.updateByPrimaryKeySelective(map);
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		ResponseUtils.respSubmitStatusData(response, flag);
		return null;
	}
   
	
	
	
}